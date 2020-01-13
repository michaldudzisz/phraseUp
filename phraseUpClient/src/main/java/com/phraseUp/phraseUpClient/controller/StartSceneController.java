package com.phraseUp.phraseUpClient.controller;

import com.phraseUp.phraseUpClient.model.LogInData;
import com.phraseUp.phraseUpClient.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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

	private Boolean sendLogInRequest(LogInData log) {
		final String url = "http://localhost:8091/phraseup/login";

		Boolean ifLogged = new RestTemplate().postForObject(url, log, Boolean.class);

		System.out.println("Logged successfully: " + ifLogged);
		return ifLogged;
	}

	public void LogInButtonHandler() throws IOException {
		String username = loginInput.getText();
		System.out.println("Reading - login: " + username + ", password: " + passwordInput.getText());

		if (sendLogInRequest(new LogInData(loginInput.getText(), passwordInput.getText()))) {
			LoggedInSceneController.setLog(loginInput.getText(), passwordInput.getText());
			MainWindowController.changeScene(LoggedInSceneController.getFxmlFileName());
		}
		else
			errorText.setText("Wrong username or password.");

		loginInput.clear();
		passwordInput.clear();
	}

	public void createAccButtonHandler() throws IOException {
		MainWindowController.changeScene(CreateAccountSceneController.getFxmlFileName());
	}
}
