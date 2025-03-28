package com.examportal.server.Repositories;


import com.examportal.server.Entity.ExamSessionEnrollment;
import com.examportal.server.Request.StudentInExamSessionEnrollmentRequest;

import java.util.List;

public interface ExamSessionEnrollmentRepository {
    List<ExamSessionEnrollment> getList();

    ExamSessionEnrollment getExamSessionEnrollment(Long id);

    void save(ExamSessionEnrollment examSessionEnrollment);

    void delete(Long id);

    List<StudentInExamSessionEnrollmentRequest> getInfoStudentInExamSessionEnrollment(Long examSessionId);
}
