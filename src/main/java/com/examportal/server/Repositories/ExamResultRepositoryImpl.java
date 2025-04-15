package com.examportal.server.Repositories;

import com.examportal.server.Entity.Exam;
import com.examportal.server.Entity.ExamResult;
import com.examportal.server.Entity.User;
import com.examportal.server.Request.StudentResultInExamSession;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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
    public ExamResult getExamResultByExamIdAndUserId(Long examId, Long userId) {
        String hql = "FROM ExamResult er WHERE er.exam.id = :examId AND er.user.id = :userId";
        List<ExamResult> results = entityManager.createQuery(hql, ExamResult.class)
                .setParameter("examId", examId)
                .setParameter("userId", userId)
                .getResultList();

        return results.isEmpty() ? null : results.get(0);
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

    @Override
    @Transactional
    public void newExamResult(Long examId, Long userId) {
        String jpql = "SELECT er FROM ExamResult er WHERE er.exam.id = :examId AND er.user.id = :userId";
        List<ExamResult> results = entityManager.createQuery(jpql, ExamResult.class)
                .setParameter("examId", examId)
                .setParameter("userId", userId)
                .getResultList();

        if (!results.isEmpty()) {
            return;
        }

        // Lấy thông tin exam và user từ database
        Exam exam = entityManager.find(Exam.class, examId);
        User user = entityManager.find(User.class, userId);

        if (exam == null || user == null) {
            throw new IllegalArgumentException("Exam hoặc User không tồn tại.");
        }

        Timestamp startTime = new Timestamp(System.currentTimeMillis());
        long endTimeMillis = startTime.getTime() + (exam.getDuration() * 60 * 1000L); // duration là phút
        Timestamp endTime = new Timestamp(endTimeMillis);

        ExamResult examResult = new ExamResult();
        examResult.setExam(exam);
        examResult.setUser(user);
        examResult.setStartTime(startTime);
        examResult.setEndTime(endTime);

        entityManager.persist(examResult);

    }

}
