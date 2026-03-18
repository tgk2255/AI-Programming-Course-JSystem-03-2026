package com.silkycoders1.jsystemssilkycodders1;

public record ChatAskResponse(
	String sessionId,
	ChatMessage userMessage,
	ChatMessage assistantMessage,
	boolean requiresCustomerData,
	boolean customerFound,
	CustomerProfile customerProfile,
	CreditAssessment creditAssessment
) {
}
