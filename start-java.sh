#!/bin/bash

echo "Starting Java Spring Boot Application..."
echo "Building the application..."

# Build the application
mvn clean compile

# Start the Spring Boot application
echo "Starting server on port 8080..."
mvn spring-boot:run
