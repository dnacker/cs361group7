package com.pronunciation_match.pronunciationmatch;

public class StringUtils {
    private static String TAG = StringUtils.class.getSimpleName();

    public static char[] VOWELS = new char[] {
            'a', 'e', 'i', 'o', 'u', 'y', 'w'
    };

    /**
     *
     * @param text
     * @return
     */
    public static String[] parsePrefixAndSuffix(String text) {
        text = text.toLowerCase();

        for (char c: VOWELS) {
            if (text.charAt(0) == c) {
                return new String[] { "", text };
            }
        }

        String[] prefixAndSuffix = new String[2];
        int vowelStartsAt = 1;
        if (text.startsWith("zh") || text.startsWith("ch") || text.startsWith("sh")) {
            vowelStartsAt = 2;
        }

        prefixAndSuffix[0] = text.substring(0, vowelStartsAt);
        prefixAndSuffix[1] = text.substring(vowelStartsAt);

        return prefixAndSuffix;
    }

    /**
     * Converts a suffix
     * @param suffix
     * @param tone
     * @return
     */
    public static String getSuffixWithTone(String suffix, Tone tone) {
        suffix = suffix.toLowerCase();
        int indexToChange = 0;
        // If there is only one letter in the suffix, then it is changed.
        if (suffix.length() != 1) {
            /*
            Ordering is important here.
            Precedence of which vowel gets changed is: a -> e -> o -> u -> i
             */
            if (suffix.contains("a")) {
                indexToChange = suffix.indexOf('a');
            } else if (suffix.contains("e")) {
                indexToChange = suffix.indexOf('e');
            } else if (suffix.contains("o")) {
                indexToChange = suffix.indexOf('o');
            } else if (suffix.contains("ui")) {
                indexToChange = suffix.indexOf('i');
            }else if (suffix.contains("u")) {
                indexToChange = suffix.indexOf('u');
            } else if (suffix.contains("i")) {
                indexToChange = suffix.indexOf('i');
            }
        }

        char changed = getCharFromTone(suffix.charAt(indexToChange), tone);
        return suffix.substring(0, indexToChange) + changed + suffix.substring(indexToChange + 1);
    }

    private static char getCharFromTone(char c, Tone tone) {
        switch (c) {
            case 'a':
                switch (tone) {
                    case FIRST:
                        return 'ā';
                    case SECOND:
                        return 'á';
                    case THIRD:
                        return 'ǎ';
                    case FOURTH:
                        return 'à';
                }
            case 'e':
                switch (tone) {
                    case FIRST:
                        return 'ē';
                    case SECOND:
                        return 'é';
                    case THIRD:
                        return 'ě';
                    case FOURTH:
                        return 'è';
                }
            case 'i':
                switch (tone) {
                    case FIRST:
                        return 'ī';
                    case SECOND:
                        return 'í';
                    case THIRD:
                        return 'ǐ';
                    case FOURTH:
                        return 'ì';
                }
            case 'o':
                switch (tone) {
                    case FIRST:
                        return 'ō';
                    case SECOND:
                        return 'ó';
                    case THIRD:
                        return 'ǒ';
                    case FOURTH:
                        return 'ò';
                }
            case 'u':
                switch (tone) {
                    case FIRST:
                        return 'ū';
                    case SECOND:
                        return 'ú';
                    case THIRD:
                        return 'ǔ';
                    case FOURTH:
                        return 'ù';
                }
            case 'ü':
                switch (tone) {
                    case FIRST:
                        return 'ǖ';
                    case SECOND:
                        return 'ǘ';
                    case THIRD:
                        return 'ǚ';
                    case FOURTH:
                        return 'ǜ';
                }
            default:
                return c;
        }
    }
}
