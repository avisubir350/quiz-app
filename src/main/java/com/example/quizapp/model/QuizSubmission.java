package com.example.quizapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "quiz_submissions")
public class QuizSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String userName;
    
    @ElementCollection
    @CollectionTable(name = "submission_answers", joinColumns = @JoinColumn(name = "submission_id"))
    @MapKeyColumn(name = "question_id")
    @Column(name = "answer")
    private Map<Long, Integer> answers; // questionId -> selectedOption
    
    @Column(nullable = false)
    private LocalDateTime submittedAt;
    
    private Integer score;
    private Integer totalQuestions;
    
    // Constructors
    public QuizSubmission() {
        this.submittedAt = LocalDateTime.now();
    }
    
    public QuizSubmission(String userName, Map<Long, Integer> answers) {
        this.userName = userName;
        this.answers = answers;
        this.submittedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
    
    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
    
    public Integer getScore() {
        return score;
    }
    
    public void setScore(Integer score) {
        this.score = score;
    }
    
    public Integer getTotalQuestions() {
        return totalQuestions;
    }
    
    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }
}
