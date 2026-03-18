package com.silkycoders1.jsystemssilkycodders1;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class BankCreditAgentService {

	private static final String ASSISTANT_NAME = "Agent Kredytowy AI";

	private final ChatMessageRepository chatMessageRepository;
	private final CustomerProfileRepository customerProfileRepository;
	private final Environment environment;
	private final ObjectMapper objectMapper;
	private final HttpClient httpClient;
	private final String openRouterUrl;
	private final String model;

	public BankCreditAgentService(
		ChatMessageRepository chatMessageRepository,
		CustomerProfileRepository customerProfileRepository,
		Environment environment,
		ObjectMapper objectMapper,
		@Value("${app.ai.openrouter.url}") String openRouterUrl,
		@Value("${app.ai.model}") String model
	) {
		this.chatMessageRepository = chatMessageRepository;
		this.customerProfileRepository = customerProfileRepository;
		this.environment = environment;
		this.objectMapper = objectMapper;
		this.openRouterUrl = openRouterUrl;
		this.model = model;
		this.httpClient = HttpClient.newBuilder()
			.connectTimeout(Duration.ofSeconds(20))
			.build();
	}

	public ChatAskResponse ask(String rawMessage, CustomerLookupRequest customerLookupRequest) {
		String message = rawMessage.trim();
		boolean creditIntent = isCreditIntent(message);
		Optional<CustomerProfile> customerProfile = findCustomer(customerLookupRequest);

		ChatMessage userMessage = chatMessageRepository.save("user", resolveUserAuthor(customerLookupRequest), message);

		boolean requiresCustomerData = creditIntent && customerProfile.isEmpty();
		String assistantText = buildAssistantMessage(message, creditIntent, customerLookupRequest, customerProfile);
		ChatMessage assistantMessage = chatMessageRepository.save("assistant", ASSISTANT_NAME, assistantText);

		return new ChatAskResponse(
			userMessage,
			assistantMessage,
			requiresCustomerData,
			customerProfile.isPresent(),
			customerProfile.orElse(null)
		);
	}

	private Optional<CustomerProfile> findCustomer(CustomerLookupRequest request) {
		if (request == null) {
			return Optional.empty();
		}
		return customerProfileRepository.findByIdentity(
			request.firstName().trim(),
			request.lastName().trim(),
			request.pesel().trim()
		);
	}

	private String resolveUserAuthor(CustomerLookupRequest request) {
		if (request == null) {
			return "Klient";
		}
		return (request.firstName().trim() + " " + request.lastName().trim()).trim();
	}

	private boolean isCreditIntent(String message) {
		String normalized = message.toLowerCase(Locale.ROOT);
		return normalized.contains("kredyt")
			|| normalized.contains("pozycz")
			|| normalized.contains("pozyczk")
			|| normalized.contains("finansowan")
			|| normalized.contains("hipotek")
			|| normalized.contains("rata")
			|| normalized.contains("zdolnosc kredyt")
			|| normalized.contains("zdolnosc");
	}

	private String buildAssistantMessage(
		String message,
		boolean creditIntent,
		CustomerLookupRequest customerLookupRequest,
		Optional<CustomerProfile> customerProfile
	) {
		if (creditIntent && customerLookupRequest == null) {
			return """
				Chetnie pomoge jako agent kredytowy banku. Widze, ze pytanie dotyczy kredytu.
				Aby przejsc dalej, podaj prosze dane klienta: imie, nazwisko i PESEL. Po ich wpisaniu sprawdze, czy mamy klienta w bazie oraz jakie dochody sa zapisane.
				""".strip();
		}

		if (creditIntent && customerLookupRequest != null && customerProfile.isEmpty()) {
			return """
				Dziekuje za dane. Nie znalazlem klienta o podanym imieniu, nazwisku i PESEL w naszej bazie.
				Sprawdz prosze wpisane dane albo podaj dane testowego klienta dostepnego w systemie.
				""".strip();
		}

		String fallback = localFallback(creditIntent, customerProfile);
		String apiResponse = fetchAiResponse(message, creditIntent, customerProfile).orElse(null);
		return apiResponse == null || apiResponse.isBlank() ? fallback : apiResponse;
	}

	private String localFallback(boolean creditIntent, Optional<CustomerProfile> customerProfile) {
		if (creditIntent && customerProfile.isPresent()) {
			CustomerProfile profile = customerProfile.get();
			return """
				Dziekuje. Znalazlem klienta w bazie.
				Klient: %s %s
				PESEL: %s
				Dochod miesieczny: %s %s

				Na tej podstawie moge przejsc do wstepnej rozmowy kredytowej. Prosze podac:
				- kwote kredytu
				- okres splaty
				- cel kredytu
				- czy sa obecne inne zobowiazania
				""".formatted(
				profile.firstName(),
				profile.lastName(),
				profile.pesel(),
				profile.monthlyIncome().toPlainString(),
				profile.currency()
			).strip();
		}

		if (creditIntent) {
			return """
				Jestem agentem kredytowym banku. Moge pomoc w rozmowie o kredycie gotowkowym lub hipotecznym.
				Jezeli chcesz przejsc dalej, podaj dane klienta albo napisz, jaki kredyt Cie interesuje.
				""".strip();
		}

		return """
			Jestem agentem kredytowym banku. Moge pomoc w pytaniach o kredyt gotowkowy, hipoteczny, raty, zdolnosc kredytowa i wstepna ocene klienta.
			Napisz, czego potrzebujesz, a poprowadze rozmowe dalej.
			""".strip();
	}

	private Optional<String> fetchAiResponse(String message, boolean creditIntent, Optional<CustomerProfile> customerProfile) {
		String apiKey = environment.getProperty("OPENROUTER_API_KEY");
		if (apiKey == null || apiKey.isBlank()) {
			return Optional.empty();
		}

		try {
			List<Map<String, String>> messages = new ArrayList<>();
			messages.add(Map.of(
				"role", "system",
				"content", buildSystemPrompt(creditIntent, customerProfile)
			));
			messages.add(Map.of("role", "user", "content", message));

			String body = objectMapper.writeValueAsString(Map.of(
				"model", model,
				"messages", messages,
				"temperature", 0.4
			));

			HttpRequest request = HttpRequest.newBuilder(URI.create(openRouterUrl))
				.timeout(Duration.ofSeconds(45))
				.header("Authorization", "Bearer " + apiKey)
				.header("Content-Type", "application/json")
				.header("HTTP-Referer", "http://localhost:8080")
				.header("X-Title", "AI Programming Course Bank Chat")
				.POST(HttpRequest.BodyPublishers.ofString(body))
				.build();

			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() < 200 || response.statusCode() >= 300) {
				return Optional.empty();
			}

			JsonNode root = objectMapper.readTree(response.body());
			JsonNode contentNode = root.path("choices").path(0).path("message").path("content");
			if (contentNode.isMissingNode() || contentNode.isNull()) {
				return Optional.empty();
			}

			return Optional.of(contentNode.asText().trim());
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
			return Optional.empty();
		} catch (IOException ex) {
			return Optional.empty();
		}
	}

	private String buildSystemPrompt(boolean creditIntent, Optional<CustomerProfile> customerProfile) {
		StringBuilder prompt = new StringBuilder("""
			Jestes polskim agentem bankowym specjalizujacym sie w kredytach.
			Pisz po polsku, krotko, rzeczowo i profesjonalnie.
			Nie udawaj decyzji kredytowej banku. Dajesz tylko wstepna informacje i prowadzisz klienta przez proces.
			""");

		if (creditIntent) {
			prompt.append("""

				Temat rozmowy dotyczy kredytu. Jezeli dane klienta sa dostepne, wykorzystaj je w odpowiedzi i popros o kolejne dane potrzebne do wstepnej analizy.
				Jezeli klient pyta o kredyt, prowadz rozmowe jak doradca kredytowy banku.
				""");
		}

		customerProfile.ifPresent(profile -> prompt.append("""

			Dane klienta znalezione w bazie:
			- imie: %s
			- nazwisko: %s
			- pesel: %s
			- dochod miesieczny: %s %s
			""".formatted(
			profile.firstName(),
			profile.lastName(),
			profile.pesel(),
			profile.monthlyIncome().toPlainString(),
			profile.currency()
		)));

		return prompt.toString();
	}
}
