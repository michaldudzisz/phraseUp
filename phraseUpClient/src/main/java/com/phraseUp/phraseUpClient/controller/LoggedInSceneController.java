package com.phraseUp.phraseUpClient.controller;

import com.phraseUp.phraseUpClient.model.Language;
import com.phraseUp.phraseUpClient.model.LogInData;
import com.phraseUp.phraseUpClient.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoggedInSceneController {

	private static final String fxmlFileName = "/fxml/LoggedInScene.fxml";
	private static User user;
	private final int FOREIGN_LANG_BUTTONS_NUMBER = 2;

	@FXML
	public Label usernameLabel;
	@FXML
	public Label nativeLanguageLabel;
	@FXML
	public Button nativeLangChatButton;
	@FXML
	public Button foreignLangChatButton1;
	@FXML
	public Button foreignLangChatButton2;
	private ArrayList<String> foreignLanguages = new ArrayList<>();
	private ArrayList<Button> foreignLangButtons = new ArrayList<>();

	static String getFxmlFileName() {
		return fxmlFileName;
	}

	static void setUser(User usr) {
		user = usr;
	}

	@FXML
	public void initialize() {
		foreignLangButtons.add(foreignLangChatButton1);
		foreignLangButtons.add(foreignLangChatButton2);

		usernameLabel.setText(user.getUsername());
		nativeLanguageLabel.setText(user.getLanguage().toString());
		nativeLangChatButton.setText(user.getLanguage().toString());

		for (Language lang : Language.values()) {
			if (!lang.equals(user.getLanguage()))
				foreignLanguages.add(lang.toString());
		}

		for (int i = 0; i < FOREIGN_LANG_BUTTONS_NUMBER; ++i)
			foreignLangButtons.get(i).setText(foreignLanguages.get(i));
	}

	public void nativeLangChatButtonHandler() throws IOException {
		FXMLLoader loader = MainWindowController.changeScene(ChatSceneController.class, ChatSceneController.getFxmlFileName());
		ChatSceneController chatController = loader.getController();
		chatController.initializeChat(user, user.getLanguage().toString());
	}

	public void logOutButtonHandler() throws IOException {
		MainWindowController.changeScene(StartSceneController.class, StartSceneController.getFxmlFileName());
	}

	public void foreignLangChatButton1Handler() throws IOException {
		FXMLLoader loader = MainWindowController.changeScene(ChatSceneController.class, ChatSceneController.getFxmlFileName());
		ChatSceneController chatController = loader.getController();
		chatController.initializeChat(user, foreignLangChatButton1.getText());
	}

	public void foreignLangChatButton2Handler() throws IOException {
		FXMLLoader loader = MainWindowController.changeScene(ChatSceneController.class, ChatSceneController.getFxmlFileName());
		ChatSceneController chatController = loader.getController();
		chatController.initializeChat(user, foreignLangChatButton2.getText());
	}
}
