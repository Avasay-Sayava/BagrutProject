package com.avasaysayava.bagrutproject.game.thread;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.avasaysayava.bagrutproject.game.Game;
import com.avasaysayava.bagrutproject.game.structure.SizedDeque;

public class GameScheduler extends GameThread {
    private final int UPS;
    private final Game game;
    private final SurfaceHolder surfaceHolder;
    // ups stands for updates per-second,
    // and fps stands for frames per-second
    private final SizedDeque<Long> upsDeque, fpsDeque;
    private double lastUPS, lastFPS;
    private int ups = 0, fps = 0;
    private long t_start;

    public GameScheduler(Game game, SurfaceHolder surfaceHolder, int ups) {
        this.UPS = ups;
        this.game = game;
        this.surfaceHolder = surfaceHolder;
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

        // Trying to update & render the game
        try {
            canvas = this.surfaceHolder.lockCanvas();
            synchronized (this.surfaceHolder) {
                this.game.update();

                // for every update, update the ups
                this.ups++;
                this.upsDeque.addLast(System.nanoTime());

                // drawing happens here,
                // because it needs to be synced with our surfaceHolder
                this.game.draw(canvas);
            }
        } catch (IllegalArgumentException e) {
            Log.e("game/thread", e.getMessage(), e);
        } finally {
            if (canvas != null) {
                try {
                    // for each draw, update the fps
                    this.fps++;
                    this.surfaceHolder.unlockCanvasAndPost(canvas);

                    this.fpsDeque.addLast(System.nanoTime());
                } catch (Exception e) {
                    Log.e("game/thread", e.getMessage(), e);
                }
            }
        }

        // pause for not exceeding the UPS value
        long t_elapsed = System.nanoTime() - this.t_start;
        // t stands for time
        long t_sleep = (long) (1E9 * this.ups / this.UPS) - t_elapsed;
        if (t_sleep > 0) {
            try {
                sleep(t_sleep / 1_000_000, (int) (t_sleep % 1_000_000));
            } catch (InterruptedException e) {
                Log.e("game/thread", e.getMessage(), e);
            }
        }

        // skip frames for keeping up with the UPS value
        while (t_sleep < 0 && ++this.ups < this.UPS) {
            this.game.update();
            this.upsDeque.addLast(System.nanoTime());
            t_elapsed = System.nanoTime() - this.t_start;
            t_sleep = (long) (1E9 * this.ups / this.UPS) - t_elapsed;
        }

        // calculate the average ups and fps
        t_elapsed = System.nanoTime() - this.t_start;

        // updates every second
        if (t_elapsed >= 1E9) {
            this.lastUPS = this.ups * 1E9 / t_elapsed;
            this.lastFPS = this.fps * 1E9 / t_elapsed;
            this.t_start = System.nanoTime();
            this.ups = this.fps = 0;
        }
    }
}
