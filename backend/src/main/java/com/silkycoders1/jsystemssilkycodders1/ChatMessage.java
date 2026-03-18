package com.silkycoders1.jsystemssilkycodders1;

import java.time.OffsetDateTime;

public record ChatMessage(
	long id,
	String author,
	String content,
	OffsetDateTime createdAt
) {
}
