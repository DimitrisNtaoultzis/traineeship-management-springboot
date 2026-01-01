# traineeship-management-springboot
# Traineeship Management Application (Spring Boot)

A full-stack **Spring Boot** web application developed as part of a Software Engineering course.
The system supports the complete lifecycle of university traineeships, involving students, companies, professors, and a central committee.

---

## Features & Roles

### 👨‍🎓 Student
- View available traineeship positions
- Apply to positions
- Fill personal profile and logbook

### 🏢 Company
- Create and manage traineeship positions
- Evaluate assigned students
- View assigned positions

### 👨‍🏫 Professor
- Supervise students
- View assigned positions
- Submit evaluations

### 🏛 Committee
- Assign students to positions
- Assign professors to students
- View final results and evaluations

---

## Architecture

The application follows a layered architecture:

- **Controllers**: Handle HTTP requests and role-based views
- **Services**: Business logic
- **Domain Model**: Core entities (Student, Company, Professor, TraineeshipPosition, Evaluation)
- **Mappers**: DTO ↔ entity mapping
- **Security**: Spring Security configuration
- **Views**: Thymeleaf templates

---

## Design Patterns

The project makes extensive use of design patterns:

- **Strategy Pattern**
  - Multiple search strategies for traineeship positions:
    - By location
    - By interests
    - Combined criteria
- **Factory Pattern**
  - Dynamic selection of search and assignment strategies

This allows easy extension and improves maintainability.

---

## Technologies

- Java
- Spring Boot
- Spring Security
- Thymeleaf
- Maven
- JUnit / Mockito
- MySQL

---

## Testing

The project includes unit and integration tests for:
- Services
- Controllers
- Authentication logic

---

## Team Project & Contribution

This was a **team project**.
My personal contributions include:
- Implementation of core service logic
- Design and implementation of search & assignment strategies
- Controller development
- Unit and integration testing

---

## How to Run

```bash
mvn spring-boot:run
