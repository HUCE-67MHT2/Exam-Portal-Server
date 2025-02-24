package com.examportal.server.Repositories;

import com.examportal.server.Entity.Word_Exam_Answer;
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
    public List<Word_Exam_Answer> getList() {
        String hql = "FROM Word_Exam_Answer";
        return entityManager.createQuery(hql).getResultList();

    }

    @Override
    public Word_Exam_Answer getWordExamAnswerById(Long id) {
        return entityManager.find(Word_Exam_Answer.class, id);
    }

    @Override
    public void save(Word_Exam_Answer word_exam_answer) {
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
        entityManager.remove(entityManager.find(Word_Exam_Answer.class, id));
    }
}
