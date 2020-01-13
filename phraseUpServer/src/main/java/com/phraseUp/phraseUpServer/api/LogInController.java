package com.phraseUp.phraseUpServer.api;


import com.phraseUp.phraseUpServer.model.LogInData;
import com.phraseUp.phraseUpServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("phraseup/login")
@RestController
public class LogInController {

	private final UserService userService;

	@Autowired
	public LogInController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public Boolean logInRequestHandler(@Valid @NonNull @RequestBody LogInData log) {
		return userService.checkLogs(log);
	}

	@PostMapping("/register")
	public Boolean registerRequestHandler(@Valid @NonNull @RequestBody LogInData log) {
		String username = log.getUsername();
		String password = log.getPassword();
		System.out.println("Creating user... Username: " + username + ", password: " + password);

		if (userService.getUserByUsername(username).isPresent())
			return false;
		userService.addUser(log);
		return true;
	}
}
