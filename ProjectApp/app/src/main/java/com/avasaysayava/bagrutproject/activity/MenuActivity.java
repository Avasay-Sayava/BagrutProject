package com.avasaysayava.bagrutproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.database.DatabaseContract;
import com.avasaysayava.bagrutproject.database.datasource.LevelDataSource;
import com.avasaysayava.bagrutproject.database.datasource.UUIDDataSource;
import com.avasaysayava.bagrutproject.game.LevelPreview;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.GameMap;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.debugmap.FloorMap;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.debugmap.GlyphFloorMap;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.debugmap.GroundMap;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.debugmap.StructuresMap;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.debugmap.WallsMap;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.levelmap.Level1Map;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.levelmap.Level2Map;

@SuppressWarnings("FieldCanBeLocal")
public class MenuActivity extends Activity {
    private Button[] levels;
    private int currentLevel;
    private Button btn_play, btn_back;
    private LevelDataSource levelDataSource;
    private UUIDDataSource uuidDataSource;
    private RelativeLayout rl_rank_first, rl_rank_faster, rl_rank_current;
    private TextView txt_rank_first_time, txt_rank_faster_time, txt_rank_current_time, txt_rank_slower_time;
    private TextView txt_rank_first_number, txt_rank_faster_number, txt_rank_current_number, txt_rank_slower_number;
    private LevelPreview sv_level_preview;
    private MediaPlayer click, load;
    private TextView txt_level_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.levelDataSource = new LevelDataSource(this, DatabaseContract.UserEntry.COLUMN_LEVEL1);
        this.uuidDataSource = new UUIDDataSource(this);

        this.click = MediaPlayer.create(this, R.raw.click);
        this.click.setVolume(.1f, .1f);
        this.load = MediaPlayer.create(this, R.raw.level_start);
        this.load.start();

        setContentView(R.layout.menu_activity);

        this.rl_rank_current = findViewById(R.id.rl_rank_current);
        this.rl_rank_faster = findViewById(R.id.rl_rank_faster);
        this.rl_rank_first = findViewById(R.id.rl_rank_first);

        this.txt_rank_slower_time = findViewById(R.id.txt_rank_slower_time);
        this.txt_rank_current_time = findViewById(R.id.txt_rank_current_time);
        this.txt_rank_faster_time = findViewById(R.id.txt_rank_faster_time);
        this.txt_rank_first_time = findViewById(R.id.txt_rank_first_time);

        this.txt_rank_slower_number = findViewById(R.id.txt_rank_slower_number);
        this.txt_rank_current_number = findViewById(R.id.txt_rank_current_number);
        this.txt_rank_faster_number = findViewById(R.id.txt_rank_faster_number);
        this.txt_rank_first_number = findViewById(R.id.txt_rank_first_number);

        this.txt_level_title = findViewById(R.id.txt_level_title);

        this.sv_level_preview = findViewById(R.id.sv_preview);

