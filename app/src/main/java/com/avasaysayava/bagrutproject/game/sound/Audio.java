package com.avasaysayava.bagrutproject.game.sound;

import android.content.Context;
import android.media.MediaPlayer;

public class Audio {
    private final Context context;
    private MediaPlayer mp;

    public Audio(Context context) {
        this.context = context;
    }

    public void setFile(int id) {
        this.mp = MediaPlayer.create(this.context, id);
    }

    public void play() {
        this.mp.start();
    }

    public void loop() {
        this.mp.setLooping(true);
    }

    public void stop() {
        this.mp.stop();
    }

    public void kill() {
        this.mp.release();
    }
}
