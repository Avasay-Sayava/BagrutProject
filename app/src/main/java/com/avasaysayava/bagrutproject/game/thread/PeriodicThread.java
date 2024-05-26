package com.avasaysayava.bagrutproject.game.thread;

public abstract class PeriodicThread extends Thread {
    private boolean running;

    public PeriodicThread() {
        super();

        // sets the thread name to the extending class's name
        setName(getClass().getSimpleName());
    }

    @Override
    public void start() {
        super.start();
        this.running = true;
    }

    @Override
    public void run() {
        super.run();

        // runs the periodic() method periodically until
        // it is interrupted by the method pause()
        while (this.running) {
            periodic();
        }

        super.interrupt();
    }

    // pauses periodic execution of the method periodic()
    public void pause() {
        this.running = false;
        try {
            join();
        } catch (InterruptedException ignored) {
        }
    }

    protected abstract void periodic();
}
