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

---

# Research: Scala 3 Tagless Final Backend (New Implementation)

## Technical Decisions

### Decision 1: Tagless Final Pattern with Cats Effect
- **Decision**: Use Scala 3 tagless final pattern with Cats Effect for effect abstraction
- **Rationale**: Tagless final allows writing pure functional code while remaining polymorphic over the effect type; Cats Effect provides well-established IO monad with resource safety; Scala 3's implicit resolution improvements make tagless final more ergonomic
- **Alternatives considered**:
  - ZIO: More feature-rich but steeper learning curve
  - Monix: Good but less community momentum than Cats Effect

### Decision 2: Database Access - Doobie
- **Decision**: Use Doobie for pure functional JDBC access
- **Rationale**: Pure functional JDBC operations via Cats Effect; type-safe query building with connection pooling
- **Alternatives considered**:
  - skunk: Pure functional but less mature than Doobie
  - Quill: Complex macro-based approach, harder to debug

### Decision 3: HTTP Server - Http4s
- **Decision**: Use Http4s for REST API
- **Rationale**: Typeful HTTP server matching tagless final philosophy; built on Cats Effect/Blaze
- **Alternatives considered**:
  - Akka HTTP: Heavy, complex, not purely functional
  - Play: Imperative style, not FP-native

### Decision 4: Property-Based Testing - ScalaCheck
- **Decision**: Use ScalaCheck with ScalaTest integration
- **Rationale**: Standard property-based testing library for Scala; can generate arbitrary instances for domain models
- **Alternatives considered**:
  - Hedgehog: Good but newer, less community resources
  - purecheck: Interesting but less mature

### Decision 5: JSON Serialization - Circe
- **Decision**: Use Circe for JSON
- **Rationale**: Standard JSON library in Scala ecosystem; automatic derivation of codecs; pure functional style

## Comparison: Java vs Scala 3

| Aspect | Java 25/Spring Boot | Scala 3 Tagless Final |
|--------|---------------------|----------------------|
| Type System | Strong, records/sealed | Stronger, enums, union types |
| Effect Handling | Reactive (Project Reactor) | IO Monad (Cats Effect) |
| Testing | JUnit 5, MockMvc | ScalaCheck (PBT), ScalaTest |
| Learning Curve | Lower | Higher |
| Boilerplate | More | Less with metaprogramming |
| Performance | Virtual threads | Fibers (Cats Effect) |

## Shared Components

- PostgreSQL 15 database (shared between implementations)
- Both backends can run on different ports for A/B comparison
- REST API contracts remain compatible
