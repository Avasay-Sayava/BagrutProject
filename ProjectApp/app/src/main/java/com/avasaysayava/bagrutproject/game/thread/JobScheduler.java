package com.avasaysayava.bagrutproject.game.thread;

import android.graphics.Canvas;

import com.avasaysayava.bagrutproject.game.Game;
import com.avasaysayava.bagrutproject.util.struct.SizedDeque;

public class JobScheduler extends PeriodicThread {
    private final int UPS;
    private final Game game;
    // ups stands for updates per-second,
    // and fps stands for frames per-second
    private final SizedDeque<Long> upsDeque, fpsDeque;
    private double lastUPS, lastFPS;
    private int updates = 0, frames = 0;
    private long t_start;

    public JobScheduler(Game game, int ups) {
        this.UPS = ups;
        this.game = game;
        this.upsDeque = new SizedDeque<>(this.UPS);
        this.fpsDeque = new SizedDeque<>(this.UPS);
    }

    public double getAvgUPS() {
        if (this.upsDeque.size() > 1)
            return this.upsDeque.size() * 1E9 / (this.upsDeque.getLast() - this.upsDeque.getFirst());
        return this.UPS;
    }

    public double getAvgFPS() {
        if (this.fpsDeque.size() > 1)
            return this.fpsDeque.size() * 1E9 / (this.fpsDeque.getLast() - this.fpsDeque.getFirst());
        return this.UPS;
    }

    public double getFramedUPS() {
        return this.lastUPS;
    }

    public double getFramedFPS() {
        return this.lastFPS;
    }

    @Override
    protected void periodic() {
        Canvas canvas = null;

        // Trying to update & render the level
        try {
            canvas = this.game.getHolder().lockCanvas();
            synchronized (this.game.getHolder()) {
                this.game.update();

                // for every update, update the ups
                this.updates++;
                this.upsDeque.addLast(System.nanoTime());

                // drawing happens here,
                // because it needs to be synced with our surfaceHolder
                this.game.draw(canvas);
            }
        } catch (IllegalArgumentException ignored) {
        } finally {
            if (canvas != null) {
                try {
                    // for each draw, update the fps
                    this.frames++;
                    this.game.getHolder().unlockCanvasAndPost(canvas);

                    this.fpsDeque.addLast(System.nanoTime());
                } catch (Exception ignored) {
                }
            }
        }

        // pause for not exceeding the UPS value
        long t_elapsed = System.nanoTime() - this.t_start;
        // t stands for time
        long t_sleep = (long) (1E9 * this.updates / this.UPS) - t_elapsed;
        if (t_sleep > 0) {
            try {
                sleep(t_sleep / 1_000_000, (int) (t_sleep % 1_000_000));
            } catch (InterruptedException ignored) {
            }
        }

        // skip frames for keeping up with the UPS value
        while (t_sleep < 0 && ++this.updates < this.UPS) {
            this.game.update();
            this.upsDeque.addLast(System.nanoTime());
            t_elapsed = System.nanoTime() - this.t_start;
            t_sleep = (long) (1E9 * this.updates / this.UPS) - t_elapsed;
        }

        // calculate the average ups and fps
        t_elapsed = System.nanoTime() - this.t_start;

        // updates every second
        if (t_elapsed >= 1E9) {
            this.lastUPS = this.updates * 1E9 / t_elapsed;
            this.lastFPS = this.frames * 1E9 / t_elapsed;
            this.t_start = System.nanoTime();
            this.updates = this.frames = 0;
        }
    }
}
