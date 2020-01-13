package com.phraseUp.phraseUpClient.controller;

import com.phraseUp.phraseUpClient.model.LogInData;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class CreateAccountSceneController {

	private static final String fxmlFileName = "/fxml/CreateAccountScene.fxml";

	@FXML
	public TextField usernameInput;
	@FXML
	public TextField passwordInput;
	@FXML
	public TextField passwordRepeatInput;
	@FXML
	public Text errorText;

	static String getFxmlFileName() {
		return fxmlFileName;
	}

	public void goBackButtonHandler() throws IOException {
		System.out.println("Moving back...");
		MainWindowController.changeScene(StartSceneController.getFxmlFileName());
	}

	private Boolean sendAccountCreateRequest(LogInData log) {
		final String url = "http://localhost:8091/phraseup/login/register";

		Boolean ifLogged = new RestTemplate().postForObject(url, log, Boolean.class);

		System.out.println("Logged successfully: " + ifLogged);
		return ifLogged;
	}

	public void createAccButtonHandler() throws IOException {
		String username = usernameInput.getText();
		String password;

		if (!passwordInput.getText().equals(passwordRepeatInput.getText())) {
			errorText.setText("Two different passwords! Please try again.");
			usernameInput.clear();
			passwordInput.clear();
			passwordRepeatInput.clear();
			return;
		}

		password = passwordInput.getText();
		if (sendAccountCreateRequest(new LogInData(username, password))) {
			LoggedInSceneController.setLog(usernameInput.getText(), passwordInput.getText());
			MainWindowController.changeScene(LoggedInSceneController.getFxmlFileName());
		} else {
			errorText.setText("Couldn't create an account. Probably there already exists account with that username.");
			usernameInput.clear();
			passwordInput.clear();
			passwordRepeatInput.clear();
		}
	}
}
