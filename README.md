# 📝 Journal Entry Backend API

This is a Spring Boot backend REST API for managing **Journal Entries**, built with clean code architecture, proper exception handling, and user-based authentication (using Spring Security & JWT).

---

## 🔧 Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Security (JWT-based auth)
- H2 / MySQL (based on configuration)
- Maven

---

## 🚀 Features

- ✅ User Registration & Login (JWT token based)
- ✅ Create Journal Entry (per user)
- ✅ View all Journal Entries (user-specific)
- ✅ View Single Entry by ID
- ✅ Update Entry
- ✅ Delete Entry
- ✅ Input Validation
- ✅ Global Exception Handling

---

## 🛠️ API Endpoints

### 🔐 Authentication

| Method | Endpoint       | Description           |
|--------|----------------|-----------------------|
| POST   | `/auth/register` | Register new user     |
| POST   | `/auth/login`    | Login and get JWT     |

---

### 📓 Journal Entry (Protected with JWT)

> All endpoints below require `Authorization: Bearer <token>` in the headers.

| Method | Endpoint              | Description               |
|--------|-----------------------|---------------------------|
| GET    | `/api/journals`       | Get all entries (user-specific) |
| GET    | `/api/journals/{id}`  | Get journal by ID         |
| POST   | `/api/journals`       | Create a new journal      |
| PUT    | `/api/journals/{id}`  | Update a journal          |
| DELETE | `/api/journals/{id}`  | Delete a journal          |

---

## 📦 Project Structure

![image](https://github.com/user-attachments/assets/766a6aeb-d9a3-4928-951c-cdc4923ce97f)
