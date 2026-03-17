# Dzień 2 — Pełny scenariusz prowadzenia
**Temat: AI jako Twój PM, UX Designer i architekt**
**Godz. 9:00–16:00 | Zoom online | Grupa: mixed seniority, Java + SQL/DB + architekci, osoby początkujące z agentami**

> 🎬 = co mówię (dosłownie lub prawie dosłownie)
> 📺 = co pokazuję na ekranie
> 💬 = wklejam na Zoom chat (gotowy tekst do skopiowania)
> 🏋️ = ćwiczenie dla uczestników
> ⏱️ = czas bloku

---

## LINKI DNIA

- App brief: `materials/references/day-2-loan-copilot-app-brief.md`
- PRD: `materials/references/day-2-loan-copilot-prd.md`
- ADR: `materials/references/day-2-loan-copilot-adr-001.md`
- Prompt pack: `materials/references/day-2-loan-copilot-prompt-pack.md`
- Sandbox mini-module: `materials/references/sandbox-safety-module.md`
- Oficjalna agenda kursu: `materials/references/jsystems-program-ai-od-pomyslu-do-mvp.md`

---

## AGENDA DNIA (wyślij na starcie)

💬 WKLEJ NA CHAT:
```text
Dzień 2 – agenda:
09:00 Reset po dniu 1 + nowy cel dnia
09:15 Krótkie intro: Codex CLI / Desktop App / Claude Code – jak dziś pracujemy
09:35 Sandbox i bezpieczny start z agentami
10:00 Ćwiczenie 1 – PostgreSQL w Dockerze z pomocą agenta
11:00 ☕ PRZERWA
11:15 Wspólna decyzja: app idea i scope MVP
11:40 Ćwiczenie 2 – model danych, tabele, joiny i scoring
12:30 AI Research + PRD pod nasze MVP
13:00 🍽️ PRZERWA (30 min)
13:30 ADR + architektura + diagramy Mermaid
14:15 Repo workflow: upstream / fork / clone / template reuse
14:40 Skill demo (opcjonalnie): Playwright Interactive + design tokens
15:00 AGENTS.md / custom agents / MCP – intro i most do dnia 3
15:30 Podsumowanie dnia + gotowy handoff do implementacji
16:00 Koniec
```

---

## GŁÓWNY CEL DNIA

🎬 **CO MÓWIĘ:**

„Wczoraj byliśmy trochę za bardzo w trybie wykładowym. Dzisiaj chcę to świadomie skorygować. Będzie mniej mówienia przeze mnie, więcej pracy Waszej, więcej promptów, więcej kodu, więcej konkretnych artefaktów.

Naszym celem nie jest dziś 'porozmawiać o fajnym pomyśle'. Naszym celem jest wyjść z działającym fundamentem pod resztę tygodnia:
- uruchomiony PostgreSQL lokalnie,
- wybrany i doprecyzowany pomysł aplikacji,
- model danych z kilkoma sensownymi tabelami i joinami,
- PRD,
- pierwszy ADR,
- szkic architektury,
- i gotowe prompty oraz backlog na jutrzejsze kodowanie.

Czyli dziś robimy most między Day 1 a Day 3. Między teorią a implementacją."

📺 **CO POKAZUJĘ:**
- Agendę dnia
- Listę artefaktów końcowych
- Jedno zdanie opisujące dzisiejszy produkt:
  - „Bankowy Loan Decision Copilot: chat + dynamiczny formularz + scoring + explainability + audit trail na PostgreSQL”

💬 WKLEJ NA CHAT:
```text
Cel dnia 2:
1) uruchomić PostgreSQL lokalnie,
2) zdecydować wspólny kierunek aplikacji,
3) zaprojektować dane, przepływ i architekturę,
4) przygotować PRD + ADR + prompty,
5) wejść w dzień 3 z gotowym planem do implementacji.
```

---

## 09:00–09:15 — Reset po dniu 1 + kontrakt na dzień 2
⏱️ 15 min

🎬 **CO MÓWIĘ:**

„Wczoraj wyszła od Was bardzo sensowna potrzeba, żeby było więcej SQL i PostgreSQL. Więc zmieniamy kształt aplikacji.

Zamiast wracać do starego pomysłu 1:1, zrobimy nowy wariant lepiej dopasowany do Was.
To będzie:
- bankowy asystent w czacie,
- który rozpoznaje, że klient chce wnioskować o pożyczkę albo kredyt,
- pokazuje formularz w rozmowie,
- pobiera dane klienta i dane finansowe,
- wykonuje scoring,
- i daje rekomendację z uzasadnieniem i śladem audytowym.

