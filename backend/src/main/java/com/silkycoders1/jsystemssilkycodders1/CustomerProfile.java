package com.silkycoders1.jsystemssilkycodders1;

import java.math.BigDecimal;

public record CustomerProfile(
	long id,
	String firstName,
	String lastName,
	String pesel,
	BigDecimal monthlyIncome,
	String currency
) {
}
