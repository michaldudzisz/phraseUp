package com.phraseup.phupserver.rest;

import com.phraseup.phupserver.entity.User;
import com.phraseup.phupserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public User getUsers(@PathVariable int userId) {
        User user = userService.getUserById(userId);

        if (user == null) {
            throw new RuntimeException("User with specified id not found.");
        }

        return user;
    }

    @PostMapping("/users/")
    public User addUser(@RequestBody User user) {
        user.setId(0);
        userService.saveUser(user);
        return user;
    }

    @PutMapping("/users/")
    public User updateUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable int userId) {
        if (userService.getUserById(userId) == null)
            throw new RuntimeException("User with specified id not found:" + userId + ".");
        userService.deleteUser(userId);
        return "Deleted user with id of " + userId + ".";
    }
}
