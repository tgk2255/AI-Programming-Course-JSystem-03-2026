# Dzień 1 — Pełny scenariusz prowadzenia
**Temat: The AI-Augmented Developer & „odprawa" przed misją**
**Godz. 9:00–16:00 | Zoom online | Uczestnicy: Michał, Łukasz + 1 osoba**

> 🎬 = co mówię (dosłownie lub prawie dosłownie)
> 📺 = co pokazuję na ekranie
> 💬 = wklejam na Zoom chat (gotowy tekst do skopiowania)
> 🏋️ = ćwiczenie dla uczestników
> ⏱️ = czas bloku

---

## AGENDA DNIA (wyślij na starcie)

💬 WKLEJ NA CHAT:
```
Dzień 1 – agenda:
09:00 Zoom start + zasady pracy
09:20 Remote hosts – logowanie, co mamy zainstalowane
09:45 Runda zapoznawcza
10:05 Quiz kalibracyjny (anonimowy)
10:25 Moduł 1.1 – The New Frontier (AI revolution, mity, dane, case studies)
11:10 ☕ PRZERWA
11:25 Moduł 1.2 – Modele, Prompty, Vibe Coding vs Vibe Engineering, Context Engineering
12:15 Moduł 1.3 – Mission Briefing + setup środowiska (część 1)
13:00 🍽️ PRZERWA (30 min)
13:30 Moduł 1.3 – Instalacja, autoryzacja, pierwsze komendy (część 2)
14:30 ☕ OPCJONALNA PRZERWA
14:40 Moduł 1.4 – AI Research + wymagania projektu
15:30 WezTerm – dlaczego warto (mini-moduł)
15:45 Podsumowanie dnia
16:00 Koniec
```

---

## 09:00–09:20 — Zoom onboarding + zasady pracy
⏱️ 20 min

🎬 **CO MÓWIĘ:**

„Dzień dobry! Zaczynamy. Proszę włączyć kamerki jeśli możecie — dużo łatwiej mi wyczuć tempo i czy jedziemy za szybko czy za wolno. Jeśli nie możecie — spoko, rozumiem.

Szybko o zasadach, żebyśmy nie tracili czasu potem.

Pierwsza zasada: 👍 na Zoomie znaczy 'jasne, działa, jestem z tobą'. To nasz protokół synchronizacji — zamiast każdy mówi 'tak tak', dacie po prostu kciuka.

Druga: pytania wrzucacie od razu na chat, nie czekamy. Jeśli pytanie jest dłuższe albo coś nie działa — mówcie głosem, przerywajcie, naprawdę.

Trzecia: to nie jest kurs o slajdach. Będziemy pracować razem. Przez ponad 60% czasu będziecie sami próbować, a nie tylko patrzeć jak ja piszę.

Czwarta: kod od AI traktujemy na szkoleniu jako draft. Weryfikujemy. Podpisujemy kod własnym imieniem, nie imieniem modelu.
Ale powiem kiedy można kodu nawet nie czytać :)

Nasza misja przez ten tydzień: zwiększyć tempo pracy z zachowaniem jakości i bezpieczeństwa, bez produkcyjnej katastrofy. Budujemy AI-first workflow — świadomie i bezpiecznie.

Pięć dni, jeden spójny projekt, realne artefakty. Nie skaczemy po 50 narzędziach, skupiamy się na jednym głównym workflow z Codex CLI i Codex App, ale porównamy je krótko z Claude Code i IntelliJ AI Assistant i z Junie.

OK — kciuki jeśli zasady jasne?"

📺 **CO POKAZUJĘ:**
- Agendę całego tygodnia (5 dni, tematy przewodnie)
- Agendę dnia 1 (wklejoną na czat)
- Mapę końcowego efektu tygodnia: repo + workflow + prompty

💬 WKLEJ NA CHAT:
```
Zasady pracy:
👍 = jasne / działa / jestem z wami
❓ = pytanie (wrzuć na chat lub powiedz głosem)
🐛 = coś nie działa, zablokowany/a
AI = draft + weryfikacja, nie wyrocznię
Pytania od razu, nie czekamy.
```

---

## 09:20–09:45 — Remote hosts: logowanie, co mamy zainstalowane
⏱️ 25 min

🎬 **CO MÓWIĘ:**

„Zanim wejdziemy w merytorykę — potrzebujemy żebyście wszyscy byli zalogowani i mieli działające środowisko. Dlatego teraz zrobimy to razem.

Wysyłam na czat PDF z listą uczestników i danymi do logowania. Znajdźcie swoje imię, swój adres IP i dane do logowania. Nie udostępniajcie tych danych publicznie."

💬 WKLEJ NA CHAT:
```
📋 PDF z danymi do hostów: [WSTAW LINK DO PDF]
Znajdź swój wiersz → weź IP + login + hasło
Połącz się przez RDP (Windows): Podłączanie pulpitu zdalnego → wpisz IP
Połącz się przez SSH (terminal): ssh uzytkownik@IP
```

🎬 „Każdy z Was ma dedykowaną maszynę zdalną — Linux Ubuntu. Na tych maszynach jest już zainstalowane wszystko czego będziemy używać przez cały tydzień:

- **Codex CLI** — nasz główny tool. Terminal-first agent od OpenAI. Zaraz się zalogujemy.
- **Codex Desktop App** — wersja z GUI, działa na Windowsie i macOS. Na waszych lokalnych maszynach, jeśli chcecie.
- **Claude Code CLI** — agent od Anthropic, alternatywa i porównanie. Też zainstalowany.
- **WezTerm** — nowoczesny emulator terminala. Bardziej ergonomiczny od Windows Terminal w pracy agentowej.
- **IntelliJ IDEA** z pluginami AI — dla tych z Was którzy pracują w IntelliJ, pokażemy to jako mostek.
- Node.js, npm, git, Python — wszystkie standardowe narzędzia.

Macie konto administracyjne na tych hostach — to ważne przy konfiguracji sandbox. Za chwilę wytłumaczę dlaczego.

OK — kto już jest połączony? Dajcie 👍 jeśli widzicie pulpit maszyny zdalnej."

📺 **CO POKAZUJĘ:**
- Swój pulpit zdalny / demo jak wygląda środowisko
- WezTerm otwarty z kilkoma panelami
- Krótki `ls` żeby pokazać co jest zainstalowane

🎬 „Świetnie. Teraz otwórzcie WezTerm na tej maszynie. Znajdziecie go w menu Start lub na pulpicie.

Dlaczego WezTerm, a nie zwykły terminal? Powiem więcej o tym po południu, ale w skrócie: lepiej radzi sobie z długimi sesjami agentów, obsługuje powiadomienia gdy agent skończy zadanie, i ma wygodne skróty. Shift+Enter działa tutaj do nowego wiersza — w Windows Terminal nie działa i musicie pisać \\ + Enter. Małe rzeczy, ale przy kilku godzinach pracy dziennie robią różnicę."

💬 WKLEJ NA CHAT:
```
WezTerm — przydatne skróty:
Shift+Enter = nowa linia (nie wysyła komendy)
Ctrl+Backspace = usuń całe słowo
Ctrl+Shift+E = splity paneli
```

---

