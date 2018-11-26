package com.pronunciation_match.pronunciationmatch.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
