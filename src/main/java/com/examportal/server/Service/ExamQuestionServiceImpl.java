package com.examportal.server.Service;

import com.examportal.server.Entity.ExamQuestion;
import com.examportal.server.Repositories.ExamQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamQuestionServiceImpl implements ExamQuestionService {

    @Autowired
    private ExamQuestionRepository examQuestionRepository;

    @Override
    public List<ExamQuestion> getList() {
        return examQuestionRepository.getList();
    }

    @Override
    public ExamQuestion getExamQuestionById(Long id) {
        return examQuestionRepository.getExamQuestionById(id);
    }

    @Override
    public void save(ExamQuestion examQuestion) {
        examQuestionRepository.save(examQuestion);
    }

    @Override
    public void delete(ExamQuestion examQuestion) {
        examQuestionRepository.delete(examQuestion);
    }
}
