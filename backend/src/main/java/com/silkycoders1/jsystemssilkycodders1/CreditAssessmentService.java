package com.silkycoders1.jsystemssilkycodders1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class CreditAssessmentService {

	private static final Pattern AMOUNT_PATTERN = Pattern.compile("(\\d{4,7})(?:[\\.,]\\d{1,2})?");
	private static final Pattern MONTH_PATTERN = Pattern.compile("(\\d{1,3})\\s*(mies|miesie|miesiecy|miesiac)");
	private static final Pattern YEAR_PATTERN = Pattern.compile("(\\d{1,2})\\s*(lat|lata|rok)");
	private static final Pattern OBLIGATION_PATTERN = Pattern.compile("(\\d{2,6})(?:[\\.,]\\d{1,2})?\\s*(?:zl|pln)?\\s*(?:rat|zobowiazan)");

	public CreditAssessment assess(CustomerProfile customerProfile, String message) {
		BigDecimal amount = parseAmount(message);
		Integer months = parseMonths(message);
		BigDecimal obligations = parseObligations(message);

		if (amount == null || months == null) {
			return new CreditAssessment(
				false,
				"missing_data",
				"Aby policzyc wstepna zdolnosc, podaj kwote kredytu i okres splaty, np. 50000 na 48 miesiecy.",
				amount,
				months,
				null,
				customerProfile.monthlyIncome(),
				obligations,
				customerProfile.monthlyIncome().multiply(BigDecimal.valueOf(0.40)).setScale(2, RoundingMode.HALF_UP)
			);
		}

		BigDecimal affordabilityLimit = customerProfile.monthlyIncome()
			.multiply(BigDecimal.valueOf(0.40))
			.subtract(obligations)
			.max(BigDecimal.ZERO)
			.setScale(2, RoundingMode.HALF_UP);

		BigDecimal estimatedInstallment = amount
			.multiply(BigDecimal.valueOf(1.18))
			.divide(BigDecimal.valueOf(months), 2, RoundingMode.HALF_UP);

		boolean eligible = estimatedInstallment.compareTo(affordabilityLimit) <= 0;
		String decision = eligible ? "preliminary_positive" : "preliminary_negative";
		String summary = eligible
			? "Wstepnie wyglada to akceptowalnie. Szacowana rata miesci sie w limicie zdolnosci."
			: "Wstepnie zdolnosc wyglada za nisko. Szacowana rata przekracza bezpieczny limit miesieczny.";

		return new CreditAssessment(
			true,
			decision,
			summary,
			amount,
			months,
			estimatedInstallment,
			customerProfile.monthlyIncome(),
			obligations,
			affordabilityLimit
		);
	}

	private BigDecimal parseAmount(String message) {
		Matcher matcher = AMOUNT_PATTERN.matcher(message);
		return matcher.find() ? new BigDecimal(matcher.group(1)) : null;
	}

	private Integer parseMonths(String message) {
		Matcher monthMatcher = MONTH_PATTERN.matcher(message.toLowerCase());
		if (monthMatcher.find()) {
			return Integer.parseInt(monthMatcher.group(1));
		}

		Matcher yearMatcher = YEAR_PATTERN.matcher(message.toLowerCase());
		if (yearMatcher.find()) {
			return Integer.parseInt(yearMatcher.group(1)) * 12;
		}

		return null;
	}

	private BigDecimal parseObligations(String message) {
		String normalized = message.toLowerCase();
		if (normalized.contains("brak zobowiazan") || normalized.contains("bez zobowiazan")) {
			return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		}

		Matcher matcher = OBLIGATION_PATTERN.matcher(normalized);
		if (matcher.find()) {
			return new BigDecimal(matcher.group(1)).setScale(2, RoundingMode.HALF_UP);
		}

		return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
	}
}
