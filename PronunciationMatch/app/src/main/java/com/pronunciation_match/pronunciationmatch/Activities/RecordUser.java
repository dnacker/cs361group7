package com.pronunciation_match.pronunciationmatch.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.pronunciation_match.pronunciationmatch.R;

import java.io.File;
import java.io.IOException;

public class RecordUser extends AppCompatActivity {

    private static String mAudioFileName = null;
    private MediaRecorder mRecorder = null;
    private MediaPlayer mPlayer = null;
    private boolean mCurrentlyRecording = false;
    private DrawerLayout mDrawerLayout;

    //The section of code below was borrowed from the example in the MediaRecorder documentation
    //on the Android Developer site since adequate experience in permissions isn't available
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_user);

        mDrawerLayout = findViewById(R.id.record_drawer_layout);
        NavigationView navigationView = findViewById(R.id.record_nav_view);

        Menu navDrawer = navigationView.getMenu();
        MenuItem currentMenu = navDrawer.findItem(R.id.nav_recorder);
        currentMenu.setChecked(true);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        mDrawerLayout.closeDrawers();
                        Intent i;
                        switch (item.getItemId()) {
                            case R.id.nav_stats:
                                i = new Intent(RecordUser.this, StatisticsViewActivity.class);
                                startActivity(i);
                                break;
                            case R.id.nav_phoneme_practice:
                                i = new Intent(RecordUser.this, PhonemeSelectionActivity.class);
                                startActivity(i);
                                break;
                            case R.id.nav_phrase_practice:
                                i = new Intent(RecordUser.this, PhraseSelectionActivity.class);
                                startActivity(i);
                                break;
                            case R.id.nav_recorder:
                                /*
                                i = new Intent(RecordUser.this, RecordUser.class);
                                startActivity(i);
                                */
                                break;
                        }
                        return false;

                    }
                }
        );

        // The following three lines came from the MediaRecorder example as well
        mAudioFileName = getExternalCacheDir().getAbsolutePath();
        mAudioFileName += "/useraudio.3gp";
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //If the recorder or player were active when screen switched then we need to stop them both
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }

        File deleteFile = new File(mAudioFileName);
        if (deleteFile.exists()) {
            deleteFile.delete();
        }
    }

    public void recordUser (View view) {
        //The following was also taken from the MediaRecorder example
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mAudioFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e("RECORDINGFAIL", "prepare() failed");
        }

        mRecorder.start();
    }

    public void stopRecording (View view) {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
    }

    public void playRecording (View view) {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mAudioFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
