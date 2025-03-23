package com.examportal.server.Repositories;

import com.examportal.server.Entity.ExamPeriod;

import java.util.List;

public interface ExamPeriodRepository {
    List<ExamPeriod> getList();
    void save(ExamPeriod examPeriod);
    void delete(Long id);
    ExamPeriod getExamPeriodById(Long id);
}
