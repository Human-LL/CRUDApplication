package com.example.CRUDApplication.Dao;

import com.example.CRUDApplication.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User updateUser(User user) {
        entityManager.merge(user);
        entityManager.flush();
        return user;
    }

    @Override
    public User addUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
        return user;
    }

    @Override
    public User deleteUser(Long id) {
        User user = getUserById(id);
        if (null == user) {
            throw new NullPointerException("User not found");
        }
        entityManager.remove(user);
        entityManager.flush();
        return user;
    }
}