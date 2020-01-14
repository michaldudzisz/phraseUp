package com.phraseUp.phraseUpClient.controller;

import com.phraseUp.phraseUpClient.model.LogInData;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StartSceneController {

	@FXML
	public TextField loginInput;
	@FXML
	public TextField passwordInput;
	@FXML
	public Text errorText;

	private static final String fxmlFileName = "/fxml/StartScene.fxml";

	static String getFxmlFileName() {
		return fxmlFileName;
	}

	public void LogInButtonHandler() throws IOException {
		LogInData log = new LogInData(loginInput.getText(), passwordInput.getText());

		if (HttpRequestController.sendLogInRequest(log)) {
			LoggedInSceneController.setUser(HttpRequestController.getUserInfo(log));
			MainWindowController.changeScene(LoggedInSceneController.class, LoggedInSceneController.getFxmlFileName());
		} else
			errorText.setText("Wrong username or password.");

		loginInput.clear();
		passwordInput.clear();
	}

	public void createAccButtonHandler() throws IOException {
		MainWindowController.changeScene(CreateAccountSceneController.class, CreateAccountSceneController.getFxmlFileName());
	}
}
