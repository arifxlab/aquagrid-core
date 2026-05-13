# AquaGrid Core 🌊

AquaGrid is a backend system for water grid and GIS-based monitoring.  
It is built using Spring Boot and PostgreSQL with a clean layered architecture.

---

## 🚀 Tech Stack

- Java 21
- Spring Boot 4
- Spring Data JPA (Hibernate)
- PostgreSQL 16
- Maven
- IntelliJ IDEA

---

## 📦 Project Structure


com.aquagrid
├── AquagridCoreApplication
├── config
├── controller
├── dto
├── exception
├── model
├── repository
├── service
└── util


---

## 🧠 Current Phase

### Phase 1: Core Backend Setup
- Spring Boot project setup
- PostgreSQL connection
- Basic entity creation (WaterZone)
- Layered architecture setup

---

## ▶️ How to Run

### 1. Start PostgreSQL
Make sure database is running:


jdbc:postgresql://localhost:5432/aquagrid


### 2. Run Application

Run:


AquagridCoreApplication.java


or via terminal:


mvn spring-boot:run


---

## 📌 Next Features

- WaterZone CRUD APIs
- Sensor data simulation
- GIS-based queries (PostGIS)
- Alert system (high water level detection)
- Scheduling system for monitoring

---

## 👨‍💻 Author

Arif Khan