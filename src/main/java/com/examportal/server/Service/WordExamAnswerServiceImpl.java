package com.examportal.server.Service;

import com.examportal.server.Entity.WordExamAnswer;
import com.examportal.server.Repositories.WordExamAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordExamAnswerServiceImpl implements WordExamAnswerService {
    @Autowired
    private WordExamAnswerRepository wordExamAnswerRepository;

    public List<WordExamAnswer> getList() {
        return wordExamAnswerRepository.getList();
    }

    public WordExamAnswer getWordExamAnswerById(Long id) {
        return wordExamAnswerRepository.getWordExamAnswerById(id);
    }

    public void save(WordExamAnswer word_exam_answer) {
        wordExamAnswerRepository.save(word_exam_answer);
    }

    public void delete(Long id) {
        wordExamAnswerRepository.delete(id);
    }
}
