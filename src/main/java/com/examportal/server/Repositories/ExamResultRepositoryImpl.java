package com.examportal.server.Repositories;

import com.examportal.server.Entity.ExamResult;
import com.examportal.server.Request.StudentResultInExamSession;
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
    public List<ExamResult> getList() {
        String hql = "FROM ExamResult";
        return entityManager.createQuery(hql).getResultList();
    }

    @Override
    public ExamResult getExamResultById(Long id) {
        return entityManager.find(ExamResult.class, id);
    }

    @Override
    public void save(ExamResult examResult) {
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
        entityManager.remove(entityManager.find(ExamResult.class, id));
    }

    @Override
    public List<StudentResultInExamSession> getListStudentResultInExamSession(Long examSessionId) {
        String jpql = "SELECT new com.examportal.server.Request.StudentResultInExamSession( " +
                "u.fullName, u.studentNumber, u.className, e.name, er.totalScore, er.submitTime) " +
                "FROM ExamResult er " +
                "JOIN er.user u " +
                "JOIN er.exam e " +
                "WHERE e.examSessionId = :examSessionId";

        return entityManager.createQuery(jpql, StudentResultInExamSession.class)
                .setParameter("examSessionId", examSessionId)
                .getResultList();
    }
}
