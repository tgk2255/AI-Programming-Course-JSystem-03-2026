const { useEffect, useState } = React;

function App() {
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

  useEffect(() => {
    loadMessages();
  }, []);

  async function loadMessages() {
    try {
      const response = await fetch("/api/chat/messages");
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

      if (data.requiresCustomerData) {
        setChatStatus("Agent potrzebuje danych klienta do rozmowy kredytowej.");
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
      "Rozmawiasz z agentem bankowym o kredytach. Gdy rozmowa wejdzie na temat kredytu, aplikacja poprosi o dane klienta i sprawdzi je w PostgreSQL."
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
        "div",
        { className: `status ${dbStatus === "idle" ? "pending" : dbStatus}` },
        dbMessage
      )
    ),
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
          ? React.createElement("p", { className: "empty-state" }, "Brak zapisanych wiadomosci.")
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
          placeholder: "Napisz do agenta kredytowego, np. Chce wziac kredyt gotowkowy"
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
