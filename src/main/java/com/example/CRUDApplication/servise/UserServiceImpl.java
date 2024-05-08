package com.example.CRUDApplication.servise;

import com.example.CRUDApplication.model.User;
import com.example.CRUDApplication.Dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        User user = userDao.getUserById(id);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public User addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        if (userDao.getUserById(id) == null) {
            return null;
        }
        return userDao.updateUser(user);
    }

    @Override
    public User deleteUser(Long id) {
        if (userDao.getUserById(id) == null) {
            return null;
        }
        return userDao.deleteUser(id);
    }
}