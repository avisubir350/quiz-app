#!/bin/bash

echo "Starting Python Flask Service..."

# Navigate to python service directory
cd python-service

# Install dependencies
echo "Installing Python dependencies..."
pip install -r requirements.txt

# Start the Flask application
echo "Starting Python service on port 5000..."
python result_service.py
