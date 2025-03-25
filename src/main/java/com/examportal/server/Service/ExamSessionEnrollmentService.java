package com.examportal.server.Service;

import com.examportal.server.Entity.ExamSessionEnrollment;

import java.util.List;

public interface ExamSessionEnrollmentService {
    List<ExamSessionEnrollment> getList();

    ExamSessionEnrollment getExamPeriodEnrollment(Long id);

    void save(ExamSessionEnrollment examPeriodEnrollment);

    void delete(Long id);
}
