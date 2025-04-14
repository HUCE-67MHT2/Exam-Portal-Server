package com.examportal.server.Controllers;


import com.examportal.server.Service.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exam/question")
public class ApiExamQuestionController {

    @Autowired
    private ExamQuestionService examQuestionService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateExamQuestions(@RequestParam String examId, @RequestParam("questionsPerExam") int questionsPerExam) {
        // Generate a set of questions for the exam
        examQuestionService.generateExamQuestions(examId, questionsPerExam);
        return ResponseEntity.ok("Generated  questions for id " + examId + " successfully");
    }
}
