# 🧑‍💼 User Service

A **Spring Boot** application implementing a **3-layer architecture** (Controller, Service, Repository) for managing **Users** and **User Types** with an **H2 in-memory database**. The service provides CRUD operations, search functionality, and basic audit tracking for created entities.

---

## 📌 Features Implemented

1. **Three-Layer Architecture**

   * **Controller Layer** – REST endpoints for user and user type management.
   * **Service Layer** – Business logic in `UsersService` and `UserTypeService` with respective implementations.
   * **Repository Layer** – `CrudRepository`-based repositories for `User` and `UserType`.

2. **Database**

   * Uses **H2** in-memory database.
   * **User Types** are treated as metadata (`PATIENT`, `DOCTOR`) and preloaded via tests.

3. **Entity Details**

   * **User**

     * `fullName`, `email`
     * `userType` (FK to `UserType`)
     * `id`, `userId` (auto-generated)
     * Audit fields: `createdBy`, `createdDate`, `lastModifiedBy`, `lastModifiedDate`
     * `OneToOne` relationship with `UserType`.
   * **UserType**

     * `userTypeId`
     * `type` (string value like `PATIENT`, `DOCTOR`)

4. **Preloading Data**

   * Run integration tests:

     * `testLoadUserTypes` → Creates `PATIENT` & `DOCTOR` user types.
     * `testUserCreationAddition` → Creates one user per type (total 2 users).

5. **REST API Endpoints**

   * **Add User**
     `POST /userservice/users` – Request body maps to `UserEntity`.
   * **List Users**
     `GET /userservice/users`
   * **Get User by ID**
     `GET /userservice/users/{id}`
   * **Update User**
     `PUT /userservice/updateusers/{id}`
   * **Delete User**
     `DELETE /userservice/deleteusers/{id}`
   * **Search Users by Name**
     `POST /userservice/searchusers/` – Request body with `fullName` (supports partial match).

6. **Error Handling**

   * Global exception handler for `ResourceNotFoundException` and generic exceptions.
   * Detailed error responses for improved UX.

7. **Logging**

   * Controller-level logging for exceptions (debug mode supported).

---

## 🚀 How to Run

1. **Build & Run Tests to Preload Data**

   ```bash
   mvn clean install
   ```

   This runs `UserServiceApplicationTests`, creating:

   * User Types (`PATIENT`, `DOCTOR`)
   * 1 user for each user type

2. **Add More Users**

   * First, list user types:

     ```http
     GET /userservice/users
     ```
   * Then use a POST request with the retrieved user type:

     **Sample Payload**

     ```json
     {
       "fullName": "Jim Jimmy",
       "email": "jimmy111@gmail.com",
       "userType": "DOCTOR"
     }
     ```

---

## 🧪 Testing

* **Integration Tests**:

  * `testLoadUserTypes` → Creates base user types
  * `testUserCreationAddition` → Creates sample users
* **Unit Tests**:

  * Limited coverage due to time constraints (`UserServiceTest` has Spring context issues).

---

## ⚠️ Known Limitations

1. Limited test coverage; not all scenarios handled.
2. Exception handling & logging only applied to some endpoints.
3. Some unit tests fail due to Spring environment setup.
4. Added extra `UserType` functionalities (bulk add) without extensive testing.

---

## 📌 Technology Stack

* **Java 17**
* **Spring Boot**
* **Spring Data JPA**
* **H2 Database**
* **JUnit 5**
* **Maven**

---

## 📂 Project Structure

```
src/main/java/com/application/userservice/
│
├── controller
│   └── UserServiceController.java
│
├── entity
│   ├── User.java
│   └── UserType.java
│
├── repository
│   ├── UserRepository.java
│   └── UserTypeRepository.java
│
├── service
│   ├── UsersService.java
│   ├── UserTypeService.java
│   ├── impl
│       ├── UsersServiceImpl.java
│       └── UserTypeServiceImpl.java
│
└── exception
    ├── GlobalExceptionHandler.java
    └── ResourceNotFoundException.java
```

---

## 📜 License

This project is licensed under the MIT License.