## 09:45–10:05 — Runda zapoznawcza
⏱️ 20 min

🎬 **CO MÓWIĘ:**

„OK — środowisko jest. Teraz zanim wejdziemy w temat, potrzebuję was poznać lepiej. Wiem już trochę z ankiety przed-kursowej, ale chcę żebyście sami opowiedzieli.

Maks 2-3 minuty na osobę. Cztery pytania:
Jeden — jaka rola i stack?
Dwa — jak dziś używacie AI? Jakich narzędzi, jak często, w jakim kontekście?
Trzy — co was najbardziej frustruje albo spowalnia przy pracy z AI?
Cztery — czego najbardziej chcecie się nauczyć w tym tygodniu?

To nie jest autoprezentacja. To jest szybki radar dla mnie, żebym wiedział gdzie zwolnić, gdzie przyspieszyć i które przykłady będą dla Was najbardziej trafne."

💬 WKLEJ NA CHAT:
```
Runda intro (2-3 min/os):
1) Rola + stack
2) Jak dziś używasz AI (narzędzia, kontekst)
3) Największa frustracja z AI
4) Co chcesz wynieść z tego tygodnia
```

📺 **CO POKAZUJĘ:**
- Te cztery pytania na ekranie
- Notuję sobie kluczowe pain pointy uczestników i wracam do nich potem

🎬 *[Po rundzie]* „Dziękuję. Słyszę że wszyscy siedzicie na Windowsie, IntelliJ, Java — i że główne use case to ChatGPT do researchu i pytania o kod. Przez tydzień wyjdziemy dalej: zamiast kopiowania odpowiedzi z chata, agent będzie pracował bezpośrednio w Waszym repozytorium. Zobaczycie różnicę."

---

## 10:05–10:25 — Quiz kalibracyjny (anonimowy)
⏱️ 20 min

🎬 **CO MÓWIĘ:**

„Teraz mały quiz. To nie egzamin. Nikt nie widzi odpowiedzi, nikt nie ocenia. Celem jest żebym wiedział które pojęcia wymagają więcej czasu, a które możemy przeskoczyć.

Jeśli czegoś nie wiecie — idealnie. Właśnie po to tu jesteście. Zaznaczacie intuicję, nie szukacie w Google.

Mam tu 8 pojęć. Przy każdym: czy rozumiesz, mogłbyś wytłumaczyć innemu programiście? 1 = nie słyszałem, 2 = słyszałem ale nie wytłumaczę, 3 = rozumiem, 4 = stosuję na co dzień."

💬 WKLEJ NA CHAT:
```
Quiz kalibracyjny — oceń 1-4:
1) Token / tokenizacja
2) Context window (okno kontekstowe)
3) Hallucination (halucynacja LLM)
4) Prompt engineering
5) RAG (Retrieval-Augmented Generation)
6) Agent vs Assistant (różnica)
7) Sandbox (izolacja procesu agenta)
8) MCP (Model Context Protocol)

1=nie słyszałem, 2=słyszałem, 3=rozumiem, 4=stosuję
Wrzućcie na czat np.: 3,2,2,3,1,2,1,1
```

🎬 *[Po zebraniu odpowiedzi]* „OK — widzę że token i context window są jaśniejsze, natomiast agent vs assistant i MCP to obszary do doprecyzowania. Świetnie — to właśnie na tym skupimy się mocniej.

Teraz szybko tylko te pojęcia gdzie widzę największy rozjazd. Nie robię pełnego wykładu — po prostu 1 zdanie + 1 przykład żebyśmy mieli wspólny język."

🎬 **DEFINICJE DO OMÓWIENIA (używaj tylko tych, które quizu wypadły najsłabiej):**

„**Token** — to nie słowo. To fragment tekstu. 'programowanie' to 4-5 tokenów. Model 'myśli' tokenami, nie słowami. Dlatego limity i koszty są w tokenach, nie w słowach. Praktyczny skutek: bardzo długi kod kosztuje więcej i może nie zmieścić się w kontekście.

**Context window** — tymczasowa pamięć robocza modelu. Wszystko co model 'widzi' podczas jednej rozmowy. Claude ma 200K tokenów — to ok. 150K słów, cała gruba książka. GPT-4o ma 128K. Ale uwaga: większy kontekst to nie zawsze lepiej. Pojawia się 'context rot' — model gubi się w zbyt długim kontekście. Ważna jest jakość i pozycja informacji, nie tylko jej ilość.

**Hallucination** — model generuje tekst który brzmi pewnie, ale jest nieprawdziwy. Może wymyślić nieistniejącą bibliotekę, zły endpoint API, niepoprawną sygnaturę metody. Dlatego zawsze weryfikujemy generated code — nie dlatego że AI jest głupie, ale dlatego że tak działają modele autoregresywne: przewidują kolejny token, nie sprawdzają faktów.

**Agent vs Assistant** — assistant odpowiada na pytania. Agent działa autonomicznie: planuje, wykonuje komendy w terminalu, edytuje pliki, uruchamia testy, iteruje. Codex CLI to agent. ChatGPT to assistant. Różnica: agent ma narzędzia i środowisko; assistant ma tylko chat."

---

## 10:25–11:10 — Moduł 1.1: The New Frontier
⏱️ 45 min

🎬 **CO MÓWIĘ:**

„OK, zaczynamy właściwy dzień.

Zanim włączymy Codexa i zaczniemy pisać — potrzebuję żebyście zrozumieli *dlaczego*. Nie żebym Was przekonał do AI — wy już tu jesteście. Ale żebyście mieli właściwy mental model: co AI robi dobrze, co robi źle, co za tym stoi.

Zacznijmy od pytania: Czy AI Was zastąpi?"

🎬 *[Pause, poczekaj na reakcje]* „Słyszę dwie odpowiedzi. Część mówi tak, część nie. Prawda jest bardziej nuansowana.

GitHub w 2025 roku zbadał 120 tysięcy developerów na 14 platformach przez rok. Stanford opublikował wyniki. I wiecie co wyszło?"

💬 WKLEJ NA CHAT:
```
Stanford / GitHub badanie AI w programowaniu (120K devs):
https://www.youtube.com/watch?v=JvosMkuNxF8

METR badanie (07.2025): https://metr.org/blog/2025-07-10-early-2025-ai-experienced-os-dev-study/
```

🎬 „Wyniki były... zaskakujące. Produktywność w wielu przypadkach spadła. Doświadczeni developerzy pracujący nad złożonymi, nieznajomymi zadaniami byli wolniejsi z AI niż bez AI. W zadaniach prostych i dobrze znanych — AI przyspieszało.

To ważne. AI nie jest magicznym turbo-doładowaniem. Jest narzędziem z charakterystyką użycia. Sprawdza się w konkretnych kontekstach i spowalnia w innych.

Ale jest jeszcze coś — tzw. **Productivity J-Curve**. Kiedy zaczynasz uczyć się nowego narzędzia, najpierw jesteś wolniejszy. Przez 1-2 miesiące możecie być mniej produktywni. Potem, jeśli nauczycie się tego dobrze, przyspieszacie.

Dlatego ten tydzień nie jest o natychmiastowym turbo. Jest o tym, żebyście wyszli stąd z właściwym workflow — takim, który po kilku tygodniach praktyki realnie przyspieszy Waszą pracę."

