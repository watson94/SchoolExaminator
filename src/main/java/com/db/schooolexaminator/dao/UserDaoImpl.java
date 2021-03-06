package com.db.schooolexaminator.dao;

import com.db.schooolexaminator.model.Configuration;
import com.db.schooolexaminator.model.OperationConstraint;
import com.db.schooolexaminator.model.Teacher;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Teacher> findByName(String userName) {
        Query query = entityManager.createQuery("select t from Teacher as t where t.userName=:userName");
        List<Teacher> teachers = query.setParameter("userName", userName).getResultList();

        return teachers;
    }

    @Override
    public void updateUser(Teacher teacher) {
        entityManager.merge(teacher);
    }

    @Override
    public void addUser(Teacher teacher) {
        entityManager.persist(teacher);
    }

    @Override
    public Configuration getConfigurationById(int id) {
        Query query = entityManager.createQuery("select c from Configuration as c where c.id =:id");
        List<Configuration> configuration = query.setParameter("id", id).getResultList();
        return configuration.get(0);
    }
}
