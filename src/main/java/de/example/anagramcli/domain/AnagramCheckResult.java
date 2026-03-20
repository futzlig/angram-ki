package de.example.anagramcli.domain;

public record AnagramCheckResult(String firstText, String secondText, boolean anagrams) {
}
