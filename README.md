# Expense Tracker API

API REST para controle de despesas pessoais.

## Tecnologias

- Java 21
- Spring Boot 3.5.7
- PostgreSQL 17
- Docker

## Requisitos

- JDK 21
- Docker e Docker Compose

## Como executar
```bash
git clone https://github.com/eduardovitorino-git/expense-tracker-api.git
cd expense-tracker
docker-compose up -d
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`

## Documentação

Swagger UI: `http://localhost:8080/docs`

## Principais endpoints
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | /api/expenses | Listar todas as despesas |
| GET | /api/expenses/{id} | Buscar despesa por ID |
| POST | /api/expenses | Criar nova despesa |
| PUT | /api/expenses | Atualizar despesa (completa) |
| PATCH | /api/expenses/{id} | Atualizar despesa (parcial) |
| DELETE | /api/expenses/{id} | Remover despesa |
