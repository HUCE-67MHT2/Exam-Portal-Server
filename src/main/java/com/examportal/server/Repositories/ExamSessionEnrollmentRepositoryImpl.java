package com.examportal.server.Repositories;

import com.examportal.server.Entity.ExamSessionEnrollment;
import com.examportal.server.Entity.User;
import com.examportal.server.Request.StudentInExamSessionEnrollmentRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    public ExamSessionEnrollment getExamSessionEnrollment(Long id) {
        return entityManager.find(ExamSessionEnrollment.class, id);
    }

    @Override
    public void save(ExamSessionEnrollment examSessionEnrollment) {

        try {
            if (examSessionEnrollment.getId() == null) {
                entityManager.persist(examSessionEnrollment);
            } else {
                entityManager.merge(examSessionEnrollment);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {

        entityManager.remove(entityManager.find(ExamSessionEnrollment.class, id));
    }

   @Override
    public List<StudentInExamSessionEnrollmentRequest> getInfoStudentInExamSessionEnrollment(Long examSessionId) {
        String sql = "FROM User u " +
                     "JOIN ExamSessionEnrollment e ON u.id = e.user.id " +
                     "WHERE e.examSessionId = :examSessionId";

        List<User> results = entityManager.createQuery(sql, User.class)
                                              .setParameter("examSessionId", examSessionId)
                                              .getResultList();

        List<StudentInExamSessionEnrollmentRequest> studentInfoList = new ArrayList<>();
        for (User result : results) {
            StudentInExamSessionEnrollmentRequest studentInfo = new StudentInExamSessionEnrollmentRequest();
            studentInfo.setStudent_number(result.getStudentNumber());
            studentInfo.setClass_name(result.getClassName());
            studentInfo.setStudent_name(result.getFullName());
            studentInfoList.add(studentInfo);
        }

        return studentInfoList;
    }

}
