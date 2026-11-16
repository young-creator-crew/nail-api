---
source_file: "src/main/java/sptech/school/nail_api/service/AppointmentService.java"
type: "code"
community: "Appointment Domain"
location: "L219"
tags:
  - graphify/code
  - graphify/EXTRACTED
  - community/Appointment_Domain
---

# .cancel()

## Connections
- [[dot-delete()]] - `calls` [INFERRED]
- [[dot-findByIdAppointmentAndUser()]] - `calls` [INFERRED]
- [[dot-findClient()]] - `calls` [EXTRACTED]
- [[dot-setAppointmentStatus()]] - `calls` [INFERRED]
- [[dot-validateEditableStatus()]] - `calls` [EXTRACTED]
- [[AppointmentNotFoundException]] - `calls` [EXTRACTED]
- [[AppointmentService]] - `method` [EXTRACTED]
- [[org.springframework.transaction.annotation.Transactional]] - `references` [EXTRACTED]

#graphify/code #graphify/EXTRACTED #community/Appointment_Domain