package com.phraseUp.phraseUpClient.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	private final String username;
	private final String password;
	private final Language language;

	public User(@JsonProperty("username") String username, @JsonProperty("password") String password, @JsonProperty("language") Language language) {
		this.username = username;
		this.password = password;
		this.language = language;
	}

	public Language getLanguage() {
		return language;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
