# VDI-Issue-Tracker-App
## FrontEnd Code: https://github.com/suhas275/VDI-Issue-Tracker-App-FrontEnd
 
VDI Issue Tracker - Microservices Backend
========================================

Overview
--------
This repository contains the backend for a microservices-based VDI Issue Tracker. It is composed of three services:

- api-gateway (Maven): Spring Cloud Gateway + Eureka Server (service registry)
- auth-service (Gradle): Authentication/authorization, JWT, user management, Eureka client
- issue-service (Gradle): Issue CRUD and comments, Eureka client

All services target Java 17 and Spring Boot 3.x.

Tech Stack
---------
- Java 17
- Spring Boot 3.x
- Spring Web, Spring Data JPA, Spring Security
- Spring Cloud (Eureka Server/Client, LoadBalancer)
- MySQL (JPA/Hibernate)
- JWT (io.jsonwebtoken:jjwt)
- Build tools: Maven (api-gateway) and Gradle (auth-service, issue-service)

Architecture
------------
- Service discovery via Eureka
- API Gateway fronts downstream services
- JWT-based stateless authentication in `auth-service`
- `issue-service` provides domain endpoints; relies on `auth-service` for auth via JWT

Repository Structure
--------------------

```
<repo-root>
  api-gateway/               # Spring Cloud Gateway + Eureka Server (Maven)
    src/main/java/com/vdi/apigateway/ApiGatewayApplication.java
    src/main/resources/application.yml
  auth-service/              # Authentication service (Gradle)
    src/main/java/com/vdi/authentication/
      AuthenticationApplication.java
      config/SecurityConfig.java
      controller/UserController.java
      entity/{UserInfo, AuthRequest}.java
      filter/JwtFilter.java
      logout/BlackList.java
      repository/UserInfoRepository.java
      service/{UserInfoService, UserInfoDetails, JwtService}.java
    src/main/resources/application.properties
  issue-service/             # Issue domain service (Gradle)
    src/main/java/com/vdi/issue/
      IssueServiceApplication.java
      controller/IssueController.java
      entity/{Issues, Comment}.java
      exception/ResourceNotFoundException.java
      repository/{IssueRepository, CommentRepository}.java
      service/IssueService.java
    src/main/resources/application.properties
```

Prerequisites
-------------
- JDK 17 installed and on PATH
- MySQL 8.x (or compatible) running and accessible
- Maven 3.9+ (for api-gateway) and Gradle (wrapper included for Gradle projects)

Configuration
-------------

Common settings (recommended):
- Set MySQL connection for each service in its `application.properties`:

auth-service/src/main/resources/application.properties (example):
```
server.port=8586
spring.datasource.url=jdbc:mysql://localhost:3306/vdi_auth?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT secret (base64-encoded preferred)
security.jwt.secret=CHANGE_ME_BASE64

spring.application.name=AUTHENTICATION
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
```

issue-service/src/main/resources/application.properties (example):
```
server.port=8587
spring.datasource.url=jdbc:mysql://localhost:3306/vdi_issue?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.application.name=ISSUE-SERVICE
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
```

api-gateway/src/main/resources/application.yml (example):
```
server:
  port: 8761

spring:
  application:
    name: API-GATEWAY

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```

Build & Run
-----------

Order of startup (recommended):
1) api-gateway (Eureka server)
2) auth-service
3) issue-service

Commands:

- api-gateway (Maven):
```
cd api-gateway
mvn -DskipTests package
mvn spring-boot:run
```

- auth-service (Gradle):
```
cd auth-service
./gradlew clean build -x test
./gradlew bootRun
```

- issue-service (Gradle):
```
cd issue-service
./gradlew clean build -x test
./gradlew bootRun
```

Security & Auth
---------------
- JWT creation/validation in `com.vdi.authentication.service.JwtService`
- Stateless auth enforced via `SecurityConfig` and `JwtFilter`
- `security.jwt.secret` configurable via properties (provide a strong Base64-encoded secret in production)

Key Endpoints (Summary)
-----------------------

auth-service (prefix `/auth`):
- POST `/addUser` – Register user
- POST `/login` – Authenticate, returns JWT and user payload
- POST `/logout` – Blacklist token (requires authority USER_ROLES or ADMIN_ROLES)
- GET `/getUsers` – List users (ADMIN_ROLES or USER_ROLES)
- GET `/getUsers/{id}` – Get user by id (USER_ROLES)
- PUT `/{id}` – Update user basic fields

issue-service:
- POST `/add` – Create new issue
- GET `/all` – List all issues
- GET `/issues/{id}` – Get issue by id
- PUT `/update/{id}` – Update issue by id
- DELETE `/delete/{id}` – Delete issue by id
- GET `/comments/issues/{id}` – List comments for issue
- POST `/comments/issues/{id}` – Add comment to issue

Coding Standards & Practices
----------------------------
- Package naming: `com.vdi.<service>.<layer>` with lowercase subpackages
- Constructor injection for services/controllers/filters/config
- Entities/DTOs leverage Lombok (@Data, @NoArgsConstructor, @AllArgsConstructor)
- Avoid business logic in controllers; keep it in services
- Use `@Transactional` for write operations affecting multiple tables
- Centralized exception types (`ResourceNotFoundException` in issue-service)

Development Tips
----------------
- Use distinct DB schemas per service in development
- Disable open-in-view for production (spring.jpa.open-in-view=false)
- Replace in-memory token blacklist with persistent/redis-based storage in production
- Configure proper CORS in gateway or services as needed

Testing
-------
- Unit tests can be added under `src/test/java` for each module
- Use `@SpringBootTest` for integration tests

Contributing
------------
1. Fork the repo
2. Create a feature branch
3. Commit with clear messages
4. Open a PR with description and context

License
-------
This project is released under the MIT License. See `LICENSE` if present or add one as needed.