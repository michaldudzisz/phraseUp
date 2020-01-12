package com.phraseUp.phraseUpClient.model;

public class LogInData {

	private final String username;
	private final String password;

	public LogInData(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