Czyli zachowujemy ciekawy, agentowy flow z poprzednich kursów, ale dodajemy więcej danych, więcej SQL, więcej joinów i więcej miejsca dla osób bazodanowych."

📺 **CO POKAZUJĘ:**
- 2 slajdy / 2 okna:
  - „Wczoraj: za dużo teorii”
  - „Dziś: hands-on recovery”
- Krótką mapę dnia:
  - agent basics -> safety -> DB -> app concept -> SQL/domain -> PRD -> ADR -> repo flow

💬 WKLEJ NA CHAT:
```text
Kontrakt na dziś:
- krócej tłumaczę,
- częściej oddaję Wam stery,
- robimy małe kroki,
- kończymy dzień konkretnymi artefaktami.
```

---

## 09:15–09:35 — Krótkie intro: Codex CLI, Codex Desktop App, Claude Code
⏱️ 20 min

🎬 **CO MÓWIĘ:**

„Zanim dam Wam pierwsze większe ćwiczenie, musimy wreszcie zrobić krótkie, praktyczne intro do samych narzędzi.

Wczoraj skupiliśmy się bardziej na logowaniu i setupie. Dzisiaj skupimy się na tym, jak naprawdę z nimi pracować.

Na dziś najważniejsze są trzy rzeczy:
1. gdzie piszę polecenie dla agenta,
2. jak widzę co agent chce zrobić,
3. jak odzyskuję kontrolę, kiedy agent chce zrobić coś ryzykownego.

I od razu ważne rozróżnienie:
- ChatGPT to głównie assistant.
- Codex CLI, Codex App, Claude Code to już agentowe narzędzia.

Różnica praktyczna jest taka, że assistant gada o kodzie, a agent może realnie uruchamiać komendy, edytować pliki, odpalać testy, analizować repo i iterować."

📺 **CO POKAZUJĘ:**
- Codex CLI:
  - prompt,
  - plan/komentarze,
  - prośbę o zgodę / approvals,
  - diff
- Codex Desktop App:
  - wątki,
  - workspace,
  - review diff
- Claude Code:
  - szybkie porównanie, bez głębokiego odchodzenia w bok

🎬 **DALSZY SKRYPT:**

„Jak ja bym to uprościł na dziś:
- Codex CLI: świetny, kiedy chcemy uczyć pracy w terminalu i dokładnie rozumieć co agent robi. Oraz do automatyzacji, CI/CD, serverów.
- Codex Desktop App: świetna, kiedy chcemy mieć wygodniejszy interfejs, wątki i przegląd pracy.
- Claude Code: dobry jako porównanie, szczególnie jeśli ktoś później będzie chciał działać bardziej w bashowym, bardzo elastycznym stylu.

Ale nasz główny tor szkoleniowy dziś to Codex-first."

💬 WKLEJ NA CHAT:
```text
Mini-checklista pracy z agentem:
1) daj konkretny cel,
2) dodaj ograniczenia i acceptance criteria,
3) patrz na plan i komendy,
4) czytaj diff,
5) testuj mały krok,
6) dopiero potem kolejny krok.
```

💬 WKLEJ NA CHAT:
```text
Assistant vs Agent:
- Assistant: odpowiada i podpowiada
- Agent: planuje, używa narzędzi, edytuje pliki, uruchamia komendy

Na kursie uczymy się pracy z agentem, ale z człowiekiem nadal jako osobą odpowiedzialną za decyzję.
```

🏋️ **ĆWICZENIE:**
- Każdy wpisuje do swojego agenta krótkie polecenie:
  - „Explain in 5 bullets what you can and cannot do in this environment, and when you should ask me for approval.”
  - "Wytłumacz w punktach co możesz a czego nie możesz zrobić w tym środowisku, jakie masz narzędzia dostępne, oraz kiedy powinieneś mnie zapytać o zgodę. Powiedz też jakim modelem językowym jesteś, kto Cię stwożył i kiedy masz knowledge cutoff."

🎬 **CO MÓWIĘ PO ĆWICZENIU:**

„To jest dobry pierwszy prompt, bo uczy Was nie tylko narzędzia, ale też jego granic. Przy agentach granice są równie ważne jak możliwości.”

---

