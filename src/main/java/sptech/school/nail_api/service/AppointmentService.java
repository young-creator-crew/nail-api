package sptech.school.nail_api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sptech.school.nail_api.dto.appointment.*;
import sptech.school.nail_api.entity.*;
import sptech.school.nail_api.enums.AppointmentStatus;
import sptech.school.nail_api.exception.appointment.AppointmentConflictException;
import sptech.school.nail_api.exception.appointment.AppointmentNotFoundException;
import sptech.school.nail_api.exception.appointment.AppointmentValidationException;
import sptech.school.nail_api.mapper.AppointmentMapper;
import sptech.school.nail_api.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Service
public class AppointmentService {

    private static final ZoneId BUSINESS_ZONE = ZoneId.of("America/Sao_Paulo");

    private static final LocalTime OPENING_TIME = LocalTime.of(8, 0);

    private static final LocalTime CLOSING_TIME = LocalTime.of(20, 0);

    private static final int SLOT_INTERVAL_MINUTES = 15;

    private static final Set<AppointmentStatus> BLOCKING_STATUSES =
            EnumSet.of(
                    AppointmentStatus.AGENDADO,
                    AppointmentStatus.CONFIRMADO
            );

    private final AppointmentRepository appointmentsRepository;
    private final NailServiceOptionRepository optionsRepository;
    private final NailServiceAddonRepository addonsRepository;
    private final UserRepository usersRepository;
    private final NailServiceRepository serviceRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentService(
            AppointmentRepository appointmentsRepository,
            NailServiceOptionRepository optionsRepository,
            NailServiceAddonRepository addonsRepository,
            UserRepository usersRepository,
            NailServiceRepository serviceRepository,
            AppointmentMapper appointmentMapper
    ) {
        this.appointmentsRepository = appointmentsRepository;
        this.optionsRepository = optionsRepository;
        this.addonsRepository = addonsRepository;
        this.usersRepository = usersRepository;
        this.serviceRepository = serviceRepository;
        this.appointmentMapper = appointmentMapper;
    }

    // =========================================================
    // DISPONIBILIDADE
    // =========================================================

    @Transactional(readOnly = true)
    public List<AvailableSlotResponseDTO> getAvailableSlots(LocalDate date, Integer serviceOptionId) {
        validateAvailabilityDate(date);

        NailServiceOption option = findActiveOption(serviceOptionId);

        LocalDateTime dayStart = date.atTime(OPENING_TIME);
        LocalDateTime dayEnd = date.atTime(CLOSING_TIME);

        List<Appointment> occupiedAppointments =
                appointmentsRepository.findOverlappingAppointments(
                        dayStart,
                        dayEnd,
                        BLOCKING_STATUSES
                );

        List<AvailableSlotResponseDTO> availableSlots = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now(BUSINESS_ZONE);
        LocalDateTime start = dayStart;

        while (true) {
            LocalDateTime end = start.plusMinutes(option.getDurationMinutes());

            // Não permite terminar depois das 20h.
            if (end.isAfter(dayEnd)) { break; }

            // No dia atual, ignora horários que já passaram.
            boolean isFuture = date.isAfter(now.toLocalDate()) || start.isAfter(now);

            if (isFuture) {
                LocalDateTime finalStart = start;
                boolean hasConflict = occupiedAppointments.stream()
                        .anyMatch(appointment ->
                                overlaps(
                                        finalStart,
                                        end,
                                        appointment.getScheduledStartAt(),
                                        appointment.getScheduledEndAt()
                                )
                        );

                if (!hasConflict) {
                    availableSlots.add(new AvailableSlotResponseDTO(start, end));
                }
            }

            start = start.plusMinutes(SLOT_INTERVAL_MINUTES);
        }

        return availableSlots;
    }

    // =========================================================
    // CRIAR AGENDAMENTO
    // =========================================================

    @Transactional
    public AppointmentResponseDTO create(Integer userId, AppointmentRequestDTO request) {
        User client = findClient(userId);

        NailServiceOption option = findActiveOption(request.getServiceOptionId());

        NailServiceAddon addon = findValidAddon(request.getAddonId(), option);

        NailService service = findService(option.getNailService().getIdNailService());

        LocalDateTime start = request.getScheduledStartAt();

        LocalDateTime end = start.plusMinutes(option.getDurationMinutes());

        validateInterval(start, end);

        ensureAvailable(start, end, null);

        Appointment appointment = new Appointment();

        appointment.setUser(client);
        appointment.setNailServiceOption(option);
        appointment.setNailServiceAddon(addon);
        appointment.setScheduledStartAt(start);
        appointment.setScheduledEndAt(end);
        appointment.setAppointmentStatus(AppointmentStatus.AGENDADO);

        applySnapshots(appointment, service, option, addon);

        Appointment saved = appointmentsRepository.save(appointment);

        return appointmentMapper.toResponseDTO(saved);
    }

