package de.example.anagramcli.service;

import de.example.anagramcli.domain.AnagramCheckResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnagramServiceTest {

    private AnagramService anagramService;

    @BeforeEach
    void setUp() {
        anagramService = new AnagramService(new AnagramSignatureFactory());
    }

    @Test
    void shouldRecognizeClassicAnagramSentence() {
        AnagramCheckResult result = anagramService.check("A gentleman", "Elegant man");

        assertTrue(result.anagrams());
    }

    @Test
    void shouldIgnoreCaseAndPunctuation() {
        AnagramCheckResult result = anagramService.check("Eleven plus two!", "Twelve plus one");

        assertTrue(result.anagrams());
    }

    @Test
    void shouldRejectNonAnagrams() {
        AnagramCheckResult result = anagramService.check("Listen", "Silence");

        assertFalse(result.anagrams());
    }

    @Test
    void shouldRejectInputWithoutLettersOrDigits() {
        assertThrows(IllegalArgumentException.class, () -> anagramService.check("   ", "..."));
    }
}
