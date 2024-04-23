package com.avasaysayava.bagrutproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class StartActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent gameIntent = new Intent(this, GameActivity.class);
        Intent mainMenuIntent = new Intent(this, MainMenuActivity.class);
        startActivity(gameIntent, savedInstanceState);
    }
}
