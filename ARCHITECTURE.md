# Three-Tier Quiz Application Architecture

## Overview
This application demonstrates a classic three-tier architecture with Java backend and Python result processing service.

## Architecture Layers

### 1. Presentation Tier (Frontend)
- **Technology**: HTML5, CSS3, JavaScript
- **Location**: `frontend/` directory
- **Responsibilities**:
  - User interface for quiz interaction
  - Form validation and user input handling
  - Communication with application tier via REST APIs
  - Responsive design for multiple devices

### 2. Application Tier (Business Logic)
- **Technology**: Java Spring Boot
- **Port**: 8080
- **Location**: `src/main/java/`
- **Components**:
  - **Controllers**: Handle HTTP requests and responses
  - **Services**: Business logic for quiz management
  - **Models**: Data entities (Question, QuizSubmission)
  - **Repositories**: Data access layer
- **Responsibilities**:
  - Quiz question management
  - Answer validation and scoring
  - REST API endpoints
  - Integration with Python service

### 3. Data Tier (Database)
- **Technology**: H2 In-Memory Database
- **Access**: JPA/Hibernate
- **Tables**:
  - `questions`: Quiz questions and options
  - `quiz_submissions`: User submissions and scores
  - `question_options`: Question options (normalized)
  - `submission_answers`: User answers (normalized)

### 4. Result Processing Service (Python)
- **Technology**: Python Flask
- **Port**: 5000
- **Location**: `python-service/`
- **Database**: SQLite for processed results
- **Responsibilities**:
  - Advanced result processing and analytics
  - Grade calculation and feedback generation
  - HTML result page generation
  - Result data persistence

## Data Flow

1. **User Interaction**: User accesses frontend HTML page
2. **Quiz Loading**: Frontend requests questions from Java API
3. **Answer Submission**: User answers sent to Java backend
4. **Score Calculation**: Java service calculates basic score
5. **Result Processing**: Java sends data to Python service
6. **Advanced Processing**: Python service generates detailed results
7. **Result Display**: User redirected to Python-generated result page

## API Endpoints

### Java Spring Boot API (Port 8080)
- `GET /api/quiz` - Retrieve all quiz questions
- `POST /api/submit` - Submit quiz answers
- `GET /api/submission/{id}` - Get submission details
- `GET /h2-console` - Database console (development)

### Python Flask Service (Port 5000)
- `POST /process-result` - Process quiz results from Java
- `GET /results/{id}` - Display formatted result page
- `GET /api/results/{id}` - Get result data as JSON

## Security Considerations

- CORS enabled for cross-origin requests
- Input validation on both frontend and backend
- SQL injection prevention through JPA
- No sensitive data exposure in APIs

## Scalability Features

- Stateless application design
- Database connection pooling
- RESTful API architecture
- Microservice-ready structure

## Development Features

- Hot reload for development
- In-memory database for easy testing
- Comprehensive error handling
- Responsive UI design
- Keyboard navigation support

## Deployment Architecture

```
[Browser] → [Frontend HTML/CSS/JS]
    ↓
[Java Spring Boot API:8080] → [H2 Database]
    ↓
[Python Flask Service:5000] → [SQLite Database]
    ↓
[Result Page HTML]
```

This architecture provides clear separation of concerns, making the application maintainable, scalable, and easy to extend with additional features.
