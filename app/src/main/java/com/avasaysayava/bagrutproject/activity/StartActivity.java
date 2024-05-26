package com.avasaysayava.bagrutproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import com.avasaysayava.bagrutproject.R;

@SuppressWarnings("FieldCanBeLocal")
public class StartActivity extends Activity {
    private RadioGroup rg_options_menu;
    private Button btn_go;
    private MediaPlayer click, load;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create media players and set click sound volume
        this.click = MediaPlayer.create(this, R.raw.click);
        this.click.setVolume(.1f, .1f);
        this.load = MediaPlayer.create(this, R.raw.level_start);
        this.load.start(); // Play start sound on activity creation

        // Set layout for the activity
        setContentView(R.layout.start_activity);

        // Find UI elements from the layout
        this.rg_options_menu = findViewById(R.id.rg_options_menu);
        this.btn_go = findViewById(R.id.btn_go);

        // Set on-checked-change listener for radio group (play click sound on change)
        this.rg_options_menu.setOnCheckedChangeListener((rg, id) -> this.click.start());

        // Set on-click listener for go button
        this.btn_go.setOnClickListener(v -> {
            // Check which radio button is selected
            int checkedRadioButtonId = this.rg_options_menu.getCheckedRadioButtonId();

            if (checkedRadioButtonId == R.id.rb_levels) {
                // Start MenuActivity if levels button is selected
                startActivity(new Intent(this, MenuActivity.class), savedInstanceState);
            } else if (checkedRadioButtonId == R.id.rb_how_to_play) {
                // Start HowToPlayActivity if how to play button is selected
                startActivity(new Intent(this, HowToPlayActivity.class), savedInstanceState);
            } else if (checkedRadioButtonId == R.id.rb_credits) {
                // Start CreditsActivity if credits button is selected
                startActivity(new Intent(this, CreditsActivity.class), savedInstanceState);
            }
        });
    }
}