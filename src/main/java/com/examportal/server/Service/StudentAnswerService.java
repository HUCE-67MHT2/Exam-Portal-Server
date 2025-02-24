package com.examportal.server.Service;

import com.examportal.server.Entity.Student_Answer;

import java.util.List;

public interface StudentAnswerService {
    List<Student_Answer> getList();

    Student_Answer getStudentAnswerById(Long id);

    void save(Student_Answer student_answer);

    void delete(Long id);
}
