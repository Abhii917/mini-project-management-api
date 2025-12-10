I cannot directly generate and attach a downloadable .pdf file in this chat interface. However, I have formatted the documentation below so you can easily create the PDF yourself.

Option 1: The Quickest Way

Copy the text between the lines below.

Paste it into Microsoft Word or Google Docs.

Save/Export as PDF.

Mini Project Management API - Documentation
1. Setup Instructions
   Prerequisites

Java: JDK 17 or higher

Build Tool: Maven 3.6+

How to Run the Application

Open a terminal in the project root directory.

Run the application using Maven:

Bash

mvn spring-boot:run
The server will start at http://localhost:8080.

Database Access (H2 Console) The application uses an in-memory database. You can view the data while the app is running.

URL: http://localhost:8080/h2-console

Driver Class: org.h2.Driver

JDBC URL: jdbc:h2:mem:pmdb

User Name: sa

Password: password

2. Database Schema
   The database consists of three relational tables.

Table: _user

id (BigInt, PK): Unique user identifier.

email (Varchar): User email (Must be unique).

password (Varchar): BCrypt encrypted password.

Table: project

id (BigInt, PK): Unique project identifier.

name (Varchar): Name of the project.

description (Varchar): Short description.

created_at: Timestamp of creation.

user_id (BigInt, FK): Foreign key linking to the User who owns the project.

Table: task

id (BigInt, PK): Unique task identifier.

title (Varchar): Task title.

description (Varchar): Task details.

status (Enum): PENDING, IN_PROGRESS, COMPLETED.

priority (Enum): LOW, MEDIUM, HIGH.

due_date: Deadline timestamp.

project_id (BigInt, FK): Foreign key linking to the Project.

3. API Endpoints
   Base URL: http://localhost:8080/api

Authentication | Method | Endpoint | Description | Auth Required | | :--- | :--- | :--- | :--- | | POST | /auth/register | Register a new user | No | | POST | /auth/login | Login and receive JWT Token | No |

Projects | Method | Endpoint | Description | Auth Required | | :--- | :--- | :--- | :--- | | POST | /projects | Create a new project | Yes | | GET | /projects | List all projects belonging to the user | Yes | | GET | /projects/{id} | Get details of a specific project | Yes | | PUT | /projects/{id} | Update project details | Yes | | DELETE | /projects/{id} | Delete a project | Yes |

Tasks | Method | Endpoint | Description | Auth Required | | :--- | :--- | :--- | :--- | | POST | /projects/{id}/tasks | Create a new task inside a project | Yes | | GET | /projects/{id}/tasks | List all tasks for a specific project | Yes | | PUT | /tasks/{id} | Update task (status, priority, etc.) | Yes | | DELETE | /tasks/{id} | Delete a task | Yes | | GET | /tasks/search | Search tasks by keyword (?keyword=xyz) | Yes |