package com.phraseUp.phraseUpClient.controller;

import com.phraseUp.phraseUpClient.model.ChatMessage;
import com.phraseUp.phraseUpClient.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.io.IOException;
import java.lang.reflect.Type;

public class ChatSceneController {

	public class MyStompSessionHandler extends StompSessionHandlerAdapter {

		final String URL = "ws://localhost:8091/ws";
		private Logger logger = LogManager.getLogger(com.phraseUp.phraseUpClient.controller.ChatSceneController.class);
		private StompSession session;

		@Override
		public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
			this.session = session;

			logger.info("New session established : " + session.getSessionId());
			session.subscribe("/topic/public", this);
			logger.info("Subscribed to /topic/public");
		}

		@Override
		public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
			logger.error("Got an exception", exception);
		}

		@Override
		public Type getPayloadType(StompHeaders headers) {
			return ChatMessage.class;
		}

		@Override
		public void handleFrame(StompHeaders headers, Object payload) {
			ChatMessage msg = (ChatMessage) payload;
			logger.info("Received : " + msg.getText() + " from : " + msg.getFrom() + ". Displaying it...");
			messageStored = msg;
			ChatSceneController.this.refreshChatView();
		}

		void sendMessage(ChatMessage msg) {
			session.send("/app/chat.sendMessage", msg);
		}
	}

	private static MyStompSessionHandler sessionHandler;
	private static final String fxmlFileName = "/fxml/ChatScene.fxml";
	private static ChatMessage messageStored;
	private static User user;

	@FXML
	public TextArea messageInput;

	@FXML
	public Text messagesText;

	static String getFxmlFileName() {
		return fxmlFileName;
	}

	static void setUser(User usr) {
		user = usr;
	}

	public void initialize() {
		WebSocketClient client = new StandardWebSocketClient();
		WebSocketStompClient stompClient = new WebSocketStompClient(client);

		stompClient.setMessageConverter(new MappingJackson2MessageConverter());

		sessionHandler = new MyStompSessionHandler();

		stompClient.connect(sessionHandler.URL, sessionHandler);
	}

	public void sendButtonHandler() {
		System.out.println("lol, wysyłam");

		ChatMessage msg = new ChatMessage();
		msg.setFrom(user.getUsername());
		msg.setText(messageInput.getText());
		sessionHandler.sendMessage(msg);
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

	private void refreshChatView() {
		System.out.print("Refreshing chat view...");
		messagesText.setText(messagesText.getText() + System.lineSeparator() + System.lineSeparator() +
				messageStored.getFrom() + ": " + messageStored.getText());
	}
}
