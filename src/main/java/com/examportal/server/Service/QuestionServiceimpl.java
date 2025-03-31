package com.examportal.server.Service;

import com.examportal.server.Entity.Question;
import com.examportal.server.Repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceimpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> getList() {
        return questionRepository.getList();
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void delete(Long id) {
        questionRepository.delete(id);
    }

    @Override
    public List<Question> getQuestionsByExamSessionId(Long id) {
        return questionRepository.getQuestionsByExamSessionId(id);
    }
}
