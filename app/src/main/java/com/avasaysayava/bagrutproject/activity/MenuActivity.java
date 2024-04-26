package com.avasaysayava.bagrutproject.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.Static;
import com.avasaysayava.bagrutproject.game.LevelPreview;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.FloorMap;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.GameMap;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.GroundMap;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.Level1Map;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.StructuresMap;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.WallsMap;

public class MenuActivity extends Activity {
    private Button levels[], btn_play, btn_back;
    private LevelPreview sv_level_preview;
    private TextView txt_level_title;
    private MediaPlayer click, load;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("activity/levels-menu", "onCreate");

        this.click = MediaPlayer.create(this, R.raw.click);
        this.click.setVolume(.1f, .1f);
        this.load = MediaPlayer.create(this, R.raw.level_start);
        this.load.start();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.menu_activity);

        this.sv_level_preview = findViewById(R.id.sv_level_preview);

        this.txt_level_title = findViewById(R.id.txt_level_title);

        this.btn_back = findViewById(R.id.btn_back);
        this.btn_back.setOnClickListener(v -> {
            this.click.start();
            super.onBackPressed();
        });

        this.btn_play = findViewById(R.id.btn_play);
        this.btn_play.setOnClickListener(v -> {
            if (Static.currentMap != null) {
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
            Static.currentMap = new GroundMap(this.sv_level_preview, 0, 0);
            this.click.start();
            previewMap(Static.currentMap, 1);
        });

        this.levels[1].setOnClickListener(v -> {
            Static.currentMap = new FloorMap(this.sv_level_preview, 0, 0);
            this.click.start();
            previewMap(Static.currentMap, 2);
        });

        this.levels[2].setOnClickListener(v -> {
            Static.currentMap = new StructuresMap(this.sv_level_preview, 0, 0);
            this.click.start();
            previewMap(Static.currentMap, 3);
        });

        this.levels[3].setOnClickListener(v -> {
            Static.currentMap = new WallsMap(this.sv_level_preview, -100, -100);
            this.click.start();
            previewMap(Static.currentMap, 4);
        });

        this.levels[4].setOnClickListener(v -> {
            Static.currentMap = new Level1Map(this.sv_level_preview, 0, 0);
            this.click.start();
            previewMap(Static.currentMap, 5);
        });

        this.levels[5].setOnClickListener(v -> {
            Static.currentMap = null;
            this.click.start();
            previewMap(Static.currentMap, 6);
        });

        this.levels[6].setOnClickListener(v -> {
            Static.currentMap = null;
            this.click.start();
            previewMap(Static.currentMap, 7);
        });

        this.levels[7].setOnClickListener(v -> {
            Static.currentMap = null;
            this.click.start();
            previewMap(Static.currentMap, 8);
        });

        this.levels[8].setOnClickListener(v -> {
            Static.currentMap = null;
            this.click.start();
            previewMap(Static.currentMap, 9);
        });

        this.levels[0].callOnClick();
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public void previewMap(GameMap map, int id) {
        this.sv_level_preview.loadMap(map);
        this.txt_level_title.setText("Level " + String.format("%02d", id));
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.sv_level_preview.onResume();
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
