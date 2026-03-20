package de.example.anagramcli.app;

import de.example.anagramcli.service.AnagramService;
import de.example.anagramcli.service.AnagramSignatureFactory;
import de.example.anagramcli.store.AnagramHistoryStore;

import java.util.Scanner;

public class AnagramCliApplication {

    public static void main(String[] args) {
        AnagramSignatureFactory signatureFactory = new AnagramSignatureFactory();
        AnagramService anagramService = new AnagramService(signatureFactory);
        AnagramHistoryStore historyStore = new AnagramHistoryStore(anagramService);
        ConsoleIO consoleIO = new ConsoleIO(new Scanner(System.in));

        new AnagramCli(consoleIO, anagramService, historyStore).run();
    }
}
