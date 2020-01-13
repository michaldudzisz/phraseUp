package com.phraseUp.phraseUpServer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class User {

	@NotBlank
	private final String username;
	@NotBlank
	private final String password;
	private final Language language;

	public User(@JsonProperty("username") String username, @JsonProperty("password") String password, @JsonProperty("language") Language language) {

		this.username = username;
		this.password = password;
		this.language = language;
	}

	public String getUsername() {
		return username;
	}
	public Language getLanguage() {
		return language;
	}
	public String getPassword() {
		return password;
	}
}
