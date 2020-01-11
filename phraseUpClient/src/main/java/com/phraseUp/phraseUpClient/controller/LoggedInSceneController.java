package com.phraseUp.phraseUpClient.controller;

import com.phraseUp.phraseUpClient.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class LoggedInSceneController {

	private static final String fxmlFileName = "/fxml/LoggedInScene.fxml";
	static User user;

	@FXML
	public Label usernameLabel;

	static String getFxmlFileName() {
		return fxmlFileName;
	}

	@FXML
	public void initialize() {
		usernameLabel.setText(user.getName());
	}

	public void startNewChatButtonHandler() throws IOException {
		MainWindowController.changeScene(ChatSceneController.getFxmlFileName());
	}

	public void logOutButtonHandler() throws IOException {
		System.out.println("You've been logged out!");
		MainWindowController.changeScene(StartSceneController.getFxmlFileName());
	}
}