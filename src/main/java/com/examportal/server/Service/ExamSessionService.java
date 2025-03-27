package com.examportal.server.Service;

import com.examportal.server.Entity.ExamSession;
import com.examportal.server.Request.ExamSessionRequest;

import java.util.List;

public interface ExamSessionService {
    List<ExamSession> getList();

    void save(ExamSession examSession);

    void delete(Long id);

    ExamSession getExamSessionById(Long id);

    List<ExamSession> getExamSessionByTeacherId(Long id);

    ExamSession getExamSessionInfo(Long id);

    ExamSession updateExamSessionById(Long id, ExamSessionRequest examSession);
}
