package com.examportal.server.Service;

import com.examportal.server.Entity.ExamQuestion;

import java.util.List;

public interface ExamQuestionService {
    List<ExamQuestion> getList();
    ExamQuestion getExamQuestionById(Long id);
    void save(ExamQuestion examQuestion);
    void delete(ExamQuestion examQuestion);
}
