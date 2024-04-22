package com.avasaysayava.bagrutproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.WindowManager;

import com.avasaysayava.bagrutproject.game.Game;
import com.avasaysayava.bagrutproject.service.BackgroundMusicService;

public class GameActivity extends Activity {
    private Game game;
    private Intent backgroundMusicService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("app/event", "onCreate");

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());

        this.backgroundMusicService = new Intent(this, BackgroundMusicService.class);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(this.game = new Game(this, 60, 5, false, false));
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("app/event", "onStart");
        this.game.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("app/event", "onResume");
        startService(this.backgroundMusicService);
        this.game.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("app/event", "onPause");
        stopService(this.backgroundMusicService);
        this.game.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("app/event", "onStop");
        this.game.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("app/event", "onDestroy");
        this.game.onDestroy();
        super.onDestroy();
    }
}
