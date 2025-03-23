package com.examportal.server.Service;

import com.examportal.server.Entity.ExamPeriod;

import java.util.List;

public interface ExamPeriodService {
    List<ExamPeriod> getList();
    void save(ExamPeriod examPeriod);
    void delete(Long id);
    ExamPeriod getExamPeriodById(Long id);
}
