package com.silkycoders1.jsystemssilkycodders1;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AskChatRequest(
	@NotBlank
	@Size(max = 64)
	String sessionId,
	@NotBlank
	@Size(max = 2000)
	String message,
	@Valid
	CustomerLookupRequest customer
) {
}