    // =========================================================
    // AGENDAMENTOS DO CLIENTE AUTENTICADO
    // =========================================================

    @Transactional(readOnly = true)
    public List<AppointmentResponseDTO> getMyAppointments(Integer userId) {
        User client = findClient(userId);

        return appointmentsRepository
                .findAllByUserOrderByScheduledStartAtDesc(client)
                .stream()
                .map(appointmentMapper::toResponseDTO)
                .toList();
    }

    // =========================================================
    // REMARCAR / ALTERAR AGENDAMENTO
    // =========================================================

    @Transactional
    public AppointmentResponseDTO update(Integer userId, Integer appointmentId, AppointmentRequestDTO request) {
        User client = findClient(userId);

        Appointment appointment = appointmentsRepository
                .findByIdAppointmentAndUser(appointmentId, client)
                .orElseThrow(() -> new AppointmentNotFoundException("Agendamento não encontrado para este usuário."));

        validateEditableStatus(appointment);

        NailServiceOption option = findActiveOption(request.getServiceOptionId());

        NailServiceAddon addon = findValidAddon(request.getAddonId(), option);

        NailService service = findService(option.getNailService().getIdNailService());

        LocalDateTime start = request.getScheduledStartAt();

        LocalDateTime end = start.plusMinutes(option.getDurationMinutes());

        validateInterval(start, end);

        ensureAvailable(start, end, appointmentId);

        appointment.setNailServiceOption(option);
        appointment.setNailServiceAddon(addon);
        appointment.setScheduledStartAt(start);
        appointment.setScheduledEndAt(end);

        applySnapshots(appointment, service, option, addon);

        Appointment saved = appointmentsRepository.save(appointment);

        return appointmentMapper.toResponseDTO(saved);
    }

    // =========================================================
    // CANCELAR AGENDAMENTO DO CLIENTE
    // =========================================================

    @Transactional
    public void cancel(Integer userId, Integer appointmentId) {
        User client = findClient(userId);

        Appointment appointment = appointmentsRepository
                .findByIdAppointmentAndUser(appointmentId, client)
                .orElseThrow(() -> new AppointmentNotFoundException("Agendamento não encontrado para este usuário."));

        validateEditableStatus(appointment);

        appointment.setAppointmentStatus(AppointmentStatus.CANCELADO);

        appointmentsRepository.save(appointment);
    }

    // =========================================================
    // ADMIN: CONSULTAR AGENDAMENTOS DO DIA
    // =========================================================

    @Transactional(readOnly = true)
    public List<AppointmentResponseDTO> getAppointmentsByDate(LocalDate date) {
        validateDate(date);

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime startOfNextDay = date.plusDays(1).atStartOfDay();

        return appointmentsRepository
                .findByScheduledStartAtGreaterThanEqualAndScheduledStartAtLessThanOrderByScheduledStartAtAsc(
                        startOfDay,
                        startOfNextDay
                )
                .stream()
                .map(appointmentMapper::toResponseDTO)
                .toList();
    }

    // =========================================================
    // ADMIN: ALTERAR STATUS
    // =========================================================

    @Transactional
    public AppointmentResponseDTO updateStatus(Integer appointmentId, AppointmentStatus newStatus) {
        Appointment appointment = appointmentsRepository.findById(appointmentId).orElseThrow(() -> new AppointmentNotFoundException("Agendamento não encontrado."));

        if (newStatus == null || newStatus == AppointmentStatus.AGENDADO) {
            throw new AppointmentValidationException("A administradora pode definir o status como " + "CONFIRMADO, CONCLUIDO ou CANCELADO.");
        }

        AppointmentStatus currentStatus = appointment.getAppointmentStatus();

        // CONFIRMADO repetido não precisa de gerar alteração.
        if (currentStatus == newStatus) {
            return appointmentMapper.toResponseDTO(appointment);
        }

        // Estados finais não podem ser reabertos por esta rota.
        if (currentStatus == AppointmentStatus.CANCELADO || currentStatus == AppointmentStatus.CONCLUIDO) {
            throw new AppointmentConflictException("Agendamentos cancelados ou concluídos não " + "podem ter seu status alterado.");
        }

        if (newStatus == AppointmentStatus.CONCLUIDO) {
            LocalDateTime now = LocalDateTime.now(BUSINESS_ZONE);

            if (appointment.getScheduledEndAt().isAfter(now)) {
                throw new AppointmentValidationException("Não é possível concluir um atendimento " + "antes do término previsto.");
            }
        }

        appointment.setAppointmentStatus(newStatus);

        Appointment saved = appointmentsRepository.save(appointment);

        return appointmentMapper.toResponseDTO(saved);
    }

    // =========================================================
    // MÉTODOS AUXILIARES
    // =========================================================

