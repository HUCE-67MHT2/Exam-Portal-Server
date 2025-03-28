package com.examportal.server.Request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentInExamSessionEnrollmentRequest {
    private Long student_id;
    private String class_name;
    private String student_name;
}
