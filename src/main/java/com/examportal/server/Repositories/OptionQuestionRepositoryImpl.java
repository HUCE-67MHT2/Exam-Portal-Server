package com.examportal.server.Repositories;

import com.examportal.server.Entity.Option;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Transactional
public class OptionQuestionRepositoryImpl implements OptionQuestionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Option> getList() {
        String hql = "FROM Option";
        return entityManager.createQuery(hql,Option.class).getResultList();
    }

    @Override
    public Option getOptionById(Long id) {
        return entityManager.find(Option.class, id);
    }

    @Override
    public void save(Option option) {
        try {
            if (option.getId() == null) {
                entityManager.persist(option);
            } else {
                entityManager.merge(option);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(Option.class, id));
    }
}
