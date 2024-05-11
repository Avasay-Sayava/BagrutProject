package com.avasaysayava.bagrutproject.game.thread;

import android.util.Log;

public abstract class PeriodicThread extends Thread {
    private boolean running;

    public PeriodicThread() {
        super();
        setName(getClass().getName());
    }

    @Override
    public void start() {
        super.start();
        this.running = true;
    }

    @Override
    public void run() {
        super.run();

        while (this.running) {
            periodic();
        }

        super.interrupt();
    }

    public void pause() {
        this.running = false;
        try {
            join();
        } catch (InterruptedException e) {
        }
    }

    protected abstract void periodic();
}
