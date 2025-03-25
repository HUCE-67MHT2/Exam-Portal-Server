package com.examportal.server.Repositories;

import com.examportal.server.Entity.ExamSession;

import java.util.List;

public interface ExamSessionRepository {
    List<ExamSession> getList();

    void save(ExamSession examSession);

    void delete(Long id);

    ExamSession getExamSessionById(Long id);

    List<ExamSession> getExamSessionByTeacherId(Long id);
}
