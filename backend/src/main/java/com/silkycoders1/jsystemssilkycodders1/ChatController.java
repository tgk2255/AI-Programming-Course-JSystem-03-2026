package com.silkycoders1.jsystemssilkycodders1;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

	private final ChatMessageRepository chatMessageRepository;
	private final BankCreditAgentService bankCreditAgentService;

	public ChatController(
		ChatMessageRepository chatMessageRepository,
		BankCreditAgentService bankCreditAgentService
	) {
		this.chatMessageRepository = chatMessageRepository;
		this.bankCreditAgentService = bankCreditAgentService;
	}

	@GetMapping("/messages")
	public List<ChatMessage> listMessages() {
		return chatMessageRepository.findAll();
	}

	@PostMapping("/ask")
	public ChatAskResponse ask(@Valid @RequestBody AskChatRequest request) {
		return bankCreditAgentService.ask(request.message(), request.customer());
	}
}
