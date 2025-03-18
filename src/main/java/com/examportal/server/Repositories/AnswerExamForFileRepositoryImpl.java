package com.examportal.server.Repositories;

import com.examportal.server.Entity.AnswerForExamFile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnswerExamForFileRepositoryImpl implements AnswerExamForFileRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AnswerForExamFile> getLists() {
        String hql = "From AnswerForExamFile";
        return entityManager.createQuery(hql).getResultList();

    }

    @Override
    public AnswerForExamFile getAnswerForExamFileById(Long id) {
        return entityManager.find(AnswerForExamFile.class, id);
    }

    @Override
    public void addorUpdateAnswerForExamFile(AnswerForExamFile answerForExamFile) {
        try {
            if (answerForExamFile.getId() == null) {
                entityManager.persist(answerForExamFile);
            } else {
                entityManager.merge(answerForExamFile);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAnswerForExamFile(Long id) {

        entityManager.remove(entityManager.find(AnswerForExamFile.class, id));
    }

    @Override
    public List<AnswerForExamFile> getAnswerForExamFileByExamId(Long examId) {
        String hql = "From AnswerForExamFile a where a.exam.id = :examId";
        return entityManager.createQuery(hql).setParameter("examId", examId).getResultList();
    }
}
