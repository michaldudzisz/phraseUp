package com.phraseUp.phraseUpClient.controller;

import com.phraseUp.phraseUpClient.model.LogInData;
import com.phraseUp.phraseUpClient.model.User;
import org.springframework.web.client.RestTemplate;

class HttpRequestController {

	static User getUserInfo(LogInData log) {
		final String url = "http://localhost:8091/phraseup/users/get-user-by-username/";
		System.out.println("xD");
		User user =  new RestTemplate().postForObject(url, log, User.class);
		System.out.println(user.getUsername());
		return user;
	}
}
