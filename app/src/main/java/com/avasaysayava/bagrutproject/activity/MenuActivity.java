package com.avasaysayava.bagrutproject.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.database.datasource.TimesDataSource;
import com.avasaysayava.bagrutproject.game.LevelPreview;
import com.avasaysayava.bagrutproject.leaderboard.Leaderboard;
import com.avasaysayava.bagrutproject.util.Util;

@SuppressWarnings("FieldCanBeLocal")
public class MenuActivity extends Activity {
    private Button[] levels;
    private int currentLevel;
    private Button btn_play, btn_back;
    private Leaderboard leaderboard;
    private TimesDataSource timesDataSource;
    private ScrollView scroll_ranks;
    private LevelPreview sv_level_preview;
    private MediaPlayer click, load;
    private TextView txt_level_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.click = MediaPlayer.create(this, R.raw.click);
        this.click.setVolume(.1f, .1f);
        this.load = MediaPlayer.create(this, R.raw.level_start);
        this.load.start();

        this.timesDataSource = new TimesDataSource(this);

        setContentView(R.layout.menu_activity);

        this.txt_level_title = findViewById(R.id.txt_level_title);

        this.leaderboard = findViewById(R.id.leaderboard);
        this.scroll_ranks = findViewById(R.id.scroll_ranks);

        this.sv_level_preview = findViewById(R.id.sv_preview);

        this.btn_back = findViewById(R.id.btn_back);
        this.btn_back.setOnClickListener(v -> {
            this.click.start();
            this.timesDataSource.close();
            super.onBackPressed();
        });

        this.btn_play = findViewById(R.id.btn_play);
        this.btn_play.setOnClickListener(v -> {
            if (Util.getMap(this.sv_level_preview, this.currentLevel + 1) != null) {
                Intent intent = new Intent(this, LevelActivity.class);
                intent.putExtra("map", this.currentLevel + 1);
                startActivity(intent, savedInstanceState);
            } else {
                this.click.start();
                Toast.makeText(this, R.string.no_level_provided, Toast.LENGTH_SHORT).show();
            }
        });

        this.levels = new Button[]{
                findViewById(R.id.level1),
                findViewById(R.id.level2),
                findViewById(R.id.level3),
                findViewById(R.id.level4),
                findViewById(R.id.level5),
                findViewById(R.id.level6),
                findViewById(R.id.level7),
                findViewById(R.id.level8),
                findViewById(R.id.level9)
        };

        this.levels[0].setOnClickListener(v -> {
            this.click.start();
            previewMap(0);
        });

        this.levels[1].setOnClickListener(v -> {
            this.click.start();
            previewMap(1);
        });

        this.levels[2].setOnClickListener(v -> {
            this.click.start();
            previewMap(2);
        });

        this.levels[3].setOnClickListener(v -> {
            this.click.start();
            previewMap(3);
        });

        this.levels[4].setOnClickListener(v -> {
            this.click.start();
            previewMap(4);
        });

        this.levels[5].setOnClickListener(v -> {
            this.click.start();
            previewMap(5);
        });

        this.levels[6].setOnClickListener(v -> {
            this.click.start();
            previewMap(6);
        });

        this.levels[7].setOnClickListener(v -> {
            this.click.start();
            previewMap(7);
        });

        this.levels[8].setOnClickListener(v -> {
            this.click.start();
            previewMap(8);
        });

        this.currentLevel = -1;
        this.leaderboard.clear();
        this.levels[0].callOnClick();
    }

    public void previewMap(int index) {
        if (this.currentLevel != index) {
            if (this.currentLevel != -1)
                this.levels[this.currentLevel].setBackground(
                        ContextCompat.getDrawable(this, R.drawable.default_button));
            this.currentLevel = index;
            this.levels[this.currentLevel].setBackground(
                    ContextCompat.getDrawable(this, R.drawable.default_button_pressed));
            this.txt_level_title.setText(this.levels[this.currentLevel].getText());
            this.sv_level_preview.loadMap(Util.getMap(this.sv_level_preview, this.currentLevel + 1));
            loadLeaderboard();
        } else {
            // animate scrolling
            if (this.scroll_ranks.getScrollY() == 0) {
                ObjectAnimator.ofInt(this.scroll_ranks,
                                "scrollY",
                                this.scroll_ranks.getScrollY(),
                                this.leaderboard.getMarkedY())
                        .setDuration(200)
                        .start();
            } else {
                ObjectAnimator.ofInt(this.scroll_ranks,
                                "scrollY",
                                this.scroll_ranks.getScrollY(),
                                0)
                        .setDuration(200)
                        .start();
            }
        }
    }

    private void loadLeaderboard() {
        this.scroll_ranks.setScrollY(0);
        this.leaderboard.loadLevel(this.timesDataSource, this.currentLevel + 1);
        Long lastTime = this.timesDataSource.getLastTime(this.currentLevel + 1);
        if (lastTime != null)
            this.leaderboard.markTime(Util.timeToString(lastTime));
        this.leaderboard.update();

        // animate scrolling when leaderboard loaded
        new Thread(() -> {
            while (!this.leaderboard.isLoaded()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
            }
            runOnUiThread(() -> ObjectAnimator.ofInt(this.scroll_ranks,
                            "scrollY",
                            0,
                            this.leaderboard.getMarkedY())
                    .setDuration(200)
                    .start());
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.sv_level_preview.onResume();
        int level = this.currentLevel;
        this.currentLevel = -1;
        this.levels[level].callOnClick();
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.sv_level_preview.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.sv_level_preview.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.sv_level_preview.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.sv_level_preview.onPause();
    }
}
