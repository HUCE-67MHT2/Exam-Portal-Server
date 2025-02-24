package com.examportal.server.Repositories;

import com.examportal.server.Entity.Exam;

import java.util.List;

public interface ExamRepository {
    List<Exam> getList();

    Exam getExamById(Long id);

    void save(Exam exam);

    void delete(Long id);
}