🎬 „Ale żeby nie było za pesymistycznie — są też przykłady naprawdę ekstremalnego przyspieszenia. Jeden z najbardziej spektakularnych to Peter Steinberger."

💬 WKLEJ NA CHAT:
```
Case study: Peter Steinberger + OpenClaw
- 1000-1500 commitów dziennie z Codex agents
- 50 równoległych agentów Codex do triażu PR
- 220K gwiazdek na GitHubie w kilka tygodni
- Dołączył do OpenAI w lutym 2026

Artykuł: https://steipete.me/posts/2025/shipping-at-inference-speed
```

🎬 „Człowiek z Wiedniem, iOS developer, zaczął używać Codexa w październiku 2025. 26 października — 1374 commitów w jeden dzień. Nie pisał ich ręcznie — orkiestrował flotę agentów. Każdy agent w osobnym git worktree, każdy pracuje nad osobnym feature, wyniki review przez człowieka przed mergem.

To nie jest typ który zniknie z rynku. To jest typ który wie jak korzystać z narzędzia.

I drugi przykład, bliżej nas — Microsoft."

💬 WKLEJ NA CHAT:
```
Microsoft: rewrite C/C++ → Rust z pomocą AI
"1 inżynier, 1 miesiąc, 1 milion linii kodu"
Cel: eliminacja C/C++ z Microsoft do 2030
https://www.linkedin.com/posts/galenh_principal-software-engineer-coreai-microsoft-activity-7407863239289729024-WTzf
```

🎬 „Galen Hunt, Principal Software Engineer z CoreAI w Microsoft: 'Moim celem jest wyeliminowanie każdej linii C i C++ z Microsoftu do 2030 roku. Nasze podejście: kombinacja AI i algorytmów do przepisywania największych codebase'ów. North Star: 1 inżynier, 1 miesiąc, 1 milion linii kodu.'

Teraz wpływ na szerszy ekosystem. Co się dzieje z narzędziami, które wygrywały przez *wizyty* programistów?"

💬 WKLEJ NA CHAT:
```
Realny wpływ AI na ekosystem:
- TailwindCSS: spadek ruchu na docs → problem z przychodami OSS
  https://www.youtube.com/watch?v=luhgjBrRulk
- StackOverflow: umierający https://www.youtube.com/watch?v=Gy0fp4Pab0g
- 60% wyszukiwań Google bez kliknięcia (AI search)
- Platformy kursowe: Udemy i podobne tracą ruch
  https://www.youtube.com/watch?v=WCGTQBCE3FA
```

🎬 „TailwindCSS ma problem — programiści przestali odwiedzać dokumentację, bo pytają AI. A ich model biznesowy opierał się na ruch na stronie docs. To pierwszy przykład jak AI pośrednio niszczy projekty open source przez zmianę zachowania użytkowników.

StackOverflow. 20 lat byliśmy z nim. Teraz odpada.

60% wyszukiwań Google nie kończy się kliknięciem — AI odpowiada bezpośrednio. To zmienia ruch na stronach i to jest dopiero początek.

Ale — i to jest ważne — to nie znaczy że wystarczy tylko AI. Jakość i niezawodność nadal mają znaczenie. AI generuje dużo kodu, ale też dużo bałaganu."

🎬 „Teraz szybko — gdzie na tej skali jesteście Wy?"

💬 WKLEJ NA CHAT:
```
Poziomy AI-Assisted Programming:
0 – Manual (piszesz wszystko ręcznie)
1 – Autocomplete (Copilot tab)
2 – Chat (pytasz, kopiujesz)
3 – Agent (edytuje pliki, używa narzędzi, AGENTS.md)
4 – Multi-Agent (flota agentów, async, CI/CD)
5 – Dark Factory (w pełni autonomiczne; ludzie zarządzają specami, nie kodem)

Gdzie jesteś teraz? Gdzie chcesz być po tym kursie?
```

🎬 „'Dark Factory' — zapożyczone z produkcji. Fabryka która działa w ciemności bo nie ma tam ludzi. W softwearze: agenty shipują fituresy 24/7, ludzie ustawiają kierunek i robią review.

Nie twierdzę że Dark Factory jest dobra dla wszystkich kontekstów. W środowisku bankowym, regulowanym — kontrola i audyt są kluczowe. Ale warto wiedzieć że ten kierunek istnieje, bo zmienia reguły gry.

I ostatnia rzecz w tym module — trzy nowe role w erze agentowej."

💬 WKLEJ NA CHAT:
```
3 nowe role IT w erze AI:

1. Orchestrator – zarządza agentami, pisze AGENTS.md, PRD, zasady.
   "Nowy tech lead"

2. System/Infra Builder – buduje scaffolding: CI/CD, MCP servers,
   sandboxes, observability, kontrola kosztów.

3. Domain Expert as Programmer – prawnik, analityk, lekarz który
   opisuje co chce w języku naturalnym i dostaje działający software.
   Nie wie że stał się programistą.
```

🎬 „Ta trzecia rola jest najbardziej disrupcyjna. Zupełnie nowa podaż 'programistów' którzy nigdy nie napisali linii kodu. To zmienia rynek pracy — ale nie tak jak myślicie. Nie eliminuje programistów. Eliminuje barierę wejścia dla ekspertów domenowych.

Pytanie: który z Was widzi siebie jako Orchestratora? To ten kurs."

🏋️ **MINI-ĆWICZENIE (3 min):**
„Napiszcie na czacie jedno zdanie: na którym poziomie (0-5) jesteście teraz, i jaka rola (Orchestrator / Builder / Domain Expert) najbardziej pasuje do waszego kontekstu pracy."

---

## 11:10–11:25 — PRZERWA ☕
**Przypomnij przed przerwą:**

💬 WKLEJ NA CHAT:
```
☕ Przerwa 15 min → wracamy 11:25
Po przerwie: Modele AI, Prompty, Vibe Coding
```

---

## 11:25–12:15 — Moduł 1.2: Modele, Prompty, Vibe Coding
⏱️ 50 min

🎬 **CO MÓWIĘ:**

„Zanim uruchomimy agenta, musimy zrozumieć z czym pracujemy. Jaka jest różnica między modelami, jak działa prompt, i co to właściwie znaczy 'Vibe Coding'.

Zacznijmy od modeli. Mamy teraz trzy główne ekosystemy i kilka open source'owych."

💬 WKLEJ NA CHAT:
```
Główne modele LLM (marzec 2026):

OpenAI: o4, o3-mini, GPT-5.2
Anthropic: Claude Opus 4.6, Sonnet 4.6, Haiku 4.5
Google: Gemini 2.5 Pro, Flash 2.0
Meta: Llama 4 (open source)
Open source: GLM-4.7 (ZhipuAI), Minimax M2.1

Benchmarki:
- SWE-Bench (coding): https://www.swebench.com/
- Terminal-Bench: https://www.tbench.ai/leaderboard/terminal-bench/2.0
- Tool Calling: https://gorilla.cs.berkeley.edu/leaderboard.html
- LM Arena WebDev: https://lmarena.ai/pl/leaderboard/webdev
```

