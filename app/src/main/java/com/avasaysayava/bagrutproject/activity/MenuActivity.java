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

        // Create media players and set click sound volume
        this.click = MediaPlayer.create(this, R.raw.click);
        this.click.setVolume(.1f, .1f);
        this.load = MediaPlayer.create(this, R.raw.level_start);
        this.load.start();

        // Create data source for level times
        this.timesDataSource = new TimesDataSource(this);

        // Set layout for the activity
        setContentView(R.layout.menu_activity);

        // Find UI elements from the layout
        this.txt_level_title = findViewById(R.id.txt_level_title);
        this.leaderboard = findViewById(R.id.leaderboard);
        this.scroll_ranks = findViewById(R.id.scroll_ranks);
        this.sv_level_preview = findViewById(R.id.sv_preview);
        this.btn_back = findViewById(R.id.btn_back);
        this.btn_play = findViewById(R.id.btn_play);

        // Set on-click listener for back button
        this.btn_back.setOnClickListener(v -> {
            this.click.start();
            this.timesDataSource.close(); // Close data source when back is pressed
            super.onBackPressed();
        });

        // Set on-click listener for play button
        this.btn_play.setOnClickListener(v -> {
            // Check if map data exists for the selected level
            if (Util.getMap(this.sv_level_preview, this.currentLevel + 1) != null) {
                Intent intent = new Intent(this, LevelActivity.class);
                intent.putExtra("map", this.currentLevel + 1); // Pass level number to LevelActivity
                startActivity(intent, savedInstanceState);
            } else {
                this.click.start();
                Toast.makeText(this, R.string.no_level_provided, Toast.LENGTH_SHORT).show();
            }
        });

        // Initialize level buttons array
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

        // Set on-click listeners for each level button
        for (int i = 0; i < levels.length; i++) {
            final int index = i;
            levels[i].setOnClickListener(v -> {
                this.click.start();
                previewMap(index);
            });
        }

        // Initialize current level
        this.currentLevel = -1;
        this.leaderboard.clear(); // Clear leaderboard initially
        this.levels[0].callOnClick(); // Simulate click on first level button to trigger preview

    }

    public void previewMap(int index) {
        if (this.currentLevel != index) {
            // Update button visuals based on selected level
            if (this.currentLevel != -1)
                this.levels[this.currentLevel].setBackground(
                        ContextCompat.getDrawable(this, R.drawable.default_button));
            this.currentLevel = index;
            this.levels[this.currentLevel].setBackground(
                    ContextCompat.getDrawable(this, R.drawable.default_button_pressed));
            this.txt_level_title.setText(this.levels[this.currentLevel].getText());

            // Load map data and update preview
            this.sv_level_preview.loadMap(Util.getMap(this.sv_level_preview, this.currentLevel + 1));
            loadLeaderboard();
        } else {
            // Animate scrolling for leaderboard
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
        // Reset scroll position and load leaderboard data for current level
        this.scroll_ranks.setScrollY(0);
        this.leaderboard.loadLevel(this.timesDataSource, this.currentLevel + 1);
        Long lastTime = this.timesDataSource.getLastTime(this.currentLevel + 1);
        if (lastTime != null)
            this.leaderboard.markTime(Util.timeToString(lastTime)); // Mark last time on leaderboard
        this.leaderboard.update();

        // Animate scrolling to marked time (if any) on a separate thread
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
        this.levels[level].callOnClick(); // Simulate click on previously selected level on resume
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
