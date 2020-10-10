package com.phraseup.phupserver.rest;

import com.phraseup.phupserver.dao.UserDAO;
import com.phraseup.phupserver.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserDAO userDAO;

    @Autowired
    public UserRestController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

}
