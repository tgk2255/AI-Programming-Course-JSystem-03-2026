package com.silkycoders1.jsystemssilkycodders1;

import java.math.BigDecimal;

public record CreditAssessment(
	boolean complete,
	String decision,
	String summary,
	BigDecimal requestedAmount,
	Integer repaymentMonths,
	BigDecimal estimatedInstallment,
	BigDecimal monthlyIncome,
	BigDecimal monthlyObligations,
	BigDecimal affordabilityLimit
) {
}
