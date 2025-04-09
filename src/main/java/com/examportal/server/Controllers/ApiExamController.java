package com.examportal.server.Controllers;

import com.examportal.server.DTO.ResponseDTO;
import com.examportal.server.Entity.Exam;
import com.examportal.server.Request.ExamRequest;
import com.examportal.server.Service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exam")
public class ApiExamController {
    @Autowired
    private ExamService examService;

    @PostMapping(value = "/add/exam/with/file", consumes = "multipart/form-data")
    public ResponseEntity<?> addExamWithFile(@ModelAttribute ExamRequest examRequest,
                                             @RequestParam("file") MultipartFile file) {
        try {
            // Create a new Exam entity from the examRequest data
            Exam exam = new Exam();
            exam.setExamSessionId(examRequest.getExamSessionId());
            exam.setName(examRequest.getName());
            exam.setDescription(examRequest.getDescription());
            exam.setTotalQuestions(examRequest.getTotalQuestions());
            exam.setDuration(examRequest.getDuration());
            exam.setSubject(examRequest.getSubject());
            exam.setStartDate(examRequest.getStartDate());
            exam.setEndDate(examRequest.getEndDate());
            exam.setCreateDate(new Timestamp(System.currentTimeMillis()));

            // Upload file and save exam
            Exam savedExam = examService.createExamByFile(exam, file);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Exam created successfully");
            response.put("examId", savedExam.getId());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("Failed to create exam: " + e.getMessage()));
        }
    }

    @PostMapping(value = "/update/exam", consumes = "multipart/form-data")
    public ResponseEntity<?> updateExam(@ModelAttribute ExamRequest examRequest,
                                        @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            // Create a new Exam entity from the examRequest data
            Exam exam = new Exam();
            exam.setExamSessionId(examRequest.getExamSessionId());
            exam.setId(examRequest.getId());
            exam.setName(examRequest.getName());
            exam.setTotalQuestions(examRequest.getTotalQuestions());
            exam.setDescription(examRequest.getDescription());
            exam.setTotalQuestions(examRequest.getTotalQuestions());
            exam.setDuration(examRequest.getDuration());
            exam.setSubject(examRequest.getSubject());
            exam.setStartDate(examRequest.getStartDate());
            exam.setEndDate(examRequest.getEndDate());

            Exam updatedExam = examService.updateExamByFile(exam, file);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Exam updated successfully");
            response.put("examId", updatedExam.getId());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("Failed to update exam: " + e.getMessage()));
        }
    }
    @PostMapping("/add/exam/manually")
    public ResponseEntity<?> addExamManually(@ModelAttribute ExamRequest examRequest) {
        try {
            Exam exam = new Exam();
            exam.setExamSessionId(examRequest.getExamSessionId());
            exam.setName(examRequest.getName());
            exam.setDescription(examRequest.getDescription());
            exam.setDuration(examRequest.getDuration());
            exam.setSubject(examRequest.getSubject());
            exam.setStartDate(examRequest.getStartDate());
            exam.setEndDate(examRequest.getEndDate());
            exam.setCreateDate(new Timestamp(System.currentTimeMillis()));

            // Save the exam manually
            Exam savedExam = examService.createExamManually(exam);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Exam created successfully");
            response.put("exam", savedExam);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("Failed to create exam: " + e.getMessage()));
        }
    }

    @GetMapping("/get/list/exams/by/sessionId/{examSessionId}")
    public ResponseEntity<?> getListExams(@PathVariable("examSessionId") Long examSessionId) {
        try {
            List<Exam> exams = examService.getExamBySessionId(examSessionId);
            if (exams.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO("No exams found for the given session ID"));
            }
            Map<String, Object> response = new HashMap<>();
            response.put("exams", exams);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("/get/exam/{id}")
    public ResponseEntity<?> getExamById(@PathVariable Long id) {
        try {
            Exam exam = examService.getExamById(id);
            if (exam == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO("Exam not found"));
            }
            return ResponseEntity.ok(exam);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("An error occurred: " + e.getMessage()));
        }
    }
}
