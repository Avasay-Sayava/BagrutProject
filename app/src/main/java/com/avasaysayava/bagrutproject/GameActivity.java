package com.avasaysayava.bagrutproject;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import com.avasaysayava.bagrutproject.game.Game;

public class GameActivity extends Activity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.game = new Game(this, 60, 5, true);
        setContentView(this.game);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.game.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.game.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.game.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.game.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.game.onDestroy();
    }
}
