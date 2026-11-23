package sptech.school.nail_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.nail_api.dto.service.create.ServiceCreateRequestDTO;
import sptech.school.nail_api.dto.service.update.ServiceUpdateRequestDTO;
import sptech.school.nail_api.dto.service.response.ServiceResponseDTO;
import sptech.school.nail_api.dto.service.response.ServiceSummaryResponseDTO;
import sptech.school.nail_api.service.ServiceService;

import java.util.List;

@RestController
@RequestMapping("/services")
@Tag(name = "Serviços", description = "Operações de consulta, cadastro, atualização e desativação de serviços.")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    @Operation(summary = "Listar serviços ativos", description = "Retorna informações resumidas dos serviços ativos, incluindo nome, descrição e foto.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de serviços recuperada com sucesso.", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ServiceSummaryResponseDTO.class))))
    })
    public ResponseEntity<List<ServiceSummaryResponseDTO>> getServices() {
        return ResponseEntity.ok(serviceService.getAll());
    }

    @GetMapping("/admin")
    @Operation(summary = "Listar todos os serviços", description = "Retorna informações resumidas de todos os serviços, incluindo os desativados. Destinada ao gerenciamento administrativo.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de serviços recuperada com sucesso.", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ServiceSummaryResponseDTO.class)))),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar o gerenciamento de serviços.", content = @Content)
    })
    public ResponseEntity<List<ServiceSummaryResponseDTO>> getAllServices() {
        return ResponseEntity.ok(serviceService.getAllForManagement());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar serviço por ID", description = "Retorna os dados completos de um serviço, incluindo modalidades, preços, durações estimadas e adicionais ativos.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Serviço encontrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado.", content = @Content),
            @ApiResponse(responseCode = "422", description = "Serviço desativado.", content = @Content)
    })
    public ResponseEntity<ServiceResponseDTO> getService(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceService.get(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cadastrar serviço", description = "Cria um serviço ativo com nome, descrição, modalidades e adicionais.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados do serviço em JSON",
            required = true
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Serviço cadastrado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados enviados inválidos.", content = @Content),
            @ApiResponse(responseCode = "409", description = "Já existe um serviço cadastrado com esse nome.", content = @Content),
            @ApiResponse(responseCode = "415", description = "Tipo de conteúdo da requisição ou de uma de suas partes não suportado.", content = @Content)
    })
    public ResponseEntity<ServiceResponseDTO> createService(@Valid @RequestBody ServiceCreateRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceService.create(request));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar serviço", description = "Atualiza o nome, a descrição, o preço e a duração de um serviço ativo existente.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados atualizados do serviço em JSON.",
            required = true
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Serviço atualizado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou modalidade ou adicional não pertencente ao serviço.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado.", content = @Content),
            @ApiResponse(responseCode = "409", description = "Já existe outro serviço cadastrado com esse nome.", content = @Content),
            @ApiResponse(responseCode = "415", description = "Tipo de conteúdo da requisição não suportado.", content = @Content),
            @ApiResponse(responseCode = "422", description = "O serviço está desativado e não pode ser atualizado.", content = @Content)
    })
    public ResponseEntity<ServiceResponseDTO> updateService(
            @PathVariable Integer id,
            @Valid @RequestBody ServiceUpdateRequestDTO request
    ) {
        return ResponseEntity.ok(serviceService.update(request, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desativar serviço", description = "Desativa um serviço sem excluí-lo fisicamente, preservando os registros relacionados.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Serviço desativado com sucesso.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado.", content = @Content),
            @ApiResponse(responseCode = "422", description = "O serviço já está desativado.", content = @Content)
    })
    public ResponseEntity<Void> deactivateService(@PathVariable Integer id) {
        serviceService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}