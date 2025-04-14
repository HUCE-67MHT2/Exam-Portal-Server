package com.examportal.server.Repositories;

import com.examportal.server.Entity.ExamQuestion;

import java.util.List;

public interface ExamQuestionRepository {
    ExamQuestion findById(Long id);

    void save(ExamQuestion examQuestion);

    List<ExamQuestion> findByExamId(String examId);

    void deleteByExamId(String examId);
}