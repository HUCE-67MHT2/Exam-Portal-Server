package com.examportal.server.Service;

import com.examportal.server.Entity.ExamSessionEnrollment;

import java.util.List;

public interface ExamSessionEnrollmentService {
    List<ExamSessionEnrollment> getList();

    ExamSessionEnrollment getExamSessionEnrollment(Long id);

    void save(ExamSessionEnrollment examSessionEnrollment);

    void delete(Long id);
}
