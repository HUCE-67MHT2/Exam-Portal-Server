package com.examportal.server.Repositories;

import com.examportal.server.Entity.StudentAnswer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class StudentAnswerRepositoryImpl implements StudentAnswerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<StudentAnswer> getList() {
        String hql = "FROM StudentAnswer";
        return entityManager.createQuery(hql).getResultList();

    }

    @Override
    public StudentAnswer getStudentAnswerById(Long id) {
        return entityManager.find(StudentAnswer.class, id);
    }

    @Override
    public void save(StudentAnswer student_answer) {
        try {
            if (student_answer.getId() == null) {
                entityManager.persist(student_answer);
            } else {
                entityManager.merge(student_answer);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(StudentAnswer.class, id));
    }
}
