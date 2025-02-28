package com.examportal.server.Repositories;

import com.examportal.server.Entity.WordExamAnswer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class WordExamAnswerRepositoryImpl implements WordExamAnswerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<WordExamAnswer> getList() {
        String hql = "FROM WordExamAnswer";
        return entityManager.createQuery(hql).getResultList();

    }

    @Override
    public WordExamAnswer getWordExamAnswerById(Long id) {
        return entityManager.find(WordExamAnswer.class, id);
    }

    @Override
    public void save(WordExamAnswer word_exam_answer) {
        try {
            if (word_exam_answer.getId() == null) {
                entityManager.persist(word_exam_answer);
            } else {
                entityManager.merge(word_exam_answer);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(WordExamAnswer.class, id));
    }
}
