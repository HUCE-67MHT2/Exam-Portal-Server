package com.examportal.server.Service;

import com.examportal.server.Entity.Word_Exam_Answer;

import java.util.List;

public interface WordExamAnswerService {
    List<Word_Exam_Answer> getList();

    Word_Exam_Answer getWordExamAnswerById(Long id);

    void save(Word_Exam_Answer word_exam_answer);

    void delete(Long id);
}
