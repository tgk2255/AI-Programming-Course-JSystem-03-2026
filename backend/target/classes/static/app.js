const { useEffect, useState } = React;

function getOrCreateSessionId() {
  const existing = window.localStorage.getItem("bank_chat_session_id");
  if (existing) {
    return existing;
  }

  const created = window.crypto.randomUUID();
  window.localStorage.setItem("bank_chat_session_id", created);
  return created;
}

function App() {
  const [sessionId, setSessionId] = useState(getOrCreateSessionId);
  const [dbStatus, setDbStatus] = useState("idle");
  const [dbMessage, setDbMessage] = useState("Backend i baza czekaja na sprawdzenie.");
  const [dbPayload, setDbPayload] = useState(null);
  const [messages, setMessages] = useState([]);
  const [messageInput, setMessageInput] = useState("");
  const [chatStatus, setChatStatus] = useState("Ladowanie rozmowy...");
  const [sending, setSending] = useState(false);
  const [showCustomerForm, setShowCustomerForm] = useState(false);
  const [customer, setCustomer] = useState({
    firstName: "",
    lastName: "",
    pesel: ""
  });
  const [customerInfo, setCustomerInfo] = useState(null);
  const [creditAssessment, setCreditAssessment] = useState(null);

  useEffect(() => {
    loadMessages(sessionId);
  }, [sessionId]);

  async function loadMessages(activeSessionId) {
    try {
      const response = await fetch(`/api/chat/messages?sessionId=${encodeURIComponent(activeSessionId)}`);
      const data = await response.json();

      if (!response.ok) {
        throw new Error("Nie udalo sie pobrac rozmowy.");
      }

      setMessages(data);
      setChatStatus(data.length === 0 ? "Rozpocznij rozmowe z agentem kredytowym." : "Rozmowa zaladowana.");
    } catch (error) {
      setChatStatus(error.message || "Nie udalo sie zaladowac rozmowy.");
    }
  }

  function startNewConversation() {
    const nextSessionId = window.crypto.randomUUID();
    window.localStorage.setItem("bank_chat_session_id", nextSessionId);
    setSessionId(nextSessionId);
    setMessages([]);
    setMessageInput("");
    setShowCustomerForm(false);
    setCustomer({ firstName: "", lastName: "", pesel: "" });
    setCustomerInfo(null);
    setCreditAssessment(null);
    setChatStatus("Nowa rozmowa gotowa.");
  }

  async function handleCheck() {
    setDbStatus("pending");
    setDbMessage("Sprawdzam polaczenie backendu z PostgreSQL...");
    setDbPayload(null);

    try {
      const response = await fetch("/api/db/health");
      const data = await response.json();

      if (!response.ok) {
        throw new Error(data.message || "Backend zwrocil blad.");
      }

      setDbStatus("ok");
      setDbMessage("Polaczenie z baza dziala.");
      setDbPayload(data);
    } catch (error) {
      setDbStatus("err");
      setDbMessage(error.message || "Nie udalo sie sprawdzic polaczenia.");
    }
  }

  async function handleAsk(event) {
    event.preventDefault();

    if (!messageInput.trim()) {
      setChatStatus("Wpisz wiadomosc do agenta.");
      return;
    }

    setSending(true);
    setChatStatus("Agent analizuje wiadomosc...");

    try {
      const payload = {
        sessionId,
        message: messageInput.trim(),
        customer: showCustomerForm && hasCustomerData()
          ? {
              firstName: customer.firstName.trim(),
              lastName: customer.lastName.trim(),
              pesel: customer.pesel.trim()
            }
          : null
      };

      const response = await fetch("/api/chat/ask", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
      });

      const data = await response.json();

      if (!response.ok) {
        throw new Error("Nie udalo sie wyslac wiadomosci.");
      }

      setMessages((current) => [...current, data.userMessage, data.assistantMessage]);
      setMessageInput("");
      setShowCustomerForm(data.requiresCustomerData);
      setCustomerInfo(data.customerProfile);
      setCreditAssessment(data.creditAssessment);

      if (data.requiresCustomerData) {
        setChatStatus("Agent potrzebuje danych klienta do rozmowy kredytowej.");
      } else if (data.creditAssessment && data.creditAssessment.complete) {
        setChatStatus("Wyliczono wstepna zdolnosc kredytowa.");
      } else if (data.customerFound && data.customerProfile) {
        setChatStatus(`Znaleziono klienta ${data.customerProfile.firstName} ${data.customerProfile.lastName} z dochodem ${data.customerProfile.monthlyIncome} ${data.customerProfile.currency}.`);
      } else {
        setChatStatus("Odpowiedz agenta zostala zapisana.");
      }
    } catch (error) {
      setChatStatus(error.message || "Wystapil blad podczas rozmowy.");
    } finally {
      setSending(false);
    }
  }

  function hasCustomerData() {
    return customer.firstName.trim() && customer.lastName.trim() && customer.pesel.trim();
  }

  function renderMessage(message) {
    return React.createElement(
      "article",
      { key: message.id, className: `message-card ${message.role}` },
      React.createElement(
        "div",
        { className: "message-meta" },
        React.createElement("strong", null, message.author),
        React.createElement("span", null, new Date(message.createdAt).toLocaleString()),
        React.createElement("span", { className: "role-badge" }, message.role)
      ),
      React.createElement("p", null, message.content)
    );
  }

  return React.createElement(
    "main",
    { className: "shell" },
    React.createElement("p", { className: "eyebrow" }, "AI Programming Course"),
    React.createElement("h1", null, "Agent kredytowy banku"),
    React.createElement(
      "p",
      { className: "lead" },
      "Rozmawiasz z agentem bankowym o kredytach. Aplikacja prowadzi osobne sesje rozmow, zapisuje dialog do PostgreSQL i potrafi policzyc wstepna zdolnosc kredytowa."
    ),
    React.createElement(
      "div",
      { className: "cta-row" },
      React.createElement(
        "button",
        { onClick: handleCheck, disabled: dbStatus === "pending" },
        dbStatus === "pending" ? "Sprawdzanie..." : "Sprawdz polaczenie z baza"
      ),
      React.createElement(
        "button",
        { className: "secondary", onClick: startNewConversation, disabled: sending },
        "Nowa rozmowa"
      ),
      React.createElement(
        "div",
        { className: `status ${dbStatus === "idle" ? "pending" : dbStatus}` },
        dbMessage
      )
    ),
    React.createElement("p", { className: "session-label" }, `Sesja: ${sessionId}`),
    dbPayload &&
      React.createElement(
        "section",
        { className: "result" },
        React.createElement("h2", null, "Status bazy"),
        React.createElement("pre", null, JSON.stringify(dbPayload, null, 2))
      ),
    React.createElement(
      "section",
      { className: "chat" },
      React.createElement(
        "div",
        { className: "chat-header" },
        React.createElement("h2", null, "Rozmowa"),
        React.createElement("p", null, chatStatus)
      ),
      customerInfo &&
        React.createElement(
          "div",
          { className: "customer-summary" },
          React.createElement("strong", null, "Klient w bazie: "),
          `${customerInfo.firstName} ${customerInfo.lastName}, PESEL ${customerInfo.pesel}, dochod ${customerInfo.monthlyIncome} ${customerInfo.currency}`
        ),
      creditAssessment &&
        React.createElement(
          "div",
          { className: "assessment-panel" },
          React.createElement("h3", null, "Wstepna ocena zdolnosci"),
          React.createElement("p", null, creditAssessment.summary),
          creditAssessment.complete &&
            React.createElement(
              "div",
              { className: "assessment-grid" },
              React.createElement("span", null, `Kwota: ${creditAssessment.requestedAmount} PLN`),
              React.createElement("span", null, `Okres: ${creditAssessment.repaymentMonths} mies.`),
              React.createElement("span", null, `Rata: ${creditAssessment.estimatedInstallment} PLN`),
              React.createElement("span", null, `Limit: ${creditAssessment.affordabilityLimit} PLN`),
              React.createElement("span", null, `Zobowiazania: ${creditAssessment.monthlyObligations} PLN`),
              React.createElement("span", null, `Decyzja: ${creditAssessment.decision}`)
            )
        ),
      showCustomerForm &&
        React.createElement(
          "div",
          { className: "customer-panel" },
          React.createElement("h3", null, "Dane klienta do sprawdzenia"),
          React.createElement(
            "div",
            { className: "customer-grid" },
            React.createElement("input", {
              value: customer.firstName,
              onChange: (event) => setCustomer({ ...customer, firstName: event.target.value }),
              placeholder: "Imie"
            }),
            React.createElement("input", {
              value: customer.lastName,
              onChange: (event) => setCustomer({ ...customer, lastName: event.target.value }),
              placeholder: "Nazwisko"
            }),
            React.createElement("input", {
              value: customer.pesel,
              onChange: (event) => setCustomer({ ...customer, pesel: event.target.value }),
              placeholder: "PESEL"
            })
          ),
          React.createElement(
            "p",
            { className: "hint" },
            "Dane testowe w bazie: Janek Tester, PESEL 1234567"
          )
        ),
      React.createElement(
        "div",
        { className: "message-list" },
        messages.length === 0
          ? React.createElement("p", { className: "empty-state" }, "Brak zapisanych wiadomosci w tej sesji.")
          : messages.map(renderMessage)
      ),
      React.createElement(
        "form",
        { className: "chat-form", onSubmit: handleAsk },
        React.createElement("textarea", {
          value: messageInput,
          onChange: (event) => setMessageInput(event.target.value),
          rows: 4,
          maxLength: 2000,
          placeholder: "Np. Chce kredyt 50000 na 48 miesiecy, bez zobowiazan"
        }),
        React.createElement(
          "button",
          { type: "submit", disabled: sending || !messageInput.trim() || (showCustomerForm && !hasCustomerData()) },
          sending ? "Wysylanie..." : "Wyslij"
        )
      )
    )
  );
}

ReactDOM.createRoot(document.getElementById("root")).render(React.createElement(App));