🎬 „Nie wchodźmy teraz w szczegóły każdego modelu. Powiem Wam co jest praktycznie ważne:

**Opus 4.6** — najlepszy dla architektury i złożonych decyzji. Drogi.
**Sonnet 4.6** — nasza codzienna 'robocza' bestia. Dobry balans ceny i jakości.
**Haiku 4.5** — szybki i tani, dobry do prostych powtarzalnych tasków.

Codex używa modeli OpenAI. Claude Code używa modeli Anthropic. W Codex możecie też skonfigurować OpenRouter i używać różnych modeli, włącznie z lokalnymi przez Ollama.

Ważna obserwacja: różnica między modelami jest mniejsza niż różnica między harness'ami — czyli środowiskiem w którym model pracuje."

💬 WKLEJ NA CHAT:
```
🔑 Kluczowy insight z badań OpenAI (Engineering Blog):
"Ten sam model w różnych środowiskach: 78% vs 42% na tym samym benchmarku.
Różnica 36 punktów procentowych — z samego środowiska, nie modelu."

Źródło: https://openai.com/index/harness-engineering/
```

🎬 „To jest może najważniejsza rzecz techniczna którą powiemy dziś. Środowisko wykonania — sandbox, narzędzia, jak agent dostaje zadanie — ma większe znaczenie niż wybór modelu. Dlatego konfiguracja i workflow są kluczowe.

Teraz prompt engineering. Jak rozmawiać z modelem żeby dostawać dobre wyniki."

💬 WKLEJ NA CHAT:
```
Prompt Engineering — fundamenty:

ZERO-SHOT: "Napisz test jednostkowy dla metody calculateInterest()"

FEW-SHOT: "Napisz test jednostkowy.
Przykład dobrego testu: [przykład]
Teraz napisz dla: calculateInterest()"

CHAIN-OF-THOUGHT: "Pomyśl krok po kroku zanim odpiszesz.
Jaka jest logika metody? Co można przetestować?
Jakie edge cases?"

ROLE: "Jesteś doświadczonym Java developerem pracującym
z Spring Boot w środowisku bankowym z wysokimi wymaganiami
bezpieczeństwa. Napisz..."
```

🎬 „Ale prompt engineering w 2026 roku to już nie to co w 2023. Teraz mówimy o **Context Engineering** — to ważniejsze niż sam prompt.

Mam tu jeden świetny artykuł od Anthropic na ten temat."

💬 WKLEJ NA CHAT:
```
Context Engineering > Prompt Engineering
Artykuł Anthropic: https://www.anthropic.com/engineering/effective-context-engineering-for-ai-agents

Praktycznie: agent jest tak dobry jak kontekst który dostaje.
AGENTS.md / CLAUDE.md to jest właśnie context engineering.
```

🎬 „Kilka praktycznych zasad dla dobrego promptu:

**1. Daj rolę i kontekst.** 'Jesteś senior Java developerem, projekt to backend bankowy na Spring Boot 3.2, używamy Java 21' — to ważniejsze niż magia słów.

**2. Konkret > ogólnik.** 'Napisz REST endpoint POST /api/payments z walidacją kwoty i currency' jest dużo lepszy niż 'zrób endpoint do płatności'.

**3. Ogranicz scope.** Agent bez jasnych granic będzie ekspandował. 'Tylko ta klasa, tylko te testy, nie ruszaj konfiguracji'.

**4. Knowledge cutoff.** AI nie zna najnowszych wersji bibliotek. Jeśli używacie Java 25 albo Spring 4 — dajcie docs do kontekstu. Bezpieczniej jest trzymać się Java 21 (2023) gdzie modele mają dobry trening.

Język promptów — i to was pewnie zaskoczy:"

💬 WKLEJ NA CHAT:
```
Czy polski działa w promptach?
TAK. Polski jest w TOP 5 języków w benchmarkach LLM.
Źródło: https://cryps.pl/sztuczna-inteligencja-mowi-po-polsku-nasz-jezyk-zdeklasowal-angielski-w-najnowszym-rankingu/

Praktycznie: możecie promptować po polsku.
Kod nadal będzie po angielsku (zmienne, komentarze).
```

🎬 „Teraz Vibe Coding — co to jest i dlaczego jest zarówno wspaniałe jak i niebezpieczne."

💬 WKLEJ NA CHAT:
```
Vibe Coding (definicja Andreja Karpathy):
https://x.com/karpathy/status/1886192184808149383

"Fully give in to the vibes, embrace exponentials,
forget that the code even exists."
– Karpathy, 2025

Vibe Coding = opisujesz co chcesz, AI generuje,
ty akceptujesz bez dogłębnej analizy.

Vibe Engineering = opisujesz, AI generuje,
TY ROZUMIESZ I WERYFIKUJESZ.
```

🎬 „Microsoft nazwał swój nowy Power Apps 'Vibe'. YCombinator ubiera się w homary. To nie jest już niszowy termin.

Ale jest problem — i to jest sedno 'Czy AI nas przyspiesza czy spowalnia'. Vibe Coding prowadzi do:
- kodu który działa ale nikt nie rozumie,
- tech debt który narosł z prędkością światła,
- problemów bezpieczeństwa które AI przemilczało bo nie pytałeś,
- zanikających 'mięśni' programistycznych — skill degradation.

Jest świetne wideo na YouTube:"

💬 WKLEJ NA CHAT:
```
"AI Coding Sucks" (dlaczego vibe coding ma granice):
https://www.youtube.com/watch?v=0ZUkQF6boNg

Kluczowe ryzyka:
- Nadmierne poleganie na AI
- Halucynacje w krytycznym kodzie
- Prywatność danych (co wklejasz do modelu?)
- Degradacja umiejętności
- Kod który przechodzi review ale nie rozumiemy go
```

🎬 „Dlatego nasz kurs nie uczy Vibe Codingu. Uczymy Vibe Engineering: używasz AI, ale rozumiesz co generuje, weryfikujesz i podpisujesz swoim imieniem.

Jeszcze jedno przed setupem. AGENTS.md i CLAUDE.md. To jest context engineering w praktyce."

💬 WKLEJ NA CHAT:
```
AGENTS.md (standard Codex) / CLAUDE.md (standard Claude Code):
Plik w repozytorium który mówi agentowi:
- co to jest za projekt,
- jak pracować,
- jakie zasady bezpieczeństwa,
- jakie ograniczenia.

Standard: https://agents.md/
Artykuł Anthropic: https://claude.com/blog/using-claude-md-files

Przykład (Microsoft mcp-for-beginners):
https://github.com/microsoft/mcp-for-beginners/blob/main/AGENTS.md
```

🏋️ **MINI-ĆWICZENIE (5 min):**

💬 WKLEJ NA CHAT:
```
Mini-ćwiczenie: Prompt improvement

Popraw ten prompt:
"Napisz kod do obsługi płatności"

Dodaj:
1) rolę i kontekst techniczny
2) konkretny scope
3) ograniczenia bezpieczeństwa
4) tech stack (Java/Spring)

Wrzućcie swoje wersje na czat — porównamy.
```

