package com.pronunciation_match.pronunciationmatch;

import java.util.ArrayList;
import java.util.List;

public class PhonemeBank {
    private static final PhonemeBank INSTANCE = new PhonemeBank();
    private List<Phoneme> mPhonemes = new ArrayList<>();

    public static PhonemeBank getInstance() {
        return INSTANCE;
    }

    private PhonemeBank() {
        init();
    }

    private void init() {
        mPhonemes.add(new Phoneme("a", Tone.FIRST, R.raw.a1));
        mPhonemes.add(new Phoneme("a", Tone.SECOND, R.raw.a2));
        mPhonemes.add(new Phoneme("a", Tone.THIRD, R.raw.a3));
        mPhonemes.add(new Phoneme("a", Tone.FOURTH, R.raw.a4));
        mPhonemes.add(new Phoneme("a", Tone.NEUTRAL, R.raw.a5));

        mPhonemes.add(new Phoneme("o", Tone.FIRST, R.raw.o1));
        mPhonemes.add(new Phoneme("o", Tone.SECOND, R.raw.o2));
        mPhonemes.add(new Phoneme("o", Tone.THIRD, R.raw.o3));
        mPhonemes.add(new Phoneme("o", Tone.FOURTH, R.raw.o4));
        mPhonemes.add(new Phoneme("o", Tone.NEUTRAL, R.raw.o5));
    }

    public List<Phoneme> getPhonemes() {
        return mPhonemes;
    }
}
