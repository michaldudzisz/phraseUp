package com.phraseUp.phraseUpClient.controller;

import com.phraseUp.phraseUpClient.model.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.List;

public class ChatSceneController {

	public static MyStompSessionHandler SessionHandler;
	private static final String fxmlFileName = "/fxml/ChatScene.fxml";
	static List<Message> messagesStored;

	@FXML
	public TextArea messageInput;

	@FXML
	public Label messagesLabel;

	static String getFxmlFileName() {
		return fxmlFileName;
	}

	public void sendButtonHandler() {
		System.out.println("lol, wysyłam");

		Message msg = new Message();
		msg.setFrom("Michałucha");
		msg.setText(messageInput.getText());
		SessionHandler.sendMessage(msg);
		messageInput.clear();
	}

	public void logOutButtonHandler() throws IOException {
		System.out.println("You've been logged out!");
		MainWindowController.changeScene(StartSceneController.getFxmlFileName());
	}

	public void goBackButtonHandler() throws IOException {
		System.out.println("Moving back...");
		MainWindowController.changeScene(LoggedInSceneController.getFxmlFileName());
	}
}
