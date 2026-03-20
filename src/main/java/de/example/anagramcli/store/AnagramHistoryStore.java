package de.example.anagramcli.store;

import de.example.anagramcli.service.AnagramService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnagramHistoryStore {

    private final AnagramService anagramService;
    private final Map<String, LinkedHashSet<String>> textsBySignature = new LinkedHashMap<>();

    public AnagramHistoryStore(AnagramService anagramService) {
        this.anagramService = anagramService;
    }

    public void recordTexts(String firstText, String secondText) {
        recordText(firstText);
        recordText(secondText);
    }

    public List<String> findAnagramsFor(String input) {
        String signature = anagramService.createSignature(input);
        String sanitizedInput = anagramService.sanitize(input);

        Set<String> candidates = textsBySignature.getOrDefault(signature, new LinkedHashSet<>());
        List<String> result = new ArrayList<>();
        for (String candidate : candidates) {
            if (!anagramService.sanitize(candidate).equals(sanitizedInput)) {
                result.add(candidate);
            }
        }
        return result;
    }

    public int size() {
        return textsBySignature.values().stream().mapToInt(Set::size).sum();
    }

    private void recordText(String input) {
        String signature = anagramService.createSignature(input);
        textsBySignature.computeIfAbsent(signature, key -> new LinkedHashSet<>()).add(input);
    }
}
