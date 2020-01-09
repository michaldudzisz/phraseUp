package com.phraseUp.phraseUpClient.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {
	private static Stage stage;
	private static Parent rootNode;

	public static void start(Stage stg, Parent root) {
		rootNode = root;
		stage = stg;
		stage.setScene(new Scene(rootNode));
		stage.setTitle("Phrase Up!");
		stage.show();
	}

	static void changeScene(String fxmlFileName) throws IOException {
		Parent root = FXMLLoader.load(MainWindowController.class.getResource(fxmlFileName));
		stage.setScene(new Scene(root));
	}
}
