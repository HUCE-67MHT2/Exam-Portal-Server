package com.examportal.server.Service;

import com.examportal.server.Entity.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getList();

    Subject getSubjectById(Long id);

    void save(Subject subject);

    void delete(Long id);
}
