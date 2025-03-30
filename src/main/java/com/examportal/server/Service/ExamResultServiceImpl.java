package com.examportal.server.Service;

import com.examportal.server.Entity.ExamResult;
import com.examportal.server.Repositories.ExamResultRepository;
import com.examportal.server.Request.StudentResultInExamSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamResultServiceImpl implements ExamResultService {

    @Autowired
    private ExamResultRepository examResultRepository;

    @Override
    public List<ExamResult> getList() {
        return examResultRepository.getList();
    }

    @Override
    public ExamResult getExamResultById(Long id) {
        return examResultRepository.getExamResultById(id);
    }

    @Override
    public void save(ExamResult examResult) {
        examResultRepository.save(examResult);
    }

    @Override
    public void delete(Long id) {
        examResultRepository.delete(id);
    }

    @Override
    public List<StudentResultInExamSession> getListStudentResultInExamSession(Long examSessionId) {
        return examResultRepository.getListStudentResultInExamSession(examSessionId);
    }
}