## 09:35–10:00 — Sandbox i bezpieczny start z agentami
⏱️ 25 min

🎬 **CO MÓWIĘ:**

„Teraz bardzo ważny blok. Krótki, ale praktyczny. Chodzi o bezpieczeństwo pracy z agentami.

Sandbox jest po to, żeby błąd był mały i odwracalny.

Jeżeli agent pomyli się w promptcie albo źle zinterpretuje Wasz zamiar, to wolę, żeby zniszczył lokalny branch w ćwiczeniowym repo, a nie połowę danych albo przypadkiem nie wysłał czegoś gdzie nie trzeba.

I jedna ważna rzecz, którą chcę żebyście zapamiętali:
WSL nie jest równoznaczne z bezpieczeństwem.
WSL pomaga uruchamiać narzędzia linuxowe i poprawia ergonomię, ale sama obecność WSL nie znaczy jeszcze, że agent jest dobrze odizolowany.
Izolacja wynika z polityki sandboxa, approvals, granic filesystemu, granic sieci i z tego, gdzie pracujemy.”

📺 **CO POKAZUJĘ:**
- Krótką listę safe defaults z `materials/references/sandbox-safety-module.md`
- Schemat:
  - repo ćwiczeniowe
  - branch
  - approvals on
  - małe taski
  - review diffu

💬 WKLEJ NA CHAT:
```text
Bezpieczny start z agentem:
- pracujemy na dedykowanym branchu,
- sandbox i approvals są domyślnie włączone,
- nie wrzucamy sekretów do promptów,
- robimy małe kroki,
- czytamy diff przed testem i commitem,
- AI daje draft, człowiek podejmuje decyzję.
```

🎬 **DALSZY SKRYPT:**

„Na dzisiejsze ćwiczenia polecam bardzo świadomy styl:
- najpierw approvals ręczne,
- zero YOLO na starcie,
- i najlepiej wszystko na środowisku, które można bez żalu odtworzyć.

Później pokażemy też, jak można agentowi dać więcej swobody, ale dopiero kiedy środowisko jest do tego przygotowane: osobna maszyna, VM, VPS, stary laptop, Mac Mini do eksperymentów, coś co ma sens jako sandbox operacyjny.”


🏋️ **ĆWICZENIE:**
- Pytanie na czacie:
  - „Jednym zdaniem: co chroni sandbox / approval?”

---

## 10:00–11:00 — Ćwiczenie 1: PostgreSQL w Dockerze z pomocą agenta
⏱️ 60 min

🎬 **CO MÓWIĘ:**

„To będzie nasz pierwszy większy blok praktyczny.

Założenie jest takie: nawet jeśli ktoś nie pamięta dokładnie jak odpalić PostgreSQL lokalnie, to ma umieć poprowadzić agenta tak, żeby zrobił to bezpiecznie i sensownie.

Czyli nie 'magia, zrób wszystko'. Tylko vibe engineering:
- mamy cel,
- mamy ograniczenia,
- mamy acceptance criteria,
- patrzymy co agent robi,
- i uczymy się po drodze.”

📺 **CO POKAZUJĘ:**
- Jedną referencyjną strukturę zadania:
  - cel
  - ograniczenia
  - acceptance criteria
  - komendy weryfikacyjne

💬 WKLEJ NA CHAT:
```text
Ćwiczenie 1 – cel:
Uruchomić lokalny PostgreSQL w Dockerze tak, aby:
- działał na znanym porcie,
- miał utworzoną bazę dla naszej aplikacji,
- dało się połączyć klientem SQL,
- dało się utworzyć pierwsze tabele,
- rozwiązanie było zrozumiałe i powtarzalne.
```

💬 WKLEJ NA CHAT:
```text
Acceptance criteria:
1) Kontener PostgreSQL działa
2) Znamy host, port, user, password, db name
3) Potrafimy połączyć się do bazy
4) Potrafimy wykonać proste CREATE TABLE i SELECT
5) Mamy zapisane komendy lub docker-compose / compose.yaml do ponownego uruchomienia
```

