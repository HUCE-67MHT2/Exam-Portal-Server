package com.examportal.server.Repositories;

import com.examportal.server.Entity.Exam_Result;

import java.util.List;

public interface ExamResultRepository {
    List<Exam_Result> getList();

    Exam_Result getExamResultById(Long id);

    void save(Exam_Result examResult);

    void delete(Long id);
}
