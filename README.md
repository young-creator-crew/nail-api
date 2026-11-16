# Nail API

API REST para gestão de horários e agendamentos de serviços, integrada a um portfólio digital.

## Contexto do projeto

O sistema foi pensado para reduzir processos manuais de agendamento, melhorar a comunicação com clientes e organizar melhor a rotina de atendimento.  
Além da agenda, a plataforma apoia a tomada de decisão com indicadores como quantidade de agendamentos, serviços mais solicitados e horários de maior procura.

## Objetivo

Facilitar a consulta de serviços e disponibilidade de horários, oferecer um portfólio digital de trabalhos e disponibilizar dados de desempenho para a gestão do negócio.

## Ecossistema tecnológico

- **Back-end:** Java 21 + Spring Boot (Spring Web, Spring Data JPA)
- **Banco de dados:** MySQL
- **Front-end:** React
- **Design:** Figma
- **Organização:** Obsidian e Google Drive

## Configuração

Crie um arquivo `.env` na raiz (há um modelo em `.env.example`):

```env
DB_URL=
DB_USER=
DB_PASSWORD=
```

## Como executar

### Windows
```powershell
.\mvnw.cmd spring-boot:run
```

### Linux/macOS
```bash
./mvnw spring-boot:run
```

## Build

```bash
./mvnw -DskipTests package
```

## Testes

O teste padrão depende de datasource configurado. Para rodar rapidamente com H2:

```bash
./mvnw test "-Dspring.datasource.url=jdbc:h2:mem:testdb" "-Dspring.datasource.driver-class-name=org.h2.Driver" "-Dspring.datasource.username=sa" "-Dspring.datasource.password="
```

## Documentação da API

Com a aplicação em execução:

- `http://localhost:8080/swagger-ui.html`
