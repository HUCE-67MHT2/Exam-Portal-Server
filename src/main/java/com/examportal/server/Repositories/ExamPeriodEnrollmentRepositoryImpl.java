package com.examportal.server.Repositories;

import com.examportal.server.Entity.ExamPeriodEnrollment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Transactional
public class ExamPeriodEnrollmentRepositoryImpl implements ExamPeriodEnrollmentRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<ExamPeriodEnrollment> getList() {
        String hql = "From ExamPeriodEnrollment";
        return entityManager.createQuery(hql, ExamPeriodEnrollment.class).getResultList();
    }

    @Override
    public ExamPeriodEnrollment getExamPeriodEnrollment(Long id) {
        return entityManager.find(ExamPeriodEnrollment.class, id);
    }

    @Override
    public void save(ExamPeriodEnrollment examPeriodEnrollment) {

        try {
            if (examPeriodEnrollment.getId() == null) {
                entityManager.persist(examPeriodEnrollment);
            } else {
                entityManager.merge(examPeriodEnrollment);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {

        entityManager.remove(entityManager.find(ExamPeriodEnrollment.class, id));
    }
}
