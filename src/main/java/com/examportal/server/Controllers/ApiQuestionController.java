package com.examportal.server.Controllers;

import com.examportal.server.DTO.ResponseDTO;
import com.examportal.server.Entity.Question;
import com.examportal.server.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/question")
public class ApiQuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/add/question")
    public ResponseEntity<?> addQuestion(@RequestBody Question question) {
        try {
            questionService.save(question);

            Map<String, Object> response = new HashMap<>();
            response.put("id", question.getId());
            response.put("message", "Question added successfully");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("Failed to add question: " + e.getMessage()));
        }
    }

    @GetMapping("/get/question/by/exam/session/id/{id}")
    public ResponseEntity<?> getQuestionBySessionId(@PathVariable("id") Long id) {
        try {
            List<Question> questions = questionService.getList();
            if (questions.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDTO("No questions found for the given exam session ID"));
            }
            Map<String, Object> response = new HashMap<>();
            response.put("questions", questions);
            response.put("message", "Questions retrieved successfully");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("Failed to get question: " + e.getMessage()));
        }
    }
}
