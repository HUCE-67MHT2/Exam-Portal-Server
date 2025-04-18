package com.examportal.server.Controllers;

import com.examportal.server.Configs.JwtTokenUtil;
import com.examportal.server.DTO.UploadExamStateResponseDTO;
import com.examportal.server.DTO.ResponseDTO;
import com.examportal.server.Entity.Exam;
import com.examportal.server.Request.ExamRequest;
import com.examportal.server.Service.ExamService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exam")
public class ApiExamController {
    @Autowired
    private ExamService examService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private HttpServletRequest request;

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

            /**
             * Dạng respone mà client sẽ nhận được:
             * {
             *     "message": "Exam created successfully",
             *     "examId": 123
             * }
             */

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

            /**
             * Dạng respone mà client sẽ nhận được:
             * {
             *     "message": "Exam updated successfully",
             *     "examId": 123
             * }
             */
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
            // Xóa tất cả các exam cũ của session này có type là "auto-generate"
            List<Exam> oldExams = examService.getExamBySessionId(examRequest.getExamSessionId());
            for (Exam oldExam : oldExams) {
                if ("auto-generate".equals(oldExam.getType())) {
                    examService.delete(oldExam.getId());
                }
            }
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
            response.put("examId", savedExam.getId());
            /**
             * Dạng respone mà client sẽ nhận được:
             * {
             *     "message": "Exam created successfully",
             *     "examId": 123
             * }
             */

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
    public ResponseEntity<?> getExamById(@PathVariable("id") Long id) {
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


    @PostMapping("/get/test/state/upload/exam/{examId}")
    public ResponseEntity<?> getTestState(@PathVariable("examId") Long examId, HttpServletRequest request) {
        try {
            String token = jwtTokenUtil.resolveToken(request);
            if (token == null || !jwtTokenUtil.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Collections.singletonMap("message", "Token không hợp lệ hoặc thiếu"));
            }
            Long userId = jwtTokenUtil.getIdFromToken(token);


            // Gọi service để kiểm tra trạng thái làm bài và tạo mới nếu cần
            UploadExamStateResponseDTO state = examService.getStateUploadExam(examId, userId);

            return ResponseEntity.ok(state);

        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null && message.contains(":")) {
                message = message.substring(message.lastIndexOf(":") + 1).trim(); // lấy phần sau dấu ":"
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", message));
        }
    }


    @PostMapping("upload/submit/{examId}")
    public ResponseEntity<?> uploadSubmit(@PathVariable("examId") Long examId, HttpServletRequest request) {
        try {
            String token = jwtTokenUtil.resolveToken(request);
            if (token == null || !jwtTokenUtil.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Collections.singletonMap("message", "Token không hợp lệ hoặc thiếu"));
            }
            Long userId = jwtTokenUtil.getIdFromToken(token);

            examService.submitUploadExam(examId, userId);

            return ResponseEntity.ok(Collections.singletonMap("message", "Exam submitted successfully"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
