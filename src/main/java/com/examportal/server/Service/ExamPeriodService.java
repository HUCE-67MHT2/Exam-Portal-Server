package com.examportal.server.Service;

import com.examportal.server.Entity.ExamSession;

import java.util.List;

public interface ExamPeriodService {
    List<ExamSession> getList();
    void save(ExamSession examPeriod);
    void delete(Long id);
    ExamSession getExamPeriodById(Long id);
    List<ExamSession> getExamPeriodsByTeacherId(Long id);
}
