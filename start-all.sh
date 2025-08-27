#!/bin/bash

echo "Starting Three-Tier Quiz Application..."

# Function to check if port is available
check_port() {
    if lsof -Pi :$1 -sTCP:LISTEN -t >/dev/null ; then
        echo "Port $1 is already in use"
        return 1
    else
        return 0
    fi
}

# Check if required ports are available
if ! check_port 8080; then
    echo "Java application port (8080) is in use. Please stop the service or use a different port."
    exit 1
fi

if ! check_port 5000; then
    echo "Python service port (5000) is in use. Please stop the service or use a different port."
    exit 1
fi

# Start Python service in background
echo "Starting Python Flask service..."
cd python-service
pip install -r requirements.txt > /dev/null 2>&1
python result_service.py &
PYTHON_PID=$!
cd ..

# Wait for Python service to start
sleep 3

# Start Java application in background
echo "Starting Java Spring Boot application..."
mvn clean compile > /dev/null 2>&1
mvn spring-boot:run &
JAVA_PID=$!

# Wait for Java application to start
sleep 10

echo ""
echo "🎉 Application started successfully!"
echo ""
echo "Services running:"
echo "- Java API: http://localhost:8080"
echo "- Python Service: http://localhost:5000"
echo "- Frontend: Open frontend/index.html in your browser"
echo ""
echo "To stop all services, press Ctrl+C"
echo ""

# Function to cleanup on exit
cleanup() {
    echo ""
    echo "Stopping services..."
    kill $PYTHON_PID 2>/dev/null
    kill $JAVA_PID 2>/dev/null
    echo "Services stopped."
    exit 0
}

# Set trap to cleanup on script exit
trap cleanup SIGINT SIGTERM

# Keep script running
wait
