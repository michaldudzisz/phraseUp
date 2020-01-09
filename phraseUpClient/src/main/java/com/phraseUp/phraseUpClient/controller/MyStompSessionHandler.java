package com.phraseUp.phraseUpClient.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import com.phraseUp.phraseUpClient.model.Message;

import java.lang.reflect.Type;

/**
 * This class is an implementation for <code>StompSessionHandlerAdapter</code>.
 * Once a connection is established, We subscribe to /topic/messages and
 * send a sample message to server.
 *
 *
 */
public class MyStompSessionHandler extends StompSessionHandlerAdapter {

	private Logger logger = LogManager.getLogger(MyStompSessionHandler.class);
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
		return Message.class;
	}

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
		Message msg = (Message) payload;
		logger.info("Received : " + msg.getText() + " from : " + msg.getFrom());
	}

	public void sendMessage(Message msg) {
		session.send("/app/chat.sendMessage", msg);
	}
}