💬 WKLEJ NA CHAT:
```text
Prompt startowy do agenta:

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

🎬 **CO MÓWIĘ W TRAKCIE:**

„Ja teraz mniej mówię, więcej chodzę po Waszych problemach.
Zwracajcie uwagę na trzy rzeczy:
1. czy agent najpierw sprawdza stan środowiska,
2. czy planuje zanim działa,
3. czy potraficie wyjaśnić co właśnie zrobił.

Jeśli agent od razu odpala długą sekwencję bez sensownego planu, to zatrzymajcie go i doprecyzujcie zadanie.”

📺 **CO POKAZUJĘ W TRAKCIE:**
- u jednego uczestnika: dobra sesja
- u drugiego: jak poprawić zbyt ogólny prompt

💬 WKLEJ NA CHAT:
```text
Prompt ratunkowy, jeśli agent jest zbyt chaotyczny:

Stop. Najpierw opisz:
1) jaki jest aktualny stan środowiska,
2) co dokładnie chcesz zrobić,
3) jakie są ryzyka,
4) jakie acceptance criteria sprawdzisz po każdym kroku.

Nie wykonuj jeszcze zmian. Najpierw pokaż plan.
```

💬 WKLEJ NA CHAT:
```text
Prompt ratunkowy, jeśli Docker lub PostgreSQL nie działa:

Przeanalizuj problem krok po kroku.
Najpierw zbierz objawy i komunikaty błędów.
Podaj 3 najbardziej prawdopodobne przyczyny.
Dla każdej przyczyny zaproponuj minimalny krok diagnostyczny.
Nie zgaduj. Opieraj się tylko na tym, co realnie widzisz w środowisku.
```

🎬 **DOMKNIĘCIE BLOKU:**

„To ćwiczenie nie było tylko o PostgreSQL. To było ćwiczenie z prowadzenia agenta.

Jeśli po tym bloku umiecie:
- zadać konkretny cel,
- patrzeć na plan,
- pilnować approvals,
- i zweryfikować wynik,
to wykonaliście bardzo ważny krok w stronę pracy agentowej.”

💬 WKLEJ NA CHAT:
```text
Checkpoint po ćwiczeniu 1:
□ kontener działa
□ połączenie do bazy działa
□ wiem jak baza została uruchomiona
□ potrafię odtworzyć setup
□ rozumiem różnicę między "agent zrobił" a "ja rozumiem co zrobił"
```

---

## 11:00–11:15 — Przerwa
⏱️ 15 min

🎬 **CO MÓWIĘ:**

„Po przerwie przechodzimy do tego, po co nam ta baza i jaki produkt właściwie budujemy przez resztę tygodnia.”

---

## 11:15–11:40 — Wspólna decyzja: app idea i scope MVP
⏱️ 25 min

🎬 **CO MÓWIĘ:**

„Teraz spinamy biznes i technologię.

Nasz wspólny kierunek to Loan Decision Copilot.
To nie ma być produkcyjny scoring bankowy. To ma być edukacyjne MVP, które jest:
- angażujące,
- agentowe,
- sensowne dla banku,
- i bogate w SQL/PSQL.

Podstawowy flow jest taki:
- klient pisze na czacie,
- agent rozpoznaje intencję związaną z pożyczką,
- pojawia się dynamiczny formularz,
- klient podaje dane,
- backend pobiera lub łączy dane finansowe,
- system liczy wynik,
- agent zwraca rekomendację z uzasadnieniem,
- a wszystko zostawia audit trail.”

📺 **CO POKAZUJĘ:**
- Bardzo prosty diagram user flow
- Analogiczność do poprzedniego course flow:
  - „tam było complaint/return”
  - „tu mamy loan decision”

💬 WKLEJ NA CHAT:
```text
Nasze wspólne MVP:
Bankowy Loan Decision Copilot

Flow:
1) rozmowa w czacie
2) wykrycie intencji "chcę pożyczkę"
3) dynamiczny formularz
4) pobranie i połączenie danych klienta
5) scoring + explainability
6) rekomendacja + audit trail
```

🎬 **DALSZY SKRYPT:**

„To jest ważne: my nie budujemy magicznego AI-oracle.
Budujemy system, który:
- zbiera dane,
- uruchamia jawne reguły,
- może wspierać się AI w interakcji i wyjaśnianiu,
- ale pozostaje zrozumiały i audytowalny.

To jest dużo lepsze dydaktycznie dla tej grupy niż demo typu 'LLM coś wymyślił'.”

🏋️ **ĆWICZENIE:**
- Na czacie każdy dopisuje po 1 rzeczy do jednej z kategorii:
  - „Jakie dane o kliencie?”
  - „Jakie dane finansowe?”
  - „Jakie czynniki wpływają na decyzję?”
  - „Jakie zdarzenia trzeba zapisać do audytu?”

---

## 11:40–12:30 — Ćwiczenie 2: model danych, tabele, joiny i scoring
⏱️ 50 min

🎬 **CO MÓWIĘ:**

„To jest blok szczególnie ważny dla osób bazodanowych, ale nie tylko dla nich.

Chcę, żeby ten projekt naprawdę czuł PostgreSQL. Nie jako 'jedna tabelka na końcu', tylko jako fundament logiki.

Czyli teraz projektujemy takie dane, żeby dało się sensownie robić:
- joiny,
- agregacje,
- historię zmian,
- scoring,
- explainability,
- i ślad audytowy.”

📺 **CO POKAZUJĘ:**
- Początkowy zestaw tabel:
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

💬 WKLEJ NA CHAT:
```text
Minimalny zestaw tabel do wspólnego MVP:
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
```

💬 WKLEJ NA CHAT:
```text
Prompt do agenta – model danych:

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

