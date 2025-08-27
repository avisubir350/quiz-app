package com.example.quizapp.service;

import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuizSubmission;
import com.example.quizapp.repository.QuestionRepository;
import com.example.quizapp.repository.QuizSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class QuizService {
    
    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private QuizSubmissionRepository submissionRepository;
    
    @Autowired
    private Environment environment;
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    // Configuration for quiz randomization
    private static final int QUESTIONS_PER_QUIZ = 20; // Number of questions per quiz
    private static final boolean RANDOMIZE_OPTIONS = true; // Whether to randomize answer options
    
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    
    /**
     * Get a randomized set of questions for a quiz session
     * @return List of randomly selected and shuffled questions
     */
    public List<Question> getRandomizedQuiz() {
        List<Question> allQuestions = questionRepository.findAll();
        
        if (allQuestions.size() <= QUESTIONS_PER_QUIZ) {
            // If we have fewer questions than desired, return all shuffled
            Collections.shuffle(allQuestions);
            return allQuestions;
        }
        
        // Randomly select QUESTIONS_PER_QUIZ questions
        Collections.shuffle(allQuestions);
        List<Question> selectedQuestions = allQuestions.subList(0, QUESTIONS_PER_QUIZ);
        
        // Create new Question objects with potentially shuffled options
        List<Question> randomizedQuestions = new ArrayList<>();
        for (Question originalQuestion : selectedQuestions) {
            Question randomizedQuestion = createRandomizedQuestion(originalQuestion);
            randomizedQuestions.add(randomizedQuestion);
        }
        
        return randomizedQuestions;
    }
    
    /**
     * Create a new Question with potentially randomized answer options
     */
    private Question createRandomizedQuestion(Question original) {
        if (!RANDOMIZE_OPTIONS) {
            return original;
        }
        
        List<String> originalOptions = new ArrayList<>(original.getOptions());
        String correctAnswer = originalOptions.get(original.getCorrectAnswer());
        
        // Shuffle the options
        Collections.shuffle(originalOptions);
        
        // Find the new index of the correct answer
        int newCorrectIndex = originalOptions.indexOf(correctAnswer);
        
        // Create new question with shuffled options
        Question randomizedQuestion = new Question(
            original.getQuestionText(),
            originalOptions,
            newCorrectIndex
        );
        
        // Set the ID to maintain consistency for submission
        randomizedQuestion.setId(original.getId());
        
        return randomizedQuestion;
    }
    
    /**
     * Get a question by ID (used for answer validation during submission)
     */
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }
    
    public QuizSubmission submitQuiz(String userName, Map<Long, Integer> answers) {
        // Calculate score using original questions (not randomized ones)
        List<Question> allQuestions = questionRepository.findAll();
        int score = 0;
        int totalQuestions = 0;
        
        // Only count questions that were actually answered
        for (Map.Entry<Long, Integer> entry : answers.entrySet()) {
            Long questionId = entry.getKey();
            Integer userAnswer = entry.getValue();
            
            // Find the original question
            Question originalQuestion = getQuestionById(questionId);
            if (originalQuestion != null) {
                totalQuestions++;
                if (userAnswer != null && userAnswer.equals(originalQuestion.getCorrectAnswer())) {
                    score++;
                }
            }
        }
        
        // Save submission
        QuizSubmission submission = new QuizSubmission(userName, answers);
        submission.setScore(score);
        submission.setTotalQuestions(totalQuestions);
        
        QuizSubmission savedSubmission = submissionRepository.save(submission);
        
        // Send to Python service for result processing
        try {
            String pythonServiceUrl = environment.getProperty("PYTHON_SERVICE_URL", "http://localhost:5000");
            String processResultUrl = pythonServiceUrl + "/process-result";
            restTemplate.postForObject(processResultUrl, savedSubmission, String.class);
        } catch (Exception e) {
            System.err.println("Failed to send data to Python service: " + e.getMessage());
        }
        
        return savedSubmission;
    }
    
    public QuizSubmission getSubmission(Long id) {
        return submissionRepository.findById(id).orElse(null);
    }
    
    /**
     * Get quiz statistics
     */
    public Map<String, Object> getQuizStats() {
        long totalQuestions = questionRepository.count();
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalQuestionsInPool", totalQuestions);
        stats.put("questionsPerQuiz", QUESTIONS_PER_QUIZ);
        stats.put("optionsRandomized", RANDOMIZE_OPTIONS);
        return stats;
    }
}
