package com.pronunciation_match.pronunciationmatch.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.pronunciation_match.pronunciationmatch.Phoneme;
import com.pronunciation_match.pronunciationmatch.PhonemeAdapter;
import com.pronunciation_match.pronunciationmatch.R;
import com.pronunciation_match.pronunciationmatch.Tone;

import java.util.ArrayList;
import java.util.List;

public class PhonemeSelectionActivity extends AppCompatActivity {
    private PhonemeAdapter phonemeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneme_selection);
        ListView listView = findViewById(R.id.phoneme_list_view);
        List<Phoneme> phonemes = new ArrayList<>();
        for (Tone t: Tone.values()) {
            phonemes.add(new Phoneme("a", t, 0));
        }
        for (Tone t: Tone.values()) {
            phonemes.add(new Phoneme("e", t, 0));
        }
        for (Tone t: Tone.values()) {
            phonemes.add(new Phoneme("o", t, 0));
        }

        phonemeAdapter = new PhonemeAdapter(this, android.R.layout.simple_list_item_1, phonemes);
        listView.setAdapter(phonemeAdapter);
    }
}
