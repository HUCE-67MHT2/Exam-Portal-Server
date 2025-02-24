package com.examportal.server.Service;

import com.examportal.server.Entity.Student_Answer;
import com.examportal.server.Repositories.StudentAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentAnswerServiceImpl implements StudentAnswerService {

    @Autowired
    private StudentAnswerRepository studentAnswerRepository;

    @Override
    public List<Student_Answer> getList() {
        return studentAnswerRepository.getList();
    }

    @Override
    public Student_Answer getStudentAnswerById(Long id) {
        return studentAnswerRepository.getStudentAnswerById(id);
    }

    @Override
    public void save(Student_Answer student_answer) {
        studentAnswerRepository.save(student_answer);
    }

    @Override
    public void delete(Long id) {
        studentAnswerRepository.delete(id);
    }
}
