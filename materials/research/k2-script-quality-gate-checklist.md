# K2 — Script Quality Gate Checklist (Day 1–Day 5)

Data: 2026-03-16  
Zakres: `materials/scripts/day-1-script.md` … `day-5-script.md`  
Źródło checklisty: `materials/references/script-quality-gate.md`

## Wynik końcowy
- **PASS** (0 kryteriów z wynikiem „NIE”, 2 obserwacje „do monitorowania” bez blokady)
- Zgodnie z regułą decyzyjną quality gate: można oznaczyć K2 jako DONE.

---

## 1) Spójność i ciągłość (day-by-day)
**Wynik: TAK**
- Każdy dzień ma jawne przejście/handoff do kolejnego dnia.
- Nie ma istotnych powtórzeń bez nowej wartości; treści przechodzą od setupu i pracy z promptem do implementacji, quality/security i wdrożenia.

## 2) Pokrycie wymagań
**Wynik: TAK**
- W blokach obecne: co mówię, co pokazuję, co na chat, prompt, ćwiczenie, feedback (Kolb), czas.
- We wszystkich day scripts są sekcje „Czego się nauczymy” i „Dlaczego warto”.
- Narracja po polsku, z objaśnieniami angielskich terminów tam, gdzie potrzeba.
- Wymóg „1 dzień = 1 plik, gotowy scenariusz” spełniony (inline prompty/chat-pasty + sekcja „Szybkie copy-paste dla trenera”).

## 3) Czas i wykonalność
**Wynik: TAK**
- Harmonogramy są w oknie 9:00–16:00 i zawierają wymagane przerwy (11:00–11:15, 13:00–13:30, opcjonalna 14:30–14:40).
- Bloki mają realistyczne estymacje i flow dostosowany do VM/classroom constraints.

## 4) Zoom/chat usability
**Wynik: TAK**
- Prompty/ćwiczenia mają osobne pliki do szybkiego udostępnienia.
- Dzienne skrypty zawierają ścieżki/deep linki do odpowiednich plików.
- Chat-pasty są krótkie i gotowe do szybkiego wklejenia.

## 5) Narzędzia i zgodność z założeniami
**Wynik: TAK**
- Codex-first utrzymany jako główny tor.
- Claude/IntelliJ są pokazane krótko, jako porównanie/most.
- Brak tool sprawl jako core curriculum.
- Każdy dzień zawiera sekcję „Zgodność z programem JSystems (Day N)” z mapowaniem modułów.

## 6) Cloud vs on-prem realizm
**Wynik: TAK**
- Widoczny dual-track: cloud-max + adaptacja on-prem.
- CI/CD i enterprise controls oparte o research + źródła (`materials/research/cicd-agent-workflows-cloud-vs-onprem.md`).
- W materiałach jest przenoszenie workflow na lokalne modele/custom endpoints (w tym Ollama/OpenRouter w promptach/research).

## 7) Styl i ton
**Wynik: TAK**
- Styl angażujący, motywujący, z lekkim nerdowym humorem.
- Nadal profesjonalny i client-facing (bank context).
- Narracja pozytywna, bez piętnowania braków uczestników.

---

## Obserwacje „do monitorowania” (nie blokują PASS)
1. W Day 4/Day 5 pozostają oznaczenia `to verify` przy części tez heurystycznych (zgodnie z K1); przed finalnym wysłaniem materiałów do klienta warto domknąć źródła lub utrzymać marker.
2. Jeśli pojawią się nowe narzędzia/wersje, aktualizować tylko sekcje porównawcze (Claude/IntelliJ), bez rozszerzania scope szkolenia.
