package com.phraseUp.phraseUpClient.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {
	private static Stage stage;

	public static void start(Stage stg, Parent root) {
		stage = stg;
		stage.setScene(new Scene(root));
		stage.setTitle("Phrase Up!");
		stage.show();
	}

	static void changeScene(String fxmlFileName) throws IOException {
		Parent root = FXMLLoader.load(MainWindowController.class.getResource(fxmlFileName));
		stage.setScene(new Scene(root));
	}
}
