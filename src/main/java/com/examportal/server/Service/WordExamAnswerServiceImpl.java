package com.examportal.server.Service;

import com.examportal.server.Entity.Word_Exam_Answer;
import com.examportal.server.Repositories.WordExamAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordExamAnswerServiceImpl implements WordExamAnswerService {
    @Autowired
    private WordExamAnswerRepository wordExamAnswerRepository;

    public List<Word_Exam_Answer> getList() {
        return wordExamAnswerRepository.getList();
    }

    public Word_Exam_Answer getWordExamAnswerById(Long id) {
        return wordExamAnswerRepository.getWordExamAnswerById(id);
    }

    public void save(Word_Exam_Answer word_exam_answer) {
        wordExamAnswerRepository.save(word_exam_answer);
    }

    public void delete(Long id) {
        wordExamAnswerRepository.delete(id);
    }
}
