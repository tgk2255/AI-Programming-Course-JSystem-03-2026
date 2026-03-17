# ADR-001 — Loan Decision Copilot: PostgreSQL, Agentic UI, and Template Reuse

## Status
Proposed

## Context
Grupa szkoleniowa chce więcej praktyki, więcej SQL i bardziej namacalny projekt niż sama teoria. Poprzedni wariant projektu oparty o complaint/return bot dobrze pokazywał agentowy flow, ale używał prostszej persystencji. Obecna grupa ma silniejszy profil bankowy i dwie osoby pracujące głównie z PostgreSQL, procedurami, funkcjami i testami SQL.

Jednocześnie projekt ma pozostać:
- angażujący,
- zrozumiały,
- możliwy do zbudowania w kilka dni,
- zgodny z celem kursu: AI jako narzędzie wspierające development end-to-end.

## Decision
Podejmujemy trzy powiązane decyzje:
1. Używamy **PostgreSQL zamiast SQLite** jako głównej bazy danych projektu.
2. Używamy **chat + dynamiczny formularz** jako głównego flow UI zamiast zwykłego, statycznego formularza od początku.
3. Traktujemy **Spring Boot + PostgreSQL + agentowy frontend + template reuse z LangGraph4j/CopilotKit** jako docelowy kierunek architektury kursowej.

## Rationale

### 1. PostgreSQL zamiast SQLite
- Lepiej odpowiada profilowi grupy.
- Daje sensowny materiał do ćwiczeń SQL, joinów, agregacji i audytu.
- Pozwala pokazać wielotabelowy model danych zamiast prostej persystencji sesji.
- Jest bliższy realnym systemom enterprise niż SQLite.

### 2. Chat + dynamiczny formularz
- Lepiej pokazuje różnicę między assistant a agentowym UI.
- Zachowuje najbardziej angażujący element poprzedniego course demo.
- Dobrze nadaje się do pokazania AG-UI / CopilotKit jako idei.
- Pozwala naturalnie przejść od rozmowy do strukturalnego zbierania danych.

### 3. Stack szkoleniowy i template reuse
- Spring Boot + Java 21 jest bliski grupie.
- PostgreSQL daje mocny fundament danych.
- Template reuse z istniejącego repo pozwala pokazać realny agentowy workflow: analiza dwóch repo, porównanie i przenoszenie elementów.
- Nie odtwarzamy całego template 1:1; używamy go jako referencji i punktu startowego.

## Rejected Alternatives

### SQLite jako baza
- Prostszą do uruchomienia, ale zbyt słabą dydaktycznie dla tej grupy.
- Za mało miejsca na ćwiczenia SQL i modelowanie danych.

### Klasyczny formularz bez warstwy konwersacyjnej
- Prostszą implementacyjnie, ale mniej agentową i mniej angażującą.
- Gorszą do pokazania wartości AG-UI / CopilotKit.

### Pełny rewrite projektu od zera bez template reuse
- Czystszy teoretycznie, ale tracący ciekawy element pracy agenta między projektami.

### Pełne kopiowanie template repo bez krytycznej selekcji
- Zbyt ryzykowne i zbyt złożone jak na kurs.
- Mogłoby przesłonić główny cel dydaktyczny.

## Consequences
- Projekt staje się lepiej dopasowany do grupy i bardziej SQL-heavy.
- Dzień 2 może zacząć się od praktycznego ćwiczenia z PostgreSQL.
- Dzień 3 i kolejne mają mocniejszy fundament pod realną implementację.
- Trzeba pilnować scope, żeby nie przeciążyć grupy zbyt wieloma frameworkami naraz.

## Risks
- Ryzyko overengineeringu modelu danych.
- Ryzyko przeciążenia dnia 2, jeśli zbyt wiele tematów zostanie potraktowanych jako „must have”.
- Ryzyko zbyt szybkiego wejścia w template/framework details zamiast w podstawowy slice produktu.

## Mitigations
- Traktować PostgreSQL setup, model danych, PRD i ADR jako obowiązkowy core dnia 2.
- Potraktować Playwright skill, AGENTS.md deep dive i MCP jako dodatki zależne od czasu.
- W template reuse zaczynać od planu migracji i analizy, nie od masowego kopiowania.

## Trigger for Revisit
Wracamy do tej decyzji, jeśli:
- projekt nie mieści się w tempie kursu,
- grupa potrzebuje prostszego wariantu,
- template reuse zaczyna dominować nad celem dydaktycznym,
- lub PostgreSQL przestaje dawać wyraźną wartość w codziennych ćwiczeniach.
