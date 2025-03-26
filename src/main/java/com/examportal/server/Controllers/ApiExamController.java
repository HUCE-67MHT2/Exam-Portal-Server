package com.examportal.server.Controllers;

import com.examportal.server.Configs.JwtTokenUtil;
import com.examportal.server.DTO.ResponseDTO;
import com.examportal.server.Entity.Exam;
import com.examportal.server.Request.ExamUploadRequest;
import com.examportal.server.Service.ExamService;
import com.examportal.server.Service.GoogleDriveService;
import com.examportal.server.Service.UserService;
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
    private GoogleDriveService googleDriveService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private ExamService examService;

    @PostMapping(value = "/add/exam/with/file", consumes = "multipart/form-data")
    public ResponseEntity<?> addExamWithFile(@ModelAttribute ExamUploadRequest request,
                                             @RequestParam("file") MultipartFile file) {
        try {
            // Create a new Exam entity from the request data
            Exam exam = new Exam();
            exam.setExamSessionId(request.getExamSessionId());
            exam.setName(request.getName());
            exam.setDescription(request.getDescription());
            exam.setDuration(request.getDuration());
            exam.setSubject(request.getSubject());
            exam.setStartDate(request.getStartDate());
            exam.setEndDate(request.getEndDate());
            exam.setCreateDate(new Timestamp(System.currentTimeMillis()));

            // Upload file and save exam
            Exam savedExam = examService.createExamByFile(exam, file);

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
}
