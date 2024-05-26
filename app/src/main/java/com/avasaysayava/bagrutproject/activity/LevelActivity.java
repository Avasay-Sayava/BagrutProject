package com.avasaysayava.bagrutproject.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.core.content.ContextCompat;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.database.datasource.TimesDataSource;
import com.avasaysayava.bagrutproject.game.Level;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.GameMap;
import com.avasaysayava.bagrutproject.service.BackgroundMusicService;
import com.avasaysayava.bagrutproject.util.Util;

public class LevelActivity extends Activity {
    private Level sv_level;
    private int levelNumber;
    private Intent backgroundMusicService;
    private TimesDataSource timesDataSource;
    private ImageButton img_btn_play, img_btn_debug, img_btn_graph, img_btn_quit;
    private MediaPlayer click, load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a MediaPlayer for the click sound with 10% volume
        this.click = MediaPlayer.create(this, R.raw.click);
        this.click.setVolume(.1f, .1f);

        // Create a MediaPlayer for the level start sound and play it
        this.load = MediaPlayer.create(this, R.raw.level_start);
        this.load.start();

        // play background music
        this.backgroundMusicService = new Intent(this, BackgroundMusicService.class);

        // initialize the datasource
        this.timesDataSource = new TimesDataSource(this);

        // get the level number from the intent
        this.levelNumber = getIntent().getIntExtra("map", 0);

        // Set the layout of the activity
        setContentView(R.layout.level_activity);

        // sound the level SurfaceView
        this.sv_level = findViewById(R.id.sv_level);
        this.sv_level.loadMap(Util.getMap(this.sv_level, this.levelNumber));

        // init the quit button
        this.img_btn_quit = findViewById(R.id.img_btn_quit);
        this.img_btn_quit.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.quit));
        this.img_btn_quit.setOnClickListener(v -> {
            this.click.start();
            showLeaveDialog();
        });


        // init the debug button
        this.img_btn_debug = findViewById(R.id.img_btn_debug);
        this.img_btn_debug.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.debug_off));
        this.img_btn_debug.setOnClickListener(v -> {
            this.click.start();
            this.sv_level.toggleDebug();

            if (this.sv_level.isDebug()) {
                this.img_btn_debug.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.debug_on));
            } else {
                this.img_btn_debug.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.debug_off));
            }
        });

        // init the graph mode button
        this.img_btn_graph = findViewById(R.id.img_btn_graph);
        this.img_btn_graph.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.graph_off));
        this.img_btn_graph.setOnClickListener(v -> {
            this.click.start();
            this.sv_level.toggleGraph();

            if (this.sv_level.isGraph()) {
                this.img_btn_graph.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.graph_on));
            } else {
                this.img_btn_graph.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.graph_off));
            }
        });

        // init the resume/pause button
        this.img_btn_play = findViewById(R.id.img_btn_play);
        this.img_btn_play.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pause));
        this.img_btn_play.setOnClickListener(v -> {
            this.click.start();

            if (this.sv_level.isPaused()) {
                resumeLevel();
            } else {
                pauseLevel();
            }
        });

        // set what happens when the level is finished
        this.sv_level.setOnCompleteListener(time -> runOnUiThread(() -> onCompleted(time)));
    }

    private void resumeLevel() {
        this.sv_level.resume();

        // make buttons invisible on resume
        this.img_btn_debug.setVisibility(View.GONE);
        this.img_btn_graph.setVisibility(View.GONE);
        this.img_btn_quit.setVisibility(View.GONE);

        // play the resume->pause button animation
        this.img_btn_play.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.play_to_pause));
        Drawable drawable = this.img_btn_play.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    private void pauseLevel() {
        this.sv_level.pause();

        // make buttons visible on pause
        this.img_btn_debug.setVisibility(View.VISIBLE);
        this.img_btn_graph.setVisibility(View.VISIBLE);
        this.img_btn_quit.setVisibility(View.VISIBLE);

        // play the resume->pause button animation
        this.img_btn_play.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pause_to_play));
        Drawable drawable = this.img_btn_play.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    private void showLeaveDialog() {
        // show quit dialog
        new AlertDialog.Builder(this)
                .setMessage(R.string.lose_all_progress)
                .setPositiveButton(R.string.yes, (dialog, id) -> {
                    this.timesDataSource.close();
                    super.onBackPressed();
                })
                .setNegativeButton(R.string.no, (dialog, id) -> {})
                .create().show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.sv_level.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.sv_level.onResume();

        // play the background music again
        startService(this.backgroundMusicService);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!this.sv_level.isPaused()) this.img_btn_play.performClick();
        this.sv_level.onPause();

        // stop the background music
        stopService(this.backgroundMusicService);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.sv_level.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.sv_level.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (!this.sv_level.isPaused()) pauseLevel();
        showLeaveDialog();
    }

    public void onCompleted(long time) {
        // pause level
        if (!this.sv_level.isPaused()) pauseLevel();

        // get best time
        this.timesDataSource.openReadable();
        Long bestTime = this.timesDataSource.getBestTime(this.levelNumber);

        // get last time
        this.timesDataSource.setLastTime(time, this.levelNumber);

        // get difference between last time and best time
        long diff = bestTime == null ? 0 : time - bestTime;

        // add the current time to the database
        this.timesDataSource.openWriteable();
        this.timesDataSource.addTime(time, this.levelNumber);

        // show game finished dialog
        showTimeDialog(time, diff, diff <= 0);
    }

    private void showTimeDialog(long time, long diff, boolean newBest) {
        // show the game finished dialog
        new AlertDialog.Builder(this)
                .setTitle((newBest ? "New Best Time! " : "Try Harder! ")
                        + Util.timeToString(time) + " (" + (diff > 0 ? "+" : "-")
                        + Util.timeToString(Math.abs(diff)) + ")")
                .setPositiveButton(R.string.ok, (dialog, id) -> super.onBackPressed())
                .setCancelable(false)
                .create().show();
    }
}
