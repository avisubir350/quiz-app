package com.example.quizapp.service;

import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuizSubmission;
import com.example.quizapp.repository.QuestionRepository;
import com.example.quizapp.repository.QuizSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class QuizService {
    
    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private QuizSubmissionRepository submissionRepository;
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    
    public QuizSubmission submitQuiz(String userName, Map<Long, Integer> answers) {
        // Calculate score
        List<Question> questions = questionRepository.findAll();
        int score = 0;
        
        for (Question question : questions) {
            Integer userAnswer = answers.get(question.getId());
            if (userAnswer != null && userAnswer.equals(question.getCorrectAnswer())) {
                score++;
            }
        }
        
        // Save submission
        QuizSubmission submission = new QuizSubmission(userName, answers);
        submission.setScore(score);
        submission.setTotalQuestions(questions.size());
        
        QuizSubmission savedSubmission = submissionRepository.save(submission);
        
        // Send to Python service for result processing
        try {
            String pythonServiceUrl = "http://localhost:5000/process-result";
            restTemplate.postForObject(pythonServiceUrl, savedSubmission, String.class);
        } catch (Exception e) {
            System.err.println("Failed to send data to Python service: " + e.getMessage());
        }
        
        return savedSubmission;
    }
    
    public QuizSubmission getSubmission(Long id) {
        return submissionRepository.findById(id).orElse(null);
    }
}
