package com.pronunciation_match.pronunciationmatch;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class StringUtilsTest {
    private static String[] prefixes = new String[] {
            "b", "p", "m", "f", "d", "t", "n", "l", "g", "k", "h", "z", "c", "s", "zh", "ch", "sh", "r", "j", "q", "x"
    };

    private static String[] suffixes = new String[] {
            "a", "o", "e", "i", "ai", "ei", "ao", "ou", "an", "en", "ang", "eng", "ong", "ia", "iao", "ie", "iu", "ian", "in", "iang", "ing", "iong", "u", "ua", "uo", "uai", "ui", "uan", "un", "uang"
    };

    private static Map<String, String[]> suffixWithToneMap = new HashMap<>();
    static {
        suffixWithToneMap.put("a", new String[] { "ā", "á", "ǎ", "à", "a" });
        suffixWithToneMap.put("e", new String[] { "ē", "é", "ě", "è", "e" });
        suffixWithToneMap.put("o", new String[] { "ō", "ó", "ǒ", "ò", "o" });
        suffixWithToneMap.put("i", new String[] { "ī", "í", "ǐ", "ì", "i" });
        suffixWithToneMap.put("u", new String[] { "ū", "ú", "ǔ", "ù", "u" });
        suffixWithToneMap.put("er", new String[] { "ēr", "ér", "ěr", "èr", "er" });
        suffixWithToneMap.put("ai", new String[] { "āi", "ái", "ǎi", "ài", "ai" });
        suffixWithToneMap.put("ei", new String[] { "ēi", "éi", "ěi", "èi", "ei" });
        suffixWithToneMap.put("ao", new String[] { "āo", "áo", "ǎo", "ào", "ao" });
        suffixWithToneMap.put("ou", new String[] { "ōu", "óu", "ǒu", "òu", "ou" });
        suffixWithToneMap.put("an", new String[] { "ān", "án", "ǎn", "àn", "an" });
        suffixWithToneMap.put("en", new String[] { "ēn", "én", "ěn", "èn", "en" });
        suffixWithToneMap.put("eng", new String[] { "ēng", "éng", "ěng", "èng", "eng" });
        suffixWithToneMap.put("ang", new String[] { "āng", "áng", "ǎng", "àng", "ang" });
        suffixWithToneMap.put("ong", new String[] { "ōng", "óng", "ǒng", "òng", "ong" });
        suffixWithToneMap.put("ia", new String[] { "iā", "iá", "iǎ", "ià", "ia" });
        suffixWithToneMap.put("iao", new String[] { "iāo", "iáo", "iǎo", "iào", "iao" });
        suffixWithToneMap.put("ie", new String[] { "iē", "ié", "iě", "iè", "ie" });
        suffixWithToneMap.put("iu", new String[] { "iū", "iú", "iǔ", "iù", "iu" });
        suffixWithToneMap.put("ian", new String[] { "iān", "ián", "iǎn", "iàn", "ian" });
        suffixWithToneMap.put("in", new String[] { "īn", "ín", "ǐn", "ìn", "in" });
        suffixWithToneMap.put("iang", new String[] { "iāng", "iáng", "iǎng", "iàng", "iang" });
        suffixWithToneMap.put("ing", new String[] { "īng", "íng", "ǐng", "ìng", "ing" });
        suffixWithToneMap.put("iong", new String[] { "iōng", "ióng", "iǒng", "iòng", "iong" });
        suffixWithToneMap.put("ua", new String[] { "uā", "uá", "uǎ", "uà", "ua" });
        suffixWithToneMap.put("uo", new String[] { "uō", "uó", "uǒ", "uò", "uo" });
        suffixWithToneMap.put("uai", new String[] { "uāi", "uái", "uǎi", "uài", "uai" });
        suffixWithToneMap.put("ui", new String[] { "uī", "uí", "uǐ", "uì", "ui" });
        suffixWithToneMap.put("uan", new String[] { "uān", "uán", "uǎn", "uàn", "uan" });
        suffixWithToneMap.put("un", new String[] { "ūn", "ún", "ǔn", "ùn", "un" });
        suffixWithToneMap.put("uang", new String[] { "uāng", "uáng", "uǎng", "uàng", "uang" });
        suffixWithToneMap.put("ueng", new String[] { "uēng", "uéng", "uěng", "uèng", "ueng" });
    }

    @Test
    public void testParseOnlySuffixes() {
        for (String current: suffixes) {
            if (current.startsWith("i")) {
                if (current.length() == 1) {
                    current = "yi";
                } else {
                    current = "y" + current.substring(1);
                }
            } else if (current.startsWith("u")) {
                if (current.length() == 1) {
                    current = "wu";
                } else {
                    current = "w" + current.substring(1);
                }
            }
            String[] parsed = StringUtils.parsePrefixAndSuffix(current);
            assertEquals("Testing prefix.", "", parsed[0]);
            assertEquals("Testing suffix.", current, parsed[1]);
        }
        String[] addons = new String[] { "er", "weng" };
        for (String current: addons) {
            String[] parsed = StringUtils.parsePrefixAndSuffix(current);
            assertEquals("Testing prefix.", "", parsed[0]);
            assertEquals("Testing suffix.", current, parsed[1]);
        }
    }

    @Test
    public void testParsePrefixAndSuffix() {
        for (String prefix: prefixes) {
            for (String suffix: suffixes) {
                String together = prefix + suffix;
                String[] parsed = StringUtils.parsePrefixAndSuffix(together);
                assertEquals("Testing prefix.", prefix, parsed[0]);
                assertEquals("Testing prefix.", suffix, parsed[1]);
            }
        }
    }

    @Test
    public void testGetTone() {
        for (String suffix: suffixWithToneMap.keySet()) {
            Tone[] tones = Tone.values();
            for (int j = 0; j < tones.length; j++) {
                Tone tone = tones[j];
                String actual = StringUtils.getSuffixWithTone(suffix, tone);
                String expected = suffixWithToneMap.get(suffix)[j];
                assertEquals(String.format("Testing suffix %s, tone %s.", suffix, tone.toString()), expected, actual);
            }
        }
    }
}