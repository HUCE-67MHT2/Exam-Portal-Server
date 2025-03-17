package com.examportal.server.Controllers;

import com.examportal.server.Configs.JwtTokenUtil;
import com.examportal.server.Entity.Exam;
import com.examportal.server.Entity.User;
import com.examportal.server.Request.Answer;
import com.examportal.server.Request.Exam_File_Request;
import com.examportal.server.Service.ExamService;
import com.examportal.server.Service.GoogleDriveService;
import com.examportal.server.Service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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

    @PostMapping("/add/exam/with/file")
    public ResponseEntity<?> addExamWithFile(HttpServletRequest request,
                                             @RequestParam("tenKyThi") String tenKyThi,
                                             @RequestParam("loaiDeThi") String loaiDeThi,
                                             @RequestParam("maDeThi") String maDeThi,
                                             @RequestParam("thoiGianLamBai") int thoiGianLamBai,
                                             @RequestParam("maKyThi") String maKyThi,
                                             @RequestParam("matKhauKyThi") String matKhauKyThi,
                                             @RequestParam("answer") List<Answer> answers,
                                             @RequestParam("file") MultipartFile file) {
        try {
            String jwt = request.getHeader("Authorization");

            if (jwt == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing token");
            }
            if (jwt.startsWith("Bearer ")) {
                jwt = jwt.substring(7);
            }
            Claims claims = jwtTokenUtil.getClaimsFromToken(jwt);
            java.util.Date expiration = claims.getExpiration();
            if (expiration.before(new java.util.Date())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token exprired");
            }
            String username = claims.getSubject(); // sub

            if (username == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }

            User user = userService.getUserByUsername(username);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
            }

            // Lưu thông tin Exam từ các tham số request
            Exam exam = new Exam();
            exam.setTitle(tenKyThi);
            exam.setSubject_id(Long.parseLong("1"));
            exam.setStartTime(LocalDateTime.now());
            exam.setDuration(thoiGianLamBai);
            exam.setExamType(loaiDeThi);
            exam.setCreated_at(new Timestamp(System.currentTimeMillis()));
            exam.setPassword(matKhauKyThi);
            exam.setTeacher_id(user.getId());
            // Upload file và lưu URL
            String fileLink = googleDriveService.uploadFile(file);
            exam.setFileUrl(fileLink);

            // Lưu exam vào database
            examService.save(exam);

            return ResponseEntity.ok("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }
}
