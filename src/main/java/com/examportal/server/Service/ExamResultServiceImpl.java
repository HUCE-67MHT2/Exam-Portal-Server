package com.examportal.server.Service;

import com.examportal.server.Entity.Exam_Result;
import com.examportal.server.Repositories.ExamResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamResultServiceImpl implements ExamResultService {

    @Autowired
    private ExamResultRepository examResultRepository;

    @Override
    public List<Exam_Result> getList() {
        return examResultRepository.getList();
    }

    @Override
    public Exam_Result getExamResultById(Long id) {
        return examResultRepository.getExamResultById(id);
    }

    @Override
    public void save(Exam_Result examResult) {
        examResultRepository.save(examResult);
    }

    @Override
    public void delete(Long id) {
        examResultRepository.delete(id);
    }
}
