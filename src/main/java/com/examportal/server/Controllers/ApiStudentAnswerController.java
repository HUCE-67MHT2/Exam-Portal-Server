package com.examportal.server.Controllers;

import com.examportal.server.Configs.JwtTokenUtil;
import com.examportal.server.DTO.ResponseDTO;
import com.examportal.server.DTO.UploadStudentAnswerRequest;
import com.examportal.server.Entity.QuestionAnswer;
import com.examportal.server.Service.StudentAnswerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/student-answer")
public class ApiStudentAnswerController {

    @Autowired
    private StudentAnswerService studentAnswerService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/upload/save")
    public ResponseEntity<?> saveStudentAnswers(@RequestBody UploadStudentAnswerRequest request) {
        try{
            String token = jwtTokenUtil.resolveToken(this.request);
            if (token == null || !jwtTokenUtil.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token không hợp lệ hoặc thiếu");
            }
            Long userId = jwtTokenUtil.getIdFromToken(token);

            // Lưu câu trả lời của sinh viên
            studentAnswerService.saveUploadStudentAnswers(request, userId);

            return ResponseEntity.ok(Collections.singletonMap("message", "Lưu câu trả lời thành công"));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "Có lỗi xảy ra: " + e.getMessage()));
        }
    }
}
