package com.examportal.server.Controllers;

import com.examportal.server.Configs.JwtTokenUtil;
import com.examportal.server.DTO.ResponseDTO;
import com.examportal.server.Entity.ExamSession;
import com.examportal.server.Entity.User;
import com.examportal.server.Request.AddNewExamSessionRequest;
import com.examportal.server.Service.ExamSessionService;
import com.examportal.server.Service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exam-session")
public class ApiExamSession {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 10;
    @Autowired
    private ExamSessionService examSessionService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;

    // Phương thức để sinh mã code ngẫu nhiên
    public String generateExamSessionCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(ALPHABET.length());
            code.append(ALPHABET.charAt(randomIndex));
        }

        return code.toString();
    }

    @GetMapping("/get/all/exam-period")
    public ResponseEntity<?> getAllExamSession(HttpServletRequest request) {
        try {

            String jwt = request.getHeader("Authorization");
            if (jwt == null || !jwt.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO("Invalid or missing token"));
            }
            jwt = jwt.substring(7);
            Claims claims = jwtTokenUtil.getClaimsFromToken(jwt);
            if (claims.getExpiration().before(new java.util.Date())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO("Token expired"));
            }
            String username = claims.getSubject();
            if (username == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO("Invalid token"));
            }
            User user = userService.getUserByUsername(username);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO("User not found"));
            }
            List<ExamSession> examSessions = examSessionService.getExamSessionByTeacherId(user.getId());
            Map<String, Object> response = new HashMap<>();
            response.put("examSessions", examSessions);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/add/exam-period")
    public ResponseEntity<?> addExamSession(@RequestBody AddNewExamSessionRequest newExamSessionRequest, HttpServletRequest request) {
        try {
            String jwt = request.getHeader("Authorization");
            if (jwt == null || !jwt.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO("Invalid or missing token"));
            }
            jwt = jwt.substring(7);
            Claims claims = jwtTokenUtil.getClaimsFromToken(jwt);
            if (claims.getExpiration().before(new java.util.Date())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO("Token expired"));
            }
            String username = claims.getSubject();
            if (username == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO("Invalid token"));
            }
            User user = userService.getUserByUsername(username);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO("User not found"));
            }
            ExamSession examSession = new ExamSession();
            examSession.setTeacherId(user.getId());
            examSession.setPassword(newExamSessionRequest.getExam_sessions_password());
            examSession.setCreateDate(new Timestamp(System.currentTimeMillis()));
            examSession.setDescription(newExamSessionRequest.getExam_sessions_description());
            examSession.setStartDate(newExamSessionRequest.getExam_sessions_start_date());
            examSession.setEndDate(newExamSessionRequest.getExam_sessions_end_date());
            examSession.setName(newExamSessionRequest.getExam_sessions_name());
            examSession.setCode(generateExamSessionCode());
            examSessionService.save(examSession);
            return ResponseEntity.ok(new ResponseDTO("Tạo kỳ thi thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
