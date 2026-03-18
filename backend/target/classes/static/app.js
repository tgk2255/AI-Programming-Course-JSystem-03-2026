const { useEffect, useState } = React;

function App() {
  const [dbStatus, setDbStatus] = useState("idle");
  const [dbMessage, setDbMessage] = useState("Backend i baza czekaja na sprawdzenie.");
  const [dbPayload, setDbPayload] = useState(null);
  const [messages, setMessages] = useState([]);
  const [author, setAuthor] = useState("Lab User");
  const [content, setContent] = useState("");
  const [chatStatus, setChatStatus] = useState("Ladowanie rozmowy...");

  useEffect(() => {
    loadMessages();
  }, []);

  async function loadMessages() {
    try {
      const response = await fetch("/api/chat/messages");
      const data = await response.json();

      if (!response.ok) {
        throw new Error("Nie udalo sie pobrac wiadomosci.");
      }

      setMessages(data);
      setChatStatus(data.length === 0 ? "Brak wiadomosci. Napisz pierwsza." : "Rozmowa zaladowana.");
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

  async function handleSubmit(event) {
    event.preventDefault();

    if (!author.trim() || !content.trim()) {
      setChatStatus("Podaj autora i tresc wiadomosci.");
      return;
    }

    setChatStatus("Zapisywanie wiadomosci do bazy...");

    try {
      const response = await fetch("/api/chat/messages", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          author: author.trim(),
          content: content.trim()
        })
      });

      const data = await response.json();

      if (!response.ok) {
        throw new Error("Nie udalo sie zapisac wiadomosci.");
      }

      setMessages((current) => [...current, data]);
      setContent("");
      setChatStatus("Wiadomosc zapisana w PostgreSQL.");
    } catch (error) {
      setChatStatus(error.message || "Wystapil blad podczas zapisu.");
    }
  }

  return React.createElement(
    "main",
    { className: "shell" },
    React.createElement("p", { className: "eyebrow" }, "AI Programming Course"),
    React.createElement("h1", null, "Chat z zapisem do PostgreSQL"),
    React.createElement(
      "p",
      { className: "lead" },
      "Frontend w React rozmawia z backendem Spring Boot. Wiadomosci trafiaja do PostgreSQL przez API i sa ladowane po otwarciu strony."
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
      React.createElement(
        "div",
        { className: "message-list" },
        messages.length === 0
          ? React.createElement("p", { className: "empty-state" }, "Brak zapisanych wiadomosci.")
          : messages.map((item) =>
              React.createElement(
                "article",
                { key: item.id, className: "message-card" },
                React.createElement(
                  "div",
                  { className: "message-meta" },
                  React.createElement("strong", null, item.author),
                  React.createElement("span", null, new Date(item.createdAt).toLocaleString())
                ),
                React.createElement("p", null, item.content)
              )
            )
      ),
      React.createElement(
        "form",
        { className: "chat-form", onSubmit: handleSubmit },
        React.createElement("input", {
          value: author,
          onChange: (event) => setAuthor(event.target.value),
          maxLength: 80,
          placeholder: "Autor"
        }),
        React.createElement("textarea", {
          value: content,
          onChange: (event) => setContent(event.target.value),
          rows: 4,
          maxLength: 2000,
          placeholder: "Napisz wiadomosc do zapisania w bazie"
        }),
        React.createElement(
          "button",
          { type: "submit", disabled: !author.trim() || !content.trim() },
          "Wyslij wiadomosc"
        )
      )
    )
  );
}

ReactDOM.createRoot(document.getElementById("root")).render(React.createElement(App));
