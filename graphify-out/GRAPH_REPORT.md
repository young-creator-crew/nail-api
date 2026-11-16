# Graph Report - nail-api  (2026-11-15)

## Corpus Check
- Corpus is ~15,174 words - fits in a single context window. You may not need a graph.

## Summary
- 724 nodes · 1688 edges · 36 communities (14 shown, 22 thin omitted)
- Extraction: 87% EXTRACTED · 13% INFERRED · 0% AMBIGUOUS · INFERRED: 213 edges (avg confidence: 0.85)
- Token cost: 0 input · 0 output

## Community Hubs (Navigation)
- Appointment Domain
- REST Controllers
- Dashboard Reporting
- Exception Handling
- Service Options
- Available Slots
- Appointment Status
- Service Responses
- Dashboard Repositories
- Application Configuration
- Service Management
- Service Creation
- User Profiles
- User Domain
- Addon Updates
- Addon Mapping
- Service Summaries
- User Registration
- Service Updates
- Service Domain
- User Management
- Authentication Flow
- Project Domain Rules
- Password Updates
- User Updates
- Password Encoding
- Application Tests
- Application Bootstrap
- Service Exceptions
- Layered Architecture
- Repository Guidance
- Multipart Requests
- Maven Project
- API Documentation

## God Nodes (most connected - your core abstractions)
1. `Appointment` - 54 edges
2. `AppointmentResponseDTO` - 46 edges
3. `NailService` - 42 edges
4. `User` - 39 edges
5. `NailServiceOption` - 37 edges
6. `NailServiceAddon` - 33 edges
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

## Communities (36 total, 22 thin omitted)

### Community 0 - "Appointment Domain"
Cohesion: 0.05
Nodes (7): AppointmentRequestDTO, Appointment, AppointmentNotFoundException, AppointmentValidationException, AppointmentMapper, AppointmentRepository, AppointmentService

### Community 1 - "REST Controllers"
Cohesion: 0.08
Nodes (3): AppointmentController, ServiceController, UserController

### Community 2 - "Dashboard Reporting"
Cohesion: 0.06
Nodes (4): AppointmentDailySummaryResponseDTO, DashboardKpiResponseDTO, DashboardResponseDTO, DashboardMapper

### Community 3 - "Exception Handling"
Cohesion: 0.08
Nodes (8): StandardErrorResponseDTO, AppointmentConflictException, InvalidCredentialsException, DataBaseAccessException, GlobalExceptionHandler, InvalidUserStatusException, UserAlreadyExistsException, UserNotFoundException

### Community 4 - "Service Options"
Cohesion: 0.07
Nodes (6): ServiceOptionCreateRequestDTO, ServiceOptionUpdateRequestDTO, NailServiceOption, OptionType, APLICACAO, MANUTENCAO

### Community 6 - "Appointment Status"
Cohesion: 0.05
Nodes (7): AppointmentResponseDTO, AppointmentStatusUpdateDTO, AppointmentStatus, AGENDADO, CANCELADO, CONCLUIDO, CONFIRMADO

### Community 7 - "Service Responses"
Cohesion: 0.07
Nodes (3): ServiceAddonResponseDTO, ServiceOptionResponseDTO, ServiceResponseDTO

### Community 8 - "Dashboard Repositories"
Cohesion: 0.13
Nodes (5): DashboardController, DashboardRepository, NailServiceOptionRepository, NailServiceRepository, DashboardService

### Community 9 - "Application Configuration"
Cohesion: 0.12
Nodes (3): CorsConfig, FotoConfig, OpenApiConfig

### Community 10 - "Service Management"
Cohesion: 0.16
Nodes (3): NailServiceAddonRepository, FileStorageService, ServiceService

### Community 12 - "User Profiles"
Cohesion: 0.14
Nodes (4): UserResponseDTO, UserType, ADMIN, CLIENTE

### Community 20 - "User Management"
Cohesion: 0.25
Nodes (3): UserMapper, UserRepository, UserService

### Community 21 - "Authentication Flow"
Cohesion: 0.21
Nodes (3): AuthController, LoginRequestDTO, AuthService

### Community 22 - "Project Domain Rules"
Cohesion: 0.20
Nodes (11): Appointment Scheduling Rules, Appointment Scheduling Rules, Image Upload Pipeline, Package Naming Convention, Appointment Management, Dashboard Reporting, Digital Portfolio, Java Spring Boot MySQL Stack (+3 more)

## Knowledge Gaps
- **17 isolated node(s):** `sptech.school:nail-api`, `AGENDADO`, `CONFIRMADO`, `CONCLUIDO`, `CANCELADO` (+12 more)
  These have ≤1 connection - possible missing edges or undocumented components. (Counts symbols only; 266 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **22 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `Appointment` connect `Appointment Domain` to `Service Options`, `Available Slots`, `Appointment Status`, `Dashboard Repositories`, `User Domain`, `Addon Mapping`?**
  _High betweenness centrality (0.109) - this node is a cross-community bridge._
- **What connects `sptech.school:nail-api`, `AGENDADO`, `CONFIRMADO` to the rest of the system?**
  _17 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Appointment Domain` be split into smaller, more focused modules?**
  _Cohesion score 0.05329153605015674 - nodes in this community are weakly interconnected._
- **Why does `AppointmentResponseDTO` connect `Appointment Status` to `Appointment Domain`, `REST Controllers`, `Service Options`, `Available Slots`?**
  _High betweenness centrality (0.085) - this node is a cross-community bridge._
- **Should `REST Controllers` be split into smaller, more focused modules?**
  _Cohesion score 0.08302485457429931 - nodes in this community are weakly interconnected._
- **Why does `User` connect `User Domain` to `Appointment Domain`, `Available Slots`, `Dashboard Repositories`, `User Profiles`, `User Registration`, `User Management`, `Password Updates`, `User Updates`?**
  _High betweenness centrality (0.073) - this node is a cross-community bridge._
- **Should `Dashboard Reporting` be split into smaller, more focused modules?**
  _Cohesion score 0.06290471785383904 - nodes in this community are weakly interconnected._