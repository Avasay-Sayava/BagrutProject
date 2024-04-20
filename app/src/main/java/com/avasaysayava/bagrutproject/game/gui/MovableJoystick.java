package com.avasaysayava.bagrutproject.game.gui;

import com.avasaysayava.bagrutproject.game.Game;

public class MovableJoystick extends Joystick {
    public MovableJoystick(Game game, float x, float y, float radius) {
        super(game, x, y, radius);
    }

    @Override
    protected void arrange() {
        if (getDistance() > getRadius()) {
            this.outerX += (float) ((isInverted() ? -1 : 1) * getCos() * (getDistance() - getRadius()) / 2);
            this.outerY += (float) ((isInverted() ? -1 : 1) * getSin() * (getDistance() - getRadius()) / 2);
        }
    }
}
