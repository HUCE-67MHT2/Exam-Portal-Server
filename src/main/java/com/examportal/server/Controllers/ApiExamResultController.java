package com.examportal.server.Controllers;

import com.examportal.server.Configs.JwtTokenUtil;
import com.examportal.server.Request.StudentResultInExamSession;
import com.examportal.server.Service.ExamResultService;
import com.examportal.server.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/exam-result")
public class ApiExamResultController {
    @Autowired
    private ExamResultService examResultService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;

    @GetMapping("get/list/student/result/in/session/{examSessionId}")
    public ResponseEntity<?> getListStudentResultInSession(@PathVariable Long examSessionId) {
        try {
            List<StudentResultInExamSession> studentList =
                    examResultService.getListStudentResultInExamSession(examSessionId);
            return ResponseEntity.ok(studentList);
        } catch (Exception e) {
            // Log lỗi nếu cần
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Có lỗi xảy ra: " + e.getMessage());
        }
    }
}
