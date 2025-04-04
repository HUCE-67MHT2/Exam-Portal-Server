package com.examportal.server.Repositories;

import com.examportal.server.Entity.ExamSession;
import com.examportal.server.Request.ExamSessionRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ExamSessionRepositoryImpl implements ExamSessionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ExamSession> getList() {
        String hql = "from ExamSession";
        return entityManager.createQuery(hql, ExamSession.class).getResultList();
    }

    @Override
    public void save(ExamSession examSession) {

        try {
            if (examSession.getId() == null) {
                entityManager.persist(examSession);
            } else {
                entityManager.merge(examSession);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(ExamSession.class, id));
    }

    @Override
    public ExamSession getExamSessionById(Long id) {
        return entityManager.find(ExamSession.class, id);
    }

    @Override
    public List<ExamSession> getExamSessionByTeacherId(Long id) {
        String hql = "from ExamSession where teacherId = :teacherId";
        return entityManager.createQuery(hql, ExamSession.class).setParameter("teacherId", id).getResultList();
    }

    @Override
    public ExamSession getExamSessionInfo(Long id) {
        String hql = "SELECT e FROM ExamSession e WHERE e.id = :sessionId";
        return entityManager.createQuery(hql, ExamSession.class)
                .setParameter("sessionId", id)
                .getSingleResult();
    }

    @Override
    public ExamSession updateExamSessionById(Long id, ExamSessionRequest examSession) {
        ExamSession existingExamSession = entityManager.find(ExamSession.class, id);
        if (existingExamSession != null && existingExamSession.getCreateDate() != null) {
            existingExamSession.setName(examSession.getExam_sessions_name());
            existingExamSession.setDescription(examSession.getExam_sessions_description());
            existingExamSession.setPassword(examSession.getExam_sessions_password());
            existingExamSession.setStartDate(examSession.getExam_sessions_start_date());
            existingExamSession.setEndDate(examSession.getExam_sessions_end_date());

            entityManager.merge(existingExamSession); // Cập nhật vào DB
            return existingExamSession;
        } else {
            System.out.println("End date nhu lon");
            return null;
        }

        // Trả về null nếu không tìm thấy kỳ thi
    }
}
