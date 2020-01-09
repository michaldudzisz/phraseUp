package com.phraseUp.phraseUpClient.model;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class User {
	private final UUID id;
	@NotBlank
	private final String name;

	public User(UUID id, String name) {
		this.id = id;
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
