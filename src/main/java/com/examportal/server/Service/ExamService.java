package com.examportal.server.Service;

import com.examportal.server.Entity.Exam;

import java.util.List;

public interface ExamService {
    List<Exam> getList();

    Exam getExamById(Long id);

    void save(Exam exam);

    void delete(Long id);

    List<Exam> getExamBySessionId(Long id);
}
