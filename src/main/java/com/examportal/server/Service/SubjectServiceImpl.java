package com.examportal.server.Service;

import com.examportal.server.Entity.Subject;
import com.examportal.server.Repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Subject> getList() {
        return subjectRepository.getList();
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.getSubjectById(id);
    }

    @Override
    public void save(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public void delete(Long id) {
        subjectRepository.delete(id);
    }

}
