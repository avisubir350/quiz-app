package com.example.quizapp.controller;

import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuizSubmission;
import com.example.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class QuizController {
    
    @Autowired
    private QuizService quizService;
    
    @GetMapping("/quiz")
    public ResponseEntity<List<Question>> getQuiz() {
        List<Question> questions = quizService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }
    
    @PostMapping("/submit")
    public ResponseEntity<QuizSubmission> submitQuiz(@RequestBody SubmissionRequest request) {
        QuizSubmission submission = quizService.submitQuiz(request.getUserName(), request.getAnswers());
        return ResponseEntity.ok(submission);
    }
    
    @GetMapping("/submission/{id}")
    public ResponseEntity<QuizSubmission> getSubmission(@PathVariable Long id) {
        QuizSubmission submission = quizService.getSubmission(id);
        if (submission != null) {
            return ResponseEntity.ok(submission);
        }
        return ResponseEntity.notFound().build();
    }
    
    // DTO for submission request
    public static class SubmissionRequest {
        private String userName;
        private Map<Long, Integer> answers;
        
        public String getUserName() {
            return userName;
        }
        
        public void setUserName(String userName) {
            this.userName = userName;
        }
        
        public Map<Long, Integer> getAnswers() {
            return answers;
        }
        
        public void setAnswers(Map<Long, Integer> answers) {
            this.answers = answers;
        }
    }
}
