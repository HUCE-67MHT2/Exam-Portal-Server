package com.examportal.server.Repositories;

import com.examportal.server.Entity.ExamQuestion;

import java.util.List;

public interface ExamQuestionRepository {
    List<ExamQuestion> getList();
    ExamQuestion getExamQuestionById(Long id);
    void save(ExamQuestion examQuestion);
    void delete(ExamQuestion examQuestion);
}
