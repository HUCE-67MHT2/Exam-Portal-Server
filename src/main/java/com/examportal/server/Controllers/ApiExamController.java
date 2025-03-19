package com.examportal.server.Controllers;

import com.examportal.server.Configs.JwtTokenUtil;
import com.examportal.server.DTO.ResponseDTO;
import com.examportal.server.Entity.AnswerForExamFile;
import com.examportal.server.Entity.AnswerRequest;
import com.examportal.server.Entity.Exam;
import com.examportal.server.Entity.User;
import com.examportal.server.Request.Answer;
import com.examportal.server.Request.Exam_File_Request;
import com.examportal.server.Service.AnswerExamForFileService;
import com.examportal.server.Service.ExamService;
import com.examportal.server.Service.GoogleDriveService;
import com.examportal.server.Service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    private AnswerExamForFileService answerExamForFileService;

    @PostMapping("/add/exam/with/file")
    public ResponseEntity<?> addExamWithFile(HttpServletRequest request,
                                             @RequestParam("tenKyThi") String tenKyThi,
                                             @RequestParam("loaiDeThi") String loaiDeThi,
                                             @RequestParam("maDeThi") String maDeThi,
                                             @RequestParam("thoiGianLamBai") int thoiGianLamBai,
                                             @RequestParam("maKyThi") String maKyThi,
                                             @RequestParam("matKhauKyThi") String matKhauKyThi,
                                             @RequestParam("answer") String answersJson,
                                             @RequestParam("file") MultipartFile file) {
        try {
            // In ra dữ liệu raw để kiểm tra trước khi parse
            System.out.println("Raw answersJson: " + answersJson);

            // Chuyển đổi JSON thành List<AnswerRequest>
            ObjectMapper objectMapper = new ObjectMapper();
            List<AnswerRequest> answers = objectMapper.readValue(answersJson, new TypeReference<List<AnswerRequest>>() {});

            // Kiểm tra nếu danh sách answers không rỗng
            if (answers == null || answers.isEmpty()) {
                throw new IllegalArgumentException("Danh sách câu trả lời không được rỗng hoặc null");
            }

            // In ra kết quả parse được để kiểm tra
            answers.forEach(answer -> System.out.println("Parsed answer: ID: " + answer.getId() + ", Select: " + answer.getSelect()));

            // Lấy JWT token từ header
            String jwt = request.getHeader("Authorization");
            if (jwt == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO("Invalid or missing token"));
            }

            // Xử lý token và xác thực người dùng
            if (jwt.startsWith("Bearer ")) {
                jwt = jwt.substring(7);
            }
            Claims claims = jwtTokenUtil.getClaimsFromToken(jwt);
            java.util.Date expiration = claims.getExpiration();
            if (expiration.before(new java.util.Date())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO("Token expired"));
            }

            String username = claims.getSubject(); // sub
            if (username == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO("Invalid token"));
            }

            // Kiểm tra người dùng
            User user = userService.getUserByUsername(username);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO("User not found"));
            }

            // Lưu thông tin exam vào database
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

            // Lưu câu trả lời vào database
            answers.forEach(answer -> {
                AnswerForExamFile answerForExamFile = new AnswerForExamFile();
                answerForExamFile.setExam(exam);
                answerForExamFile.setQuestionId(answer.getId()); // Chuyển từ String sang Long nếu cần
                answerForExamFile.setSelectedOption(answer.getSelect());

                // Lưu vào cơ sở dữ liệu
                answerExamForFileService.addorUpdateAnswerForExamFile(answerForExamFile);
            });

            return ResponseEntity.ok(new ResponseDTO("Success"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("Error"));
        }
    }
}
