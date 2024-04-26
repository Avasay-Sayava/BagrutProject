package com.avasaysayava.bagrutproject.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.Static;
import com.avasaysayava.bagrutproject.database.UsersTableHelper;

public class RegisterActivity extends Activity {
    private Button btn_back, btn_submit;
    private EditText etxt_username, etxt_password;
    private UsersTableHelper usersTableHelper;
    private MediaPlayer click, load;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.usersTableHelper = new UsersTableHelper(this);

        this.click = MediaPlayer.create(this, R.raw.click);
        this.click.setVolume(.1f, .1f);
        this.load = MediaPlayer.create(this, R.raw.level_start);
        this.load.start();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.register_activity);

        this.btn_back = findViewById(R.id.btn_back);
        this.btn_back.setOnClickListener(v -> {
            this.click.start();
            super.onBackPressed();
        });

        this.etxt_username = findViewById(R.id.etxt_username);
        this.etxt_password = findViewById(R.id.etxt_password);

        this.btn_submit = findViewById(R.id.btn_submit);
        this.btn_submit.setOnClickListener(v -> {
            this.click.start();

            String username = this.etxt_username.getText().toString();
            String password = this.etxt_password.getText().toString();

            username = username.replaceAll("'", "");
            password = password.replaceAll("'", "");

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, R.string.invalid_info, Toast.LENGTH_SHORT).show();
                return;
            }

            if (!this.usersTableHelper.doesExist(username, password)) {
                this.usersTableHelper.addData(username, password);
            }

            Static.userid = this.usersTableHelper.getId(username, password);
            super.onBackPressed();
        });
    }
}
