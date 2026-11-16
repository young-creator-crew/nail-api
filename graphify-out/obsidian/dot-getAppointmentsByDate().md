---
source_file: "src/main/java/sptech/school/nail_api/service/AppointmentService.java"
type: "code"
community: "Appointment Domain"
location: "L238"
tags:
  - graphify/code
  - graphify/EXTRACTED
  - community/Appointment_Domain
---

# .getAppointmentsByDate()

## Connections
- [[dot-findByScheduledStartAtGreaterThanEqualAndScheduledStartAtLessThanOrderByScheduledStartAtAsc()]] - `calls` [INFERRED]
- [[dot-validateDate()]] - `calls` [EXTRACTED]
- [[AppointmentResponseDTO]] - `references` [EXTRACTED]
- [[AppointmentService]] - `method` [EXTRACTED]
- [[org.springframework.transaction.annotation.Transactional]] - `references` [EXTRACTED]

#graphify/code #graphify/EXTRACTED #community/Appointment_Domain