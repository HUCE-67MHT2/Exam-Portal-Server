package com.examportal.server.Service;

import com.examportal.server.Entity.Answer;
import com.examportal.server.Repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;
    @Override
    public List<Answer> getList() {
        return answerRepository.getList();
    }

    @Override
    public Answer getAnswerById(Long id) {
        return answerRepository.getAnswerById(id);
    }

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public void delete(Long id) {
        answerRepository.delete(id);
    }
}
