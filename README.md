# Anagram CLI

Ein vollständiges, lauffähiges Java-Projekt für die gestellte Aufgabe: 

1. **Feature 1:** Prüft, ob zwei Texte Anagramme sind.
2. **Feature 2:** Liefert für einen eingegebenen Text alle **zuvor über Feature 1** erfassten Texte, die Anagramme davon sind.

Die Lösung ist als einfache **Kommandozeilen-Anwendung** umgesetzt und kann direkt auf GitHub hochgeladen werden.

## Fachliche Entscheidungen

- Es werden **Buchstaben und Ziffern** berücksichtigt.
- **Leerzeichen, Satzzeichen und Groß-/Kleinschreibung** werden ignoriert.
- Die Historie gilt **nur während einer Programmausführung**.
- Für Feature 2 werden Ergebnisse **eindeutig** und **in Einfügereihenfolge** zurückgegeben.

## Technischer Überblick

- **Java 17**
- **Maven**
- **JUnit 5** für Tests
- Keine produktiven Fremdbibliotheken nötig

## Projektstruktur

```text
src
├── main
│   └── java/de/example/anagramcli
│       ├── app
│       ├── domain
│       ├── service
│       ├── store
│       └── util
└── test
    └── java/de/example/anagramcli
```

## Voraussetzungen

- Java 17 oder höher
- Optional: Maven 3.8+

Das Projekt enthält zusätzlich `build.sh` und `run.sh`, damit es auch ohne lokal installiertes Maven direkt gebaut und gestartet werden kann.

## Bauen und starten

### Tests ausführen

```bash
mvn test
```

### Anwendung starten

Mit Maven:

```bash
mvn compile exec:java
```

Oder ohne Maven:

```bash
./run.sh
```

### Jar-Datei bauen

Mit Maven:

```bash
mvn clean package
```

Oder ohne Maven:

```bash
./build.sh
```

Danach liegt die Jar-Datei unter:

```bash
target/anagram-cli-1.0.0.jar
```

Start über Java:

```bash
java -jar target/anagram-cli-1.0.0.jar
```

## Beispielablauf

```text
Anagram CLI
-----------
1) Prüft, ob zwei Texte Anagramme sind.
2) Findet zu einem Text alle zuvor über Funktion 1 eingegebenen Anagramme.

Menü
1 - Anagramm prüfen
2 - Bereits eingegebene Anagramme suchen
3 - Statistik anzeigen
0 - Beenden
Auswahl: 1
Erster Text: post
Zweiter Text: pots
Ergebnis: Ja, die Texte sind Anagramme.

Auswahl: 1
Erster Text: post
Zweiter Text: stone
Ergebnis: Nein, die Texte sind keine Anagramme.

Auswahl: 1
Erster Text: post
Zweiter Text: stop
Ergebnis: Ja, die Texte sind Anagramme.

Auswahl: 2
Text für die Suche: post
Gefundene Anagramme: [pots, stop]
```

## Bezug zur Aufgabenstellung

Die Umsetzung erfüllt die zwei im PDF beschriebenen Kernfunktionen und speichert die Eingaben aus **Feature 1** nur im Speicher der laufenden Session. Das entspricht direkt den Vorgaben aus dem Anforderungsdokument. Außerdem orientiert sich die Lösung an der dort erlaubten CLI-Interaktion. 

Zusätzlich habe ich mich strukturell an einem kleinen öffentlichen Java-Anagramm-Projekt orientiert, das ebenfalls Maven und eine Kommandozeilen-Ausführung nutzt.

## Mögliche Erweiterungen

- Separate Command-Handler statt Menü-Loop
- Persistenz über mehrere Läufe hinweg
- Export/Import der Session-Historie
- Dockerfile und GitHub Actions Workflow
- CLI-Argumentmodus zusätzlich zum interaktiven Modus

## Lizenz

Für deinen Upload nach GitHub kannst du nach Bedarf noch eine eigene `LICENSE` ergänzen.
