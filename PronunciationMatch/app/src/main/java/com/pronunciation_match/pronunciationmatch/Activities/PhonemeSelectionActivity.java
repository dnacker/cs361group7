package com.pronunciation_match.pronunciationmatch.Activities;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pronunciation_match.pronunciationmatch.Phoneme;
import com.pronunciation_match.pronunciationmatch.PhonemeAdapter;
import com.pronunciation_match.pronunciationmatch.PhonemeBank;
import com.pronunciation_match.pronunciationmatch.R;

import java.io.IOException;

public class PhonemeSelectionActivity extends AppCompatActivity {
    private static final String TAG = PhonemeSelectionActivity.class.getSimpleName();
    private PhonemeAdapter phonemeAdapter;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private PhonemeBank phonemeBank;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneme_selection);

        mDrawerLayout = findViewById(R.id.phoneme_drawer_layout);
        NavigationView navigationView = findViewById(R.id.phoneme_nav_view);

        // Reflect current page in nav drawer
        Menu navDrawer = navigationView.getMenu();
        MenuItem currentMenu = navDrawer.findItem(R.id.nav_phoneme_practice);
        currentMenu.setChecked(true);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        // menuItem.setChecked(true);
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
