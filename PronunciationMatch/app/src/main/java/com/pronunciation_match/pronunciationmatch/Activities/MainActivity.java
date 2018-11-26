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

    public void startPhonemeSelectionActivity(View view) {
        //The body of this is commented out since we aren't verifying the user anymore
        /*if (verifyUser()) {
            startActivity(new Intent(this, PhonemeSelectionActivity.class));
        } else {
            Toast toast = Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT);
            toast.show();
        }*/
    }

    //Don't necessarily need this anymore since we are using oogle OAuth
    /*private boolean verifyUser() {
        EditText username = findViewById(R.id.editTextUserName);
        EditText password = findViewById(R.id.editTextPassword);

        return username.getText().toString().equals("user") && password.getText().toString().equals("password");
    }*/
}
