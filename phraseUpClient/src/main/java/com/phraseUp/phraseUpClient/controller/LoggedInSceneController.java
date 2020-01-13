package com.phraseUp.phraseUpClient.controller;

import com.phraseUp.phraseUpClient.model.LogInData;
import com.phraseUp.phraseUpClient.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class LoggedInSceneController {

	private static final String fxmlFileName = "/fxml/LoggedInScene.fxml";
	private static LogInData log;
	private static User user;

	@FXML
	public Label usernameLabel;

	static String getFxmlFileName() {
		return fxmlFileName;
	}

	@FXML
	public void initialize() {
		user = HttpRequestController.getUserInfo(log);
		usernameLabel.setText(user.getUsername());
	}

	static void setLog(String username, String password) {
		log = new LogInData(username, password);
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
