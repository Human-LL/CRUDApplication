package com.example.CRUDApplication.Dao;

import com.example.CRUDApplication.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(User user);

    User addUser(User user);

    User deleteUser(Long id);
}