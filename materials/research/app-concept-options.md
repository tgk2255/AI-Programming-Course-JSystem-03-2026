# NBP — Opcje konceptu aplikacji (Phase E1)

Data: 2026-03-15  
Cel: przygotować 3–5 realnych opcji projektu końcowego na 5-dniowy kurs (profil: bank, mieszany poziom seniority, Java + DB/SQL + TS/full-stack), w podejściu Codex-first.

## Kryteria oceny (1–5)
- **Wartość edukacyjna** dla grupy NBP (bank, Java/DB/full-stack mix)
- **Złożoność implementacji** (niżej = lepiej dla 5 dni)
- **Zaangażowanie** (historia, stawka, „nerd fun”, motywacja)
- **Dopasowanie do 5 dni**
- **Dopasowanie do Codex-first** (CLI/App + workflow agenta)
- **Nauka „jak agent działa w środku”** (tools, JSON schema, system prompt, guardrails)
- **Czy AG-UI/CopilotKit/langgraph4j ma sens** (value vs complexity)

---

## Opcja 1 — SOC Copilot „Incident Triage” (SQL + hacker-defense)
**Opis:** wewnętrzny asystent SOC do triage incydentów: konsoliduje logi, proponuje priorytety, generuje checklistę działań i raport audytowy.  
**Dlaczego pasuje do NBP:** bankowy kontekst bezpieczeństwa, silny komponent SQL/analizy danych, łatwe osadzenie ćwiczeń audytowych.

- Wartość edukacyjna: **5/5**
- Złożoność implementacji: **3/5**
- Zaangażowanie: **5/5**
- Fit do 5 dni: **4/5**
- Fit do Codex-first: **5/5**
- Agent internals: **5/5**
- AG-UI/CopilotKit/langgraph4j: **raczej NIE jako core**; ewentualnie krótki optional demo (wysoka złożoność, mały zwrot w 5-dniowym kursie)

**Ryzyka:** łatwo „odpłynąć” w cyber-szczegóły; trzeba trzymać scope (symulowany dataset, nie pełny SIEM).

---

## Opcja 2 — Legacy Refactor & Risk Assistant (JFTP-style)
**Opis:** asystent do przeglądu starego modułu (np. JFTP-like): wykrywa ryzyka, proponuje refaktoryzacje, checklistę testów i plan migracji.  
**Dlaczego pasuje:** idealny most do Day 4/5 (quality + security + legacy), bardzo praktyczny dla zespołów enterprise.

- Wartość edukacyjna: **5/5**
- Złożoność implementacji: **2/5**
- Zaangażowanie: **4/5**
- Fit do 5 dni: **5/5**
- Fit do Codex-first: **5/5**
- Agent internals: **4/5**
- AG-UI/CopilotKit/langgraph4j: **NIEpotrzebne** do celu kursu; klasyczny chat + pipeline narzędzi wystarczy

**Ryzyka:** mniej „wow” produktowo; trzeba dodać narrację „misji” i konkretną stawkę biznesową.

---

## Opcja 3 — Compliance Q&A + Evidence Builder
**Opis:** asystent odpowiadający na pytania compliance i budujący „evidence pack” (cytaty z polityk, ślad decyzji, lista luk).  
**Dlaczego pasuje:** bankowy use-case, mocne połączenie RAG + audytowalność + kontrola halucynacji.

- Wartość edukacyjna: **4/5**
- Złożoność implementacji: **3/5**
- Zaangażowanie: **3/5**
- Fit do 5 dni: **4/5**
- Fit do Codex-first: **4/5**
- Agent internals: **5/5**
- AG-UI/CopilotKit/langgraph4j: **opcjonalne tylko pokazowo**; bez tego też osiągamy cele dydaktyczne

**Ryzyka:** może wyjść „zbyt dokumentowo”; trzeba dodać dynamiczny incident thread i decyzje GO/NO-GO.

---

## Opcja 4 — Data Quality & SQL Guard Copilot
**Opis:** asystent do walidacji jakości danych i bezpieczeństwa zapytań SQL (detekcja anty-patternów, sugestie indeksów, kontrola ryzyk).  
**Dlaczego pasuje:** mocny DB/SQL angle, dobre dla uczestników z backend i analityką.

- Wartość edukacyjna: **4/5**
- Złożoność implementacji: **2/5**
- Zaangażowanie: **3/5**
- Fit do 5 dni: **5/5**
- Fit do Codex-first: **4/5**
- Agent internals: **4/5**
- AG-UI/CopilotKit/langgraph4j: **NIE**; overkill względem efektu

**Ryzyka:** mniejsza „story value” dla części frontend/full-stack; wymaga dobrego framingu biznesowego.

---

## Opcja 5 — Team Delivery Copilot (Jira + CI/CD + PR coach)
**Opis:** asystent wspierający flow zespołu: przygotowuje plan tasków, checklisty PR, analizę ryzyk release i sugestie pipeline (cloud vs on-prem).  
**Dlaczego pasuje:** bezpośrednio łączy temat agenta z codziennym delivery i CI/CD.

- Wartość edukacyjna: **4/5**
- Złożoność implementacji: **3/5**
- Zaangażowanie: **4/5**
- Fit do 5 dni: **4/5**
- Fit do Codex-first: **4/5**
- Agent internals: **4/5**
- AG-UI/CopilotKit/langgraph4j: **NIE jako core**; można dodać 5-min wzmiankę o AG-UI

**Ryzyka:** duże ryzyko „slajdowości”, jeśli brak realistycznego mini-datasetu i konkretnych artefaktów.

---

## Wnioski po E1 (bez finalnej rekomendacji)
- Najmocniejsze edukacyjnie i najbardziej „bankowo-praktyczne” są:
  1) **SOC Copilot Incident Triage**,
  2) **Legacy Refactor & Risk Assistant**.
- Obie opcje najlepiej wspierają cel kursu: **pokazać pracę agenta end-to-end**, a nie tylko „chat z LLM”.
- **AG-UI/CopilotKit/langgraph4j**: w tej edycji kursu raczej jako **opcjonalny krótki wgląd**, nie jako rdzeń implementacji (koszt złożoności > zysk dydaktyczny w 5 dniach).

> Kolejne kroki (poza E1):
> - E2: analiza gałęzi `origin/Lucas-sinsay-2nd-February-CopilotKit` jako potencjalnej bazy reuse.
> - E3: finalna rekomendacja (single preferred + fallback).
