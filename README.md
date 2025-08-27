# Three-Tier Quiz Application

## Architecture Overview
- **Presentation Tier**: HTML/CSS frontend
- **Application Tier**: Java Spring Boot backend
- **Data Tier**: H2 in-memory database
- **Results Processing**: Python Flask service

## Components
1. Java Spring Boot API for quiz management
2. Python Flask service for result processing
3. HTML frontend for user interaction
4. H2 database for data persistence

## Setup Instructions
1. Run Java application: `mvn spring-boot:run`
2. Run Python service: `python result_service.py`
3. Open `frontend/index.html` in browser

## API Endpoints
- GET /api/quiz - Get quiz questions
- POST /api/submit - Submit quiz answers
- GET /results/{id} - Get processed results (Python service)
