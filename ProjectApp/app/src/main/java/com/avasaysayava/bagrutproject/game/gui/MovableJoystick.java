package com.avasaysayava.bagrutproject.game.gui;

import android.view.MotionEvent;

import com.avasaysayava.bagrutproject.game.Level;

public class MovableJoystick extends Joystick {
    public MovableJoystick(Level level, float x, float y, float radius) {
        super(level, x, y, radius);
    }

    @Override
    protected void arrange() {
        if (getDistance() > getRadius()) {
            this.outerX += (float) (getCos() * (getDistance() - getRadius()) / 2);
            this.outerY += (float) (getSin() * (getDistance() - getRadius()) / 2);
        }
    }

    @Override
    public void enable(MotionEvent event) {
        super.enable(event);

        this.outerX = this.innerX = event.getX();
        this.outerY = this.innerY = event.getY();
    }
}