🎬 *[Omów 2-3 wersje z uczestników]* „Świetnie. Widzicie jak różne są wyniki? Ten sam cel, ale zupełnie inny prompt daje zupełnie inne wejście dla agenta. OK — czas na setup!"

---

## 12:15–13:00 — Moduł 1.3 (część 1): Mission Briefing + przegląd narzędzi
⏱️ 45 min

🎬 **CO MÓWIĘ:**

„Teraz Mission Briefing — co będziemy budować przez ten tydzień i jakich narzędzi będziemy używać.

Projekt tygodnia to aplikacja webowa — prosty 'AI Idea Validator'. Użytkownik wpisuje pomysł na aplikację lub feature, AI robi research, waliduje rynkowo, zwraca raport. Backend w Java Spring Boot, frontend uproszczony. Dlaczego ten projekt? Bo dotyka wszystkich etapów SDLC które będziemy przerabiać: wymagania, design, kod, testy, deploy.

Będziecie mogli zaadaptować go do swojego tech stacku — Łukasz, Ty możesz równolegle myśleć o Waszym systemie bankowym. Wrócę do angularowych odpowiedników React-owych rozwiązań.

Narzędzia — teraz pokażę Wam ekosystem, a następnie zainstalujemy i zalogujemy się do każdego."

📺 **CO POKAZUJĘ:**
- Tabelę narzędzi (poniżej)

💬 WKLEJ NA CHAT:
```
Nasze narzędzia (hierarchia):

GŁÓWNY WORKFLOW:
├── Codex CLI — terminal agent, sandbox, multi-agent
└── Codex Desktop App — GUI, równoległe agenty, automations

PORÓWNANIE (pokazujemy, nie główny tor):
├── Claude Code CLI — bash harness, MCP, świetny do eksploracji
└── IntelliJ Junie/AI Assistant — mostek IDE dla Java devs

TERMINAL:
└── WezTerm — ergonomia, powiadomienia, TUI splits

RESEARCH / CHAT:
├── ChatGPT (macie Plus/Teams)
└── Perplexity — deep research

NIE ROBIMY TEGO KURSU:
Cursor, Zed, OpenCode, Goose, Aider
(istnieją, znamy je, ale nie rozpraszamy się)
```

🎬 „Dlaczego Codex CLI jako główny, a nie Claude Code?

Codex ma sandbox ON domyślnie — to ważne w kontekście enterprise i regulowanym. Claude Code wymaga ręcznej aktywacji sandbox. Codex ma też bardziej strukturowane worktree dla multi-agent. Na Windowsie Codex nie potrzebuje WSL — działa natywnie.

Claude Code jest świetny do eksploracji i ma lepszy bash harness — wrócimy do tego porównania za chwilę i przez cały kurs. Ale dla 'domyślnego bezpiecznego startu' — Codex.

IntelliJ z Junie i AI Assistant — dla tych z Was którzy chcą zostać w IDE. Pokazujemy je jako mostek, nie jako główny workflow. JetBrains AI Assistant ma niski limit (10 credits dla $10), GitHub Copilot w IntelliJ jest lepszą opcją jeśli chcecie agent w IDE.

OK — teraz zalogujmy się do wszystkiego."

---

## 13:00–13:30 — PRZERWA 🍽️
**Przypomnij przed przerwą:**

💬 WKLEJ NA CHAT:
```
🍽️ Przerwa obiadowa 30 min → wracamy 13:30
Po przerwie: autoryzacja Codex + Claude Code,
konfiguracja sandbox, pierwsze komendy.
Zostawcie otwarte WezTerm na maszynie zdalnej.
```

---

## 13:30–14:30 — Moduł 1.3 (część 2): Instalacja, autoryzacja, sandbox, pierwsze komendy
⏱️ 60 min

🎬 **CO MÓWIĘ:**

„OK — wracamy. Teraz robimy to co jest naprawdę ważne: zalogowanie się do narzędzi i pierwsze komendy. Będziemy to robić razem, krok po kroku. Jeśli coś nie działa — mówicie od razu, nie czekamy.

Zacznijmy od Codex CLI. Jest już zainstalowany na maszynach zdalnych. Możecie go też zainstalować lokalnie — przez npm."

💬 WKLEJ NA CHAT:
```
Instalacja Codex CLI (jeśli chcecie lokalnie):
npm install -g @openai/codex

Sprawdź wersję:
codex --version

Dokumentacja: https://developers.openai.com/codex/cli/
```

🎬 „Teraz autoryzacja. To jest miejsce gdzie na maszynach zdalnych jest pewna komplikacja — bo nie chcemy logować się naszym kontem OpenAI na cudzej maszynie. Pokażę Wam trzy opcje."

💬 WKLEJ NA CHAT:
```
Codex CLI — opcje autoryzacji:
codex auth

Opcja 1: OpenAI konto (OAuth przez przeglądarkę)
Opcja 2: Kod z aplikacji OpenAI (NAJŁATWIEJSZE ✅)
Opcja 3: Własny klucz API (dla firmowego konta/OpenRouter/Ollama)
```

🎬 „**Opcja 2 — rekomendowana dla Was dziś:**

Otwórzcie aplikację OpenAI (chatgpt.com) lub aplikację mobilną na Waszym lokalnym urządzeniu. Przejdźcie do Settings → API → Generate code. Skopiujcie kod. Wróćcie do terminala na maszynie zdalnej, wybierzcie opcję 2, wklejcie kod.

Gotowe — zalogowani bez udostępniania hasła na maszynie zdalnej.

**Opcja 1 — jeśli ktoś woli OAuth:**

Uruchamiamy `codex auth`, wybieramy opcję 1. Terminal chce otworzyć przeglądarkę. Ale UWAGA — nie logujemy się tam. Zamiast tego:

Przejdźcie do paska URL w przeglądarce na maszynie zdalnej. Naciśnijcie Ctrl+Z — to cofa do poprzedniego URL, przed przekierowaniem. Zobaczycie URL z `authorize_response` i `app_id`. Skopiujcie go. Otwórzcie TEN URL w lokalnej przeglądarce (Waszej, nie maszyny zdalnej). Tam się zalogujcie. Po zalogowaniu dostaniecie redirect na `localhost` — skopiujcie ten localhost URL. Wróćcie do przeglądarki na maszynie zdalnej, wklejcie ten localhost URL. Gotowe — zaautentyfikowane."

📺 **CO POKAZUJĘ:**
- Demo krok po kroku opcji 2 (kod) i opcji 1 (OAuth redirect trick)

💬 WKLEJ NA CHAT:
```
Codex auth — OAuth redirect trick (opcja 1):
1. codex auth → wybierz opcja 1
2. W przeglądarce zdalnej: Ctrl+Z w pasku URL
   (cofa do URL sprzed redirect)
3. Skopiuj URL z "authorize_response"
4. Wklej w LOKALNEJ przeglądarce → zaloguj się
5. Po zalogowaniu: skopiuj redirect localhost URL
6. Wklej w przeglądarce NA MASZYNIE ZDALNEJ
→ Zaautentyfikowane ✅
```

🎬 „Teraz Claude Code CLI — analogicznie. Jedyna różnica: przy opcji OAuth po zalogowaniu w lokalnej przeglądarce jest automatyczny redirect — nie trzeba kopiować localhost URL, robi się samo.

