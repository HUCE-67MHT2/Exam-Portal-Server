package com.examportal.server.Repositories;

import com.examportal.server.Entity.ExamQuestion;

import java.util.List;

public interface ExamQuestionRepository {

    void save(ExamQuestion examQuestion);

    List<ExamQuestion> getExamQuestionsByExamIdRandOrder(String examId);
    List<ExamQuestion> getExamQuestionsByExamId(Long examId);
    void deleteByExamId(String examId);
}