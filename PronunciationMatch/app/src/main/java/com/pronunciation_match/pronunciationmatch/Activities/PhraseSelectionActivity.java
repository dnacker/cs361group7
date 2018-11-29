package com.pronunciation_match.pronunciationmatch.Activities;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pronunciation_match.pronunciationmatch.Phoneme;
import com.pronunciation_match.pronunciationmatch.Phrase;
import com.pronunciation_match.pronunciationmatch.R;

import java.util.LinkedList;
import java.util.List;

public class PhraseSelectionActivity extends AppCompatActivity {
    private static final String TAG = PhraseSelectionActivity.class.getSimpleName();
    private Phrase mPhrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase_selection);
    }

    public void convertTextToPhrase(View view) {
        EditText editText = findViewById(R.id.editTextPhrase);
        TextView textView = findViewById(R.id.textViewPhrase);
        String text = editText.getText().toString();
        Log.v(TAG, text);
        try {
            mPhrase = new Phrase(text);
            textView.setText(mPhrase.toString());
        } catch (IllegalArgumentException e) {
            mPhrase = null;
            textView.setText("");
            Toast.makeText(view.getContext(), R.string.toast_unable_to_convert, Toast.LENGTH_SHORT).show();
        }
    }

    public void playPhrase(View view) {
        if (mPhrase != null) {
            List<Phoneme> phonemeList = mPhrase.getPhonemes();
            List<MediaPlayer> mediaPlayers = new LinkedList<>();
            MediaPlayer prev;
            MediaPlayer current = null;
            for (int i = 0; i < phonemeList.size(); i++) {
                Phoneme p = phonemeList.get(i);
                prev = current;
                current = MediaPlayer.create(view.getContext(), p.getResourceId());
                if (prev != null) {
                    prev.setNextMediaPlayer(current);
                }
                mediaPlayers.add(current);
            }
            mediaPlayers.get(0).start();
        }
    }

}
