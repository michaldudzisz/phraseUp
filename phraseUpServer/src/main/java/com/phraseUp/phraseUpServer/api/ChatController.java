package com.phraseUp.phraseUpServer.api;

import com.phraseUp.phraseUpServer.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@MessageMapping()
public class ChatController {

	@MessageMapping("/chat/polish")
	@SendTo("/topic/polish")
	public Message sendMessagePolish(@Payload Message message) {
		return message;
	}

	@MessageMapping("/chat/spanish")
	@SendTo("/topic/spanish")
	public Message sendMessageSpanish(@Payload Message message) {
		return message;
	}

	@MessageMapping("/chat/german")
	@SendTo("/topic/german")
	public Message sendMessageGerman(@Payload Message message) {
		return message;
	}
}