Wymagania:
- odpowiedź po polsku,
- konkretnie,
- bez overengineeringu,
- wynik ma nadawać się do kursowego MVP w 5 dni.
```

🎬 **CO MÓWIĘ W TRAKCIE:**

„Chcę, żebyście tu zadawali agentowi bardzo konkretne pytania.
Na przykład:
- dlaczego ta tabela istnieje?
- dlaczego to pole jest tu, a nie gdzie indziej?
- czy to jest bardziej snapshot czy historia?
- jak odtworzyć decyzję po czasie?

To jest ten moment, w którym AI ma przyspieszyć modelowanie, ale odpowiedzialność za sens domeny pozostaje po naszej stronie.”

💬 WKLEJ NA CHAT:
```text
Pytania pomocnicze do modelu danych:
- Co jest encją główną?
- Co jest historią, a co snapshotem?
- Jak wyjaśnić decyzję po czasie?
- Jakie dane są wrażliwe?
- Co chcemy liczyć w SQL, a co w logice aplikacyjnej?
```

🎬 **DOMKNIĘCIE BLOKU:**

„Po tym bloku chcę, żebyśmy potrafili opisać jednym zdaniem:
'Jakie dane łączymy, żeby podjąć decyzję i jak potem tę decyzję odtworzyć?'”

---

## 12:30–13:00 — AI Research + PRD pod nasze MVP
⏱️ 30 min

🎬 **CO MÓWIĘ:**

„Teraz odzyskujemy to, czego zabrakło wczoraj: research i PRD.

Ale nie robimy PRD dla samego PRD.
Robimy dokument, który pomoże agentowi i zespołowi kodować sensownie jutro.

Czyli ma być:
- konkretny,
- implementacyjny,
- z user stories,
- z acceptance criteria,
- z ograniczeniami,
- bez marketingowego lania wody.”

📺 **CO POKAZUJĘ:**
- Strukturę prostego PRD
- Różnicę między:
  - „ładny dokument”
  - „dokument przydatny agentowi i zespołowi”

💬 WKLEJ NA CHAT:
```text
Sekcje PRD, które dziś chcemy mieć:
1) Problem statement
2) Użytkownicy / persony
3) Główne flow
4) User stories
5) Acceptance criteria
6) Ograniczenia i ryzyka
7) Zakres MVP / out of scope
8) Założenia dla danych demo
```

💬 WKLEJ NA CHAT:
```text
Prompt do agenta – PRD:

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

🏋️ **ĆWICZENIE:**
- Każda osoba lub para bierze jedną sekcję PRD do krytycznego przeczytania i poprawienia:
  - persony
  - stories
  - acceptance criteria
  - out of scope

---

## 13:00–13:30 — Przerwa obiadowa
⏱️ 30 min

---

## 13:30–14:15 — ADR + architektura + diagramy Mermaid
⏱️ 45 min

🎬 **CO MÓWIĘ:**

„Po południu przechodzimy z poziomu produktu na poziom architektury.

I tutaj chcę, żebyśmy bardzo jasno odróżnili dwie rzeczy:
- PRD mówi co i po co budujemy,
- ADR mówi dlaczego podjęliśmy konkretną decyzję techniczną zamiast innych opcji.

ADR nie ma być akademickim dokumentem na 10 stron.
Ma być krótki, ale ma zapisać realny trade-off.”

📺 **CO POKAZUJĘ:**
- przykład mini ADR
- prosty diagram high-level

🎬 **DALSZY SKRYPT:**

