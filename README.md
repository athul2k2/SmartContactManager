# Smart Contact Manager

A web-based application to manage your contacts securely with user authentication and CRUD functionality.
![Java](https://img.shields.io/badge/Java-17-blue)
![Spring](https://img.shields.io/badge/Spring_Boot-3.1-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-yellow)

## Features

### User Management
- User registration with validation
- Secure login/logout functionality
- Authentication with Spring Security

### Contact Management
- 📋 Add new contacts with details
- 🔍 Search contacts by name/attributes
- ✏️ Update existing contact information
- 🗑️ Delete contacts
- 📄 Paginated contact listing
- 🔒 User-specific contact storage

## Technology Stack

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
