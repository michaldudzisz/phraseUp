package com.phraseUp.phraseUpClient.controller;

import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
public class LoggedInSceneController {
	private static final String fxmlFileName = "/fxml/LoggedInScene.fxml";

	static String getFxmlFileName() {
		return fxmlFileName;
	}
}
