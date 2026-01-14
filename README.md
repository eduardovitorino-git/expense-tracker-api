# Expense Tracker API

API REST para controle de despesas pessoais.

## Tecnologias

- Java 21
- Spring Boot 3.5.9
- PostgreSQL 17
- Docker

## Requisitos

- JDK 21 [Download](https://adoptium.net/download?link=https%3A%2F%2Fgithub.com%2Fadoptium%2Ftemurin21-binaries%2Freleases%2Fdownload%2Fjdk-21.0.9%252B10%2FOpenJDK21U-jdk_x64_windows_hotspot_21.0.9_10.msi&vendor=Adoptium)
- Docker e Docker Compose [Download](https://desktop.docker.com/win/main/amd64/Docker%20Desktop%20Installer.exe?utm_source=docker&utm_medium=webreferral&utm_campaign=docs-driven-download-win-amd64)

## Como executar
```bash
git clone https://github.com/eduardovitorino-git/expense-tracker-api.git
cd expense-tracker-api/app/
docker-compose up -d
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`

## Documentação

Swagger UI: `http://localhost:8080/docs`

## Principais endpoints
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /api/expenses | Lista despesas do usuário |
| GET | /api/expenses/{id} | Busca despesa por ID |
| GET | /api/expenses/category/{name} | Filtra por categoria |
| GET | /api/expenses?startDate=X&endDate=Y | Filtra por período |
| GET | /api//expenses/min-amount| Filtra por valor mínimo |
| GET | /api//expenses/max-amount| Filtra por valor máximo |
| POST | /api/expenses | Cria nova despesa |
| PUT | /api/expenses/{id} | Atualiza despesa |
| DELETE | /api/expenses/{id} | Remove despesa (soft delete) |
