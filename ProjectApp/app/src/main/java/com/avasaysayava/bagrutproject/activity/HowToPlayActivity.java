package com.avasaysayava.bagrutproject.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.avasaysayava.bagrutproject.R;

public class HowToPlayActivity extends Activity {
    private Button btn_back;
    private MediaPlayer click, load;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.click = MediaPlayer.create(this, R.raw.click);
        this.click.setVolume(.1f, .1f);
        this.load = MediaPlayer.create(this, R.raw.level_start);
        this.load.start();

        setContentView(R.layout.how_to_play_activity);

        this.btn_back = findViewById(R.id.btn_back);
        this.btn_back.setOnClickListener(v -> {
            this.click.start();
            super.onBackPressed();
        });
    }
}
