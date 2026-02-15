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
├── backend/               # Spring Boot backend
│   ├── src/
│   │   └── main/
│   │       ├── java/      # Java source code
│   │       └── resources/ # Configuration
│   ├── Dockerfile         # Backend Docker image
│   └── pom.xml
└── db/                    # Database scripts
    └── init.sql           # Database initialization
```
