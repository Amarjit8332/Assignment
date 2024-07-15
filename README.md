Customer Management System
Overview
The Customer Management System is a web-based application that allows users to manage customer information,
including creating, reading, updating, and deleting customer records.
The system uses Spring Boot and Spring Security to provide a secure and scalable solution.

Features

User authentication and authorization using Spring Security
Customer CRUD (Create, Read, Update, Delete) operations
RESTful API for interacting with the system
JWT-based authentication for API requests

Getting Started

Prerequisites

Java 11 or later
Maven 3.6 or later
A relational database (e.g. MySQL)

Building and Running the Application

Clone the repository: git clone https://github.com/your-username/customer-management-system.git
Navigate to the project directory: cd customer-management-system
Build the project: mvn clean package
Run the application: java -jar target/customer-management-system-0.0.1-SNAPSHOT.jar
Database Configuration
Create a database schema for the application (e.g. customer_management_system)
Update the application.properties file with your database credentials:

properties

spring.datasource.url=jdbc:mysql://localhost:3306/customer_management_system
spring.datasource.username=your_username
spring.datasource.password=your_password

API Endpoints

POST /api/authenticate: Authenticate a user and receive a JWT token
GET /api/customers: Retrieve a list of all customers
GET /api/customers/{id}: Retrieve a single customer by ID
POST /api/customers: Create a new customer
PUT /api/customers/{id}: Update an existing customer
DELETE /api/customers/{id}: Delete a customer

Authentication

Use the POST /api/authenticate endpoint to authenticate a user and receive a JWT token
Include the JWT token in the Authorization header for subsequent API requests

Troubleshooting

Verify that the database connection is configured correctly
