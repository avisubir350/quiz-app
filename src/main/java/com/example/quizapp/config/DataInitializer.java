package com.example.quizapp.config;

import com.example.quizapp.model.Question;
import com.example.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private QuestionRepository questionRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Initialize sample questions
        if (questionRepository.count() == 0) {
            Question q1 = new Question(
                "What is the capital of France?",
                Arrays.asList("London", "Berlin", "Paris", "Madrid"),
                2
            );
            
            Question q2 = new Question(
                "Which programming language is known as 'Write Once, Run Anywhere'?",
                Arrays.asList("Python", "Java", "C++", "JavaScript"),
                1
            );
            
            Question q3 = new Question(
                "What does HTTP stand for?",
                Arrays.asList("HyperText Transfer Protocol", "High Tech Transfer Protocol", 
                             "HyperText Transport Protocol", "High Transfer Text Protocol"),
                0
            );
            
            Question q4 = new Question(
                "Which of the following is NOT a relational database?",
                Arrays.asList("MySQL", "PostgreSQL", "MongoDB", "Oracle"),
                2
            );
            
            Question q5 = new Question(
                "What is the time complexity of binary search?",
                Arrays.asList("O(n)", "O(log n)", "O(n²)", "O(1)"),
                1
            );
            
            questionRepository.saveAll(Arrays.asList(q1, q2, q3, q4, q5));
            System.out.println("Sample questions initialized!");
        }
    }
}
