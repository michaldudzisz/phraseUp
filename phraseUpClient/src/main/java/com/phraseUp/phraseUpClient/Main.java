package com.phraseUp.phraseUpClient;

import com.phraseUp.phraseUpClient.controller.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main extends Application {
	private ConfigurableApplicationContext springContext;
	private Parent rootNode;

	@Override
	public void start(Stage stage) throws Exception {
		MainWindowController.start(stage, rootNode);
	}

	public void init() throws  Exception {
		springContext = SpringApplication.run(Main.class);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/StartScene.fxml"));
		fxmlLoader.setControllerFactory(springContext::getBean);
		rootNode = fxmlLoader.load();
	}

	public void stop() throws Exception {
		springContext.close();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
