package com.examportal.server.Service;

import com.examportal.server.Entity.Exam;
import com.examportal.server.Repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public List<Exam> getList() {
        return examRepository.getList();
    }

    @Override
    public Exam getExamById(Long id) {
        return examRepository.getExamById(id); // Sửa lỗi dấu chấm phẩy
    }

    @Override
    public void save(Exam exam) {
        examRepository.save(exam);
    }

    @Override
    public void delete(Long id) {
        examRepository.delete(id);
    }
}
