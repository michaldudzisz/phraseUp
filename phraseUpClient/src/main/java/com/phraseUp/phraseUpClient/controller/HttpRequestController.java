package com.phraseUp.phraseUpClient.controller;

import com.phraseUp.phraseUpClient.model.LogInData;
import com.phraseUp.phraseUpClient.model.User;
import org.springframework.web.client.RestTemplate;

class HttpRequestController {

	static User getUserInfo(LogInData log) {
		final String url = "http://localhost:8091/phraseup/users/get-user-by-username/";
		User user =  new RestTemplate().postForObject(url, log, User.class);
		System.out.println(user.getUsername());
		return user;
	}

	static Boolean sendLogInRequest(LogInData log) {
		final String url = "http://localhost:8091/phraseup/login";

		Boolean ifLogged = new RestTemplate().postForObject(url, log, Boolean.class);

		System.out.println("Logged successfully: " + ifLogged);
		return ifLogged;
	}

	static Boolean sendAccountCreateRequest(User user) {
		final String url = "http://localhost:8091/phraseup/login/register";

		Boolean ifLogged = new RestTemplate().postForObject(url, user, Boolean.class);

		System.out.println("Logged successfully: " + ifLogged);
		return ifLogged;
	}
}
