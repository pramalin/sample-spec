# Research: ContactHub Application

## Technical Decisions

### Decision 1: Frontend Stack - Vite + Vanilla JS/HTML/CSS
- **Decision**: Use Vite 5 as the build tool with vanilla HTML, CSS, and JavaScript
- **Rationale**: Vite provides fast dev server and optimized production builds while allowing vanilla JS for minimal dependencies
- **Alternatives considered**: 
  - React/Vue/Angular - rejected for requiring additional libraries
  - Plain HTML without build tool - rejected for lack of optimization and hot reload

### Decision 2: Backend Stack - Java 25 + Spring Boot 4
- **Decision**: Java 25 with Spring Boot 4 using functional programming patterns
- **Rationale**: Spring Boot provides robust framework while Java 25 supports modern features including records, pattern matching, and virtual threads
- **Alternatives considered**:
  - Kotlin + Spring - rejected in favor of Java 25 for consistency
  - Scala 3 - rejected as primary requirement was Java

### Decision 3: Functional Programming in Java
- **Decision**: Use Algebraic Data Types (records, sealed classes), pure functions, and functional interfaces
- **Rationale**: ADTs provide type safety and exhaustive pattern matching; pure functions enable testability
- **Implementation**:
  - Use Java records for immutable data
  - Use sealed classes for sum types
  - Use functional interfaces for pure functions
  - Use Stream API for collection operations

### Decision 4: Database - PostgreSQL 15
- **Decision**: PostgreSQL 15 for relational data storage
- **Rationale**: PostgreSQL provides robust ACID compliance, JSON support, and excellent performance
- **Alternatives considered**:
  - MySQL - rejected for less feature-rich JSON support
  - SQLite - rejected for production use limitations

### Decision 5: Docker Images for All Tiers
- **Decision**: Create custom Docker images for frontend, backend, and database
- **Rationale**: Custom images allow tailored configuration and easier local development
- **Implementation**:
  - Frontend: Node.js 20 + Nginx for production serving
  - Backend: Eclipse Temurin Java 25 runtime
  - Database: PostgreSQL 15 with initialization scripts

### Decision 6: Docker Compose for Local Development
- **Decision**: Use docker-compose to orchestrate all three tiers
- **Rationale**: Simplifies local development with single command startup
- **Implementation**:
  - Frontend service on port 3000
  - Backend service on port 8080
  - Database service on port 5432

## API Design Patterns

### REST API Endpoints
- `GET /api/contacts` - List all contacts with pagination
- `POST /api/contacts` - Create new contact
- `GET /api/contacts/{id}` - Get contact by ID
- `PUT /api/contacts/{id}` - Update contact
- `DELETE /api/contacts/{id}` - Delete contact
- `GET /api/contacts/search?q={query}` - Search contacts
- `GET /api/categories` - List all categories
- `POST /api/categories` - Create category
- `GET /api/tags` - List all tags
- `POST /api/tags` - Create tag

## Data Model Design

### Entities
- **Contact**: id, name, phone, email, notes, categoryIds, tagIds, createdAt, updatedAt
- **Category**: id, name, color, createdAt
- **Tag**: id, name, createdAt

### Relationships
- Contact to Category: Many-to-Many
- Contact to Tag: Many-to-Many

## Docker Configuration

### Frontend Dockerfile
- Base: node:20-alpine for build, nginx:alpine for serving
- Build stage compiles Vite project
- Production stage serves static files via Nginx

### Backend Dockerfile
- Base: eclipse-temurin:25-jre-alpine
- Multi-stage build for JAR optimization
- Expose port 8080

### Database Configuration
- PostgreSQL 15 with volume for data persistence
- Initialization script for schema creation
- Environment variables for configuration

## Best Practices Applied

1. **Code Quality**: Static analysis with Checkstyle, Spotbugs
2. **Testing**: JUnit 5 for backend, Jest for frontend, Cypress for E2E
3. **Performance**: Virtual threads in Java 25, connection pooling
4. **Security**: Input validation, parameterized queries, HTTPS in production
