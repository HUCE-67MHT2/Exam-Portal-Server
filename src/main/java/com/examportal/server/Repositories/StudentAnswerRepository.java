package com.examportal.server.Repositories;

import com.examportal.server.Entity.StudentAnswer;

import java.util.List;

public interface StudentAnswerRepository {
    List<StudentAnswer> getList();

    StudentAnswer getStudentAnswerById(Long id);

    void save(StudentAnswer student_answer);

    void delete(Long id);

    StudentAnswer findExitingUploadAnswer(Long studentId, Long examId, int questionNo);

    List<StudentAnswer> getUploadStudentAnswer(Long examId, Long studentId);
}
