package com.phraseUp.phraseUpClient.controller;

import com.phraseUp.phraseUpClient.model.LogInData;
import com.phraseUp.phraseUpClient.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class LoggedInSceneController {

	private static final String fxmlFileName = "/fxml/LoggedInScene.fxml";
	private static User user;

	@FXML
	public Label usernameLabel;

	static String getFxmlFileName() {
		return fxmlFileName;
	}

	static void setUser(User usr) {
		user = usr;
	}

	@FXML
	public void initialize() {
		usernameLabel.setText(user.getUsername());
	}

	public void startNewChatButtonHandler() throws IOException {
		MainWindowController.changeScene(ChatSceneController.getFxmlFileName());
		ChatSceneController.setUser(user);
	}

	public void logOutButtonHandler() throws IOException {
		System.out.println("You've been logged out!");
		MainWindowController.changeScene(StartSceneController.getFxmlFileName());
	}
}
