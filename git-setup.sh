#!/bin/bash

echo "🚀 Git Repository Setup for Quiz Application"
echo "============================================="

# Check if git is installed
if ! command -v git &> /dev/null; then
    echo "❌ Git is not installed. Please install Git first."
    exit 1
fi

# Initialize git repository if not already initialized
if [ ! -d ".git" ]; then
    echo "📁 Initializing Git repository..."
    git init
    echo "✅ Git repository initialized"
else
    echo "📁 Git repository already exists"
fi

# Add all files to staging
echo "📦 Adding files to Git..."
git add .

# Show status
echo "📊 Git status:"
git status

# Create initial commit
echo ""
echo "💾 Creating initial commit..."
read -p "Enter commit message (or press Enter for default): " commit_message

if [ -z "$commit_message" ]; then
    commit_message="Initial commit: Three-tier quiz application with Java backend and Python result service"
fi

git commit -m "$commit_message"

echo ""
echo "🎉 Repository setup complete!"
echo ""
echo "📋 Next steps:"
echo "1. Create a repository on GitHub/GitLab/Bitbucket"
echo "2. Add remote origin:"
echo "   git remote add origin <your-repo-url>"
echo "3. Push to remote repository:"
echo "   git push -u origin main"
echo ""
echo "📁 Files ready to push:"
echo "   - Java Spring Boot application"
echo "   - Python Flask result service"
echo "   - Frontend HTML/CSS/JS"
echo "   - Documentation and setup scripts"
echo "   - Git configuration files"
echo ""
echo "🔗 Example commands:"
echo "   git remote add origin https://github.com/yourusername/quiz-app.git"
echo "   git branch -M main"
echo "   git push -u origin main"
