package com.examportal.server.Controllers;


import com.examportal.server.Request.StudentInExamSessionEnrollmentRequest;
import com.examportal.server.Service.ExamSessionEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/exam-session-enrollment")
public class ApiExamSessionEnrollmentController {
    @Autowired
    private ExamSessionEnrollmentService examSessionEnrollmentService;

    @GetMapping("/get/list/student/in/sessionId/{examSessionId}")
    public ResponseEntity<?> getListStudentsInSession(@PathVariable Long examSessionId) {
        try {
            List<StudentInExamSessionEnrollmentRequest> studentList =
                    examSessionEnrollmentService.getInfoStudentInExamSessionEnrollment(examSessionId);
            return ResponseEntity.ok(studentList);
        } catch (Exception e) {
            // Log lỗi nếu cần
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Có lỗi xảy ra: " + e.getMessage());
        }
    }
}
