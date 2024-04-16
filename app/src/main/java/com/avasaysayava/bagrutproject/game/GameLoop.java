package com.avasaysayava.bagrutproject.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.avasaysayava.bagrutproject.game.structure.SizedDeque;

public class GameLoop extends Thread {
    private final int UPS;
    private final Game game;
    private final SurfaceHolder surfaceHolder;
    // ups stands for updates per-second,
    // and fps stands for frames per-second
    private final SizedDeque<Long> upsDeque, fpsDeque;
    private volatile boolean running; // volatile: var change immediately visible to all threads
    private double fixedUPS, fixedFPS;

    public GameLoop(Game game, SurfaceHolder surfaceHolder, int ups) {
        this.running = false;
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

    public double getFixedUPS() {
        return this.fixedUPS;
    }

    public double getFixedFPS() {
        return this.fixedFPS;
    }

    @Override
    public void start() {
        super.start();

        this.running = true;
    }

    @Override
    public void run() {
        super.run();

        int ups = 0, fps = 0;

        // t stands for time
        long t_start, t_elapsed, t_sleep;

        t_start = System.nanoTime();

        // game loop
        Canvas canvas = null;

        while (this.running) {
            // Trying to update & render the game
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (this.surfaceHolder) {
                    this.game.update();

                    // for every update, update the ups
                    ups++;
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
                        this.surfaceHolder.unlockCanvasAndPost(canvas);
                        fps++;

                        this.fpsDeque.addLast(System.nanoTime());
                    } catch (Exception ignored) {
                    }
                }
            }

            // pause for not exceeding the UPS value
            t_elapsed = System.nanoTime() - t_start;
            t_sleep = (long) (1E9 * ups / this.UPS) - t_elapsed;
            if (t_sleep > 0) {
                Util.sleep(t_sleep);
            }

            // skip frames for keeping up with the UPS value
            while (t_sleep < 0 && ++ups < this.UPS) {
                this.game.update();
                this.upsDeque.addLast(System.nanoTime());
                t_elapsed = System.nanoTime() - t_start;
                t_sleep = (long) (1E9 * ups / this.UPS) - t_elapsed;
            }

            // calculate the average ups and fps
            t_elapsed = System.nanoTime() - t_start;

            // updates every second
            if (t_elapsed >= 1E9) {
                t_start = System.nanoTime();
                this.fixedUPS = ups * 1E9 / t_elapsed;
                this.fixedFPS = fps * 1E9 / t_elapsed;
                ups = fps = 0;
            }
        }

        super.interrupt();
    }

    public void pause() {
        this.running = false;
        try {
            join();
        } catch (InterruptedException ignored) {}
    }
}
