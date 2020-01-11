package com.phraseUp.phraseUpClient;

import com.phraseUp.phraseUpClient.controller.ChatSceneController;
import com.phraseUp.phraseUpClient.controller.MainWindowController;
import com.phraseUp.phraseUpClient.controller.StartSceneController;
import com.phraseUp.phraseUpClient.controller.MyStompSessionHandler;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main extends Application {
	private ConfigurableApplicationContext springContext;
	private Parent rootNode;
	private static String URL = "ws://localhost:8091/ws";

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

		WebSocketClient client = new StandardWebSocketClient();
		WebSocketStompClient stompClient = new WebSocketStompClient(client);

		stompClient.setMessageConverter(new MappingJackson2MessageConverter());

		MyStompSessionHandler sessionHandler = new MyStompSessionHandler();
		stompClient.connect(URL, sessionHandler);

		ChatSceneController.SessionHandler = sessionHandler;
		//javafx, ale trzeba to zrobić inaczej, cóż, na razie tak
		Application.launch(args);
	}
}
