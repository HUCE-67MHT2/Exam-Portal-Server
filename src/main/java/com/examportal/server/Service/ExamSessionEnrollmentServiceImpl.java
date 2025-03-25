package com.examportal.server.Service;

import com.examportal.server.Entity.ExamSessionEnrollment;
import com.examportal.server.Repositories.ExamSessionEnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExamSessionEnrollmentServiceImpl implements ExamSessionEnrollmentService {
    @Autowired
    private ExamSessionEnrollmentRepository examPeriodEnrollmentRepository;
    @Override
    public List<ExamSessionEnrollment> getList() {
        return examPeriodEnrollmentRepository.getList();
    }

    @Override
    public ExamSessionEnrollment getExamPeriodEnrollment(Long id) {
        return examPeriodEnrollmentRepository.getExamPeriodEnrollment(id);
    }

    @Override
    public void save(ExamSessionEnrollment examPeriodEnrollment) {
        examPeriodEnrollmentRepository.save(examPeriodEnrollment);
    }

    @Override
    public void delete(Long id) {
        examPeriodEnrollmentRepository.delete(id);
    }
}
