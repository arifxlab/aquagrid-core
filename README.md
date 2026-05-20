# AquaGrid Core 🌊

AquaGrid Core is a Spring Boot-based backend system for managing water distribution zones with real-time monitoring, analytics-ready structure, and enterprise-grade API design.

---

## 🚀 Tech Stack

- Java 21
- Spring Boot 4
- Spring Data JPA
- Spring Security
- PostgreSQL
- Hibernate
- JWT (Authentication layer - in progress)
- Swagger / OpenAPI
- Maven

---

## 🏗 Architecture

This project follows a clean layered architecture:


Controller → Service → Repository → Database


With additional layers:

- DTO Layer (Request/Response separation)
- Mapper Layer (Entity ↔ DTO conversion)
- Exception Handling Layer (Global errors)
- Security Layer (JWT-based auth in progress)

---

## 📦 Features

### ✅ Completed Features

- CRUD operations for Water Zones
- Pagination, Sorting, Filtering
- Search by city
- Status management (ACTIVE / WARNING / CRITICAL)
- Duplicate zone prevention
- Global exception handling
- Validation (DTO-based)
- PostgreSQL integration
- Swagger API documentation
- Clean REST API design

---

## 📡 API Endpoints

### Zone APIs

| Method | Endpoint |
|--------|----------|
| POST | `/api/zones` |
| GET | `/api/zones` |
| GET | `/api/zones/{id}` |
| PUT | `/api/zones/{id}` |
| DELETE | `/api/zones/{id}` |
| GET | `/api/zones/search?city=` |
| GET | `/api/zones/status/{status}` |

---

## 📖 Swagger UI


http://localhost:8080/swagger-ui.html


---

## 🔐 Security (Upcoming)

- JWT Authentication
- Role-based access (ADMIN / USER)
- Secure endpoints

---

## 📊 Example Response

```json
{
  "success": true,
  "message": "Water zone created successfully",
  "data": {
    "id": 1,
    "zoneName": "Karachi Central",
    "city": "Karachi",
    "waterLevel": 65,
    "status": "WARNING"
  }
}
🛠 Current Status

✔ Backend fully functional
✔ Production-style architecture
🔄 JWT authentication in progress
🔜 Unit testing + logging improvements

🎯 Goal

To build an enterprise-level backend system suitable for:

Smart city water monitoring
Real-time analytics systems
Scalable microservice architecture transition
👨‍💻 Author

Arif Khan (Software Engineering Student)


---

# 🧾 STEP 2 — REQUIREMENTS FILE (ADD THIS)

Create a new file:

```text
REQUIREMENTS.md

Then add:

# AquaGrid Core - Requirements

## Functional Requirements

### Water Zone Management
- Create water zones
- Update water zones
- Delete water zones
- Retrieve zone details
- List all zones with pagination

### Search & Filtering
- Search zones by city
- Filter by status (ACTIVE, WARNING, CRITICAL)
- Sort results by fields (id, waterLevel, etc.)

### Validation
- Zone name must not be empty
- City must not be empty
- Water level must be within valid range
- Duplicate zone names not allowed

---

## System Requirements

- Java 21+
- Spring Boot 4+
- PostgreSQL 14+
- Maven 3.8+

---

## API Standards

- RESTful architecture
- Standard HTTP status codes
- Consistent response wrapper
- JSON-based communication

---

## Security (Planned)

- JWT authentication
- Role-based access control
- Secure API endpoints

---

## Non-Functional Requirements

- Scalable architecture
- Clean layered design
- Maintainable code structure
- Logging support (future)
- Unit testing coverage (future)