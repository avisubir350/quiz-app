#!/bin/bash

echo "Testing Quiz Application Setup..."
echo "================================="

# Check if Java is installed
echo "Checking Java installation..."
if command -v java &> /dev/null; then
    java -version
    echo "✅ Java is installed"
else
    echo "❌ Java is not installed. Please install Java 17 or higher."
    exit 1
fi

# Check if Maven is installed
echo ""
echo "Checking Maven installation..."
if command -v mvn &> /dev/null; then
    mvn -version | head -1
    echo "✅ Maven is installed"
else
    echo "❌ Maven is not installed. Please install Maven."
    exit 1
fi

# Check if Python is installed
echo ""
echo "Checking Python installation..."
if command -v python3 &> /dev/null; then
    python3 --version
    echo "✅ Python is installed"
elif command -v python &> /dev/null; then
    python --version
    echo "✅ Python is installed"
else
    echo "❌ Python is not installed. Please install Python 3.7 or higher."
    exit 1
fi

# Check if pip is installed
echo ""
echo "Checking pip installation..."
if command -v pip3 &> /dev/null; then
    pip3 --version
    echo "✅ pip is installed"
elif command -v pip &> /dev/null; then
    pip --version
    echo "✅ pip is installed"
else
    echo "❌ pip is not installed. Please install pip."
    exit 1
fi

# Check project structure
echo ""
echo "Checking project structure..."
required_files=(
    "pom.xml"
    "src/main/java/com/example/quizapp/QuizApplication.java"
    "python-service/result_service.py"
    "python-service/requirements.txt"
    "frontend/index.html"
    "frontend/styles.css"
    "frontend/script.js"
)

for file in "${required_files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file exists"
    else
        echo "❌ $file is missing"
        exit 1
    fi
done

echo ""
echo "🎉 All checks passed! Your quiz application is ready to run."
echo ""
echo "To start the application:"
echo "1. Run: ./start-all.sh"
echo "2. Open frontend/index.html in your browser"
echo ""
echo "Or start services individually:"
echo "- Java API: ./start-java.sh"
echo "- Python Service: ./start-python.sh"
