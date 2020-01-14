package com.phraseUp.phraseUpServer.api;

import com.phraseUp.phraseUpServer.model.LogInData;
import com.phraseUp.phraseUpServer.model.User;
import com.phraseUp.phraseUpServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("phraseup/users")
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

	@PostMapping("/get-user-by-username")
	public User getUserByUsername(@Valid @NonNull @RequestBody LogInData log) {
		if (userService.checkLogs(log))
			return userService.getUserByUsername(log.getUsername()).orElse(null);
		else
			return null;
	}

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	/*@GetMapping(path = "{username}")
	public User getUserById(@PathVariable("id") UUID id) {
		return userService.getUserById(id).orElse(null);
	}

	@PutMapping(path = "{id}")
	public void updateUser(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody User user) {
		userService.updateUser(id, user);
	}

	@DeleteMapping(path = "{id}")
	public void deleteUser(@PathVariable("id") UUID id) {
		userService.deleteUser(id);
	}*/
}
