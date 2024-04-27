package com.avasaysayava.bagrutproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.database.UUIDDataSource;
import com.avasaysayava.bagrutproject.util.Util;

public class StartActivity extends Activity {
    private RadioGroup optionsMenu;
    private Button btn_go;
    private MediaPlayer click, load;
    private UUIDDataSource uuidDataSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());

        this.uuidDataSource = new UUIDDataSource(this);
        Util.updateUUID(this.uuidDataSource);

        this.click = MediaPlayer.create(this, R.raw.click);
        this.click.setVolume(.1f, .1f);
        this.load = MediaPlayer.create(this, R.raw.level_start);
        this.load.start();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.start_activity);

        this.optionsMenu = findViewById(R.id.rg_options_menu);
        this.optionsMenu.setOnCheckedChangeListener((rg, id) -> this.click.start());

        this.btn_go = findViewById(R.id.btn_go);
        this.btn_go.setOnClickListener(v -> {
            if (this.optionsMenu.getCheckedRadioButtonId() == R.id.rb_levels) {
                startActivity(new Intent(this, MenuActivity.class), savedInstanceState);
            }
        });

        Log.d(getClass().getSimpleName(), "UUID: " + this.uuidDataSource.getUUID());
    }
}
