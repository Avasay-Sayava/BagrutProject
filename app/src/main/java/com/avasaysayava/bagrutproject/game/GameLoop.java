package com.avasaysayava.bagrutproject.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.avasaysayava.bagrutproject.game.structure.SizedDeque;

public class GameLoop extends Thread {
    private final int UPS;
    private final Game game;
    private final SurfaceHolder surfaceHolder;
    // ups stands for updates per-second,
    // and fps stands for frames per-second
    private final SizedDeque<Long> upsDeque, fpsDeque;
    private boolean running;
    private double fixedUPS, fixedFPS;

    public GameLoop(Game game, SurfaceHolder surfaceHolder, int ups) {
        super("GameLoop");

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
            Log.d("game/loop", "instance start");

            // Trying to update & render the game
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (this.surfaceHolder) {
                    Log.d("game/loop", "update start");
                    this.game.update();
                    Log.d("game/loop", "update end");

                    // for every update, update the ups
                    ups++;
                    this.upsDeque.addLast(System.nanoTime());

                    // drawing happens here,
                    // because it needs to be synced with our surfaceHolder
                    Log.d("game/loop", "draw start");
                    this.game.draw(canvas);
                }
            } catch (IllegalArgumentException ignored) {
            } finally {
                if (canvas != null) {
                    try {
                        Log.d("game/loop", "draw end");

                        // for each draw, update the fps
                        fps++;
                        this.surfaceHolder.unlockCanvasAndPost(canvas);

                        this.fpsDeque.addLast(System.nanoTime());
                    } catch (Exception ignored) {
                    }
                }
            }

            // pause for not exceeding the UPS value
            t_elapsed = System.nanoTime() - t_start;
            t_sleep = (long) (1E9 * ups / this.UPS) - t_elapsed;
            if (t_sleep > 0) {
                Log.d("game/loop", "sleep start (" + t_sleep + "ns)");
                try {
                    super.sleep(t_sleep / 1_000_000, (int) (t_sleep % 1_000_000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("game/loop", "sleep end");
            }

            // skip frames for keeping up with the UPS value
            while (t_sleep < 0 && ++ups < this.UPS) {
                Log.d("game/loop", "no draw update start");
                this.game.update();
                Log.d("game/loop", "no draw update end");
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

            Log.d("game/loop", "instance end");
        }

        Log.d("game/loop", "interrupt");
        super.interrupt();
    }

    public void pause() {
        this.running = false;
        try {
            join();
        } catch (InterruptedException ignored) {
        }
    }
}
