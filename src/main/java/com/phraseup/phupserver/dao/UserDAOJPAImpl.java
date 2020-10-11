package com.phraseup.phupserver.dao;

import com.phraseup.phupserver.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOJPAImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAllUsers() {
        Query query = entityManager.createQuery("from User");
        List<User> users = query.getResultList();
        return users;
    }

    @Override
    public User getUserById(int id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(int id) {
        Query query = entityManager.createQuery("delete from User where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
