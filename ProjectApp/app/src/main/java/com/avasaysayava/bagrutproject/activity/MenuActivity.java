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
import com.avasaysayava.bagrutproject.database.datasource.LevelsDataSource;
import com.avasaysayava.bagrutproject.game.LevelPreview;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.GameMap;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.debugmap.FloorMap;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.debugmap.GlyphFloorMap;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.debugmap.GroundMap;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.debugmap.StructuresMap;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.debugmap.WallsMap;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.levelmap.Level1Map;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.levelmap.Level2Map;
import com.avasaysayava.bagrutproject.leaderboard.Leaderboard;

@SuppressWarnings("FieldCanBeLocal")
public class MenuActivity extends Activity {
    private Button[] levels;
    private int currentLevel;
    private Button btn_play, btn_back;
    private Leaderboard leaderboard;
    private LevelsDataSource levelsDataSource;
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

        this.levelsDataSource = new LevelsDataSource(this);

        setContentView(R.layout.menu_activity);

        this.txt_level_title = findViewById(R.id.txt_level_title);

        this.leaderboard = findViewById(R.id.leaderboard);
        this.scroll_ranks = findViewById(R.id.scroll_ranks);

        this.sv_level_preview = findViewById(R.id.sv_preview);

        this.btn_back = findViewById(R.id.btn_back);
        this.btn_back.setOnClickListener(v -> {
            this.click.start();
            this.levelsDataSource.close();
            super.onBackPressed();
        });

        this.btn_play = findViewById(R.id.btn_play);
        this.btn_play.setOnClickListener(v -> {
            if (GameMap.currentMap != null) {
                startActivity(new Intent(this, LevelActivity.class), savedInstanceState);
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
            previewMap(0, GameMap.currentMap = new Level1Map(this.sv_level_preview));
        });

        this.levels[1].setOnClickListener(v -> {
            this.click.start();
            previewMap(1, GameMap.currentMap = new Level2Map(this.sv_level_preview));
        });

        this.levels[2].setOnClickListener(v -> {
            this.click.start();
            previewMap(2, GameMap.currentMap = new StructuresMap(this.sv_level_preview, 0, 0));
        });

        this.levels[3].setOnClickListener(v -> {
            this.click.start();
            previewMap(3, GameMap.currentMap = new WallsMap(this.sv_level_preview, -100, -100));
        });

        this.levels[4].setOnClickListener(v -> {
            this.click.start();
            previewMap(4, GameMap.currentMap = new GroundMap(this.sv_level_preview, 0, 0));
        });

        this.levels[5].setOnClickListener(v -> {
            this.click.start();
            previewMap(5, GameMap.currentMap = new FloorMap(this.sv_level_preview, 0, 0));
        });

        this.levels[6].setOnClickListener(v -> {
            this.click.start();
            previewMap(6, GameMap.currentMap = new GlyphFloorMap(this.sv_level_preview, 0, 0));
        });

        this.levels[7].setOnClickListener(v -> {
            this.click.start();
            previewMap(7, GameMap.currentMap = null);
        });

        this.levels[8].setOnClickListener(v -> {
            this.click.start();
            previewMap(8, GameMap.currentMap = null);
        });

        this.currentLevel = -1;
        this.leaderboard.clear();
        this.levels[0].callOnClick();
    }

    public void previewMap(int index, GameMap map) {
        if (this.currentLevel != index) {
            if (this.currentLevel != -1)
                this.levels[this.currentLevel].setBackground(
                        ContextCompat.getDrawable(this, R.drawable.default_button));
            this.currentLevel = index;
            this.levels[this.currentLevel].setBackground(
                    ContextCompat.getDrawable(this, R.drawable.default_button_pressed));
            this.txt_level_title.setText(this.levels[this.currentLevel].getText());
            this.sv_level_preview.loadMap(map);
            loadLeaderboard();
        } else {
            // animate scrolling
            if (this.scroll_ranks.getScrollY() == 0) {
                ObjectAnimator.ofInt(this.scroll_ranks,
                                "scrollY",
                                this.scroll_ranks.getScrollY(),
                                (int) (getResources().getDisplayMetrics().density
                                        * (this.leaderboard.getMarkedY())))
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
        this.leaderboard.loadLevel(this.levelsDataSource, this.currentLevel + 1);
        this.leaderboard.markTime(this.levelsDataSource.getLastTime(this.currentLevel + 1));
        this.leaderboard.update();

        // animate scrolling
        ObjectAnimator.ofInt(this.scroll_ranks,
                "scrollY",
                0,
                (int) (getResources().getDisplayMetrics().density
                        * (this.leaderboard.getMarkedY())))
                .setDuration(1000)
                .start();
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
