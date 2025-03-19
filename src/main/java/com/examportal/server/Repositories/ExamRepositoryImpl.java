package com.examportal.server.Repositories;

import com.examportal.server.Entity.Exam;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ExamRepositoryImpl implements ExamRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Exam> getList() {
        String hql = "FROM Exam";
        return entityManager.createQuery(hql).getResultList();

    }

    @Override
    public Exam getExamById(Long id) {
        return entityManager.find(Exam.class, id);
    }

    @Override
    public void save(Exam exam) {
        try {
            if (exam.getId() == null) {
                entityManager.persist(exam);
            } else {
                entityManager.merge(exam);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(Exam.class, id));
    }

    public List<Exam> getExamByTeacherId(Long id) {
        String hql = "FROM Exam e WHERE e.teacher_id = :id";
        return entityManager.createQuery(hql).setParameter("id", id).getResultList();
    }
}
