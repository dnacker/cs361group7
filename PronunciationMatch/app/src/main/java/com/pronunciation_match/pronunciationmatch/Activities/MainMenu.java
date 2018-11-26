package com.pronunciation_match.pronunciationmatch.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pronunciation_match.pronunciationmatch.Activities.GoogleLogin;
import com.pronunciation_match.pronunciationmatch.R;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra(GoogleLogin.USER_NAME);
        String idToken = intent.getStringExtra(GoogleLogin.ID_TOKEN);

        TextView textView = findViewById(R.id.textView);
        textView.setText("Welcome " + firstName);
    }

    public void gotoPhonemes(View view) {
        Intent intent = new Intent(this, PhonemeSelectionActivity.class);
        startActivity(intent);
    }

    public void gotoStatistics(View view) {
        Intent intent = new Intent (this, StatisticsViewActivity.class);
        startActivity(intent);
    }
}
