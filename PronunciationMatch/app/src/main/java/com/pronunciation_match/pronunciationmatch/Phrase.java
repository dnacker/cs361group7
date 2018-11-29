package com.pronunciation_match.pronunciationmatch;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class Phrase {
    private static final String TAG = Phrase.class.getSimpleName();
    private PhonemeBank mPhonemeBank = PhonemeBank.getInstance();
    private List<Phoneme> mPhonemes;

    /**
     * Constructs a phrase from text.
     *
     * The text is parsed according to the following schema:
     *
     * phonemetone, phonemetone
     *
     * For example the word ní hǎo would be written:
     *
     * ni2, hao3
     *
     * @param text
     */
    public Phrase(String text) {
        mPhonemes = new LinkedList<>();
        String[] split = text.split(",");
        for (int i = 0; i < split.length; i++) {
            String word = split[i].trim().toLowerCase();
            int numIndex = word.length() - 1;
            char toneNumber = word.charAt(numIndex);
            word = word.substring(0, numIndex);
            Tone tone;
            switch (toneNumber) {
                case '1':
                    tone = Tone.FIRST;
                    break;
                case '2':
                    tone = Tone.SECOND;
                    break;
                case '3':
                    tone = Tone.THIRD;
                    break;
                case '4':
                    tone = Tone.FOURTH;
                    break;
                default:
                    tone = Tone.NEUTRAL;
                    break;
            }
            Phoneme toAdd = mPhonemeBank.getPhoneme(word, tone);
            if (toAdd == null) {
                throw new IllegalArgumentException(String.format("Unable to parse %s", text));
            }
            mPhonemes.add(toAdd);
        }
    }

    public List<Phoneme> getPhonemes() {
        return mPhonemes;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Phoneme p: mPhonemes) {
            stringBuilder.append(p);
        }
        return stringBuilder.toString();
    }
}
