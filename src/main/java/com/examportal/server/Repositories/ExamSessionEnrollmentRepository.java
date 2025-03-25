package com.examportal.server.Repositories;


import com.examportal.server.Entity.ExamSessionEnrollment;

import java.util.List;

public interface ExamSessionEnrollmentRepository {
    List<ExamSessionEnrollment> getList();
    ExamSessionEnrollment getExamPeriodEnrollment(Long id);
    void save(ExamSessionEnrollment examPeriodEnrollment);
    void delete(Long id);
}
