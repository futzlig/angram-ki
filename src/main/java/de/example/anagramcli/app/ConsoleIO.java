package de.example.anagramcli.app;

import java.util.Scanner;

public class ConsoleIO {

    private final Scanner scanner;

    public ConsoleIO(Scanner scanner) {
        this.scanner = scanner;
    }

    public void println(String text) {
        System.out.println(text);
    }

    public void print(String text) {
        System.out.print(text);
    }

    public String readLine(String prompt) {
        print(prompt);
        return scanner.nextLine();
    }
}
