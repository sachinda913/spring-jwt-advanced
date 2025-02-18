# 🔐 Spring Boot Advanced JWT Authentication

A Advanced Spring Boot application implementing **JWT-Advanced authentication**. It provides user Authentication and Authorization, Refresh & Access token, Redis token blacklist and secured API access using JSON Web Tokens (JWT).

---

## 📌 Features

✅ **User Authentication** – Register, login, and secure endpoints using JWT.  
✅ **Spring Security Integration** – Role-based access control (RBAC).  
✅ **JWT Token Handling** – Generate, validate, and refresh tokens.  
✅ **Role-Based Authorization** – Users and Admins have different permissions.                                  
✅ **Token Blacklist** – Add expire tokens to redis token cache.  
✅ **RESTful API** – Secure API endpoints with authentication.  

---

## 🛠️ Tech Stack

- **Backend:** Spring Boot, Spring Security, Spring Data JPA  
- **Authentication:** JWT (JSON Web Token)  
- **Database:** MySQL  
- **Tools:** Maven, Lombok, Redis

---

## 🚀 Project Setup & Installation

### Clone the Repository
```sh
git clone https://github.com/sachinda913/spring-jwt-advanced.git
```

### Configure the Database
Modify `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/spring_security_jwt_advanced
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.data.redis.host=localhost
spring.data.redis.port=6379
```

### Run the Application
```sh
mvn clean and install
mvn spring-boot:run
```

---

## 🔑 API Endpoints & Usage

### Authentication APIs

| HTTP Method | Endpoint           | Description |
|------------|-------------------|-------------|
| `POST` | `/userController/create-use` | Register a new user |
| `POST` | `/userController/login-user` | Authenticate and get a JWT token |
| `POST` | `/userController/refresh-token` | Get new Refresh & Access token when token expired|
| `POST` | `/userController/log-out` | Log Out User and add token to blacklist |
| `POST` | `/userController/check-backlist` | check token is blacklist successfully |
| `POST` | `/userController/post-test` | Create test data |
| `GET`  | `/userController/get-test` | Check data retrieve |
| `GET`  | `/redisController/redis-test` | Check redis is working |

### Protected APIs (Require JWT)

| HTTP Method | Endpoint | Description |
|------------|---------|-------------|
| `GET` | `/api/users/profile` | Get user details |
| `GET` | `/api/admin/dashboard` | Admin-only access |

### How to Test JWT Authentication?

1. **Register a new user:**
   ```json
   POST /api/auth/register  
   {
     "username": "user1",
     "password": "password123"
   }
   ```

2. **Login to get a JWT Token:**
   ```json
   POST /api/auth/login  
   {
     "username": "user1",
     "password": "password123"
   }
   ```
   ✅ **Response:**
   ```json
   {
     "accessToken": "eyJhbGciOiJIUzI1...",
     "refreshToken": "eyJhbGciOiJIUzI2..."
   }
   ```

3. **Access Protected Endpoint:**
   Add the JWT token in the `Authorization` header:
   ```http
   GET /api/users/profile  
   Authorization: Bearer <JWT_TOKEN>
   ```

---

## 🔒 Security & JWT Configuration

- The JWT is generated using a **secret key** (`application.properties`):
  ```properties
  jwt.secret=your_secret_key
  jwt.expiration=3600000 # 1 hour in milliseconds
  ```
- **Spring Security Configuration (`SecurityConfig.java`)**
  - Defines authentication and authorization rules.  
  - Allows `/api/auth/**` without authentication but secures other endpoints.  

---

## 🔗 Contact & Support
For any issues, feel free to open an issue on GitHub or reach out via email at `sachinda.nirosh@gmail.com`.  

---

