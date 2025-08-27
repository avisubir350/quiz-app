// Global variables
let questions = [];
let currentQuestionIndex = 0;
let userAnswers = {};
let userName = '';

// API endpoints
const API_BASE = 'http://localhost:8080/api';
const PYTHON_SERVICE = 'http://localhost:5000';

// Initialize the application
document.addEventListener('DOMContentLoaded', function() {
    // Focus on name input
    document.getElementById('userName').focus();
    
    // Allow Enter key to start quiz
    document.getElementById('userName').addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            startQuiz();
        }
    });
});

// Reset quiz to initial state
function resetQuiz() {
    // Reset global variables
    questions = [];
    currentQuestionIndex = 0;
    userAnswers = {};
    userName = '';
    
    // Reset UI
    document.getElementById('userName').value = '';
    document.getElementById('quizSection').classList.add('hidden');
    document.getElementById('loadingSection').classList.add('hidden');
    document.getElementById('loginSection').classList.remove('hidden');
    
    // Focus on name input
    document.getElementById('userName').focus();
}

// Start the quiz
async function startQuiz() {
    const nameInput = document.getElementById('userName');
    userName = nameInput.value.trim();
    
    if (!userName) {
        alert('Please enter your name to continue.');
        nameInput.focus();
        return;
    }
    
    try {
        // Fetch questions from Java backend
        const response = await fetch(`${API_BASE}/quiz`);
        if (!response.ok) {
            throw new Error('Failed to fetch questions');
        }
        
        questions = await response.json();
        
        if (questions.length === 0) {
            alert('No questions available. Please try again later.');
            return;
        }
        
        // Hide login section and show quiz
        document.getElementById('loginSection').classList.add('hidden');
        document.getElementById('quizSection').classList.remove('hidden');
        
        // Initialize quiz
        currentQuestionIndex = 0;
        userAnswers = {};
        displayQuestion();
        updateProgress();
        
    } catch (error) {
        console.error('Error starting quiz:', error);
        alert('Failed to load quiz. Please check if the server is running.');
    }
}

// Display current question
function displayQuestion() {
    const question = questions[currentQuestionIndex];
    
    // Update question counter
    document.getElementById('questionCounter').textContent = 
        `Question ${currentQuestionIndex + 1} of ${questions.length}`;
    
    // Update question text
    document.getElementById('questionText').textContent = question.questionText;
    
    // Create options
    const optionsContainer = document.getElementById('optionsContainer');
    optionsContainer.innerHTML = '';
    
    question.options.forEach((option, index) => {
        const optionDiv = document.createElement('div');
        optionDiv.className = 'option';
        optionDiv.onclick = () => selectOption(index);
        
        const isSelected = userAnswers[question.id] === index;
        if (isSelected) {
            optionDiv.classList.add('selected');
        }
        
        optionDiv.innerHTML = `
            <input type="radio" name="question_${question.id}" value="${index}" ${isSelected ? 'checked' : ''}>
            ${option}
        `;
        
        optionsContainer.appendChild(optionDiv);
    });
    
    // Update navigation buttons
    updateNavigationButtons();
}

// Select an option
function selectOption(optionIndex) {
    const question = questions[currentQuestionIndex];
    userAnswers[question.id] = optionIndex;
    
    // Update visual selection
    const options = document.querySelectorAll('.option');
    options.forEach((option, index) => {
        option.classList.toggle('selected', index === optionIndex);
        const radio = option.querySelector('input[type="radio"]');
        radio.checked = index === optionIndex;
    });
    
    updateNavigationButtons();
}

// Navigate to previous question
function previousQuestion() {
    if (currentQuestionIndex > 0) {
        currentQuestionIndex--;
        displayQuestion();
        updateProgress();
    }
}

// Navigate to next question
function nextQuestion() {
    if (currentQuestionIndex < questions.length - 1) {
        currentQuestionIndex++;
        displayQuestion();
        updateProgress();
    }
}

// Update navigation buttons
function updateNavigationButtons() {
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const submitBtn = document.getElementById('submitBtn');
    
    // Previous button
    prevBtn.disabled = currentQuestionIndex === 0;
    
    // Next/Submit button logic
    const isLastQuestion = currentQuestionIndex === questions.length - 1;
    const hasAnsweredCurrent = userAnswers.hasOwnProperty(questions[currentQuestionIndex].id);
    
    if (isLastQuestion) {
        nextBtn.classList.add('hidden');
        submitBtn.classList.remove('hidden');
        submitBtn.disabled = !hasAnsweredCurrent;
    } else {
        nextBtn.classList.remove('hidden');
        submitBtn.classList.add('hidden');
        nextBtn.disabled = !hasAnsweredCurrent;
    }
}

// Update progress bar
function updateProgress() {
    const progress = ((currentQuestionIndex + 1) / questions.length) * 100;
    document.getElementById('progressFill').style.width = `${progress}%`;
}

// Submit the quiz
async function submitQuiz() {
    // Check if all questions are answered
    const unansweredQuestions = questions.filter(q => !userAnswers.hasOwnProperty(q.id));
    
    if (unansweredQuestions.length > 0) {
        const proceed = confirm(
            `You have ${unansweredQuestions.length} unanswered question(s). ` +
            'Do you want to submit anyway?'
        );
        if (!proceed) {
            return;
        }
    }
    
    try {
        // Show loading section
        document.getElementById('quizSection').classList.add('hidden');
        document.getElementById('loadingSection').classList.remove('hidden');
        
        // Submit to Java backend
        const response = await fetch(`${API_BASE}/submit`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                userName: userName,
                answers: userAnswers
            })
        });
        
        if (!response.ok) {
            throw new Error('Failed to submit quiz');
        }
        
        const result = await response.json();
        
        // Wait a moment for Python service to process
        setTimeout(() => {
            // Redirect to Python result page
            window.location.href = `${PYTHON_SERVICE}/results/${result.id}`;
        }, 2000);
        
    } catch (error) {
        console.error('Error submitting quiz:', error);
        alert('Failed to submit quiz. Please try again.');
        
        // Show quiz section again
        document.getElementById('loadingSection').classList.add('hidden');
        document.getElementById('quizSection').classList.remove('hidden');
    }
}

// Utility function to get answered questions count
function getAnsweredCount() {
    return Object.keys(userAnswers).length;
}

// Add keyboard navigation
document.addEventListener('keydown', function(e) {
    if (document.getElementById('quizSection').classList.contains('hidden')) {
        return;
    }
    
    switch(e.key) {
        case 'ArrowLeft':
            if (currentQuestionIndex > 0) {
                previousQuestion();
            }
            break;
        case 'ArrowRight':
            if (currentQuestionIndex < questions.length - 1 && 
                userAnswers.hasOwnProperty(questions[currentQuestionIndex].id)) {
                nextQuestion();
            }
            break;
        case '1':
        case '2':
        case '3':
        case '4':
            const optionIndex = parseInt(e.key) - 1;
            const question = questions[currentQuestionIndex];
            if (optionIndex < question.options.length) {
                selectOption(optionIndex);
            }
            break;
    }
});

// Make resetQuiz available globally for external calls
window.resetQuiz = resetQuiz;
