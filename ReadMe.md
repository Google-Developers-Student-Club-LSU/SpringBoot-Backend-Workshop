# Spring Boot Workshop Starter

This project is a classroom starter for:
- Spring Boot controllers
- REST API design
- Spring Data JPA
- Spring Security with a custom session cookie

The boilerplate is already here so class time can focus on concepts instead of setup:
- Maven configuration
- entities
- repositories
- DTO records
- controller routes
- service classes
- H2 database config
- custom security filter
- security configuration
- global exception handling

## What Students Should Fill In

Look for `TODO` comments in the codebase. The main teaching exercises are:
- validate user registration
- check passwords during login
- decide when to return `404`, `401`, or empty lists
- protect resources so users only access their own decks and cards
- discuss safer production cookie settings

## Endpoints

Public:
- `POST /user/register`
- `POST /user/login`

Protected:
- `GET /user/logout`
- `POST /deck`
- `GET /deck`
- `DELETE /deck/delete/{id}`
- `POST /card/{id}`
- `GET /card/{id}/deck`
- `DELETE /card/delete/{id}`

## Run The App

```bash
./mvnw spring-boot:run
```

H2 console:
- `/h2-console`

## Suggested Teaching Flow

1. Start with the controller annotations and request/response bodies.
2. Move into the service layer and add business rules.
3. Show how repositories remove boilerplate SQL for CRUD.
4. Walk through `SecurityConfig` and `SessionAuthFilter`.
5. Test the API in Postman using the `auth` cookie from login.
