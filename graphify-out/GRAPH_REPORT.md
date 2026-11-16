# Graph Report - nail-api  (2026-11-16)

## Corpus Check
- 70 files · ~15,455 words
- Verdict: corpus is large enough that graph structure adds value.
- Unclassified: 6 file(s) not represented in the graph (top: (none) 2, .properties 2, .example 1)

## Summary
- 757 nodes · 1700 edges · 69 communities (12 shown, 57 thin omitted)
- Extraction: 87% EXTRACTED · 13% INFERRED · 0% AMBIGUOUS · INFERRED: 213 edges (avg confidence: 0.85)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `416ec3b9`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- Appointment
- org.springframework.http.ResponseEntity
- ServiceService.java
- StandardErrorResponseDTO
- io.swagger.v3.oas.annotations.media.Schema
- NailServiceOption
- AppointmentResponseDTO
- FileStorageService.java
- AppointmentDailySummaryResponseDTO
- .create
- UserResponseDTO
- User
- NailService
- localdatetime
- RegisterRequestDTO
- UserService.java
- Project Domain Rules
- ServiceAddonUpdateRequestDTO
- NailServiceAddon
- .login
- Application Tests
- Application Bootstrap
- ServiceUpdateRequestDTO
- Layered Architecture
- Repository Guidance
- Multipart Requests
- Maven Project
- API Documentation
- .updatePassword
- UserUpdateRequestDTO
- UserNotFoundException
- ServiceNotFoundException

## God Nodes (most connected - your core abstractions)
1. `Appointment` - 54 edges
2. `AppointmentResponseDTO` - 46 edges
3. `NailService` - 43 edges
4. `User` - 41 edges
5. `NailServiceOption` - 39 edges
6. `NailServiceAddon` - 35 edges
7. `OptionType` - 32 edges
8. `AppointmentService` - 31 edges
9. `ServiceResponseDTO` - 29 edges
10. `UserResponseDTO` - 28 edges

## Surprising Connections (you probably didn't know these)
- `Appointment Scheduling Rules` --semantically_similar_to--> `Appointment Scheduling Rules`  [INFERRED] [semantically similar]
  AGENTS.md → .github/copilot-instructions.md
- `Image Upload Pipeline` --references--> `French Manicure with Gold Glitter Accents`  [INFERRED]
  AGENTS.md → uploads/fotos/067611f0-c610-48a6-84aa-e488264042ef.webp
- `Image Upload Pipeline` --references--> `French Manicure with Gold Glitter Accents`  [INFERRED]
  AGENTS.md → uploads/fotos/235eabc2-4f5b-406e-93cf-19efddbf75ce.webp
- `Digital Portfolio` --conceptually_related_to--> `French Manicure with Gold Glitter Accents`  [INFERRED]
  README.md → uploads/fotos/067611f0-c610-48a6-84aa-e488264042ef.webp
- `Digital Portfolio` --conceptually_related_to--> `French Manicure with Gold Glitter Accents`  [INFERRED]
  README.md → uploads/fotos/235eabc2-4f5b-406e-93cf-19efddbf75ce.webp

## Import Cycles
- None detected.

## Hyperedges (group relationships)
- **Nail API Core Domains** — readme_appointment_management, readme_digital_portfolio, readme_dashboard_reporting [EXTRACTED 1.00]

## Communities (69 total, 57 thin omitted)

### Community 0 - "Appointment"
Cohesion: 0.06
Nodes (7): AppointmentService, Appointment, AppointmentRequestDTO, AppointmentConflictException, AppointmentNotFoundException, AppointmentValidationException, AppointmentMapper

### Community 1 - "org.springframework.http.ResponseEntity"
Cohesion: 0.09
Nodes (3): AppointmentController, ServiceController, UserController

### Community 2 - "ServiceService.java"
Cohesion: 0.05
Nodes (10): AppointmentRepository, DashboardService, DashboardController, DashboardRepository, FileStorageService, ServiceService, ServiceSummaryResponseDTO, NailServiceAddonRepository (+2 more)

