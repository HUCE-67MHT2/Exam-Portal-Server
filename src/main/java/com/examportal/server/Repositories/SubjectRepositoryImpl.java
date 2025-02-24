package com.examportal.server.Repositories;

import com.examportal.server.Entity.Subject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class SubjectRepositoryImpl implements SubjectRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Subject> getList() {
        String hql = "FROM Subject";
        return entityManager.createQuery(hql).getResultList();

    }

    @Override
    public Subject getSubjectById(Long id) {
        return entityManager.find(Subject.class, id);
    }

    @Override
    public void save(Subject subject) {
        try {
            if (subject.getId() == null) {
                entityManager.persist(subject);
            } else {
                entityManager.merge(subject);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(Subject.class, id));
    }
}
