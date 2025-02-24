package com.examportal.server.Repositories;

import com.examportal.server.Entity.Student_Answer;
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
    public List<Student_Answer> getList() {
        String hql = "FROM Student_Answer";
        return entityManager.createQuery(hql).getResultList();

    }

    @Override
    public Student_Answer getStudentAnswerById(Long id) {
        return entityManager.find(Student_Answer.class, id);
    }

    @Override
    public void save(Student_Answer student_answer) {
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
        entityManager.remove(entityManager.find(Student_Answer.class, id));
    }
}
