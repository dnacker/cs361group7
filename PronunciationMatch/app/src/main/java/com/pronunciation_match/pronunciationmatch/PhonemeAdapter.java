package com.pronunciation_match.pronunciationmatch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

public class PhonemeAdapter extends ArrayAdapter<Phoneme> {
    public PhonemeAdapter(@NonNull Context context, int resource, @NonNull List<Phoneme> phonemes) {
        super(context, resource, phonemes);
    }
}
