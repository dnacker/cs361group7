package com.pronunciation_match.pronunciationmatch.Activities;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pronunciation_match.pronunciationmatch.Phoneme;
import com.pronunciation_match.pronunciationmatch.PhonemeAdapter;
import com.pronunciation_match.pronunciationmatch.PhonemeBank;
import com.pronunciation_match.pronunciationmatch.R;
import com.pronunciation_match.pronunciationmatch.Tone;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class PhonemeSelectionActivity extends AppCompatActivity {
    private static final String TAG = PhonemeSelectionActivity.class.getSimpleName();
    private PhonemeAdapter phonemeAdapter;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private PhonemeBank phonemeBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneme_selection);
        ListView listView = findViewById(R.id.phoneme_list_view);
        phonemeBank = PhonemeBank.getInstance(this);

        phonemeAdapter = new PhonemeAdapter(this, android.R.layout.simple_list_item_1, phonemeBank.getPhonemes());
        listView.setAdapter(phonemeAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                Phoneme selected = phonemeAdapter.getItem(position);
                try {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    AssetFileDescriptor afd = view.getContext().getAssets().openFd(selected.getPath());
                    mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    afd.close();
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    Log.e(TAG, String.format("Unable to open file %s", selected.getPath()));
                }
            }
        });
    }
}
