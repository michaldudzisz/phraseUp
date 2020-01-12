package com.phraseUp.phraseUpServer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class User {

	private final UUID id;
	@NotBlank
	private final String username;

	public User(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {

		this.id = id;
		this.username = name;
		System.out.println("mam id: " +  id); // -> dwa razy się to tworzy niepotrzebnie
	}

	public UUID getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}
}
