package com.avasaysayava.bagrutproject.game.thread;

public abstract class GameThread extends Thread {
    private boolean running;

    public GameThread() {
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
        } catch (InterruptedException ignored) {
        }
    }

    protected abstract void periodic();
}
