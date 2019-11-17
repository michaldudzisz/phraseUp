package com.phraseUp.phraseUpServer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class User {

	private final UUID id;
	private final String name;

	public User(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {
		this.id = id;
		this.name = name;
		System.out.println("mam id: " +  id); // -> dwa razy się to tworzy niepotrzebnie
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
