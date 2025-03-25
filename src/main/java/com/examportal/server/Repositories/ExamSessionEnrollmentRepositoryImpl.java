package com.examportal.server.Repositories;

import com.examportal.server.Entity.ExamSessionEnrollment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Transactional
public class ExamSessionEnrollmentRepositoryImpl implements ExamSessionEnrollmentRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<ExamSessionEnrollment> getList() {
        String hql = "From ExamSessionEnrollment";
        return entityManager.createQuery(hql, ExamSessionEnrollment.class).getResultList();
    }

    @Override
    public ExamSessionEnrollment getExamPeriodEnrollment(Long id) {
        return entityManager.find(ExamSessionEnrollment.class, id);
    }

    @Override
    public void save(ExamSessionEnrollment examPeriodEnrollment) {

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

        entityManager.remove(entityManager.find(ExamSessionEnrollment.class, id));
    }
}
