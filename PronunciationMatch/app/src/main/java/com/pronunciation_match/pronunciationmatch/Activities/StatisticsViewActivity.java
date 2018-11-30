package com.pronunciation_match.pronunciationmatch.Activities;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.pronunciation_match.pronunciationmatch.Phoneme;
import com.pronunciation_match.pronunciationmatch.PhonemeAdapter;
import com.pronunciation_match.pronunciationmatch.PhonemeBank;
import com.pronunciation_match.pronunciationmatch.R;

public class StatisticsViewActivity extends AppCompatActivity {
    private PhonemeAdapter phonemeAdapter;
    private PhonemeBank phonemeBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_view);

        ListView listView = findViewById(R.id.phonemeList);
        phonemeBank = PhonemeBank.getInstance();

        phonemeAdapter = new PhonemeAdapter(this, android.R.layout.simple_list_item_1, phonemeBank.getPhonemes());
        listView.setAdapter(phonemeAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Phoneme selected = phonemeAdapter.getItem(position);
                Context context = getApplicationContext();
                CharSequence text = "Score goes here";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}
