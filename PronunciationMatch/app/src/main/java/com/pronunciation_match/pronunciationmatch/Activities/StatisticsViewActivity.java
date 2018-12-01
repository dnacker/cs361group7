package com.pronunciation_match.pronunciationmatch.Activities;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_view);

        mDrawerLayout = findViewById(R.id.stats_drawer_layout);
        NavigationView navigationView = findViewById(R.id.stats_nav_view);

        // Reflect current page in nav drawer
        Menu navDrawer = navigationView.getMenu();
        MenuItem currentMenu = navDrawer.findItem(R.id.nav_stats);
        currentMenu.setChecked(true);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        // item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        Intent i;
                        switch(item.getItemId()) {
                            case R.id.nav_stats:
                                /*
                                i = new Intent(StatisticsViewActivity.this, StatisticsViewActivity.class);
                                startActivity(i);
                                */
                                break;
                            case R.id.nav_phoneme_practice:
                                i = new Intent(StatisticsViewActivity.this, PhonemeSelectionActivity.class);
                                startActivity(i);
                                break;
                            case R.id.nav_phrase_practice:
                                i = new Intent(StatisticsViewActivity.this, PhraseSelectionActivity.class);
                                startActivity(i);
                                break;
                            case R.id.nav_recorder:
                                i = new Intent(StatisticsViewActivity.this, RecordUser.class);
                                startActivity(i);
                                break;
                        }
                        return false;
                    }
                }

        );

        ListView listView = findViewById(R.id.phonemeList);
        phonemeBank = PhonemeBank.getInstance(this);

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
