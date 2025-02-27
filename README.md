# Linktree alike - Backend - Spring Boot Application

## Overview
This project is a **backend system for a platform similar to Linktr.ee or Bento.me**, built using **Java Spring Boot**. It includes user authentication, referral tracking, secure session management, and performance optimizations. The system is designed to be **secure, scalable, and efficient** while providing essential features such as **user registration, login, referral system, password management, and API endpoints**.

## Features
### 1. **User Registration & Authentication**
- Users can **register** using **email, username, and password**.
- **Validation** for email format, password strength, and duplicate usernames/emails.
- **Login system** using email/username and password.
- **JWT-based authentication** for secure user sessions.
- Password reset system with **email verification and token expiration**.

### 2. **Referral System**
- Each registered user receives a **unique referral link** (e.g., `https://yourdomain.com/register?referral=USER_ID`).
- The system **tracks referrals** and counts successful sign-ups.
- Optionally, referrers **earn rewards** (such as credits or premium features) based on successful referrals.

### 3. **Password Management**
- Secure password storage using **bcrypt hashing**.
- Password recovery through **secure tokens** sent via email.

### 4. **API Endpoints**
- `POST /api/register` - Register a new user (email, username, password, referral code).
- `POST /api/login` - Authenticate user and return a JWT token.
- `POST /api/forgot-password` - Handle password recovery requests.
- `GET /api/referrals` - Retrieve the list of users referred by the logged-in user.
- `GET /api/referral-stats` - Retrieve statistics on the referral system.

### 5. **Data Validation & Error Handling**
- Validates all inputs (**email format, password length, referral code validity**).
- Provides **detailed error messages** (e.g., "Email already in use", "Invalid referral code").
- Uses appropriate **HTTP status codes** for handling errors.

### 6. **Database Design**
The system is built using **Spring Data JPA with a MySQL database**, and includes the following tables:
- **Users Table** (`id`, `username`, `email`, `password_hash`, `referral_code`, `referred_by`, `created_at`)
- **Referrals Table** (`referrer_id`, `referred_user_id`, `date_referred`, `status`)
- **Rewards Table** (Optional) to track referrer incentives

### 7. **Security**
- **Prevention of common attacks**:
  - **SQL Injection**: Uses parameterized queries in JPA.
  - **Cross-Site Scripting (XSS)**: Uses HttpOnly cookies for JWT storage.
  - **Cross-Site Request Forgery (CSRF)**: Disabled for API endpoints that require authentication.
- **Rate Limiting**: Prevents brute-force attacks on login and registration endpoints.
- **JWT Tokens**: Securely stored in HttpOnly cookies to prevent XSS vulnerabilities.

### 8. **Session Management**
- Uses **JWT for authentication**, ensuring stateless and secure user sessions.
- Stores tokens in **HttpOnly cookies**, making them inaccessible to JavaScript.

### 9. **Performance & Scalability**
- Optimized for handling **large numbers of concurrent users**.
- **Caching** implemented for frequently accessed data (e.g., user details, referral stats).
- Supports **horizontal scaling and load balancing** for future growth.

### 10. **Testing**
- **Unit and integration tests** written for API endpoints (**registration, login, and referral logic**).
- **Edge case testing**:
  - Invalid referral codes
  - Users trying to refer themselves
  - Expired referral tokens
- Ensures **proper input validation** and **error handling**.

## **Technology Stack**
- **Java 17**
- **Spring Boot** (REST API, Security, JPA, Validation)
- **Spring Data JPA** (Persistence Layer)
- **JWT (JSON Web Tokens)** (Authentication)
- **MySQL** (Database)
- **BCrypt** (Password Hashing)
- **JUnit & Mockito** (Testing Frameworks)

## **Installation & Setup**
### 1. **Clone the repository**
```sh
$ git clone https://github.com/yourusername/linktree-backend-java.git
$ cd linktree-backend-java
```

### 2. **Configure Database**
Update `src/main/resources/application.properties` with your **MySQL database credentials**:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/linktree_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### 3. **Run the Application**
Use Maven to build and start the project:
```sh
$ mvn clean install
$ mvn spring-boot:run
```

### 4. **Test API Endpoints**
Use **Postman** or **cURL**:
```sh
curl -X POST http://localhost:8080/api/register -H "Content-Type: application/json" -d '{"email":"test@example.com", "username":"testUser", "password":"SecurePass1!"}'
```

### 5. **Running Unit Tests**
Execute tests with Maven:
```sh
$ mvn test
```

## **Project Status**
✅ **Completed Features**
- User authentication (registration, login, JWT-based session management)
- Secure referral system (with unique links and referral tracking)
- Password hashing and secure email-based recovery
- RESTful API endpoints with data validation and error handling
- Security best practices (SQL Injection, XSS, CSRF prevention)
- Performance optimization with caching
- Unit & integration testing

🚀 **Planned Improvements**
- Implement a reward system for referrals
- Add OAuth 2.0 support for third-party login (Google, Facebook)
- Enhance scalability with distributed caching (e.g., Redis)

---
### **Acknowledgments**
Special thanks to the open-source **Spring Boot** community and contributors for providing an excellent framework for backend development , I have done this project on my own using this spring documentations form the internet.

