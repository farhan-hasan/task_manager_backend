# Task Manager Backend

## Overview
This is a Spring Boot backend application for a Task Manager. It provides CRUD operations for managing tasks and includes user authentication and role-based authorization.

## Features
- Create, Read, Update, and Delete (CRUD) tasks. [No JPA used]
- User authentication using JWT (JSON Web Token).
- Role-based access control.
- PostgreSQL as the database.

## Tech Stack
- **Backend**: Spring Boot
- **Database**: PostgreSQL
- **Security**: Spring Security, JWT

## Setup Instructions

### Prerequisites
- Install [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- Install [Maven](https://maven.apache.org/download.cgi)
- Install [PostgreSQL](https://www.postgresql.org/download/)

### Clone the Repository
```sh
git clone https://github.com/yourusername/task-manager-backend.git
cd task-manager-backend
```

### Configure the Database
Update `application.properties` or `application.yml` with your PostgreSQL configuration:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/task_manager
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
```

### Run the Application
Use Maven to build and run the application:
```sh
mvn spring-boot:run
```

### API Endpoints
Coming soon..

### Authentication
- After logging in, users receive a JWT token.
- This token must be included in the `Authorization` header as `Bearer <token>` for secured endpoints.

