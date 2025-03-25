package com.examportal.server.Repositories;

import com.examportal.server.Entity.ExamPeriod;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Transactional
public class ExamPeriodRepositoryImpl implements ExamPeriodRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ExamPeriod> getList() {
        String hql = "from ExamPeriod";
        return entityManager.createQuery(hql,ExamPeriod.class).getResultList();
    }

    @Override
    public void save(ExamPeriod examPeriod) {

        try {
            if (examPeriod.getId() == null) {
                entityManager.persist(examPeriod);
            } else {
                entityManager.merge(examPeriod);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(ExamPeriod.class, id));
    }

    @Override
    public ExamPeriod getExamPeriodById(Long id) {
        return entityManager.find(ExamPeriod.class, id);
    }

    @Override
    public List<ExamPeriod> getExamPeriodsByTeacherId(Long id) {
        String hql = "from ExamPeriod where teacherId = :teacherId";
        return entityManager.createQuery(hql,ExamPeriod.class).setParameter("teacherId",id).getResultList();
    }
}
