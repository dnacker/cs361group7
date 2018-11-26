package com.pronunciation_match.pronunciationmatch.Activities;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pronunciation_match.pronunciationmatch.Phoneme;
import com.pronunciation_match.pronunciationmatch.PhonemeAdapter;
import com.pronunciation_match.pronunciationmatch.PhonemeBank;
import com.pronunciation_match.pronunciationmatch.R;
import com.pronunciation_match.pronunciationmatch.Tone;

import java.util.ArrayList;
import java.util.List;

public class PhonemeSelectionActivity extends AppCompatActivity {
    private PhonemeAdapter phonemeAdapter;
    private MediaPlayer mediaPlayer;
    private PhonemeBank phonemeBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneme_selection);
        ListView listView = findViewById(R.id.phoneme_list_view);
        phonemeBank = PhonemeBank.getInstance();

        phonemeAdapter = new PhonemeAdapter(this, android.R.layout.simple_list_item_1, phonemeBank.getPhonemes());
        listView.setAdapter(phonemeAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Phoneme selected = phonemeAdapter.getItem(position);
                MediaPlayer mediaPlayer = MediaPlayer.create(view.getContext(), selected.getResourceId());
                mediaPlayer.start();
            }
        });
    }
}
