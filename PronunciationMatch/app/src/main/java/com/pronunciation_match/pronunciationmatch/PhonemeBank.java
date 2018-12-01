package com.pronunciation_match.pronunciationmatch;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhonemeBank {
    private static final String TAG = PhonemeBank.class.getSimpleName();
    private static final String DIR = "audio";
    private static PhonemeBank mPhonemeBank;
    private List<Phoneme> mPhonemes = new ArrayList<>();

    public static PhonemeBank getInstance(Context context) {
        if (mPhonemeBank == null) {
            try {
                mPhonemeBank = new PhonemeBank(context);
            } catch (IOException e) {
                Log.e(TAG,"Failed to load assets.", e);
            }
        }
        return mPhonemeBank;
    }

    private PhonemeBank(Context context) throws IOException {
        init(context);
    }

    private void init(Context context) throws IOException {

        AssetManager assetManager = context.getAssets();
        String[] audioFiles = assetManager.list(DIR);
        for (int i = 0; i < audioFiles.length; i++) {
            String filename = audioFiles[i];
            String name = filename.substring(0, filename.indexOf('.'));
            int numIndex = name.length() - 1;
            String phonemeName = name.substring(0, numIndex);
            Tone tone = Tone.getTone(name.charAt(numIndex));
            mPhonemes.add(new Phoneme(phonemeName, tone,DIR + "/" + filename));
        }
    }

    public List<Phoneme> getPhonemes() {
        return mPhonemes;
    }

    public Phoneme getPhoneme(String text, Tone tone) {
        for (Phoneme p: mPhonemes) {
            if (p.getText().equals(text) && p.getTone().equals(tone)) {
                return p;
            }
        }
        return null;
    }
}
