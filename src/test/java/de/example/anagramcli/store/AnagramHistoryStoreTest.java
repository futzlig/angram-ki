package de.example.anagramcli.store;

import de.example.anagramcli.service.AnagramService;
import de.example.anagramcli.service.AnagramSignatureFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnagramHistoryStoreTest {

    private AnagramHistoryStore historyStore;

    @BeforeEach
    void setUp() {
        AnagramService anagramService = new AnagramService(new AnagramSignatureFactory());
        historyStore = new AnagramHistoryStore(anagramService);
    }

    @Test
    void shouldMirrorChallengeExample() {
        historyStore.recordTexts("post", "pots");
        historyStore.recordTexts("post", "stone");
        historyStore.recordTexts("post", "stop");

        assertEquals(List.of("pots", "stop"), historyStore.findAnagramsFor("post"));
        assertEquals(List.of("post", "stop"), historyStore.findAnagramsFor("pots"));
        assertEquals(List.of(), historyStore.findAnagramsFor("stone"));
    }

    @Test
    void shouldDeduplicateTextsAndPreserveInsertionOrder() {
        historyStore.recordTexts("Dormitory", "Dirty room");
        historyStore.recordTexts("Dormitory", "Dirty room");
        historyStore.recordTexts("Dormitory", "Dormitory!!");

        assertEquals(List.of("Dirty room"), historyStore.findAnagramsFor("Dormitory"));
    }
}
