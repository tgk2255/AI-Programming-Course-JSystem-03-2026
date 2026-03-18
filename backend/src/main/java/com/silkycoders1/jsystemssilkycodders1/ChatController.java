package com.silkycoders1.jsystemssilkycodders1;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat/messages")
public class ChatController {

	private final ChatMessageRepository chatMessageRepository;

	public ChatController(ChatMessageRepository chatMessageRepository) {
		this.chatMessageRepository = chatMessageRepository;
	}

	@GetMapping
	public List<ChatMessage> listMessages() {
		return chatMessageRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ChatMessage createMessage(@Valid @RequestBody CreateChatMessageRequest request) {
		return chatMessageRepository.save(request.author().trim(), request.content().trim());
	}
}