    private User findClient(Integer id) {
        return usersRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException("Usuário autenticado não encontrado."));
    }

    private NailServiceOption findActiveOption(Integer optionId) {


        NailServiceOption option = optionsRepository
                .findById(optionId)
                .orElseThrow(() -> new AppointmentNotFoundException("Modalidade de serviço não encontrada."));

        NailService service = findService(option.getNailService().getIdNailService());

        if (!service.getActive()) {
            throw new AppointmentValidationException("O serviço selecionado está inativo.");
        }

        if (!option.getActive()) {
            throw new AppointmentValidationException("A modalidade selecionada está inativa.");
        }

        return option;
    }

    private NailServiceAddon findValidAddon(Integer addonId, NailServiceOption option) {
        if (addonId == null) { return null; }

        NailServiceAddon addon = addonsRepository
                .findById(addonId)
                .orElseThrow(() -> new AppointmentNotFoundException("Adicional não encontrado."));

        if (!addon.getActive()) {
            throw new AppointmentValidationException("O adicional selecionado está inativo.");
        }

        if (!addon.getNailService().getIdNailService().equals(option.getNailService().getIdNailService())) {
            throw new AppointmentValidationException("O adicional não pertence ao serviço selecionado.");
        }

        return addon;
    }

    private NailService findService(Integer serviceId) {
        return serviceRepository.findById(serviceId).orElseThrow(() -> new AppointmentNotFoundException("Serviço não encontrado."));
    }

    private void validateDate(LocalDate date) {
        if (date == null) {
            throw new AppointmentValidationException(
                    "A data do agendamento é obrigatória."
            );
        }
    }

    private void validateAvailabilityDate(LocalDate date) {
        validateDate(date);

        if (date.isBefore(LocalDate.now(BUSINESS_ZONE))) {
            throw new AppointmentValidationException(
                    "Não é possível agendar em uma data passada."
            );
        }
    }

    private void validateInterval(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new AppointmentValidationException("O início e o término do agendamento são obrigatórios.");
        }

        LocalDateTime now = LocalDateTime.now(BUSINESS_ZONE);

        if (!start.isAfter(now)) {
            throw new AppointmentValidationException("O horário escolhido deve ser futuro.");
        }

        if (!start.toLocalDate().equals(end.toLocalDate())) {
            throw new AppointmentValidationException("O atendimento deve terminar no mesmo dia em que começa.");
        }

        if (start.toLocalTime().isBefore(OPENING_TIME) || end.toLocalTime().isAfter(CLOSING_TIME)) {
            throw new AppointmentValidationException("Os atendimentos devem ocorrer entre 08h e 20h.");
        }

        boolean aligned = start.getMinute() % SLOT_INTERVAL_MINUTES == 0 && start.getSecond() == 0 && start.getNano() == 0;

        if (!aligned) {
            throw new AppointmentValidationException("O horário deve respeitar intervalos de 15 minutos.");
        }
    }

    private void ensureAvailable(LocalDateTime start, LocalDateTime end, Integer excludeAppointmentId) {
        List<Appointment> conflicts;

        if (excludeAppointmentId == null) {
            conflicts = appointmentsRepository
                    .findOverlappingAppointments(
                            start,
                            end,
                            BLOCKING_STATUSES
                    );
        } else {
            conflicts = appointmentsRepository
                    .findOverlappingAppointmentsExcluding(
                            start,
                            end,
                            BLOCKING_STATUSES,
                            excludeAppointmentId
                    );
        }

        if (!conflicts.isEmpty()) {
            throw new AppointmentConflictException("O horário escolhido não está mais disponível.");
        }
    }

    private boolean overlaps(LocalDateTime startA, LocalDateTime endA, LocalDateTime startB, LocalDateTime endB) {
        return startA.isBefore(endB) && endA.isAfter(startB);
    }

    private void validateEditableStatus(Appointment appointment) {
        AppointmentStatus status = appointment.getAppointmentStatus();

        if (status != AppointmentStatus.AGENDADO && status != AppointmentStatus.CONFIRMADO) {
            throw new AppointmentConflictException("Somente agendamentos AGENDADO ou CONFIRMADO " + "podem ser alterados ou cancelados.");
        }
    }

    private void applySnapshots(Appointment appointment, NailService service, NailServiceOption option, NailServiceAddon addon) {
        BigDecimal addonPrice = addon == null ? BigDecimal.ZERO : addon.getAdditionalPrice();

        appointment.setServiceNameSnapshot(service.getServiceName());
        appointment.setOptionTypeSnapshot(option.getOptionType());
        appointment.setServicePriceSnapshot(option.getPrice());
        appointment.setAddonNameSnapshot(addon == null ? null : addon.getAddonName());
        appointment.setAddonPriceSnapshot(addonPrice);
        appointment.setAppointmentValue(option.getPrice().add(addonPrice));
    }
}