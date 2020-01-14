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

	static FXMLLoader changeScene(String fxmlFileName) throws IOException {
		FXMLLoader loader = new FXMLLoader(MainWindowController.class.getResource(fxmlFileName));
		Parent root = loader.load();
		stage.setScene(new Scene(root));
		return loader;
	}
}
