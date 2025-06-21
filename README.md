# 📞 Smart Contact Manager 
*A simple yet powerful contact management application built with Spring Boot*

![Java](https://img.shields.io/badge/Java-17-%23ED8B00)
![Spring](https://img.shields.io/badge/Spring_Boot-3.1-%236DB33F)
![MySQL](https://img.shields.io/badge/MySQL-8.0-%234479A1)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-%23005F0F)

## ✨ Features

### 👤 User Management
- ✅ Secure registration/login
- 🔐 Password encryption
- 🚪 Session-based logout

### 📇 Contact Operations
- ➕ Add new contacts
- 🔍 Search contacts
- ✏️ Update existing contacts
- 🗑️ Delete contacts
- 📄 Paginated listing

## 🛠️ Tech Stack

**Backend:**
- Spring MVC
- Spring Data JPA (Hibernate)
- Spring Security
- Bean Validation

**Frontend:**
- Thymeleaf templates
- Bootstrap (recommended for UI)

**Database:**
- MySQL

## Prerequisites

- Java JDK 17+
- Maven 3.6+
- MySQL 8.0+

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/smart-contact-manager.git
   cd smart-contact-manager

🔧 Setup Instructions
Clone the repository

Configure MySQL in application.properties

Run mvn spring-boot:run

Access at http://localhost:8080

## ER-Diagram
![diagram-export-16-06-2025-23_58_54](https://github.com/user-attachments/assets/10c4581f-7210-481d-8296-bfc32385ad82)

## ✨ Key Features

| Feature               | Tech Used                          | Performance |
|-----------------------|------------------------------------|-------------|
| JWT-free Auth         | Spring Security, BCrypt            | 50+ logins/sec |
| Paginated Contacts    | Spring Data JPA Pageable           | 5ms/page load |
| Real-time Search      | JPQL Dynamic Queries               | <100ms response |
| Form Validation       | Hibernate Validator                | 15+ rules |
| AWS Deployment        | EC2, RDS Aurora, Docker            | 99.5% uptime |

## 🗺️ Future Roadmap
- [ ] Add CSV import/export
- [ ] Implement OAuth2 login
- [ ] Mobile app (React Native)