WAŻNA kolejność: najpierw CLI, potem Desktop App. Desktop App pobiera auth dane z CLI. W aplikacjach desktopowych auth na hostach zdalnych działa gorzej, więc CLI jest fundamentem."

💬 WKLEJ NA CHAT:
```
Claude Code CLI — auth:
claude auth login

OAuth: skopiuj URL → wklej w lokalnej przeglądarce
→ zaloguj → automatyczny redirect ✅

Kolejność: CLI najpierw, Desktop App potem
(Desktop pobiera auth z CLI)

Dokumentacja: https://code.claude.com/docs/en/setup
```

🎬 „Teraz **konfiguracja sandbox w Codex**. To jest jeden z powodów dla których Codex jest naszym głównym torem.

Po pierwszej autentyfikacji Codex zapyta Was o tryb sandbox. Mamy trzy opcje:"

💬 WKLEJ NA CHAT:
```
Codex — tryby sandbox (przy pierwszym uruchomieniu):

1. Set up default sandbox (requires Administrator) ← WYBIERAMY TO
   → Najlepsza izolacja. Na naszych hostach mamy konto admina.
   → Może zająć kilka minut przy pierwszym setup.

2. Use non-admin sandbox (wyższe ryzyko przy prompt injection)

3. Quit

Tryby sandbox (po konfiguracji):
read-only     = tylko czyta, nic nie zmienia
workspace-write = pisze tylko w workspace (DOMYŚLNY)
danger-full-access = bez ograniczeń (tylko w VM/kontenerze!)
```

🎬 „Dlaczego sandbox jest ważny? Mały, ale ważny przykład bezpieczeństwa:

Landlock LSM na Linuxie — to kernel-level security module. Codex ustawia go przy starcie procesu. Oznacza to: nawet gdyby ktoś wstrzyknął złośliwy prompt do agenta, agent nie może wyjść poza katalog projektu. Nie może dotknąć `~/.ssh`, `~/.aws`, innych projektów. To jest fundamentalna różnica od pracy bez sandbox — gdzie agent ma dostęp do wszystkiego co ma user.

Na Windowsie Codex używa AppContainer — analogiczny mechanizm, bez potrzeby WSL.

Pierwszy uruchomienie sandbox może zająć 3-5 minut — Codex pobiera i konfiguruje środowisko. Cierpliwość."

📺 **CO POKAZUJĘ:**
- `codex` → pierwszy start → wybór sandbox → czekamy
- Jak wygląda Codex CLI po uruchomieniu

🎬 „Teraz kiedy jesteśmy zalogowani i mamy sandbox — podstawowe komendy Codex CLI."

💬 WKLEJ NA CHAT:
```
Codex CLI — kluczowe komendy:

# Start:
codex                    ← interaktywny chat
codex "zadanie"          ← od razu z taskiem
codex --help             ← pełna lista opcji

# W sesji:
/help                    ← lista komend
/status                  ← aktualny stan
/statusline              ← konfiguracja status bar
/model                   ← zmiana modelu
/history                 ← historia tasków
/experimental            ← eksperymentalne funkcje (w tym multi-agent!)

# Statusline (limity):
/statusline              ← ustaw limit 5h i tygodniowy + git branch

# Nowa linia:
\ + Enter               ← nowa linia bez wysyłania (Windows Terminal)
Shift + Enter            ← nowa linia (WezTerm i nowoczesne terminale)

# Tryb sandbox:
codex --sandbox workspace-write "zadanie"
codex --sandbox read-only "przejrzyj kod i zrób raport"
```

💬 WKLEJ NA CHAT:
```
Codex vs Claude Code — szybkie porównanie:

                    Codex CLI    Claude Code CLI
Sandbox domyślny:   ✅ ON        ❌ OFF (opt-in)
Windows natywny:    ✅ tak       ⚠️ WSL zalecany
Multi-agent:        ✅ worktree  ✅ sub-agents
Bash harness:       ❌ RPC       ✅ pełny bash
Eksploracja kodu:   ⚠️ słabsza  ✅ mocna
Context window:     128K         200K (Claude)
Modele:             OpenAI       Anthropic
Koszt/task:         Niższy      Wyższy (~3-4x)
```

🎬 „Multi-agent w Codex — to jest eksperymentalna funkcja. Żeby ją włączyć:"

💬 WKLEJ NA CHAT:
```
Codex — włącz multi-agent (eksperymentalne):
/experimental
→ włącz multi-agent

Jak działa:
- Każdy agent = osobny git worktree
- Agenty koordynują przez git branches i PRs
- Landlock izoluje worktree od siebie
- Nie możesz przypadkowo zmieszać zmian między agentami

Dokumentacja: https://developers.openai.com/codex/multi-agent/
```

🎬 „Statusline — jeden ze sprytniejszych feature'ów Codexa. Możecie ustawić limity godzinowe i tygodniowe żeby nie przepalić całego budżetu API przez przypadek, i mieć w terminalu info o aktualnej branch git."

💬 WKLEJ NA CHAT:
```
Codex statusline:
/statusline
→ ustaw limit 5h (dzienny) i tygodniowy
→ dodaj git branch do status bar

Pojawi się w dolnej belce terminala - widoczne zawsze.
```

🏋️ **ĆWICZENIE 1 — Pierwsze zadanie w Codex (10 min):**

🎬 „OK — teraz wasz pierwszy task w Codex. Stwórzcie katalog projektu i każcie Codexowi zainicjować strukturę."

💬 WKLEJ NA CHAT:
```
Ćwiczenie 1: Pierwszy task w Codex

1. Stwórzcie katalog projektu:
   mkdir ai-idea-validator && cd ai-idea-validator
   git init

2. Uruchomcie Codex:
   codex

3. Wpiszcie (pamiętajcie: \ + Enter dla nowej linii!):
   Stwórz strukturę projektu Java Spring Boot dla aplikacji
   "AI Idea Validator". Backend REST API. Dodaj README.md
   z krótkim opisem projektu i listą planowanych endpointów.
   Tylko struktura katalogów i pliki konfiguracyjne - bez implementacji.

4. Przejrzyjcie co wygenerował. Sprawdźcie README.
5. Jeśli OK → approve. Jeśli nie → poproście o korektę.

Wyniki wrzućcie na czat: co dostaliście?
```

🎬 *[Pomagaj uczestnikom, obserwuj co generują, omawiaj wyniki]*

🎬 „Teraz krótko porównanie głosowe — i na boku IntelliJ."

💬 WKLEJ NA CHAT:
```
Opcjonalnie: voice mode

Handy STT (open source, jak SuperWhisper):
https://handy.computer/

Alternatywa: Claude Code /voice (tylko Claude Code CLI)
claude /voice

Przydatne gdy: dyktujecie długie prompty lub opisujecie złożony problem
```

---

## 14:30–14:40 — OPCJONALNA PRZERWA ☕

💬 WKLEJ NA CHAT:
```
☕ Opcjonalna przerwa 10 min
Po przerwie: AI Research + wymagania projektu (Moduł 1.4)
```

---

## 14:40–15:30 — Moduł 1.4: AI Research i wymagania projektu
⏱️ 50 min

🎬 **CO MÓWIĘ:**

