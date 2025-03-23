package com.examportal.server.Repositories;


import com.examportal.server.Entity.ExamPeriodEnrollment;

import java.util.List;

public interface ExamPeriodEnrollmentRepository {
    List<ExamPeriodEnrollment> getList();
    ExamPeriodEnrollment getExamPeriodEnrollment(Long id);
    void save(ExamPeriodEnrollment examPeriodEnrollment);
    void delete(Long id);
}
