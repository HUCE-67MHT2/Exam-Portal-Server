package com.examportal.server.Service;

import com.examportal.server.Entity.WordExamAnswer;

import java.util.List;

public interface WordExamAnswerService {
    List<WordExamAnswer> getList();

    WordExamAnswer getWordExamAnswerById(Long id);

    void save(WordExamAnswer word_exam_answer);

    void delete(Long id);
}
