package com.examportal.server.Repositories;

import com.examportal.server.Entity.QuestionAnswer;

import java.util.List;

public interface QuestionAnswerRepository {
    List<QuestionAnswer> getList();

    QuestionAnswer getAnswerById(Long id);

    void save(QuestionAnswer answer);

    void delete(Long id);

    void deleteByExamId(Long examId);
}
