package com.avasaysayava.bagrutproject.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.core.content.ContextCompat;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.game.Level;
import com.avasaysayava.bagrutproject.service.BackgroundMusicService;

public class LevelActivity extends Activity {
    private Level sv_level;
    private Intent backgroundMusicService;
    private ImageButton img_btn_play, img_btn_debug, img_btn_graph, img_btn_quit;
    private MediaPlayer click, load;
    private long t_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.t_start = System.currentTimeMillis();

        Log.d("app/event", "onCreate");

        this.click = MediaPlayer.create(this, R.raw.click);
        this.click.setVolume(.1f, .1f);
        this.load = MediaPlayer.create(this, R.raw.level_start);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        this.backgroundMusicService = new Intent(this, BackgroundMusicService.class);

        setContentView(R.layout.level_activity);

        this.sv_level = findViewById(R.id.sv_level);

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
        // create dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.lose_all_progress)
                .setPositiveButton(R.string.yes, (DialogInterface.OnClickListener) (dialog, id) -> {
                    super.onBackPressed();
                })
                .setNegativeButton(R.string.no, (DialogInterface.OnClickListener) (dialog, id) -> {

                });
        builder.create().show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("app/event", "onStart");
        this.load.start();
        this.sv_level.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("app/event", "onResume");
        startService(this.backgroundMusicService);
        this.sv_level.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("app/event", "onPause");
        stopService(this.backgroundMusicService);
        if (!this.sv_level.isPaused())
            this.img_btn_play.performClick();
        this.sv_level.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("app/event", "onStop");
        this.sv_level.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("app/event", "onDestroy");
        this.sv_level.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (!this.sv_level.isPaused()) pauseLevel();
        showLeaveDialog();
    }
}
