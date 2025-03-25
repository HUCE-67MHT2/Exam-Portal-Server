package com.examportal.server.Service;

import com.examportal.server.Entity.ExamSessionEnrollment;
import com.examportal.server.Repositories.ExamSessionEnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamSessionEnrollmentServiceImpl implements ExamSessionEnrollmentService {
    @Autowired
    private ExamSessionEnrollmentRepository ExamSessionEnrollmentRepository;

    @Override
    public List<ExamSessionEnrollment> getList() {
        return ExamSessionEnrollmentRepository.getList();
    }

    @Override
    public ExamSessionEnrollment getExamSessionEnrollment(Long id) {
        return ExamSessionEnrollmentRepository.getExamSessionEnrollment(id);
    }

    @Override
    public void save(ExamSessionEnrollment examSessionEnrollment) {
        ExamSessionEnrollmentRepository.save(examSessionEnrollment);
    }

    @Override
    public void delete(Long id) {
        ExamSessionEnrollmentRepository.delete(id);
    }
}
