package com.avasaysayava.bagrutproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.database.datasource.UUIDDataSource;
import com.avasaysayava.bagrutproject.util.Util;

@SuppressWarnings("FieldCanBeLocal")
public class StartActivity extends Activity {
    private RadioGroup rg_options_menu;
    private Button btn_go;
    private MediaPlayer click, load;
    private UUIDDataSource uuidDataSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.uuidDataSource = new UUIDDataSource(this);
        Util.updateUUID(this.uuidDataSource);

        this.click = MediaPlayer.create(this, R.raw.click);
        this.click.setVolume(.1f, .1f);
        this.load = MediaPlayer.create(this, R.raw.level_start);
        this.load.start();

        setContentView(R.layout.start_activity);

        this.rg_options_menu = findViewById(R.id.rg_options_menu);
        this.rg_options_menu.setOnCheckedChangeListener((rg, id) -> this.click.start());

        this.btn_go = findViewById(R.id.btn_go);
        this.btn_go.setOnClickListener(v -> {
            if (this.rg_options_menu.getCheckedRadioButtonId() == R.id.rb_levels) {
                startActivity(new Intent(this, MenuActivity.class), savedInstanceState);
            } else if (this.rg_options_menu.getCheckedRadioButtonId() == R.id.rb_how_to_play) {
                startActivity(new Intent(this, HowToPlayActivity.class), savedInstanceState);
            } else if (this.rg_options_menu.getCheckedRadioButtonId() == R.id.rb_credits) {
                startActivity(new Intent(this, CreditsActivity.class), savedInstanceState);
            }
        });
    }
}
