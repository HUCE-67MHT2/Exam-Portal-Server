package com.examportal.server.Service;

import com.examportal.server.Entity.ExamPeriod;
import com.examportal.server.Repositories.ExamPeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExamPeriodServiceImpl implements ExamPeriodService {

    @Autowired
    private ExamPeriodRepository examPeriodRepository;

    @Override
    public List<ExamPeriod> getList() {
        return examPeriodRepository.getList();
    }

    @Override
    public void save(ExamPeriod examPeriod) {
        examPeriodRepository.save(examPeriod);
    }

    @Override
    public void delete(Long id) {
        examPeriodRepository.delete(id);
    }

    @Override
    public ExamPeriod getExamPeriodById(Long id) {
        return examPeriodRepository.getExamPeriodById(id);
    }

    @Override
    public List<ExamPeriod> getExamPeriodsByTeacherId(Long id) {
        return examPeriodRepository.getExamPeriodsByTeacherId(id);
    }
}
