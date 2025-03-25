package com.examportal.server.Service;

import com.examportal.server.Entity.QuestionAnswer;
import com.examportal.server.Repositories.QuestionAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionAnswerServiceImpl implements QuestionAnswerService {

    @Autowired
    private QuestionAnswerRepository answerRepository;

    @Override
    public List<QuestionAnswer> getList() {
        return answerRepository.getList();
    }

    @Override
    public QuestionAnswer getAnswerById(Long id) {
        return answerRepository.getAnswerById(id);
    }

    @Override
    public void save(QuestionAnswer answer) {
        answerRepository.save(answer);
    }

    @Override
    public void delete(Long id) {
        answerRepository.delete(id);
    }
}
