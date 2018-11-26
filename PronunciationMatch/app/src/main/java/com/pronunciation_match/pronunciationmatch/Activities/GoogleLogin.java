package com.pronunciation_match.pronunciationmatch.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.pronunciation_match.pronunciationmatch.R;

public class GoogleLogin extends AppCompatActivity implements View.OnClickListener {
    public static final String USER_NAME = "com.pronunciation_match.NAME";
    public static final String ID_TOKEN = "com.pronunciation_match.TOKEN";
    static final int RC_SIGN_IN = 1;
    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        setContentView(R.layout.activity_google_login);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
    }

    //This doesn't do a whole lot yet, or at least what I know of
    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    }

    @Override
    public void onClick(View v) {
        signIn();
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in do stuff with profile
            String firstName = account.getGivenName();
            String idToken = account.getIdToken();
            Intent mainMenu = new Intent(this, MainMenu.class);
            mainMenu.putExtra(USER_NAME, firstName);
            mainMenu.putExtra(ID_TOKEN, idToken);
            startActivity(mainMenu);
        } catch (ApiException e) {
            Log.d("LOGINEXCEPTION","signInResult:failed code=" + e.getStatusCode());
        }
    }
}