### Community 3 - "StandardErrorResponseDTO"
Cohesion: 0.11
Nodes (4): StandardErrorResponseDTO, DataBaseAccessException, GlobalExceptionHandler, InvalidUserStatusException

### Community 5 - "io.swagger.v3.oas.annotations.media.Schema"
Cohesion: 0.06
Nodes (3): ServiceAddonResponseDTO, ServiceOptionResponseDTO, ServiceResponseDTO

### Community 6 - "NailServiceOption"
Cohesion: 0.08
Nodes (6): NailServiceOption, OptionType, APLICACAO, MANUTENCAO, ServiceOptionCreateRequestDTO, ServiceOptionUpdateRequestDTO

### Community 7 - "AppointmentResponseDTO"
Cohesion: 0.05
Nodes (7): AppointmentStatus, AGENDADO, CANCELADO, CONCLUIDO, CONFIRMADO, AppointmentResponseDTO, AppointmentStatusUpdateDTO

### Community 9 - "FileStorageService.java"
Cohesion: 0.10
Nodes (4): CorsConfig, FotoConfig, OpenApiConfig, PasswordEncoderConfig

### Community 10 - "AppointmentDailySummaryResponseDTO"
Cohesion: 0.08
Nodes (4): AppointmentDailySummaryResponseDTO, DashboardKpiResponseDTO, DashboardResponseDTO, DashboardMapper

### Community 12 - "UserResponseDTO"
Cohesion: 0.14
Nodes (4): UserType, ADMIN, CLIENTE, UserResponseDTO

### Community 20 - "UserService.java"
Cohesion: 0.18
Nodes (6): AuthService, AuthController, UserService, UserAlreadyExistsException, UserMapper, UserRepository

### Community 22 - "Project Domain Rules"
Cohesion: 0.20
Nodes (11): Appointment Scheduling Rules, Appointment Scheduling Rules, Image Upload Pipeline, Package Naming Convention, Appointment Management, Dashboard Reporting, Digital Portfolio, Java Spring Boot MySQL Stack (+3 more)

## Knowledge Gaps
- **17 isolated node(s):** `AGENDADO`, `CONFIRMADO`, `CONCLUIDO`, `CANCELADO`, `APLICACAO` (+12 more)
  These have ≤1 connection - possible missing edges or undocumented components. (Counts symbols only; 298 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **57 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `Appointment` connect `Appointment` to `ServiceService.java`, `NailServiceOption`, `AppointmentResponseDTO`, `User`, `localdatetime`, `NailServiceAddon`?**
  _High betweenness centrality (0.081) - this node is a cross-community bridge._
- **What connects `AGENDADO`, `CONFIRMADO`, `CONCLUIDO` to the rest of the system?**
  _17 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Appointment` be split into smaller, more focused modules?**
  _Cohesion score 0.05617283950617284 - nodes in this community are weakly interconnected._
- **Why does `AppointmentResponseDTO` connect `AppointmentResponseDTO` to `Appointment`, `org.springframework.http.ResponseEntity`, `NailServiceOption`?**
  _High betweenness centrality (0.078) - this node is a cross-community bridge._
- **Should `org.springframework.http.ResponseEntity` be split into smaller, more focused modules?**
  _Cohesion score 0.08587570621468926 - nodes in this community are weakly interconnected._
- **Why does `User` connect `User` to `Appointment`, `ServiceService.java`, `.updatePassword`, `UserUpdateRequestDTO`, `UserNotFoundException`, `UserResponseDTO`, `localdatetime`, `RegisterRequestDTO`, `UserService.java`, `.login`?**
  _High betweenness centrality (0.074) - this node is a cross-community bridge._
- **Should `ServiceService.java` be split into smaller, more focused modules?**
  _Cohesion score 0.051106639839034206 - nodes in this community are weakly interconnected._