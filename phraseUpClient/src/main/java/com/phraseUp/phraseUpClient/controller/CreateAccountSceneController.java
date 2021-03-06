package com.phraseUp.phraseUpClient.controller;

import com.phraseUp.phraseUpClient.model.Language;
import com.phraseUp.phraseUpClient.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
	public ChoiceBox<String> languageChoiceBox;
	@FXML
	public Text errorText;

	static String getFxmlFileName() {
		return fxmlFileName;
	}

	public void goBackButtonHandler() throws IOException {
		MainWindowController.changeScene(StartSceneController.getFxmlFileName());
	}

	public void initialize() {
		for (Language lang : Language.values()) {
			languageChoiceBox.getItems().add(lang.toString());
		}
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

		if (languageChoiceBox.getSelectionModel().isEmpty()) {
			errorText.setText("You have to select your language. Please try again.");
			usernameInput.clear();
			passwordInput.clear();
			passwordRepeatInput.clear();
			return;
		}

		Language lang = Language.fromString(languageChoiceBox.getValue());
		User user = new User(username, password, lang);
		if (HttpRequestController.sendAccountCreateRequest(user)) {
			LoggedInSceneController.setUser(user);
			MainWindowController.changeScene(LoggedInSceneController.getFxmlFileName());
		} else {
			errorText.setText("Couldn't create an account. Probably there already exists account with that username.");
			usernameInput.clear();
			passwordInput.clear();
			passwordRepeatInput.clear();
		}
	}
}
