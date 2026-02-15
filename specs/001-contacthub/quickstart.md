# Quickstart: ContactHub

## Prerequisites

- Docker 20.10+
- Docker Compose 2.0+
- Git

## Running the Application

### 1. Clone the repository

```bash
git clone <repository-url>
cd sample-spec
```

### 2. Start the application

From the project root, run:

```bash
docker-compose up -d
```

This will start all three tiers:
- **Frontend**: http://localhost:3000 (Vite dev server)
- **Backend**: http://localhost:8080 (Spring Boot API)
- **Database**: localhost:5432 (PostgreSQL)

### 3. Verify services are running

```bash
# Check container status
docker-compose ps

# Check logs
docker-compose logs -f
```

### 4. Access the application

Open your browser and navigate to:
- **Frontend**: http://localhost:3000
- **API Documentation**: http://localhost:8080/api/swagger-ui.html (if enabled)

## Stopping the Application

```bash
docker-compose down
```

To also remove volumes (database data):

```bash
docker-compose down -v
```

## Development

### Backend Development

The backend runs on Java 25 with Spring Boot 4. Source code is in the `backend/` directory.

```bash
cd backend
./mvnw spring-boot:run
```

### Frontend Development

The frontend uses Vite with vanilla JS/HTML/CSS. Source code is in the `frontend/` directory.

```bash
cd frontend
npm install
npm run dev
```

### Database Access

Connect to PostgreSQL:

```bash
# Using docker-compose exec
docker-compose exec db psql -U contacthub -d contacthub

# Or connect externally
psql -h localhost -p 5432 -U contacthub -d contacthub
```

Default credentials are defined in `docker-compose.yml`.

## Testing

### Backend Tests

```bash
cd backend
./mvnw test
```

### Frontend Tests

```bash
cd frontend
npm test
```

### End-to-End Tests

```bash
cd frontend
npm run cypress
```

## Project Structure

```
sample-spec/
├── docker-compose.yml      # Docker Compose configuration
├── frontend/               # Vite frontend application
│   ├── src/
│   │   ├── components/    # UI components
│   │   ├── pages/         # Page views
│   │   └── services/      # API services
│   ├── Dockerfile         # Frontend Docker image
│   └── package.json
├── backend/               # Spring Boot backend (Java 25)
│   ├── src/
│   │   └── main/
│   │       ├── java/      # Java source code
│   │       └── resources/ # Configuration
│   ├── Dockerfile         # Backend Docker image
│   └── pom.xml
├── scala-backend/         # Scala 3 backend (NEW - tagless final)
│   ├── src/
│   │   └── main/
│   │       └── scala/    # Scala source code
│   │           └── com/
│   │               └── contacthub/
│   │                   ├── domain/    # Domain models & algebras
│   │                   ├── service/   # Business logic
│   │                   ├── repository/ # Data access
│   │                   └── api/        # HTTP routes
│   ├── build.sbt
│   └── project/
└── db/                    # Database scripts
    └── init.sql           # Database initialization
```

---

# Scala 3 Backend Quickstart (New Implementation)

This is a new Scala 3 implementation using tagless final pattern for comparison with the existing Java backend.

## Prerequisites (Scala 3)

- Java 21+ (for Scala 3.5)
- sbt 1.9+
- Docker (for PostgreSQL)

## Running Scala Backend

### 1. Start PostgreSQL

```bash
docker run -d \
  --name contacthub-db \
  -e POSTGRES_USER=contacthub \
  -e POSTGRES_PASSWORD=contacthub \
  -e POSTGRES_DB=contacthub \
  -p 5432:5432 \
  postgres:15
```

### 2. Run the Scala backend

```bash
cd scala-backend
sbt run
```

The Scala backend runs on port 8081 (to avoid conflict with Java backend on 8080).

### 3. Run tests including property-based tests

```bash
cd scala-backend
sbt test
```

To run only property-based tests:

```bash
sbt "testOnly *PropertyBased*"
```

## Project Structure

```
scala-backend/
├── src/
│   ├── main/
│   │   └── scala/com/contacthub/
│   │       ├── domain/           # Case classes and algebras
│   │       ├── service/          # Business logic (tagless final)
│   │       ├── repository/       # Doobie implementations
│   │       ├── api/              # Http4s routes
│   │       └── config/           # Configuration
│   └── test/
│       └── scala/com/contacthub/
│           ├── unit/             # Unit tests
│           ├── property/         # ScalaCheck property tests
│           └── integration/      # Integration tests
├── build.sbt
└── project/
```

## Testing with ScalaCheck

The Scala 3 implementation includes property-based tests:

```scala
// Example property-based test
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object ContactPropertySpec extends Properties("Contact") {
  
  property("contact name is not empty") = forAll { contact: Contact =>
    contact.name.nonEmpty
  }
  
  property("email format is valid when present") = forAll { contact: Contact =>
    contact.email.forall(isValidEmail)
  }
}
```

## Compare Both Backends

Run both backends simultaneously:
- **Java Backend**: http://localhost:8080
- **Scala Backend**: http://localhost:8081

Both connect to the same PostgreSQL database for comparison.
```
