package com.examportal.server.Service;

import com.examportal.server.Entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getList();

    void save(Question question);

    void delete(Long id);

    List<Question> getQuestionsByExamSessionId(Long id);
}
