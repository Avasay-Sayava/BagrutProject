package com.avasaysayava.bagrutproject;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.WindowManager;

import com.avasaysayava.bagrutproject.game.Game;

public class GameActivity extends Activity {
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("app/event", "onCreate");

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build());

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(this.game = new Game(this, 60, 5, true, false));
    }

    @Override
    protected void onStart() {
        Log.d("app/event", "onStart");
        this.game.onStart();
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("app/event", "onResume");
        this.game.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("app/event", "onPause");
        this.game.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("app/event", "onStop");
        this.game.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("app/event", "onDestroy");
        this.game.onDestroy();
        super.onDestroy();
    }
}
