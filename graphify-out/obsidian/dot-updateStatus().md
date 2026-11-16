---
source_file: "src/main/java/sptech/school/nail_api/service/AppointmentService.java"
type: "code"
community: "Appointment Domain"
location: "L259"
tags:
  - graphify/code
  - graphify/EXTRACTED
  - community/Appointment_Domain
---

# .updateStatus()

## Connections
- [[dot-getAppointmentStatus()]] - `calls` [INFERRED]
- [[dot-getScheduledEndAt()]] - `calls` [INFERRED]
- [[dot-setAppointmentStatus()]] - `calls` [INFERRED]
- [[dot-toResponseDTO()]] - `calls` [INFERRED]
- [[AppointmentConflictException]] - `calls` [EXTRACTED]
- [[AppointmentNotFoundException]] - `calls` [EXTRACTED]
- [[AppointmentResponseDTO]] - `references` [EXTRACTED]
- [[AppointmentService]] - `method` [EXTRACTED]
- [[AppointmentStatus]] - `references` [EXTRACTED]
- [[AppointmentValidationException]] - `calls` [EXTRACTED]
- [[org.springframework.transaction.annotation.Transactional]] - `references` [EXTRACTED]

#graphify/code #graphify/EXTRACTED #community/Appointment_Domain