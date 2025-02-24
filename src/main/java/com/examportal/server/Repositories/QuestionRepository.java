package com.examportal.server.Repositories;

import com.examportal.server.Entity.Question;

import java.util.List;

public interface QuestionRepository {
    List<Question> getList();

    Question getQuestionById(Long id);

    void save(Question question);

    void delete(Long id);
}
