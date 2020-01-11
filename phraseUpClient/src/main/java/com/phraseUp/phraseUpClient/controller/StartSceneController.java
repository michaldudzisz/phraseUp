package com.phraseUp.phraseUpClient.controller;

import com.phraseUp.phraseUpClient.model.Message;
import com.phraseUp.phraseUpClient.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class StartSceneController {

	@FXML
	public TextField loginInput;
	@FXML
	public TextField passwordInput;

	private static final String fxmlFileName = "/fxml/StartScene.fxml";

	static String getFxmlFileName() {
		return fxmlFileName;
	}

	public void LogInButtonHandler() throws IOException {
		System.out.println("Reading - login: " + loginInput.getText() + ", password: " + passwordInput.getText());
		User user = new User(UUID.randomUUID(), loginInput.getText());
		LoggedInSceneController.user = user;
		loginInput.clear();
		passwordInput.clear();
		MainWindowController.changeScene(LoggedInSceneController.getFxmlFileName());
	}
}
