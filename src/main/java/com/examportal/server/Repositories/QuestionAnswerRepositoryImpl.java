package com.examportal.server.Repositories;

import com.examportal.server.Entity.QuestionAnswer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class QuestionAnswerRepositoryImpl implements QuestionAnswerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<QuestionAnswer> getList() {
        String hql = "FROM QuestionAnswer";
        return entityManager.createQuery(hql, QuestionAnswer.class).getResultList();

    }

    @Override
    public QuestionAnswer getAnswerById(Long id) {
        return entityManager.find(QuestionAnswer.class, id);
    }

    @Override
    public void save(QuestionAnswer answer) {
        try {
            if (answer.getId() == null) {
                entityManager.persist(answer);
            } else {
                entityManager.merge(answer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        QuestionAnswer answer = entityManager.find(QuestionAnswer.class, id);
        entityManager.remove(answer);
    }
}
