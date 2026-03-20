package de.example.anagramcli.util;

import java.text.Normalizer;
import java.util.Locale;

public final class TextNormalizer {

    private TextNormalizer() {
    }

    public static String sanitize(String input) {
        if (input == null) {
            return "";
        }

        String normalized = Normalizer.normalize(input, Normalizer.Form.NFKC).toLowerCase(Locale.ROOT);
        StringBuilder builder = new StringBuilder();

        normalized.codePoints()
                .filter(Character::isLetterOrDigit)
                .forEach(builder::appendCodePoint);

        return builder.toString();
    }

    public static boolean hasRelevantCharacters(String input) {
        return !sanitize(input).isEmpty();
    }
}
