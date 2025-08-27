#!/bin/bash

# Docker build script for Quiz Application
set -e

echo "🚀 Building Quiz Application Docker Images..."

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Function to print colored output
print_status() {
    echo -e "${GREEN}[INFO]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
    print_error "Docker is not running. Please start Docker and try again."
    exit 1
fi

# Check if Docker Compose is available
if ! command -v docker-compose > /dev/null 2>&1; then
    print_error "Docker Compose is not installed. Please install Docker Compose and try again."
    exit 1
fi

print_status "Cleaning up existing containers and images..."
docker-compose down --remove-orphans 2>/dev/null || true

# Build all services
print_status "Building all services with Docker Compose..."
docker-compose build --no-cache

# Verify builds
print_status "Verifying built images..."
if docker images | grep -q "quiz-app"; then
    print_status "✅ Java Spring Boot API image built successfully"
else
    print_error "❌ Failed to build Java Spring Boot API image"
    exit 1
fi

if docker images | grep -q "python-service"; then
    print_status "✅ Python Flask service image built successfully"
else
    print_error "❌ Failed to build Python Flask service image"
    exit 1
fi

if docker images | grep -q "frontend"; then
    print_status "✅ Frontend image built successfully"
else
    print_error "❌ Failed to build Frontend image"
    exit 1
fi

print_status "🎉 All Docker images built successfully!"
print_status "To start the application, run: docker-compose up -d"
print_status "To view logs, run: docker-compose logs -f"
print_status "To stop the application, run: docker-compose down"

echo ""
print_status "Application URLs after startup:"
echo "  Frontend: http://localhost"
echo "  Java API: http://localhost:8080"
echo "  Python Service: http://localhost:5000"
echo "  H2 Console: http://localhost:8080/h2-console"
