package com.avasaysayava.bagrutproject.game.thread;

import android.media.MediaPlayer;
import android.util.Log;

import androidx.annotation.RawRes;

import com.avasaysayava.bagrutproject.game.Game;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class AudioScheduler extends GameThread {
    private final Game game;
    private final Queue<Integer> queue;
    private MediaPlayer mp;

    public AudioScheduler(Game game) {
        this.mp = new MediaPlayer();
        this.queue = new LinkedList<>();
        this.game = game;
    }

    @Override
    protected void periodic() {
        if (!this.queue.isEmpty()) {
            try {
                play();
            } catch (IOException e) {
                Log.e("game/thread", e.getMessage(), e);
            }
        } else {
            try {
                sleep(1_000 / this.game.UPS);
            } catch (InterruptedException e) {
                Log.e("game/thread", e.getMessage(), e);
            }
        }
    }

    private void play() throws IOException {
        if (!this.mp.isPlaying()) {
            this.mp.release();
            this.mp = MediaPlayer.create(this.game.getContext(), this.queue.remove());
            this.mp.setVolume(.1f, .1f);
            this.mp.start();
        }
    }

    public void scheduleFile(@RawRes int raw) {
        this.queue.add(raw);
    }
}
