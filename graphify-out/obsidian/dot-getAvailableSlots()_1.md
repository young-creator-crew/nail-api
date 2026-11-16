---
source_file: "src/main/java/sptech/school/nail_api/service/AppointmentService.java"
type: "code"
community: "Appointment Domain"
location: "L69"
tags:
  - graphify/code
  - graphify/EXTRACTED
  - community/Appointment_Domain
---

# .getAvailableSlots()

## Connections
- [[dot-findActiveOption()]] - `calls` [EXTRACTED]
- [[dot-findOverlappingAppointments()]] - `calls` [INFERRED]
- [[dot-getAvailableSlots()]] - `calls` [INFERRED]
- [[dot-getDurationMinutes()]] - `calls` [INFERRED]
- [[dot-overlaps()]] - `calls` [EXTRACTED]
- [[dot-validateAvailabilityDate()]] - `calls` [EXTRACTED]
- [[AppointmentService]] - `method` [EXTRACTED]
- [[AvailableSlotResponseDTO]] - `references` [EXTRACTED]
- [[org.springframework.transaction.annotation.Transactional]] - `references` [EXTRACTED]

#graphify/code #graphify/EXTRACTED #community/Appointment_Domain