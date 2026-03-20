package de.example.anagramcli.service;

import de.example.anagramcli.domain.AnagramCheckResult;
import de.example.anagramcli.util.TextNormalizer;

public class AnagramService {

    private final AnagramSignatureFactory signatureFactory;

    public AnagramService(AnagramSignatureFactory signatureFactory) {
        this.signatureFactory = signatureFactory;
    }

    public AnagramCheckResult check(String firstText, String secondText) {
        validate(firstText);
        validate(secondText);

        boolean areAnagrams = signatureFactory.createSignature(firstText)
                .equals(signatureFactory.createSignature(secondText));

        return new AnagramCheckResult(firstText, secondText, areAnagrams);
    }

    public String createSignature(String input) {
        validate(input);
        return signatureFactory.createSignature(input);
    }

    public String sanitize(String input) {
        validate(input);
        return TextNormalizer.sanitize(input);
    }

    private void validate(String input) {
        if (!TextNormalizer.hasRelevantCharacters(input)) {
            throw new IllegalArgumentException("Der Text muss mindestens ein Buchstaben- oder Ziffernzeichen enthalten.");
        }
    }
}
