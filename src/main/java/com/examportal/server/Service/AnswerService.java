package com.examportal.server.Service;

import com.examportal.server.Entity.Answer;

import java.util.List;

public interface AnswerService {
    List<Answer> getList();
    Answer getAnswerById(Long id);
    void save(Answer answer);
    void delete(Long id);
}