„Na dziś chcemy zapisać przynajmniej trzy ważne decyzje:
1. Dlaczego PostgreSQL zamiast SQLite.
2. Dlaczego chat + dynamiczny formularz zamiast zwykłego formularza od początku.
3. Dlaczego idziemy w ten stack szkoleniowy:
   - Spring Boot Java 21,
   - PostgreSQL,
   - frontend chat UI,
   - CopilotKit / AG-UI jako kierunek UI agentowego,
   - LangGraph4j lub powiązany template jako punkt wejścia do orkiestracji.”

💬 WKLEJ NA CHAT:
```text
ADR zapisuje:
- kontekst,
- decyzję,
- alternatywy,
- konsekwencje,
- ryzyka,
- kiedy wracamy do tematu.
```

💬 WKLEJ NA CHAT:
```text
Prompt do agenta – ADR:

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

💬 WKLEJ NA CHAT:
```text
Prompt do agenta – diagram Mermaid:

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

🎬 **CO MÓWIĘ POD KONIEC:**

„Jeżeli dokumentacja po tym bloku nie pomaga osobie implementującej jutro, to jest za słaba.
Dokumentacja ma przyspieszać delivery, a nie istnieć dla samej siebie.”

---

## 14:15–14:40 — Repo workflow: upstream / fork / clone / template reuse
⏱️ 25 min

🎬 **CO MÓWIĘ:**

„Teraz bardzo praktyczny temat: jak pracujemy z repo przez resztę kursu.

Moja rekomendacja na tę grupę jest taka:
- jedno wspólne repo szkoleniowe jako źródło prawdy i materiałów,
- każdy pracuje na swoim forku lub swojej kopii lokalnej,
- dzięki temu mamy wspólny upstream, ale każdy może eksperymentować bez blokowania innych.

To jest prostsze do ogarnięcia na kursie i dużo bezpieczniejsze operacyjnie.”

📺 **CO POKAZUJĘ:**
- szkic workflow:
  - upstream repo
  - forks uczestników
  - lokalne klony
- gdzie wejdzie template reuse z zewnętrznego repo

🎬 **DALSZY SKRYPT:**

„I teraz ważna rzecz dydaktyczna:
podczas kursu możemy pokazać bardzo ciekawy agentowy use case:
- agent analizuje nasze repo,
- agent analizuje repo z template'em,
- porównuje struktury,
- proponuje co skopiować, co zmienić, czego nie ruszać.

To jest świetny przykład, że agent może pracować nie tylko w jednym folderze i nie tylko pisać nowe pliki od zera, ale też robić migrację wiedzy między projektami.

Jednocześnie to jest dobry moment, żeby pokazać approvals, sandbox i pytanie o dostęp do szerszego kontekstu.”

💬 WKLEJ NA CHAT:
```text
Rekomendowany workflow repo:
1) wspólne repo kursowe = upstream
2) każdy uczestnik ma swój fork lub własny klon
3) pracujemy lokalnie na własnej kopii
4) porównujemy wyniki, nie nadpisujemy się nawzajem
5) template z zewnętrznego repo traktujemy jako źródło referencyjne, nie jako świętość
```

💬 WKLEJ NA CHAT:
```text
Prompt do agenta – analiza template repo:

Chcę porównać moje repo szkoleniowe z repo referencyjnym i przygotować plan bezpiecznego przeniesienia wybranych elementów.

Twoje zadanie:
1) opisz strukturę obu projektów,
2) wskaż podobieństwa i różnice,
3) zaproponuj co warto przenieść najpierw,
4) wskaż ryzyka bezrefleksyjnego kopiowania,
5) przygotuj plan małymi krokami,
6) niczego jeszcze nie kopiuj bez mojej zgody.
```

---

## 14:40–15:00 — Skill demo opcjonalny: Playwright Interactive + design tokens
⏱️ 20 min

🎬 **CO MÓWIĘ:**

„Jeśli mamy chwilę, pokażę Wam też bardzo praktyczny przykład użycia skilla.

Nie po to, żeby zrobić 'wow demo', tylko po to, żeby pokazać, że agent może mieć specjalizowane workflow.

Tutaj celem będzie wyciągnięcie design tokens z publicznej strony klienta:
- kolory,
- fonty,
- radiusy,
- shadowy,
- spacing,
- klasy CSS,
- logo lub assety.

To jest świetny most między research, designem i późniejszą implementacją frontendu.”

