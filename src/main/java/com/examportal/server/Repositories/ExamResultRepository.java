package com.examportal.server.Repositories;

import com.examportal.server.Entity.ExamResult;

import java.util.List;

public interface ExamResultRepository {
    List<ExamResult> getList();

    ExamResult getExamResultById(Long id);

    void save(ExamResult examResult);

    void delete(Long id);
}
