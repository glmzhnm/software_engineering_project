# software_engineering_project
Student: Galymzhan Bazarbaev. University: Narxoz University. Specialty: Digital Engineering. Discipline: Software Engineering

Project Overview
FinalSpring is a RESTful backend application designed to manage tourism data. It provides functionality for users to browse countries, cities, and tourist attractions. The system implements Role-Based Access Control (RBAC), ensuring that Administrators have management privileges while standard users are restricted to viewing content.

The project demonstrates the implementation of a 3-Tier Architecture using Spring Boot, Spring Security, and PostgreSQL.

Technology Stack
Language: Java 17

Framework: Spring Boot 3.4.0

Build Tool: Gradle

Database: PostgreSQL (v14+)

ORM: Hibernate / Spring Data JPA

Security: Spring Security (Basic Auth & Sessions, BCrypt Encryption)

Utilities: Lombok, MapStruct

Testing: JUnit 5, Mockito

System Architecture
The project follows the standard N-Tier Architecture:

Presentation Layer (Controllers): Handles HTTP requests (REST API) and maps JSON data to Data Transfer Objects (DTOs).

Business Logic Layer (Services): Contains the core application logic, validation rules, and transaction management.

Data Access Layer (Repositories): Interfaces with the PostgreSQL database using Spring Data JPA.

Database Layer: Relational data storage.

Database Schema (Entity Relationships)

Nation/Country: Parent entity.

City: Linked to a Country (Many-to-One relationship).

TouristAttraction: Linked to a City (Many-to-One relationship).

Users & Roles: Many-to-Many relationship (t_users mapped to t_roles via t_user_roles).

Installation and Launch Instructions
Prerequisites

Java Development Kit (JDK) 17 or higher

PostgreSQL installed and running

Postman (for API testing)

1. Database Configuration

Ensure the PostgreSQL service is active. Configure the database connection in src/main/resources/application.properties:

Properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
2. Database Migrations (SQL Initialization)

The application uses Hibernate ddl-auto for structure generation. However, initial data, security roles, and the administrator account must be loaded manually via SQL console or pgAdmin.

Execute the following SQL commands to initialize the system:

SQL
-- 1. Create Roles
INSERT INTO t_roles (name) VALUES 
('ROLE_ADMIN'), 
('ROLE_USER'), 
('ROLE_MANAGER') 
ON CONFLICT (name) DO NOTHING;

-- 2. Create Admin User (Password is 'g1234')
-- Note: Password is BCrypt encrypted
INSERT INTO t_users (email, password, full_name) 
VALUES ('admin@gmail.com', '$2a$12$55IHszCzphzV/Y8yIvUste1oIDMY86gKxpCcKGqTRDiIKefEN2e9y', 'Galymzhan Admin');

-- 3. Assign Admin Role to User
INSERT INTO t_user_roles (user_id, role_id) 
VALUES (
    (SELECT id FROM t_users WHERE email = 'admin@gmail.com'),
    (SELECT id FROM t_roles WHERE name = 'ROLE_ADMIN')
);
3. Launching the Application

Open the terminal in the project root directory and execute the following command:

Mac/Linux:

Bash
./gradlew bootRun
Windows:

Bash
gradlew.bat bootRun
The server will start at: http://localhost:8080

API Documentation and Postman Collection
The project includes a Postman collection to test all endpoints (Auth, Countries, Cities, Attractions).

Usage Instructions:

Locate the file FinalSpring.postman_collection.json in the root folder of this repository.

Open Postman.

Select Import and upload the file.

Authorization: The API uses Basic Auth (or JSESSIONID cookies).

Username: admin@gmail.com

Password: g1234

Key Endpoints

Method	Endpoint	Description	Auth Required
POST	/auth/login	Log in to the system	No
GET	/country	Get all countries	Yes
POST	/country	Add a new country	Yes (Admin)
GET	/cities	Get all cities	Yes
POST	/attractions	Add a new attraction	Yes (Admin)
Testing
To run unit tests:

Bash
./gradlew test
