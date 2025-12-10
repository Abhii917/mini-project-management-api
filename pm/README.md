# Mini Project Management API 🚀

Welcome to the **Mini Project Management API**. This is a RESTful backend application designed to help users manage projects and their associated tasks efficiently. 

It provides a secure and scalable foundation for project tracking, featuring User Authentication (JWT), comprehensive CRUD operations for Projects and Tasks, and search capabilities.

---

## 🛠️ Tech Stack
This project was built using the following technologies:
* **Java 17**
* **Spring Boot 3.x** (Web, Data JPA, Security, Validation)
* **H2 Database** (In-memory database for rapid testing)
* **JWT (JSON Web Tokens)** for secure authentication
* **Maven** for dependency management

---

## ⚙️ Getting Started

Follow these instructions to get the project up and running on your local machine.

### Prerequisites
Ensure you have the following installed:
* **Java JDK 17** or higher
* **Maven 3.6+**

### How to Run
1.  **Clone the repository** (or download the source code).
2.  Open your terminal in the project root directory.
3.  Run the application using the Maven wrapper:
    ```bash
    mvn spring-boot:run
    ```
4.  Once started, the server will be live at: `http://localhost:8080`

---

## 🗄️ Database & Schema

The application uses an **H2 In-Memory Database**, meaning you don't need to install MySQL or PostgreSQL to run it. The database is created automatically when the app starts.

### Accessing the Database Console
You can view the live data tables while the application is running:
* **URL:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
* **Driver Class:** `org.h2.Driver`
* **JDBC URL:** `jdbc:h2:mem:pmdb`
* **User Name:** `sa`
* **Password:** `password`

### Database Structure
The system consists of three main tables:

**1. Table: `_user`**
Stores user credentials for authentication.
* `id` (PK): Unique identifier.
* `email`: User's unique email address.
* `password`: Securely encrypted password (BCrypt).

**2. Table: `project`**
Represents a project created by a user.
* `id` (PK): Unique project identifier.
* `name`: The name of the project.
* `description`: A brief summary of the project.
* `created_at`: Timestamp of creation.
* `user_id` (FK): Links the project to its owner.

**3. Table: `task`**
Represents individual tasks within a specific project.
* `id` (PK): Unique task identifier.
* `title`: The name of the task.
* `description`: Detailed task information.
* `status`: (PENDING, IN_PROGRESS, COMPLETED).
* `priority`: (LOW, MEDIUM, HIGH).
* `due_date`: Deadline for the task.
* `project_id` (FK): Links the task to a specific Project.

---

## 🔌 API Endpoints

**Base URL:** `http://localhost:8080/api`

### 🔐 Authentication
*No authentication required for these endpoints.*

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **POST** | `/auth/register` | Register a new user account |
| **POST** | `/auth/login` | Login with credentials to receive a **JWT Token** |

### 📂 Projects
*Requires `Authorization: Bearer <token>` header.*

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **POST** | `/projects` | Create a new project |
| **GET** | `/projects` | View all projects created by the logged-in user |
| **GET** | `/projects/{id}` | Get details of a specific project |
| **PUT** | `/projects/{id}` | Update a project's name or description |
| **DELETE** | `/projects/{id}` | Delete a project (and all its tasks) |

### ✅ Tasks
*Requires `Authorization: Bearer <token>` header.*

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **POST** | `/projects/{id}/tasks` | Add a new task to a specific project |
| **GET** | `/projects/{id}/tasks` | View all tasks within a project |
| **PUT** | `/tasks/{id}` | Update task details (Status, Priority, Due Date) |
| **DELETE** | `/tasks/{id}` | Remove a task |
| **GET** | `/tasks/search` | Search tasks by keyword (e.g., `?keyword=design`) |

---

### 📝 Note on Security
This API uses **Stateless JWT Authentication**. 
1.  First, register and login via the Auth endpoints.
2.  Copy the `token` received in the login response.
3.  Include this token in the `Authorization` header (`Bearer <your-token>`) for all subsequent Project and Task requests.
