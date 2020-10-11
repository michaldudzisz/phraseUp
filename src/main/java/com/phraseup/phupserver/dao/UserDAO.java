package com.phraseup.phupserver.dao;


import com.phraseup.phupserver.entity.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    User getUserById(int id);

    void saveUser(User user);

    void deleteUser(int id);
}
