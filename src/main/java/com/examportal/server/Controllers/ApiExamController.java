package com.examportal.server.Controllers;

import com.examportal.server.Configs.JwtTokenUtil;
import com.examportal.server.DTO.ResponseDTO;
import com.examportal.server.Entity.AnswerForExamFile;
import com.examportal.server.Entity.AnswerRequest;
import com.examportal.server.Entity.Exam;
import com.examportal.server.Entity.User;
import com.examportal.server.Request.Answer;
import com.examportal.server.Request.ExamRequest;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
         @RequestParam("thoiGianBatDau") String thoiGianBatDau,
         @RequestParam("thoiGianKetThuc") String thoiGianKetThuc,
         @RequestParam("answer") String answersJson,
         @RequestParam("file") MultipartFile file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<AnswerRequest> answers = objectMapper.readValue(answersJson, new TypeReference<List<AnswerRequest>>() {});

            String jwt = request.getHeader("Authorization");

            if (jwt == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( new ResponseDTO("Invalid or missing token"));
            }
            if (jwt.startsWith("Bearer ")) {
                jwt = jwt.substring(7);
            }
            Claims claims = jwtTokenUtil.getClaimsFromToken(jwt);
            java.util.Date expiration = claims.getExpiration();
            if (expiration.before(new java.util.Date())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO("token exprired"));
            }
            String username = claims.getSubject(); // sub

            if (username == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO("Invalid token"));
            }

            User user = userService.getUserByUsername(username);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( new ResponseDTO("User not found"));
            }
            System.out.print(tenKyThi + thoiGianLamBai + loaiDeThi + maKyThi + matKhauKyThi + answers + file + thoiGianBatDau + thoiGianKetThuc);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(thoiGianBatDau, formatter);
            LocalDate endDate = LocalDate.parse(thoiGianKetThuc, formatter);

            // Tạo exam
            Exam exam = new Exam();
            exam.setTitle(tenKyThi);
            exam.setSubject_id(1L);
            exam.setStartTime(startDate);
            exam.setEndTime(endDate);
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
            if (answers == null || answers.isEmpty()) {
                throw new IllegalArgumentException("Danh sách câu trả lời không được rỗng hoặc null");
            }

            for (AnswerRequest answer : answers) {
                AnswerForExamFile answerForExamFile = new AnswerForExamFile();
                answerForExamFile.setExam(exam);
                answerForExamFile.setQuestionId(answer.getId());
                answerForExamFile.setSelectedOption(answer.getSelect());
                answerExamForFileService.addorUpdateAnswerForExamFile(answerForExamFile);
            }
            return ResponseEntity.ok(new ResponseDTO("success"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("error"));
        }
    }
    @GetMapping("/get/list/exams")
    public ResponseEntity<?> getListExams(HttpServletRequest request) {
        try {
            String jwt = request.getHeader("Authorization");
            if (jwt == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO("Invalid or missing token"));
            }
            if (jwt.startsWith("Bearer ")) {
                jwt = jwt.substring(7);
            }
            Claims claims = jwtTokenUtil.getClaimsFromToken(jwt);
            java.util.Date expiration = claims.getExpiration();
            if (expiration.before(new java.util.Date())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO("token exprired"));
            }
            String username = claims.getSubject(); // sub
            if (username == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO("Invalid token"));
            }
            User user = userService.getUserByUsername(username);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO("User not found"));
            }

            List<ExamRequest> examData = new ArrayList<>();
            List<Exam> exams = examService.getExamByTeacherId(user.getId());
            for(Exam exam : exams) {
                ExamRequest examRequest = new ExamRequest();
                examRequest.setExamId(exam.getId().toString());
                examRequest.setExamName(exam.getTitle());
                examRequest.setExamDuration(exam.getDuration());
                examRequest.setExamType(exam.getExamType());
                examRequest.setExamCreatedDate(exam.getCreated_at());
                examRequest.setExamPassword(exam.getPassword());
                examData.add(examRequest);
            }
            return ResponseEntity.ok(examData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("error"));
        }
    }
}
