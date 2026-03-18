package com.silkycoders1.jsystemssilkycodders1;

public record ChatAskResponse(
	ChatMessage userMessage,
	ChatMessage assistantMessage,
	boolean requiresCustomerData,
	boolean customerFound,
	CustomerProfile customerProfile
) {
}
