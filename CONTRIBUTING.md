# Contributing to Quiz Application

Thank you for your interest in contributing to the Quiz Application! This document provides guidelines for contributing to this project.

## Getting Started

1. Fork the repository
2. Clone your fork locally
3. Follow the setup instructions in `SETUP.md`
4. Create a new branch for your feature/fix

## Development Workflow

### 1. Setting Up Development Environment
```bash
git clone <your-fork-url>
cd quiz-app
./test-setup.sh
```

### 2. Making Changes

#### Java Backend Changes
- Code is located in `src/main/java/com/example/quizapp/`
- Follow Spring Boot conventions
- Add tests for new functionality
- Ensure code compiles: `mvn clean compile`

#### Python Service Changes
- Code is located in `python-service/`
- Follow PEP 8 style guidelines
- Test your changes locally
- Update requirements.txt if adding dependencies

#### Frontend Changes
- Files are in `frontend/` directory
- Test in multiple browsers
- Ensure responsive design
- Validate HTML/CSS

### 3. Testing Your Changes
```bash
# Test the complete application
./start-all.sh

# Test individual services
./start-java.sh
./start-python.sh
```

## Code Style Guidelines

### Java
- Use Spring Boot conventions
- Follow camelCase naming
- Add JavaDoc for public methods
- Use proper exception handling

### Python
- Follow PEP 8 style guide
- Use descriptive variable names
- Add docstrings for functions
- Handle exceptions appropriately

### JavaScript
- Use modern ES6+ features
- Follow consistent indentation
- Add comments for complex logic
- Ensure cross-browser compatibility

## Commit Guidelines

### Commit Message Format
```
type(scope): description

[optional body]

[optional footer]
```

### Types
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes
- `refactor`: Code refactoring
- `test`: Adding tests
- `chore`: Maintenance tasks

### Examples
```
feat(api): add new quiz category endpoint
fix(frontend): resolve mobile responsive issues
docs(readme): update setup instructions
```

## Pull Request Process

1. **Create a Feature Branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Make Your Changes**
   - Write clean, documented code
   - Add tests if applicable
   - Update documentation

3. **Test Thoroughly**
   - Run the application locally
   - Test all affected functionality
   - Verify no regressions

4. **Commit Your Changes**
   ```bash
   git add .
   git commit -m "feat(scope): your descriptive message"
   ```

5. **Push and Create PR**
   ```bash
   git push origin feature/your-feature-name
   ```
   - Create pull request on GitHub
   - Provide clear description
   - Reference any related issues

## Feature Requests

When suggesting new features:

1. **Check existing issues** to avoid duplicates
2. **Provide clear description** of the feature
3. **Explain the use case** and benefits
4. **Consider implementation complexity**

## Bug Reports

When reporting bugs:

1. **Use the bug report template**
2. **Provide steps to reproduce**
3. **Include error messages/logs**
4. **Specify environment details**
5. **Add screenshots if helpful**

## Areas for Contribution

### High Priority
- [ ] Add user authentication
- [ ] Implement quiz categories
- [ ] Add timer functionality
- [ ] Create admin dashboard
- [ ] Add question difficulty levels

### Medium Priority
- [ ] Improve mobile responsiveness
- [ ] Add more question types
- [ ] Implement quiz sharing
- [ ] Add result analytics
- [ ] Create API documentation

### Low Priority
- [ ] Add dark mode theme
- [ ] Implement quiz bookmarks
- [ ] Add social media sharing
- [ ] Create quiz templates
- [ ] Add multilingual support

## Development Tips

### Java Development
- Use Spring Boot DevTools for hot reload
- Access H2 console at http://localhost:8080/h2-console
- Check application logs for debugging

### Python Development
- Use Flask debug mode during development
- Check SQLite database with DB browser
- Use virtual environment for dependencies

### Frontend Development
- Use browser developer tools
- Test on different screen sizes
- Validate HTML/CSS with online tools

## Questions?

If you have questions about contributing:
- Open an issue with the `question` label
- Check existing documentation
- Review similar implementations

Thank you for contributing to make this project better! 🎉
