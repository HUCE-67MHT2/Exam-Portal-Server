package com.examportal.server.Repositories;

import com.examportal.server.Entity.ExamQuestion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ExamQuestionRepositoryImpl implements ExamQuestionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ExamQuestion> getList() {
        String hql = "FROM ExamQuestion";
        return entityManager.createQuery(hql, ExamQuestion.class).getResultList();
    }

    @Override
    public ExamQuestion getExamQuestionById(Long id) {
        return entityManager.find(ExamQuestion.class, id);
    }

    @Override
    public void save(ExamQuestion examQuestion) {

        try {
            if (examQuestion.getId() == null) {
                entityManager.persist(examQuestion);
            } else {
                entityManager.merge(examQuestion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ExamQuestion examQuestion) {
        entityManager.remove(examQuestion);
    }
}
