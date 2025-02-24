package com.examportal.server.Repositories;

import com.examportal.server.Entity.Subject;

import java.util.List;

public interface SubjectRepository {
    List<Subject> getList();

    Subject getSubjectById(Long id);

    void save(Subject subject);

    void delete(Long id);
}
