package com.examportal.server.Service;

import com.examportal.server.Entity.ExamPeriodEnrollment;
import com.examportal.server.Repositories.ExamSessionEnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExamPeriodEnrollmentServiceImpl implements ExamPeriodEnrollmentService{
    @Autowired
    private ExamSessionEnrollmentRepository examPeriodEnrollmentRepository;
    @Override
    public List<ExamPeriodEnrollment> getList() {
        return examPeriodEnrollmentRepository.getList();
    }

    @Override
    public ExamPeriodEnrollment getExamPeriodEnrollment(Long id) {
        return examPeriodEnrollmentRepository.getExamPeriodEnrollment(id);
    }

    @Override
    public void save(ExamPeriodEnrollment examPeriodEnrollment) {
        examPeriodEnrollmentRepository.save(examPeriodEnrollment);
    }

    @Override
    public void delete(Long id) {
        examPeriodEnrollmentRepository.delete(id);
    }
}
