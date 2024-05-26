package com.avasaysayava.bagrutproject.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.avasaysayava.bagrutproject.R;

@SuppressWarnings("FieldCanBeLocal")
public class CreditsActivity extends Activity {
    private Button btn_back;
    private MediaPlayer click, load;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create MediaPlayer for click sound with 10% volume
        this.click = MediaPlayer.create(this, R.raw.click);
        this.click.setVolume(.1f, .1f);

        // Create MediaPlayer for level start sound and play it
        this.load = MediaPlayer.create(this, R.raw.level_start);
        this.load.start();

        // Set the layout of the activity
        setContentView(R.layout.credits_activity);

        // Find the button with ID "btn_back" from the layout
        this.btn_back = findViewById(R.id.btn_back);

        // Set on-click listener for the back button
        this.btn_back.setOnClickListener(v -> {
            // Play click sound when button pressed
            this.click.start();

            // Finish the activity (go back to start screen)
            super.onBackPressed();
        });
    }
}