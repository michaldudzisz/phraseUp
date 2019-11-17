package com.phraseUp.phraseUpServer.api;

import com.phraseUp.phraseUpServer.model.User;
import com.phraseUp.phraseUpServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public void addUser(@Valid @NonNull @RequestBody User user) {
		userService.addUser(user);
	}

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping(path = "{id}")
	public User getUserById(@PathVariable("id") UUID id) {
		return userService.getUserById(id).orElse(null); // O TU można wyjątek(404)
	}

	@PutMapping(path = "{id}")
	public void updateUser(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody User user) {
		userService.updateUser(id, user);
	}

	@DeleteMapping(path = "{id}")
	public void deleteUser(@PathVariable("id") UUID id) {
		userService.deleteUser(id);
	}
}
