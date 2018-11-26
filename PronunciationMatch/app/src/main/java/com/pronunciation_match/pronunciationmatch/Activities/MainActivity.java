package com.pronunciation_match.pronunciationmatch.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pronunciation_match.pronunciationmatch.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Log.v("TAG","Hello world!");
        //setContentView(R.layout.activity_main);

    }

    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, GoogleLogin.class);
        startActivity(intent);
    }
}
