package com.phraseUp.phraseUpClient.model;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class User {
	private UUID id;
	@NotBlank
	private String username;

	public User() {
	}

	public User(UUID id, String name) {
		this.id = id;
		this.username = name;
	}

	public UUID getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
