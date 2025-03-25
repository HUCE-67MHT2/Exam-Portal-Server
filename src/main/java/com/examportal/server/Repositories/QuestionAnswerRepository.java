package com.examportal.server.Repositories;

import com.examportal.server.Entity.Answer;

import java.util.List;

public interface QuestionAnswerRepository {
    List<Answer> getList();
    Answer getAnswerById(Long id);
    void save(Answer answer);
    void delete(Long id);
}