        this.btn_back = findViewById(R.id.btn_back);
        this.btn_back.setOnClickListener(v -> {
            this.click.start();
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

        this.currentLevel = 0;
        this.levels[this.currentLevel].callOnClick();
    }

    public void previewMap(int index, GameMap map) {
        this.levels[this.currentLevel].setBackground(ContextCompat.getDrawable(this, R.drawable.default_button));
        this.currentLevel = index;
        this.levels[this.currentLevel].setBackground(ContextCompat.getDrawable(this, R.drawable.default_button_pressed));

        this.txt_level_title.setText(this.levels[this.currentLevel].getText());

        this.sv_level_preview.loadMap(map);

        loadLeaderboards(index + 1);
    }

    private void loadLeaderboards(int id) {
        this.levelDataSource.setLevel("level" + id);
        this.levelDataSource.openReadable();

        String currentTime = this.levelDataSource.getTimeByUUID(this.uuidDataSource.getUUID());
        int currentNumber = currentTime == null ? -1 : this.levelDataSource.getRankByUUID(this.uuidDataSource.getUUID());

        // reset leaderboard colors
        this.rl_rank_first.setBackgroundColor(Color.TRANSPARENT);
        this.txt_rank_first_time.setTextColor(Color.WHITE);
        this.txt_rank_first_number.setTextColor(Color.WHITE);

        this.rl_rank_faster.setBackgroundColor(Color.TRANSPARENT);
        this.txt_rank_faster_time.setTextColor(Color.WHITE);
        this.txt_rank_faster_number.setTextColor(Color.WHITE);

        this.rl_rank_faster.setBackgroundColor(Color.TRANSPARENT);
        this.txt_rank_current_time.setTextColor(Color.WHITE);
        this.txt_rank_current_number.setTextColor(Color.WHITE);

        switch (currentNumber) {
            case -1: {
                String firstTime = this.levelDataSource.getTimeByRank(1);
                this.txt_rank_first_time.setText(firstTime == null ? "HH:MM:SS.mmm" : firstTime);
                this.txt_rank_first_number.setText("1");

                String secondTime = this.levelDataSource.getTimeByRank(2);
                this.txt_rank_faster_time.setText(secondTime == null ? "HH:MM:SS.mmm" : secondTime);
                this.txt_rank_faster_number.setText("2");

                String thirdTime = this.levelDataSource.getTimeByRank(3);
                this.txt_rank_current_time.setText(thirdTime == null ? "HH:MM:SS.mmm" : thirdTime);
                this.txt_rank_current_number.setText("3");

                String fourthTime = this.levelDataSource.getTimeByRank(4);
                this.txt_rank_slower_time.setText(fourthTime == null ? "HH:MM:SS.mmm" : fourthTime);
                this.txt_rank_slower_number.setText("4");
                break;
            }
            case 1: {
                this.txt_rank_first_time.setText(currentTime);
                this.txt_rank_first_number.setText("1");
                this.rl_rank_first.setBackgroundColor(Color.WHITE);
                this.txt_rank_first_time.setTextColor(Color.BLACK);
                this.txt_rank_first_number.setTextColor(Color.BLACK);

                String secondTime = this.levelDataSource.getTimeByRank(2);
                this.txt_rank_faster_time.setText(secondTime == null ? "HH:MM:SS.mmm" : secondTime);
                this.txt_rank_faster_number.setText("2");

                String thirdTime = this.levelDataSource.getTimeByRank(3);
                this.txt_rank_current_time.setText(thirdTime == null ? "HH:MM:SS.mmm" : thirdTime);
                this.txt_rank_current_number.setText("3");

                String fourthTime = this.levelDataSource.getTimeByRank(4);
                this.txt_rank_slower_time.setText(fourthTime == null ? "HH:MM:SS.mmm" : fourthTime);
                this.txt_rank_slower_number.setText("4");
                break;
            }
            case 2: {
                String firstTime = this.levelDataSource.getTimeByRank(1);
                this.txt_rank_first_time.setText(firstTime);
                this.txt_rank_first_number.setText("1");

                this.txt_rank_faster_time.setText(currentTime);
                this.txt_rank_faster_number.setText("2");
                this.rl_rank_faster.setBackgroundColor(Color.WHITE);
                this.txt_rank_faster_time.setTextColor(Color.BLACK);
                this.txt_rank_faster_number.setTextColor(Color.BLACK);

                String thirdTime = this.levelDataSource.getTimeByRank(3);
                this.txt_rank_current_time.setText(thirdTime == null ? "HH:MM:SS.mmm" : thirdTime);
                this.txt_rank_current_number.setText("3");

                String fourthTime = this.levelDataSource.getTimeByRank(4);
                this.txt_rank_slower_time.setText(fourthTime == null ? "HH:MM:SS.mmm" : fourthTime);
                this.txt_rank_slower_number.setText("4");
                break;
            }
            default: {
                String firstTime = this.levelDataSource.getTimeByRank(1);
                this.txt_rank_first_time.setText(firstTime);
                this.txt_rank_first_number.setText("1");

                String fasterTime = this.levelDataSource.getTimeByRank(currentNumber - 1);
                this.txt_rank_faster_time.setText(fasterTime);
                this.txt_rank_faster_number.setText(String.valueOf(currentNumber - 1));

                this.txt_rank_current_time.setText(currentTime);
                this.txt_rank_current_number.setText(String.valueOf(currentNumber));
                this.rl_rank_current.setBackgroundColor(Color.WHITE);
                this.txt_rank_current_time.setTextColor(Color.BLACK);
                this.txt_rank_current_number.setTextColor(Color.BLACK);

                String slowerTime = this.levelDataSource.getTimeByRank(currentNumber + 1);
                this.txt_rank_slower_time.setText(slowerTime == null ? "HH:MM:SS.mmm" : slowerTime);
                this.txt_rank_slower_number.setText(String.valueOf(currentNumber + 1));
                break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.sv_level_preview.onResume();
        this.levels[this.currentLevel].callOnClick();
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