„Moduł 1.4. Teraz AI Research — jak AI może pomóc w fazie analizy i wymagań. To jest często pomijane miejsce, a to tu AI daje ogromną wartość.

Zaczniemy od Deep Research — czyli jak wygenerować solidną analizę rynkową, techniczną i wymagania projektowe za pomocą AI.

Macie ChatGPT Plus — to doskonałe miejsce na start."

💬 WKLEJ NA CHAT:
```
Deep Research tools:
- ChatGPT (macie Plus): chatgpt.com → Deep Research mode
- Perplexity: perplexity.ai (świetny do researchu technicznego)
- Gemini Research: gemini.google.com
- Groq: groq.com (szybki, dobry do iteracji)

Kiedy używać którego:
- Perplexity → fakty techniczne, porównania bibliotek, aktualne dane
- ChatGPT → synthesizing, business analysis, pisanie dokumentów
- Gemini → research z dostępem do aktualnych danych Google
```

🎬 „Pokażę Wam jak przeprowadzić research dla naszego projektu. Najpierw Perplexity."

📺 **CO POKAZUJĘ:**
- Otwieram Perplexity
- Wpisuję research prompt dla projektu

💬 WKLEJ NA CHAT:
```
Prompt do Perplexity — research techniczny (skopiuj i użyj!):

"Research for Java Spring Boot REST API project.
I need to validate AI-powered web application ideas.
Compare: Spring AI vs LangChain4j vs OpenAI Java SDK for
integration with OpenAI APIs in Spring Boot 3.2 / Java 21.
Include: maturity level, community, documentation quality,
feature set, licensing. Which is best for a banking-context
Java backend team starting with AI in 2026?"
```

🎬 *[Po wygenerowaniu]* „Zobaczcie — Perplexity daje nam konkretne porównanie z linkami do źródeł. To nie jest hallucynacja bo Perplexity szuka w sieci. Możemy to zweryfikować.

Teraz używamy tych wyników do stworzenia pierwszego artefaktu projektu — README z wymaganiami."

💬 WKLEJ NA CHAT:
```
Prompt do Codex — wygeneruj README z wymaganiami:

Na podstawie tego researchu stwórz README.md projektu
"AI Idea Validator" zawierający:
1. Opis projektu (2-3 zdania)
2. Tech stack: Java 21, Spring Boot 3.2, Spring AI
3. User Stories (min. 5, format: "Jako [rola], chcę [akcja], żeby [cel]")
4. Roadmapa: MVP (tydzień 1) i V1 (przyszłe)
5. Wymagania do uruchomienia
6. Struktura endpointów API (placeholder)

Projekt jest w środowisku bankowym — bezpieczeństwo i audyt są kluczowe.
```

🎬 „Teraz AGENTS.md — plik który będzie 'briefem' dla agentów przez cały tydzień."

💬 WKLEJ NA CHAT:
```
Prompt do Codex — stwórz AGENTS.md:

Stwórz AGENTS.md dla projektu AI Idea Validator.
Projekt: Java 21, Spring Boot 3.2, Spring AI, Maven.
Środowisko: bankowe, regulowane.
Zasady pracy:
- Pisze testy dla nowej logiki
- Nie zmienia konfiguracji bezpieczeństwa bez aprobaty
- Commit messages w języku angielskim
- Sprawdza czy nowy kod ma walidację inputu
- Sandbox mode: workspace-write (domyślny)
Format: użyj przykładu z https://agents.md/ jako bazy.
```

🏋️ **ĆWICZENIE 2 — Research własnego projektu (15 min):**

💬 WKLEJ NA CHAT:
```
Ćwiczenie 2: Research + wymagania

1. W Perplexity lub ChatGPT zrób research:
   - Jaki framework AI dla Java chcecie użyć?
   - Jakie alternatywy stoją? (Spring AI / LangChain4j / OpenAI SDK)

2. W Codex wygenerujcie pierwszy szkic README.md
   z user stories dla waszego projektu

3. Stwórzcie AGENTS.md (możecie użyć promptu wyżej)

4. Przejrzyjcie wyniki — co poprawić?

Pytajcie jeśli coś nie działa lub chcecie feedback!
```

🎬 *[Chodź po uczestnikach, sprawdzaj wyniki, daj feedback]*

🎬 „Zanim przejdziemy do WezTerm — krótkie podsumowanie tego modułu.

AI w fazie requirements/research to może być jedno z najlepszych zastosowań. Nie generuje kodu — generuje strukturę myślenia. Często najlepszy prompt na research to 'porównaj X i Y w kontekście Z, daj pros/cons i rekomendację dla zespołu beginners/intermediate.'

Ale pamiętajcie: weryfikujcie. Szczególnie linki i wersje bibliotek."

---

## 15:30–15:45 — WezTerm mini-moduł
⏱️ 15 min

🎬 **CO MÓWIĘ:**

„Na koniec dnia — krótko o WezTermie. To nie jest obowiązkowy element kursu. Wasz obecny terminal nadal działa. Ale chcę pokazać dlaczego warto go rozważyć przy dłuższej pracy z agentami.

Trzy powody:"

💬 WKLEJ NA CHAT:
```
WezTerm vs Windows Terminal — kiedy warto zmienić:

✅ WezTerm lepszy dla pracy agentowej:
1. Shift+Enter = nowa linia (Windows Terminal: musisz pisać \+Enter)
2. Stabilna praca z TUI przy długich sesjach agentów
3. Powiadomienia gdy agent skończy zadanie (Windows Terminal: brak)
4. Ctrl+Backspace = usuń całe słowo/string
5. Splity paneli: kod / agent / logi równocześnie
6. Spójny cross-platform (Windows/macOS/Linux)

⚠️ Zostań przy obecnym terminalu jeśli:
- Działa stabilnie i nie masz problemów z sesjami
- Nie chcesz kolejnej konfiguracji

WezTerm nie jest obowiązkowy. Narzędzie ma zmniejszać tarcie, nie ego.
```

📺 **CO POKAZUJĘ:**
- Szybkie demo: split paneli, Shift+Enter, powiadomienie po zadaniu

💬 WKLEJ NA CHAT:
```
Mikro-checklista: czy potrzebujesz WezTerm?
□ Czy terminal gubi mnie przy długich sesjach agenta?
□ Czy brakuje mi wygodnych splitów / szybkich skrótów?
□ Czy potrzebuję powiadomień o zakończeniu długich komend?

Jeśli 2+ odpowiedzi to TAK → warto przetestować WezTerm.
Instalacja: https://wezfurlong.org/wezterm/installation.html
```

---

## 15:45–16:00 — Podsumowanie dnia
⏱️ 15 min

🎬 **CO MÓWIĘ:**

„Dobra — zamykamy dzień pierwszy. Zrobiliśmy dziś naprawdę dużo jak na jeden dzień. Podsumujmy co mamy."

💬 WKLEJ NA CHAT:
```
✅ Co mamy po Dniu 1:
□ Działające środowisko na zdalnym hoście
□ Codex CLI zalogowany + sandbox skonfigurowany
□ Claude Code CLI zalogowany (porównanie)
□ WezTerm skonfigurowany
□ Pierwsze zadanie w Codex wykonane
□ README.md projektu z user stories
□ AGENTS.md – kontekst dla agentów
□ Wspólny mental model: AI-first z kontrolą

Jutro (Dzień 2): Od pomysłu do projektu
Temat: AI jako PM i UX Designer
→ Architektura, diagramy, wireframes, prototyp UI
```