📺 **CO POKAZUJĘ:**
- ideę skilla
- gdzie w Codex Desktop można go skonfigurować
- placeholder URL w skrypcie

💬 WKLEJ NA CHAT:
```text
Skill demo – idea:
Agent z Playwright Interactive może:
- wejść na stronę,
- czytać DOM i style,
- zebrać design tokens,
- pomóc przygotować design guide pod naszą aplikację.

Na dziś używamy placeholdera URL i traktujemy to jako opcjonalny blok.
```

💬 WKLEJ NA CHAT:
```text
Prompt do agenta – design tokens:

Użyj narzędzi przeglądarkowych / Playwright, aby przeanalizować publiczną stronę:
[PLACEHOLDER_URL]

Twoje zadanie:
1) zbierz podstawowe design tokens,
2) wskaż fonty, kolory, radius, shadows, spacing i najważniejsze style komponentów,
3) zaproponuj zwięzły design guide dla naszej aplikacji, aby była stylistycznie spójna z marką,
4) jeśli to możliwe, wskaż logo lub główne assety do późniejszego reuse.

Nie kopiuj całego serwisu 1:1. Przygotuj praktyczny przewodnik projektowy.
```

---

## 15:00–15:30 — AGENTS.md, custom agents, MCP – intro i most do dnia 3
⏱️ 30 min

🎬 **CO MÓWIĘ:**

„Nie chcę dziś przeciążyć Was konfiguracją, ale muszę Wam pokazać kierunek na jutro i kolejne dni.

Są trzy rzeczy, które bardzo zwiększają skuteczność pracy agentowej na większym projekcie:
1. dobre instrukcje projektowe w AGENTS.md,
2. wyspecjalizowane role lub sub-agenci,
3. narzędzia zewnętrzne wystawione jako MCP.

To jest moment, w którym agent przestaje być tylko sprytnym terminalem, a zaczyna być częścią workflow zespołu.”

📺 **CO POKAZUJĘ:**
- przykład AGENTS.md jako spisu zasad i kontekstu
- pomysły na role:
  - FE
  - BE
  - DB/SQL
  - QA/testy
- przykład MCP:
  - IntelliJ jako źródło lintów, błędów, otwierania plików

🎬 **DALSZY SKRYPT:**

„Na tym kursie nie chcę z tego robić ceremonii. To ma być praktyczne.

Więc nasza wersja minimum na dziś jest taka:
- rozumiemy po co jest AGENTS.md,
- umiemy wymyślić 3-4 sensowne role,
- wiemy, że MCP może dać agentowi nowe narzędzia,
- a głębszą konfigurację i użycie zostawimy głównie na dzień 3, kiedy zaczniemy realnie budować.”

💬 WKLEJ NA CHAT:
```text
Przykładowe role agentowe dla naszego projektu:
- FE Agent: chat UI, formularz, komponenty, style
- BE Agent: API, orchestracja, Spring Boot
- DB Agent: schema, SQL, seed data, zapytania, explainability
- QA Agent: testy, scenariusze, walidacja acceptance criteria
```

💬 WKLEJ NA CHAT:
```text
Mini-szkielet AGENTS.md:
- cel projektu
- stack i ograniczenia
- zasady bezpieczeństwa
- zasady jakości
- styl pracy: małe kroki, diff, test
- kiedy agent ma przerwać i poprosić człowieka o decyzję
```

💬 WKLEJ NA CHAT:
```text
MCP – praktycznie:
MCP to sposób, żeby agent dostał dodatkowe narzędzia.
Np. IntelliJ MCP może dać:
- dokładniejsze błędy,
- możliwość otwierania plików,
- dostęp do kontekstu IDE.
```

---

## 15:30–16:00 — Podsumowanie dnia + handoff do dnia 3
⏱️ 30 min

🎬 **CO MÓWIĘ:**

„Dzisiaj zrobiliśmy dokładnie to, czego brakowało nam po dniu 1.

Nie tylko pogadaliśmy o narzędziach, ale przygotowaliśmy sensowne wejście do implementacji.

Po dzisiejszym dniu chcę, żebyście umieli powiedzieć:
- jaki produkt budujemy,
- jakie dane przetwarzamy,
- jaką decyzję system podejmuje,
- jak ją uzasadnia,
- i jak bezpiecznie pracować z agentem, który nam w tym pomaga.”

📺 **CO POKAZUJĘ:**
- listę gotowych artefaktów
- most do dnia 3

