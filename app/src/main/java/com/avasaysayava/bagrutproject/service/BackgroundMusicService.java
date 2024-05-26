package com.avasaysayava.bagrutproject.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.avasaysayava.bagrutproject.R;

public class BackgroundMusicService extends Service {
    private MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // looping the music at 50% volume
        this.player = MediaPlayer.create(this, R.raw.background1);
        this.player.setVolume(.5f, .5f);
        this.player.setLooping(true);
        this.player.start();

        return Service.START_STICKY;
    }

    @Override
    // stops the music
    public void onDestroy() {
        super.onDestroy();
        this.player.stop();
        this.player.release();
    }
}
