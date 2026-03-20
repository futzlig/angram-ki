package de.example.anagramcli.app;

import de.example.anagramcli.domain.AnagramCheckResult;
import de.example.anagramcli.service.AnagramService;
import de.example.anagramcli.store.AnagramHistoryStore;

import java.util.List;

public class AnagramCli {

    private final ConsoleIO consoleIO;
    private final AnagramService anagramService;
    private final AnagramHistoryStore historyStore;

    public AnagramCli(ConsoleIO consoleIO, AnagramService anagramService, AnagramHistoryStore historyStore) {
        this.consoleIO = consoleIO;
        this.anagramService = anagramService;
        this.historyStore = historyStore;
    }

    public void run() {
        printWelcome();

        boolean running = true;
        while (running) {
            printMenu();
            String choice = consoleIO.readLine("Auswahl: ").trim();

            switch (choice) {
                case "1" -> handleFeatureOne();
                case "2" -> handleFeatureTwo();
                case "3" -> handleStats();
                case "0" -> {
                    running = false;
                    consoleIO.println("Bis bald.");
                }
                default -> consoleIO.println("Ungültige Auswahl. Bitte 0, 1, 2 oder 3 eingeben.");
            }

            consoleIO.println("");
        }
    }

    private void printWelcome() {
        consoleIO.println("Anagram CLI");
        consoleIO.println("-----------");
        consoleIO.println("1) Prüft, ob zwei Texte Anagramme sind.");
        consoleIO.println("2) Findet zu einem Text alle zuvor über Funktion 1 eingegebenen Anagramme.");
        consoleIO.println("Berücksichtigt werden Buchstaben und Ziffern; Leerzeichen, Satzzeichen und Groß-/Kleinschreibung werden ignoriert.");
        consoleIO.println("");
    }

    private void printMenu() {
        consoleIO.println("Menü");
        consoleIO.println("1 - Anagramm prüfen");
        consoleIO.println("2 - Bereits eingegebene Anagramme suchen");
        consoleIO.println("3 - Statistik anzeigen");
        consoleIO.println("0 - Beenden");
    }

    private void handleFeatureOne() {
        String firstText = consoleIO.readLine("Erster Text: ");
        String secondText = consoleIO.readLine("Zweiter Text: ");

        try {
            AnagramCheckResult result = anagramService.check(firstText, secondText);
            historyStore.recordTexts(firstText, secondText);

            if (result.anagrams()) {
                consoleIO.println("Ergebnis: Ja, die Texte sind Anagramme.");
            } else {
                consoleIO.println("Ergebnis: Nein, die Texte sind keine Anagramme.");
            }
        } catch (IllegalArgumentException exception) {
            consoleIO.println("Fehler: " + exception.getMessage());
        }
    }

    private void handleFeatureTwo() {
        String input = consoleIO.readLine("Text für die Suche: ");

        try {
            List<String> anagrams = historyStore.findAnagramsFor(input);
            consoleIO.println("Gefundene Anagramme: " + anagrams);
        } catch (IllegalArgumentException exception) {
            consoleIO.println("Fehler: " + exception.getMessage());
        }
    }

    private void handleStats() {
        consoleIO.println("Gespeicherte eindeutige Texte aus Funktion 1: " + historyStore.size());
    }
}
