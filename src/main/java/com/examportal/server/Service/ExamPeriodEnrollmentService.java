package com.examportal.server.Service;

import com.examportal.server.Entity.ExamPeriodEnrollment;

import java.util.List;

public interface ExamPeriodEnrollmentService {
    List<ExamPeriodEnrollment> getList();
    ExamPeriodEnrollment getExamPeriodEnrollment(Long id);
    void save(ExamPeriodEnrollment examPeriodEnrollment);
    void delete(Long id);
}
