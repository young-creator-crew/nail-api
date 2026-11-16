# Copilot instructions

## Source of truth

Read `AGENTS.md` before making changes. It is the authoritative project guidance for this repository; follow its persona, evidence requirements, review behavior, safety gates, and implementation rules. If this file conflicts with `AGENTS.md`, `AGENTS.md` wins.

## Build and run

This is a single-module Spring Boot 4.1.1 application using Java 21 and the Maven wrapper.

- Windows: `.\mvnw.cmd spring-boot:run`
- Linux/macOS: `./mvnw spring-boot:run`
- Package without tests: `.\mvnw.cmd -DskipTests package`
- Run the full test suite with an in-memory H2 datasource:
  `.\mvnw.cmd test "-Dspring.datasource.url=jdbc:h2:mem:testdb" "-Dspring.datasource.driver-class-name=org.h2.Driver" "-Dspring.datasource.username=sa" "-Dspring.datasource.password="`
- Run one test class:
  `.\mvnw.cmd "-Dtest=NailApiApplicationTests" test "-Dspring.datasource.url=jdbc:h2:mem:testdb" "-Dspring.datasource.driver-class-name=org.h2.Driver" "-Dspring.datasource.username=sa" "-Dspring.datasource.password="`

The normal datasource is MySQL and requires `DB_URL`, `DB_USER`, and `DB_PASSWORD`; use `.env.example` to configure a local `.env`. Tests that start the Spring context need datasource settings. No repository lint command is defined.

## Architecture

The application entry point is `src/main/java/sptech/school/nail_api/NailApiApplication.java`. Request flow follows `controller -> service -> repository`: controllers expose REST endpoints, services contain business rules, repositories persist JPA entities, and manual mappers convert between entities and DTOs.

The main domains are authentication/users, services and their options/add-ons, appointments, and dashboard reporting. DTOs are grouped by domain and operation, while exceptions are translated centrally by `GlobalExceptionHandler`. Configuration classes provide CORS, file serving, OpenAPI, and password encoding.

## Repository-specific conventions

- Use the package `sptech.school.nail_api`; the underscore is intentional.
- Service create/update requests are multipart: the JSON part is named `service`, and the optional image part is named `foto`.
- Images are limited to JPEG, PNG, and WEBP, stored under `uploads/fotos`, and served through `/fotos/**`.
- User and service deletion is soft deletion (`is_active=false`), not physical deletion.
- Updating service `options` or `addons` replaces the complete list; omitted existing items are soft-deactivated.
- Appointment rules are enforced in service logic: timezone `America/Sao_Paulo`, business hours `08:00-20:00`, 15-minute alignment, and overlap blocking for `AGENDADO` and `CONFIRMADO`.
- Preserve the existing DTO, mapper, service, repository, and exception boundaries when extending behavior.
