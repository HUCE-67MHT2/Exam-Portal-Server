package com.examportal.server.Service;

import com.examportal.server.Entity.ExamSession;
import com.examportal.server.Repositories.ExamSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    @Autowired
    private ExamSessionRepository examPeriodRepository;

    @Override
    public List<ExamSession> getList() {
        return examPeriodRepository.getList();
    }

    @Override
    public void save(ExamSession examSession) {
        examPeriodRepository.save(examSession);
    }

    @Override
    public void delete(Long id) {
        examPeriodRepository.delete(id);
    }

    @Override
    public ExamSession getExamSessionById(Long id) {
        return examPeriodRepository.getExamSessionById(id);
    }

    @Override
    public List<ExamSession> getExamSessionByTeacherId(Long id) {
        return examPeriodRepository.getExamSessionByTeacherId(id);
    }

    @Override
    public ExamSession getExamSessionInfo(Long id) {
        return examPeriodRepository.getExamSessionInfo(id);
    }
}
