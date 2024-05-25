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

        this.click = MediaPlayer.create(this, R.raw.click);
        this.click.setVolume(.1f, .1f);
        this.load = MediaPlayer.create(this, R.raw.level_start);

        this.backgroundMusicService = new Intent(this, BackgroundMusicService.class);
        this.timesDataSource = new TimesDataSource(this);
        this.levelNumber = getIntent().getIntExtra("map", 0);

        setContentView(R.layout.level_activity);

        this.sv_level = findViewById(R.id.sv_level);
        this.sv_level.loadMap(Util.getMap(this.sv_level, this.levelNumber));

        this.img_btn_quit = findViewById(R.id.img_btn_quit);
        this.img_btn_quit.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.quit));
        this.img_btn_quit.setOnClickListener(v -> {
            this.click.start();
            showLeaveDialog();
        });

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

        this.sv_level.setOnCompleteListener(time -> runOnUiThread(() -> onCompleted(time)));
    }

    private void resumeLevel() {
        this.sv_level.resume();
        this.img_btn_debug.setVisibility(View.GONE);
        this.img_btn_graph.setVisibility(View.GONE);
        this.img_btn_quit.setVisibility(View.GONE);
        this.img_btn_play.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.play_to_pause));
        Drawable drawable = this.img_btn_play.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    private void pauseLevel() {
        this.sv_level.pause();
        this.img_btn_debug.setVisibility(View.VISIBLE);
        this.img_btn_graph.setVisibility(View.VISIBLE);
        this.img_btn_quit.setVisibility(View.VISIBLE);
        this.img_btn_quit.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.quit));
        this.img_btn_play.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pause_to_play));
        Drawable drawable = this.img_btn_play.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    private void showLeaveDialog() {
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
        this.load.start();
        this.sv_level.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.sv_level.onResume();
        startService(this.backgroundMusicService);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!this.sv_level.isPaused()) this.img_btn_play.performClick();
        this.sv_level.onPause();
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
        if (!this.sv_level.isPaused()) pauseLevel();
        this.timesDataSource.openReadable();
        Long bestTime = this.timesDataSource.getBestTime(this.levelNumber);
        this.timesDataSource.setLastTime(time, this.levelNumber);
        long diff = bestTime == null ? 0 : time - bestTime;
        this.timesDataSource.openWriteable();
        this.timesDataSource.addTime(time, this.levelNumber);
        showTimeDialog(time, diff, diff <= 0);
    }

    private void showTimeDialog(long time, long diff, boolean newBest) {
        new AlertDialog.Builder(this)
                .setTitle((newBest ? "New Best Time! " : "Try Harder! ")
                        + Util.timeToString(time) + " (" + (diff > 0 ? "+" : "-")
                        + Util.timeToString(Math.abs(diff)) + ")")
                .setPositiveButton(R.string.ok, (dialog, id) -> super.onBackPressed())
                .setCancelable(false)
                .create().show();
    }
}
