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
        // Initialize DevOps questions pool (50+ questions)
        if (questionRepository.count() == 0) {
            Question q1 = new Question(
                "What does CI/CD stand for?",
                Arrays.asList("Continuous Integration/Continuous Deployment", "Code Integration/Code Deployment", 
                             "Central Integration/Central Deployment", "Custom Integration/Custom Deployment"),
                0
            );
            
            Question q2 = new Question(
                "Which tool is commonly used for containerization?",
                Arrays.asList("Jenkins", "Docker", "Ansible", "Terraform"),
                1
            );
            
            Question q3 = new Question(
                "What is the primary purpose of Infrastructure as Code (IaC)?",
                Arrays.asList("To write application code", "To manage infrastructure through code", 
                             "To test software", "To deploy applications"),
                1
            );
            
            Question q4 = new Question(
                "Which of the following is a container orchestration platform?",
                Arrays.asList("Git", "Maven", "Kubernetes", "NPM"),
                2
            );
            
            Question q5 = new Question(
                "What is the main benefit of microservices architecture?",
                Arrays.asList("Easier to deploy as a single unit", "Better scalability and maintainability", 
                             "Requires less resources", "Simpler database design"),
                1
            );
            
            Question q6 = new Question(
                "Which tool is primarily used for configuration management?",
                Arrays.asList("Docker", "Ansible", "Git", "Jenkins"),
                1
            );
            
            Question q7 = new Question(
                "What does API stand for?",
                Arrays.asList("Application Programming Interface", "Automated Programming Interface", 
                             "Advanced Programming Interface", "Application Process Interface"),
                0
            );
            
            Question q8 = new Question(
                "Which cloud service model provides the most control over the underlying infrastructure?",
                Arrays.asList("SaaS", "PaaS", "IaaS", "FaaS"),
                2
            );
            
            Question q9 = new Question(
                "What is the purpose of a load balancer?",
                Arrays.asList("To store data", "To distribute incoming requests across multiple servers", 
                             "To compile code", "To monitor system performance"),
                1
            );
            
            Question q10 = new Question(
                "Which version control system is most commonly used in DevOps?",
                Arrays.asList("SVN", "Git", "Mercurial", "Perforce"),
                1
            );
            
            Question q11 = new Question(
                "What is the main purpose of monitoring in DevOps?",
                Arrays.asList("To write code", "To track system performance and detect issues", 
                             "To deploy applications", "To manage databases"),
                1
            );
            
            Question q12 = new Question(
                "Which of the following is a popular CI/CD tool?",
                Arrays.asList("Photoshop", "Jenkins", "Excel", "PowerPoint"),
                1
            );
            
            Question q13 = new Question(
                "What does REST stand for in web services?",
                Arrays.asList("Representational State Transfer", "Remote State Transfer", 
                             "Relational State Transfer", "Responsive State Transfer"),
                0
            );
            
            Question q14 = new Question(
                "Which practice involves automatically testing code changes?",
                Arrays.asList("Continuous Integration", "Continuous Deployment", 
                             "Continuous Monitoring", "Continuous Planning"),
                0
            );
            
            Question q15 = new Question(
                "What is the primary benefit of using containers?",
                Arrays.asList("Faster internet connection", "Application portability and consistency", 
                             "Better graphics", "Cheaper hardware"),
                1
            );
            
            Question q16 = new Question(
                "Which tool is commonly used for infrastructure provisioning?",
                Arrays.asList("Photoshop", "Terraform", "Word", "Excel"),
                1
            );
            
            Question q17 = new Question(
                "What is a Docker image?",
                Arrays.asList("A photo of a container", "A template for creating containers", 
                             "A type of virtual machine", "A database backup"),
                1
            );
            
            Question q18 = new Question(
                "Which HTTP status code indicates a successful request?",
                Arrays.asList("404", "500", "200", "301"),
                2
            );
            
            Question q19 = new Question(
                "What is the purpose of a reverse proxy?",
                Arrays.asList("To hide client identity", "To act as an intermediary for requests from clients", 
                             "To store passwords", "To compile code"),
                1
            );
            
            Question q20 = new Question(
                "Which of the following is a key principle of DevOps?",
                Arrays.asList("Waterfall development", "Collaboration between development and operations", 
                             "Manual testing only", "Separate development and operations teams"),
                1
            );
            
            Question q21 = new Question(
                "What is blue-green deployment?",
                Arrays.asList("A color scheme for applications", "A deployment strategy with two identical environments", 
                             "A type of database", "A monitoring tool"),
                1
            );
            
            Question q22 = new Question(
                "Which tool is commonly used for log aggregation?",
                Arrays.asList("ELK Stack", "Photoshop", "Microsoft Word", "Calculator"),
                0
            );
            
            Question q23 = new Question(
                "What does SLA stand for?",
                Arrays.asList("Service Level Agreement", "Software License Agreement", 
                             "System Load Average", "Security Level Assessment"),
                0
            );
            
            Question q24 = new Question(
                "Which practice involves deploying small, frequent changes?",
                Arrays.asList("Big Bang deployment", "Continuous Deployment", 
                             "Waterfall deployment", "Manual deployment"),
                1
            );
            
            Question q25 = new Question(
                "What is the main purpose of a staging environment?",
                Arrays.asList("To store production data", "To test changes before production deployment", 
                             "To develop new features", "To backup data"),
                1
            );
            
            // Additional 25 questions for variety
            Question q26 = new Question(
                "What is GitOps?",
                Arrays.asList("A Git hosting service", "A deployment methodology using Git as source of truth", 
                             "A Git GUI tool", "A Git backup solution"),
                1
            );
            
            Question q27 = new Question(
                "Which of the following is a service mesh technology?",
                Arrays.asList("Docker", "Istio", "Jenkins", "Maven"),
                1
            );
            
            Question q28 = new Question(
                "What does YAML stand for?",
                Arrays.asList("Yet Another Markup Language", "YAML Ain't Markup Language", 
                             "Young Adult Markup Language", "Year-based Markup Language"),
                1
            );
            
            Question q29 = new Question(
                "Which tool is used for secrets management?",
                Arrays.asList("HashiCorp Vault", "Notepad", "Excel", "PowerPoint"),
                0
            );
            
            Question q30 = new Question(
                "What is canary deployment?",
                Arrays.asList("Deploying to bird servers", "Gradually rolling out changes to a subset of users", 
                             "A yellow-colored deployment", "Emergency deployment"),
                1
            );
            
            Question q31 = new Question(
                "Which metric is commonly used to measure system reliability?",
                Arrays.asList("Lines of code", "Uptime percentage", "Number of developers", "Server color"),
                1
            );
            
            Question q32 = new Question(
                "What is the purpose of a health check in microservices?",
                Arrays.asList("To check developer health", "To monitor service availability and status", 
                             "To count lines of code", "To measure server temperature"),
                1
            );
            
            Question q33 = new Question(
                "Which protocol is commonly used for container communication?",
                Arrays.asList("FTP", "HTTP/HTTPS", "SMTP", "POP3"),
                1
            );
            
            Question q34 = new Question(
                "What is the difference between horizontal and vertical scaling?",
                Arrays.asList("Color difference", "Horizontal adds more instances, vertical adds more power", 
                             "No difference", "Horizontal is faster"),
                1
            );
            
            Question q35 = new Question(
                "Which tool is used for container image scanning?",
                Arrays.asList("Photoshop", "Clair", "Paint", "GIMP"),
                1
            );
            
            Question q36 = new Question(
                "What is immutable infrastructure?",
                Arrays.asList("Infrastructure that cannot be changed", "Infrastructure that is replaced rather than modified", 
                             "Very expensive infrastructure", "Infrastructure made of stone"),
                1
            );
            
            Question q37 = new Question(
                "Which of the following is a feature flag management tool?",
                Arrays.asList("LaunchDarkly", "Photoshop", "Calculator", "Notepad"),
                0
            );
            
            Question q38 = new Question(
                "What is the purpose of a circuit breaker pattern?",
                Arrays.asList("To break electrical circuits", "To prevent cascading failures in distributed systems", 
                             "To break code", "To stop deployments"),
                1
            );
            
            Question q39 = new Question(
                "Which tool is commonly used for API gateway functionality?",
                Arrays.asList("Kong", "Paint", "Calculator", "Notepad"),
                0
            );
            
            Question q40 = new Question(
                "What is the twelve-factor app methodology?",
                Arrays.asList("A math concept", "A set of best practices for building SaaS applications", 
                             "A counting system", "A calendar system"),
                1
            );
            
            Question q41 = new Question(
                "Which of the following is a chaos engineering tool?",
                Arrays.asList("Chaos Monkey", "Happy Monkey", "Sad Monkey", "Dancing Monkey"),
                0
            );
            
            Question q42 = new Question(
                "What is the purpose of distributed tracing?",
                Arrays.asList("To trace network cables", "To track requests across multiple services", 
                             "To trace drawings", "To track employees"),
                1
            );
            
            Question q43 = new Question(
                "Which storage type is best for stateless applications?",
                Arrays.asList("Persistent storage", "Ephemeral storage", "Magnetic tape", "Floppy disk"),
                1
            );
            
            Question q44 = new Question(
                "What is the purpose of a service registry?",
                Arrays.asList("To register services", "To discover and locate services in a distributed system", 
                             "To count services", "To name services"),
                1
            );
            
            Question q45 = new Question(
                "Which of the following is a container runtime?",
                Arrays.asList("containerd", "Microsoft Word", "Photoshop", "Calculator"),
                0
            );
            
            Question q46 = new Question(
                "What is the purpose of a sidecar pattern?",
                Arrays.asList("To add a motorcycle sidecar", "To extend functionality without modifying the main application", 
                             "To add colors", "To add music"),
                1
            );
            
            Question q47 = new Question(
                "Which tool is used for policy as code?",
                Arrays.asList("Open Policy Agent (OPA)", "Notepad", "Calculator", "Paint"),
                0
            );
            
            Question q48 = new Question(
                "What is the purpose of observability in DevOps?",
                Arrays.asList("To observe employees", "To understand system behavior through metrics, logs, and traces", 
                             "To watch movies", "To observe weather"),
                1
            );
            
            Question q49 = new Question(
                "Which of the following is a GitOps tool?",
                Arrays.asList("ArgoCD", "Photoshop", "Calculator", "Notepad"),
                0
            );
            
            Question q50 = new Question(
                "What is the purpose of a deployment pipeline?",
                Arrays.asList("To transport water", "To automate the process of software delivery", 
                             "To transport oil", "To transport data"),
                1
            );
            
            questionRepository.saveAll(Arrays.asList(
                q1, q2, q3, q4, q5, q6, q7, q8, q9, q10,
                q11, q12, q13, q14, q15, q16, q17, q18, q19, q20,
                q21, q22, q23, q24, q25, q26, q27, q28, q29, q30,
                q31, q32, q33, q34, q35, q36, q37, q38, q39, q40,
                q41, q42, q43, q44, q45, q46, q47, q48, q49, q50
            ));
            System.out.println("50 DevOps questions initialized!");
        }
    }
}
