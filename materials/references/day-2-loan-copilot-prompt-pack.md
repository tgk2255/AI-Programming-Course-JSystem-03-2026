# Day 2 Prompt Pack — Loan Decision Copilot

## 1. Codex Intro
```text
Explain in 5 bullets what you can and cannot do in this environment, and when you should ask me for approval.
```

## 2. Safe PostgreSQL Setup in Docker
```text
Chcę uruchomić lokalny PostgreSQL do projektu szkoleniowego.

Założenia:
- pracuję ostrożnie, z ręcznymi approvalami,
- preferuję Docker,
- chcę rozwiązanie powtarzalne i proste do zrozumienia,
- nie zakładaj, że mam wszystko idealnie skonfigurowane,
- zanim coś zainstalujesz lub uruchomisz, sprawdź obecny stan środowiska.

Twoje zadanie:
1) sprawdź, czy mam Docker i jakie mam możliwości,
2) zaproponuj najprostszy bezpieczny setup PostgreSQL dla lokalnego developmentu,
3) przygotuj plan krok po kroku,
4) wykonuj kroki dopiero po mojej akceptacji,
5) na końcu zweryfikuj połączenie i pokaż minimalny test SQL.

Na końcu wypisz:
- co zostało zrobione,
- jakie dane dostępowe ustawiono,
- jak zatrzymać i uruchomić bazę ponownie,
- jakie są kolejne sensowne kroki.
```

## 3. SQL Data Model
```text
Pracujesz jako architekt danych PostgreSQL dla szkoleniowego MVP bankowego.

Budujemy aplikację:
- chatowy Loan Decision Copilot,
- dynamiczny formularz dla wniosku o pożyczkę,
- scoring i explainability,
- pełny audit trail.

Twoje zadanie:
1) zaproponuj model relacyjny dla poniższych tabel,
2) wskaż klucze główne i obce,
3) dodaj najważniejsze constraints,
4) wskaż przykładowe indeksy,
5) pokaż 5 przykładowych zapytań SQL z joinami i agregacją,
6) zaproponuj jak przechowywać ślad decyzji i działań agenta.

Minimalny zestaw tabel:
- customers
- loan_applications
- financial_profiles
- income_records
- liabilities
- repayment_history
- scoring_rules
- decision_audit_log
- chat_sessions
- agent_actions

Wymagania:
- odpowiedź po polsku,
- konkretnie,
- bez overengineeringu,
- wynik ma nadawać się do kursowego MVP w 5 dni.
```

## 4. Research and PRD
```text
Jesteś product managerem i analitykiem systemowym.

Przygotuj PRD dla szkoleniowego MVP:
"Loan Decision Copilot" dla środowiska bankowego.

Kontekst:
- aplikacja działa w formie czatu,
- agent wykrywa intencję złożenia wniosku o pożyczkę,
- agent pokazuje dynamiczny formularz,
- backend pobiera dane klienta i dane finansowe z PostgreSQL,
- system oblicza wynik i zwraca rekomendację z uzasadnieniem,
- decyzja i działania muszą zostawić audit trail.

Przygotuj:
1) problem statement,
2) 2-3 persony,
3) 8-10 user stories,
4) mierzalne acceptance criteria,
5) ograniczenia niefunkcjonalne,
6) out of scope,
7) ryzyka i założenia dla danych demo.

Pisz po polsku, konkretnie, bez marketingu.
```

## 5. ADR
```text
Napisz mini ADR dla szkoleniowego projektu "Loan Decision Copilot".

Temat decyzji:
1) PostgreSQL zamiast SQLite
2) Chat + dynamiczny formularz zamiast zwykłego flow formularzowego
3) Stack: Spring Boot + PostgreSQL + agentowy frontend + template reuse

Format obowiązkowy:
- Kontekst
- Decyzja
- Odrzucone alternatywy
- Konsekwencje
- Ryzyka
- Trigger rewizji decyzji

Pisz krótko, konkretnie, po polsku.
```

## 6. Mermaid Diagrams
```text
Przygotuj 4 diagramy Mermaid dla projektu "Loan Decision Copilot":
1) user flow,
2) high-level architecture,
3) decision flow,
4) uproszczony data model overview.

Diagramy mają być:
- czytelne,
- małe,
- gotowe do wklejenia do Markdown,
- bez przesadnej szczegółowości.
```

## 7. Template Reuse / Repo Alignment
```text
Chcę porównać moje repo szkoleniowe z repo referencyjnym i przygotować plan bezpiecznego przeniesienia wybranych elementów.

Twoje zadanie:
1) opisz strukturę obu projektów,
2) wskaż podobieństwa i różnice,
3) zaproponuj co warto przenieść najpierw,
4) wskaż ryzyka bezrefleksyjnego kopiowania,
5) przygotuj plan małymi krokami,
6) niczego jeszcze nie kopiuj bez mojej zgody.
```

## 8. Design Tokens / Playwright Skill
```text
Użyj narzędzi przeglądarkowych / Playwright, aby przeanalizować publiczną stronę:
[PLACEHOLDER_URL]

Twoje zadanie:
1) zbierz podstawowe design tokens,
2) wskaż fonty, kolory, radius, shadows, spacing i najważniejsze style komponentów,
3) zaproponuj zwięzły design guide dla naszej aplikacji, aby była stylistycznie spójna z marką,
4) jeśli to możliwe, wskaż logo lub główne assety do późniejszego reuse.

Nie kopiuj całego serwisu 1:1. Przygotuj praktyczny przewodnik projektowy.
```
