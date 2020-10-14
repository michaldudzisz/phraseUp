package com.phraseup.phupserver.service;

import com.phraseup.phupserver.entity.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public User getUserById(int id);

    public void saveUser(User user);

    public void deleteUser(int id);
}