🎬 „Co jutro robimy?

Rano zacznijemy od architektury — jak AI pomaga w planowaniu struktury projektu, wyborze bibliotek, tworzeniu diagramów. Potem wejdziemy w UX i design — Lovable, v0.dev, prototypy. I zaczniemy przekładać design na kod.

Nie ma obowiązkowej pracy domowej. Ale jeśli chcecie — możecie:
- Dokończyć README lub AGENTS.md jeśli nie było czasu,
- Zainstalować Codex Desktop App lokalnie na swoim laptopie,
- Zapisać jedno pytanie które chcecie jutro zadać."

💬 WKLEJ NA CHAT:
```
Opcjonalne do jutra (bez presji):
□ Dokończyć README.md / AGENTS.md
□ Zainstalować Codex Desktop App lokalnie:
  https://openai.com/codex
□ Zapisać 1 pytanie na jutro

Pytania dziś wieczorem? Wrzucajcie na chat — odpiszę.
```

🎬 „Jedno pytanie na koniec do Was: co z dzisiaj najbardziej Wam się przyda jutro w pracy? Wrzućcie na czat — jedno zdanie.

*[Poczekaj na odpowiedzi, skomentuj krótko]*

Świetnie. Do jutra!"

💬 WKLEJ NA CHAT:
```
Dzień 1 – koniec 🎯
Materiały i skrypt: w repo szkoleniowym
Jutro: 09:00 — Dzień 2 — Od pomysłu do projektu
Do jutra! 👋
```

---

## APPENDIX A — Linki do szybkiego skopiowania (wszystko w jednym miejscu)

| Co | Link |
|---|---|
| Codex CLI docs | https://developers.openai.com/codex/cli/ |
| Codex Windows sandbox | https://developers.openai.com/codex/windows |
| Codex Multi-agent | https://developers.openai.com/codex/multi-agent/ |
| Claude Code docs | https://code.claude.com/docs/en/setup |
| Claude Code sandboxing | https://www.anthropic.com/engineering/claude-code-sandboxing |
| Harness engineering (OpenAI) | https://openai.com/index/harness-engineering/ |
| Context engineering (Anthropic) | https://www.anthropic.com/engineering/effective-context-engineering-for-ai-agents |
| AGENTS.md standard | https://agents.md/ |
| CLAUDE.md blog | https://claude.com/blog/using-claude-md-files |
| SWE-Bench leaderboard | https://www.swebench.com/ |
| Terminal Bench | https://www.tbench.ai/leaderboard/terminal-bench/2.0 |
| Stanford AI dev study (YT) | https://www.youtube.com/watch?v=JvosMkuNxF8 |
| METR badanie 07.2025 | https://metr.org/blog/2025-07-10-early-2025-ai-experienced-os-dev-study/ |
| OpenClaw case study | https://steipete.me/posts/2025/shipping-at-inference-speed |
| Vibe Coding (Karpathy) | https://x.com/karpathy/status/1886192184808149383 |
| WezTerm instalacja | https://wezfurlong.org/wezterm/installation.html |
| Handy STT (voice) | https://handy.computer/ |
| Perplexity | https://perplexity.ai |
| Spring AI docs | https://docs.spring.io/spring-ai/reference/index.html |
| OpenRouter | https://openrouter.ai/ |
| Prompting techniques | https://www.promptingguide.ai/techniques |

---

## APPENDIX B — Adaptacje dla uczestników

### Dla Łukasza (banking Java/Spring Boot, Angular, Jenkins/Argo CD):
- Przy przykładach backend → Angular zamiast React: „W Angular to samo osiągniesz przez HttpClient i service injection zamiast fetch/hooks"
- CI/CD mapping: GitHub Actions → Jenkins; Argo CD analogia do co to robi
- Security i regulacje: podkreślaj sandbox i approval flows przy każdym agentic demo
- Dla on-prem AI: Codex + OpenRouter z lokalnym endpointem Ollama jako alternatywa chmury

### Dla Michała (full-stack/backend/DevOps, Java, IntelliJ):
- Pokaż jak Codex działa z IntelliJ przez terminal bez opuszczania IDE workflowa
- Security audit workflow (Dzień 4) będzie szczególnie interesujący
- Risk assessment → sandbox modes + approval levels

### Dla Respondenta C (SQL, Bash, Notepad++):
- Więcej czasu na basic terminal orientation jeśli potrzeba
- Bash scripts jako pierwsze zadania dla Codexa — bliski kontekst
- WezTerm może być dla tej osoby dużą wygraną (lepsza ergonomia niż Notepad++ + cmd)

---

## APPENDIX C — Troubleshooting / najczęstsze problemy

| Problem | Rozwiązanie |
|---|---|
| `codex auth` nie otwiera przeglądarki | Sprawdź czy masz X11/display, użyj opcji 2 (kod) |
| Sandbox setup trwa za długo | Normalne — do 5 min przy pierwszym uruchomieniu, poczekaj |
| `permission denied` w sandbox | Sprawdź tryb: workspace-write jest domyślny, danger-full-access jeśli potrzebujesz więcej |
| Claude Code `/voice` nie działa | Handy STT jako alternatywa, lub po prostu pisz |
| WezTerm brak Shift+Enter | Sprawdź terminal.conf — może być nadpisany |
| Codex ignoruje AGENTS.md | AGENTS.md musi być w root katalogu projektu gdzie uruchamiasz `codex` |
| Wysoka latencja na hoscie zdalnym | Normalne przez VPN/RDP. Claude Code może być szybszy w takim przypadku (mniej round-trips) |
| Windows Terminal Shift+Enter nie działa | Znane ograniczenie — użyj \\ + Enter albo przełącz na WezTerm |

---

## APPENDIX D — Różnica sandbox: Codex vs Claude Code (krótkie wyjaśnienie)

Do pokazania przy pytaniach o bezpieczeństwo:

```
Codex CLI na Linux:
→ Landlock LSM (kernel 5.13+)
→ Sandbox ON domyślnie
→ Write: tylko workspace + /tmp
→ Sieć: zablokowana (WFP/stubs)
→ Nie potrzebuje WSL

Codex CLI na Windows:
→ AppContainer (restricted access token)
→ Windows Filtering Platform (WFP) blokuje sieć
→ Bez Hyper-V, bez WSL

Claude Code CLI na Linux:
→ bubblewrap (bwrap) – nowe namespace'y
→ Sandbox OFF domyślnie (opt-in)
→ Pełna izolacja gdy włączone

Claude Code Desktop (Windows):
→ ZAWSZE uruchamia Hyper-V VM
→ Wymaga Windows Pro/Enterprise/Education
→ Home edition: nie działa

Praktycznie dla Was:
Codex CLI sandbox = bezpieczniejszy start domyślnie.
Claude Code CLI sandbox = mocniejsza izolacja gdy włączone,
ale trzeba pamiętać żeby włączyć.
```
