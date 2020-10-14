package com.phraseup.phupserver.rest;

import com.phraseup.phupserver.entity.User;
import com.phraseup.phupserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUsers(@PathVariable int userId) {
        User user = userService.getUserById(userId);

        if (user == null) {
            throw new RuntimeException("User with specified id not found.");
        }

        return user;
    }

    @PostMapping("/")
    public User addUser(@RequestBody User user) {
        user.setId(0);
        userService.saveUser(user);
        return user;
    }

    @PutMapping("/")
    public User updateUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @DeleteMapping("/")
    public String deleteUser(@PathVariable int userId) {
        if (userService.getUserById(userId) == null)
            throw new RuntimeException("User with specified id not found:" + userId + ".");
        userService.deleteUser(userId);
        return "Deleted user with id of " + userId + ".";
    }
}
