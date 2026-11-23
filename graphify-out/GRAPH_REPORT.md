# Graph Report - nail-api  (2026-11-23)

## Corpus Check
- cluster-only mode — file stats not available

## Summary
- 619 nodes · 1341 edges · 27 communities (13 shown, 14 thin omitted)
- Extraction: 90% EXTRACTED · 10% INFERRED · 0% AMBIGUOUS · INFERRED: 130 edges (avg confidence: 0.85)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `d9fa457e`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- Appointment
- NailService
- org.springframework.http.ResponseEntity
- io.swagger.v3.oas.annotations.media.Schema
- StandardErrorResponseDTO
- ServiceResponseDTO
- NailServiceImg
- AppointmentResponseDTO
- org.springframework.context.annotation.Configuration
- AppointmentRepository.java
- UserResponseDTO
- RegisterRequestDTO
- User
- UserRepository
- AppointmentStatus
- .update
- UserService
- OptionType
- .login
- AvailableSlotResponseDTO
- NailApiApplicationTests.java
- NailApiApplication.java
- ServiceNotFoundException
- sptech.school:nail-api

## God Nodes (most connected - your core abstractions)
1. `Appointment` - 48 edges
2. `AppointmentResponseDTO` - 46 edges
3. `User` - 39 edges
4. `NailService` - 35 edges
5. `AppointmentService` - 31 edges
6. `ServiceResponseDTO` - 28 edges
7. `UserResponseDTO` - 28 edges
8. `AppointmentStatus` - 25 edges
9. `StandardErrorResponseDTO` - 23 edges
10. `ServiceSummaryResponseDTO` - 22 edges

## Surprising Connections (you probably didn't know these)
- `AppointmentController` --references--> `AppointmentService`  [EXTRACTED]
  src/main/java/sptech/school/nail_api/controller/AppointmentController.java → src/main/java/sptech/school/nail_api/service/AppointmentService.java
- `ServiceController` --references--> `ServiceService`  [EXTRACTED]
  src/main/java/sptech/school/nail_api/controller/ServiceController.java → src/main/java/sptech/school/nail_api/service/ServiceService.java
- `AuthController` --references--> `AuthService`  [EXTRACTED]
  src/main/java/sptech/school/nail_api/controller/AuthController.java → src/main/java/sptech/school/nail_api/service/AuthService.java
- `NailService` --references--> `NailServiceImg`  [EXTRACTED]
  src/main/java/sptech/school/nail_api/entity/NailService.java → src/main/java/sptech/school/nail_api/entity/NailServiceImg.java
- `NailServiceRepository` --references--> `NailService`  [EXTRACTED]
  src/main/java/sptech/school/nail_api/repository/NailServiceRepository.java → src/main/java/sptech/school/nail_api/entity/NailService.java

## Import Cycles
- None detected.

## Communities (27 total, 14 thin omitted)

### Community 0 - "Appointment"
Cohesion: 0.05
Nodes (7): AppointmentRequestDTO, Appointment, AppointmentNotFoundException, AppointmentValidationException, AppointmentMapper, AppointmentRepository, AppointmentService

### Community 1 - "NailService"
Cohesion: 0.05
Nodes (5): ServiceCreateRequestDTO, ServiceUpdateRequestDTO, NailService, ServiceMapper, ServiceService

### Community 2 - "org.springframework.http.ResponseEntity"
Cohesion: 0.08
Nodes (3): AppointmentController, AuthController, ServiceController

### Community 3 - "io.swagger.v3.oas.annotations.media.Schema"
Cohesion: 0.06
Nodes (4): AppointmentDailySummaryResponseDTO, DashboardKpiResponseDTO, DashboardResponseDTO, DashboardMapper

### Community 4 - "StandardErrorResponseDTO"
Cohesion: 0.09
Nodes (7): StandardErrorResponseDTO, AppointmentConflictException, InvalidCredentialsException, DataBaseAccessException, GlobalExceptionHandler, UserAlreadyExistsException, UserNotFoundException

### Community 5 - "ServiceResponseDTO"
Cohesion: 0.06
Nodes (3): MediaResponseDTO, ServiceResponseDTO, ServiceSummaryResponseDTO

### Community 6 - "NailServiceImg"
Cohesion: 0.07
Nodes (5): MediaController, MediaRequestDTO, NailServiceImg, NailServiceMediaRepository, MediaService

### Community 8 - "org.springframework.context.annotation.Configuration"
Cohesion: 0.13
Nodes (4): CorsConfig, FotoConfig, OpenApiConfig, PasswordEncoderConfig

### Community 9 - "AppointmentRepository.java"
Cohesion: 0.14
Nodes (4): DashboardController, DashboardRepository, NailServiceRepository, DashboardService

### Community 10 - "UserResponseDTO"
Cohesion: 0.14
Nodes (4): UserResponseDTO, UserType, ADMIN, CLIENTE

### Community 13 - "UserRepository"
Cohesion: 0.23
Nodes (3): UserMapper, UserRepository, AuthService

### Community 15 - "AppointmentStatus"
Cohesion: 0.21
Nodes (6): AppointmentStatusUpdateDTO, AppointmentStatus, AGENDADO, CANCELADO, CONCLUIDO, CONFIRMADO

### Community 19 - "OptionType"
Cohesion: 0.25
Nodes (3): OptionType, APLICACAO, MANUTENCAO

## Knowledge Gaps
- **9 isolated node(s):** `ADMIN`, `CLIENTE`, `AGENDADO`, `CANCELADO`, `CONCLUIDO` (+4 more)
  These have ≤1 connection - possible missing edges. (Counts symbols only; 245 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **14 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `AppointmentResponseDTO` connect `AppointmentResponseDTO` to `Appointment`, `org.springframework.http.ResponseEntity`, `localdatetime`, `AppointmentStatus`, `OptionType`?**
  _High betweenness centrality (0.101) - this node is a cross-community bridge._
- **What connects `ADMIN`, `CLIENTE`, `AGENDADO` to the rest of the system?**
  _9 weakly-connected nodes found - possible missing edges._
- **Should `Appointment` be split into smaller, more focused modules?**
  _Cohesion score 0.054061624649859946 - nodes in this community are weakly interconnected._
- **Why does `Appointment` connect `Appointment` to `AppointmentRepository.java`, `User`, `localdatetime`, `AppointmentStatus`, `OptionType`?**
  _High betweenness centrality (0.093) - this node is a cross-community bridge._
- **Should `NailService` be split into smaller, more focused modules?**
  _Cohesion score 0.054563492063492064 - nodes in this community are weakly interconnected._
- **Why does `User` connect `User` to `Appointment`, `AppointmentRepository.java`, `UserResponseDTO`, `RegisterRequestDTO`, `UserRepository`, `localdatetime`, `UserService`, `.login`?**
  _High betweenness centrality (0.086) - this node is a cross-community bridge._
- **Should `org.springframework.http.ResponseEntity` be split into smaller, more focused modules?**
  _Cohesion score 0.07885304659498207 - nodes in this community are weakly interconnected._