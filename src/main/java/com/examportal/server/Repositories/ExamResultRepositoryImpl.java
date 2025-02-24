package com.examportal.server.Repositories;

import com.examportal.server.Entity.Exam_Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ExamResultRepositoryImpl implements ExamResultRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Exam_Result> getList() {
        String hql = "FROM Exam_Result";
        return entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Exam_Result getExamResultById(Long id) {
        return entityManager.find(Exam_Result.class, id);
    }

    @Override
    public void save(Exam_Result examResult) {
        try {
            if (examResult.getId() == null) {
                entityManager.persist(examResult);
            } else {
                entityManager.merge(examResult);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(Exam_Result.class, id));
    }
}
