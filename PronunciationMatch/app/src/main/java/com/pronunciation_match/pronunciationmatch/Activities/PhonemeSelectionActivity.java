package com.pronunciation_match.pronunciationmatch.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneme_selection);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        Intent i;
                        switch (menuItem.getItemId()) {
                            case R.id.nav_stats:
                                i = new Intent(PhonemeSelectionActivity.this, StatisticsViewActivity.class);
                                startActivity(i);
                                break;
                            case R.id.nav_phoneme_practice:
                                /*
                                i = new Intent(PhonemeSelectionActivity.this, PhonemeSelectionActivity.class);
                                startActivity(i);
                                */
                                break;
                            case R.id.nav_phrase_practice:
                                i = new Intent(PhonemeSelectionActivity.this, PhraseSelectionActivity.class);
                                startActivity(i);
                                break;
                            case R.id.nav_recorder:
                                i = new Intent(PhonemeSelectionActivity.this, RecordUser.class);
                                startActivity(i);
                                break;
                        }
                        return false;
                    }
                }
        );


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
