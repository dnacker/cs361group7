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
        setContentView(R.layout.activity_main);

    }

    public void startPhonemeSelectionActivity(View view) {
        if (verifyUser()) {
            startActivity(new Intent(this, PhonemeSelectionActivity.class));
        } else {
            Toast toast = Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private boolean verifyUser() {
        EditText username = findViewById(R.id.editTextUserName);
        EditText password = findViewById(R.id.editTextPassword);

        return username.getText().toString().equals("user") && password.getText().toString().equals("password");
    }
}
