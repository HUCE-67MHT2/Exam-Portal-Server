package com.examportal.server.Service;

import com.examportal.server.Entity.StudentAnswer;
import com.examportal.server.Repositories.StudentAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentAnswerServiceImpl implements StudentAnswerService {

    @Autowired
    private StudentAnswerRepository studentAnswerRepository;

    @Override
    public List<StudentAnswer> getList() {
        return studentAnswerRepository.getList();
    }

    @Override
    public StudentAnswer getStudentAnswerById(Long id) {
        return studentAnswerRepository.getStudentAnswerById(id);
    }

    @Override
    public void save(StudentAnswer student_answer) {
        studentAnswerRepository.save(student_answer);
    }

    @Override
    public void delete(Long id) {
        studentAnswerRepository.delete(id);
    }
}