💬 WKLEJ NA CHAT:
```text
Po dniu 2 mamy gotowe:
□ lokalny PostgreSQL
□ wspólny pomysł aplikacji
□ model danych i główne tabele
□ PRD
□ pierwszy ADR
□ diagramy Mermaid
□ repo workflow
□ prompty do dalszej pracy
□ most do implementacji w dniu 3
```

💬 WKLEJ NA CHAT:
```text
Pytania kontrolne na koniec:
1) Czym agent różni się dziś od assistant?
2) Co realnie chroni sandbox / approvals?
3) Jak jednym zdaniem opisać nasze MVP?
4) Jakie 3 tabele są kluczowe dla decyzji?
5) Dlaczego audit trail jest ważny w tym projekcie?
```

🎬 **DALSZY SKRYPT:**

„Jutro wejdziemy w budowę rdzenia aplikacji.
Czyli z dokumentów i planów przejdziemy do kodu:
- backendu,
- bazy,
- integracji,
- pierwszego działającego flow przez UI i API.

Jeżeli dziś zrobiliśmy dobrą robotę, to jutro agent nie będzie zgadywał, tylko budował z dużo większym sensem.”

💬 WKLEJ NA CHAT:
```text
Jutro:
- zaczynamy implementację pierwszych slice'ów,
- używamy dzisiejszych artefaktów jako kontekstu,
- pokazujemy pracę z agentami bardziej "na serio",
- i dopiero wtedy dokładamy więcej automatyzacji oraz specjalizacji.
```

---

## APPENDIX A — Prompt Pack na dziś

### 1) Codex intro
```text
Explain in 5 bullets what you can and cannot do in this environment, and when you should ask me for approval.
```

### 2) PostgreSQL / Docker
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
```

### 3) Research / PRD
```text
Jesteś product managerem i analitykiem systemowym.
Przygotuj PRD dla szkoleniowego MVP "Loan Decision Copilot" dla środowiska bankowego.
Uwzględnij:
- problem statement,
- persony,
- user stories,
- acceptance criteria,
- ograniczenia niefunkcjonalne,
- out of scope,
- ryzyka,
- założenia dla danych demo.
Pisz po polsku, konkretnie, bez marketingu.
```

### 4) ADR
```text
Napisz mini ADR dla projektu "Loan Decision Copilot" obejmujący:
1) PostgreSQL zamiast SQLite
2) Chat + dynamiczny formularz zamiast zwykłego formularza
3) Stack szkoleniowy: Spring Boot + PostgreSQL + agentowy frontend + template reuse
Format:
- Kontekst
- Decyzja
- Odrzucone alternatywy
- Konsekwencje
- Ryzyka
- Trigger rewizji decyzji
```

### 5) Template repo reuse
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

### 6) Design tokens
```text
Użyj narzędzi przeglądarkowych / Playwright, aby przeanalizować publiczną stronę:
[PLACEHOLDER_URL]

Twoje zadanie:
1) zbierz podstawowe design tokens,
2) wskaż fonty, kolory, radius, shadows, spacing i najważniejsze style komponentów,
3) zaproponuj zwięzły design guide dla naszej aplikacji,
4) jeśli to możliwe, wskaż logo lub główne assety do późniejszego reuse.
```

---

## APPENDIX B — Główne zdania do zapamiętania

- „Sandbox nie jest o spowalnianiu. Jest o tym, żeby błąd był mały i odwracalny.”
- „Dziś mniej mówienia, więcej pracy i więcej czytelnych artefaktów.”
- „Nie budujemy AI-oracle. Budujemy system, który zostawia wyjaśnialny ślad decyzji.”
- „PostgreSQL w tym projekcie nie jest dodatkiem. Jest częścią logiki biznesowej i audytu.”
- „PRD i ADR są po to, żeby jutro agent i zespół nie zgadywali.”

---

## APPENDIX C — Plan minimum jeśli znów zacznie brakować czasu

Jeśli dzień zacznie się obsówać:
1. Nie skracaj ćwiczenia z PostgreSQL.
2. Nie rezygnuj z doprecyzowania app idea i modelu danych.
3. Skróć Playwright / design tokens do 5 minut lub przenieś na dzień 3.
4. AGENTS.md i MCP pokaż jako krótki teaser i przenieś głębsze demo na dzień 3.
5. Koniecznie domknij PRD + 1 ADR + backlog pierwszych slice'ów.
