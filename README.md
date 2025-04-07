# Spring Boot Blog Application

A full-stack Blog Management Application built using **Spring Boot** and **Thymeleaf**.
This app allows users to register, log in, create, view, update, and delete blog posts.
Only the blog author can update or delete their blogs. Public users can view all blogs without logging in.

---

## Features

- User Authentication (Register / Login / Logout)
- Create Blog Posts (only when logged in)
- View All Blog Posts (publicly accessible)
- Edit & Delete Blog Posts (only by the author)
- Form Validations for required fields (title, content)
- Full CRUD Operations
- SQL Database Integration (MySQL/H2)

---

## Tech Stack

### Backend:
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate

### Frontend:
- Thymeleaf
- HTML5, CSS3, Bootstrap 5
- JavaScript (optional for enhancements)

### Database:
- MySQL

---

## Project Structure

src/main/java/
  --com.example.blog/
    --BlogApplication.java
    --com.example.blog.controller/
      --BlogController.java
      --UserController.java
    --com.example.blog.model/
      --BlogModel.java
      --UserModel.java
    --com.example.blog.repository/
      --BlogRepository.java         //Interface
      --UserRepository.java         //Interface
    --com.example.blog.service/
      --BlogService.java
      --UserService.java
src/main/resources/
  static/
    --css files
  templates/
    --HTML files
  application.properties

  
---

## How to Run

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/springboot-blog-app.git
   cd springboot-blog-app

2. Configure the Database

Update application.properties with your MySQL DB credentials.
OR use H2 in-memory database for testing.

3. Build and Run
   
./mvnw spring-boot:run

4. Access the App

Visit: http://localhost:8080/

---

Authentication Flow

Users must register and log in to create or manage their own blog posts.
Blogs are publicly viewable, even without login.
Only the author of a blog can update or delete it.

---

Validations

Blog Title and Content are required.
User registration requires unique username and password.

---

## Author

Chowdavaram Susmithasri
Email:sushmareddy665@gmail.com
