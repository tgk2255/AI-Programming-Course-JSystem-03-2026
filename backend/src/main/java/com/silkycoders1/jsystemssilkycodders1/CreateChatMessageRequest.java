package com.silkycoders1.jsystemssilkycodders1;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateChatMessageRequest(
	@NotBlank
	@Size(max = 80)
	String author,
	@NotBlank
	@Size(max = 2000)
	String content
) {
}
