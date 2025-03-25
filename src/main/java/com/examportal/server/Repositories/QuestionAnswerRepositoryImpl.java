package com.examportal.server.Repositories;

import com.examportal.server.Entity.Answer;
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
    public List<Answer> getList() {
        String hql = "FROM Answer";
        return entityManager.createQuery(hql,Answer.class).getResultList();

    }

    @Override
    public Answer getAnswerById(Long id) {
        return entityManager.find(Answer.class, id);
    }

    @Override
    public void save(Answer answer) {
       try{
           if (answer.getId() == null) {
                entityManager.persist(answer);
              } else {
                entityManager.merge(answer);
           }
       }
       catch (Exception e){
           e.printStackTrace();
       }
    }

    @Override
    public void delete(Long id) {
        Answer answer = entityManager.find(Answer.class, id);
        entityManager.remove(answer);
    }
}
