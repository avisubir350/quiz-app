# Quiz Application Setup Guide

## Prerequisites

Before running this application, ensure you have the following installed:

- **Java 17 or higher**
- **Maven 3.6 or higher**
- **Python 3.7 or higher**
- **pip (Python package manager)**

## Quick Start

### 1. Clone the Repository
```bash
git clone <your-repo-url>
cd quiz-app
```

### 2. Test Your Environment
```bash
./test-setup.sh
```

### 3. Start the Application
```bash
# Start all services at once
./start-all.sh

# OR start services individually:
# Terminal 1: Java API
./start-java.sh

# Terminal 2: Python Service
./start-python.sh
```

### 4. Access the Application
- Open `frontend/index.html` in your web browser
- Java API: http://localhost:8080
- Python Service: http://localhost:5000
- H2 Database Console: http://localhost:8080/h2-console

## Manual Setup

### Java Service Setup
```bash
# Build and run Java application
mvn clean compile
mvn spring-boot:run
```

### Python Service Setup
```bash
# Navigate to python service directory
cd python-service

# Install dependencies
pip install -r requirements.txt

# Run the service
python result_service.py
```

## Application URLs

- **Frontend**: Open `frontend/index.html` in browser
- **Java API**: http://localhost:8080/api
- **Python Results**: http://localhost:5000/results/{id}
- **Database Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:quizdb`
  - Username: `sa`
  - Password: (leave empty)

## API Endpoints

### Java Spring Boot API (Port 8080)
- `GET /api/quiz` - Get all quiz questions
- `POST /api/submit` - Submit quiz answers
- `GET /api/submission/{id}` - Get submission details

### Python Flask Service (Port 5000)
- `POST /process-result` - Process quiz results
- `GET /results/{id}` - Display result page
- `GET /api/results/{id}` - Get result data as JSON

## Troubleshooting

### Port Already in Use
If you get port conflicts:
```bash
# Check what's using the ports
lsof -i :8080
lsof -i :5000

# Kill processes if needed
kill -9 <PID>
```

### Java Issues
- Ensure JAVA_HOME is set correctly
- Check Java version: `java -version`
- Verify Maven installation: `mvn -version`

### Python Issues
- Check Python version: `python --version` or `python3 --version`
- Install pip if missing: `python -m ensurepip --upgrade`
- Use virtual environment if needed:
  ```bash
  python -m venv venv
  source venv/bin/activate  # On Windows: venv\Scripts\activate
  pip install -r python-service/requirements.txt
  ```

### Database Issues
- H2 database is in-memory and resets on restart
- Check database console at http://localhost:8080/h2-console
- Python service uses SQLite file database

## Development

### Adding New Questions
Edit `src/main/java/com/example/quizapp/config/DataInitializer.java` to add more questions.

### Modifying Frontend
- Edit `frontend/index.html` for structure
- Edit `frontend/styles.css` for styling
- Edit `frontend/script.js` for functionality

### Extending Python Service
- Modify `python-service/result_service.py`
- Add new result processing features
- Customize the result page template

## Architecture

This is a three-tier microservices application:
- **Presentation Tier**: HTML/CSS/JavaScript frontend
- **Application Tier**: Java Spring Boot API
- **Data Tier**: H2 database + Python Flask service with SQLite

For detailed architecture information, see `ARCHITECTURE.md`.
