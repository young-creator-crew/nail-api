---
source_file: "src/main/java/sptech/school/nail_api/repository/AppointmentRepository.java"
type: "code"
community: "Appointment Domain"
location: "L15"
tags:
  - graphify/code
  - graphify/EXTRACTED
  - community/Appointment_Domain
---

# AppointmentRepository

## Connections
- [[dot-AppointmentService()]] - `references` [EXTRACTED]
- [[dot-findAllByUserOrderByScheduledStartAtDesc()]] - `method` [EXTRACTED]
- [[dot-findByIdAppointmentAndUser()]] - `method` [EXTRACTED]
- [[dot-findByScheduledStartAtGreaterThanEqualAndScheduledStartAtLessThanOrderByScheduledStartAtAsc()]] - `method` [EXTRACTED]
- [[dot-findOverlappingAppointments()]] - `method` [EXTRACTED]
- [[dot-findOverlappingAppointmentsExcluding()]] - `method` [EXTRACTED]
- [[Appointment]] - `references` [EXTRACTED]
- [[AppointmentRepository.java]] - `contains` [EXTRACTED]
- [[AppointmentService]] - `references` [EXTRACTED]
- [[org.springframework.data.jpa.repository.JpaRepository]] - `inherits` [EXTRACTED]

#graphify/code #graphify/EXTRACTED #community/Appointment_Domain