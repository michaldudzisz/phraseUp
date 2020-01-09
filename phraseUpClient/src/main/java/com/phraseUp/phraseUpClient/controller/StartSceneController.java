package com.phraseUp.phraseUpClient.controller;

import com.phraseUp.phraseUpClient.model.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StartSceneController {
	public static MyStompSessionHandler SessionHandler;

	public void LogInButtonClick() throws IOException {
		System.out.println("Sending message...");

		Message msg = new Message();
		msg.setFrom("Michałucha");
		msg.setText("Siema");
		SessionHandler.sendMessage(msg);

		MainWindowController.changeScene(LoggedInSceneController.getFxmlFileName());
		System.out.println("lol, zmieniłem");
	}
}
