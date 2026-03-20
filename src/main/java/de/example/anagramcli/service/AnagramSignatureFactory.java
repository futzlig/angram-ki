package de.example.anagramcli.service;

import de.example.anagramcli.util.TextNormalizer;

import java.util.Arrays;

public class AnagramSignatureFactory {

    public String createSignature(String input) {
        String sanitized = TextNormalizer.sanitize(input);
        int[] codePoints = sanitized.codePoints().toArray();
        Arrays.sort(codePoints);

        StringBuilder builder = new StringBuilder(codePoints.length);
        for (int codePoint : codePoints) {
            builder.appendCodePoint(codePoint);
        }
        return builder.toString();
    }
}
