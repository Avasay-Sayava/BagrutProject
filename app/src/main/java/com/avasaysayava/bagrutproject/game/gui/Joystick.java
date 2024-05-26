package com.avasaysayava.bagrutproject.game.gui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import androidx.core.content.ContextCompat;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.game.Level;
import com.avasaysayava.bagrutproject.util.Util;

public class Joystick {
    private final float radius;
    private final Paint innerPaint;
    private final Paint outerPaint;
    private final float X, Y;
    protected float outerX, outerY;
    protected float innerX, innerY;
    private boolean enabled;

    public Joystick(Level level, float x, float y, float radius) {
        this.X = this.innerX = this.outerX = x;
        this.Y = this.innerY = this.outerY = y;
        this.radius = radius;
        this.enabled = false;

        // initialize the Paints
        this.innerPaint = new Paint();
        this.innerPaint.setColor(ContextCompat.getColor(level.getContext(), R.color.White));
        this.innerPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        this.outerPaint = new Paint();
        this.outerPaint.setColor(Util.withAlpha(ContextCompat.getColor(level.getContext(), R.color.White), 0x80));
        this.outerPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public void draw(Canvas canvas) {
        // draw the outer circle
        canvas.drawCircle(this.outerX, this.outerY, this.radius, this.outerPaint);

        // draw the inner circle
        canvas.drawCircle(this.innerX, this.innerY, this.radius / 2, this.innerPaint);
    }

    // disallows the joystick handle to move outside the joystick bounds
    protected void arrange() {
        if (getDistance() > this.radius) {
            this.innerX = (float) ((this.innerX - this.outerX) * (this.radius / getDistance()) + this.outerX);
            this.innerY = (float) ((this.innerY - this.outerY) * (this.radius / getDistance()) + this.outerY);
        }
    }

    // returns the distance between the joystick's center and the touch event
    public double getDistance(MotionEvent event) {
        return Math.sqrt(((this.outerX - event.getX()) * (this.outerX - event.getX())) + ((this.outerY - event.getY()) * (this.outerY - event.getY())));
    }

    // returns the distance between the joystick's center and the handle's center
    public double getDistance() {
        return Math.sqrt(((this.outerX - this.innerX) * (this.outerX - this.innerX)) + ((this.outerY - this.innerY) * (this.outerY - this.innerY)));
    }

    // returns the percentage of the distance between the joystick's center and the handle's center
    public double getPercentage() {
        return Util.bound(0, getDistance() / getRadius(), 1);
    }

    // calculates the angle (radians) between the joystick's center,
    // the handle's center, and the x-axis and returns it
    public double getRadians() {
        double dx = this.innerX - this.outerX;
        double dy = this.innerY - this.outerY;
        return Math.atan2(dy, dx);
    }

    // calculates the angle (degrees) between the joystick's center,
    // the handle's center, and the x-axis and returns it
    public double getAngle() {
        return getRadians() * 180 / Math.PI;
    }

    // calculates the sin of getRadians()
    public double getSin() {
        return getDistance() == 0 ? 0 : (this.innerY - this.outerY) / getDistance();
    }

    // calculates the cos of getRadians()
    public double getCos() {
        return getDistance() == 0 ? 0 : (this.innerX - this.outerX) / getDistance();
    }

    // returns if the touch event is pressing the joystick within it's bounds
    public boolean isPressed(MotionEvent event) {
        return getDistance(event) <= this.radius;
    }

    // enables the joystick
    public void enable(MotionEvent ignored) {
        this.enabled = true;
    }

    // disables the joystick
    public void disable() {
        this.enabled = false;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    // resets the joystick to its original position
    public void reset() {
        this.innerX = this.outerX = this.X;
        this.innerY = this.outerY = this.Y;
    }

    // moving the joystick handle to the touch event position
    public void press(MotionEvent event) {
        this.innerX = event.getX();
        this.innerY = event.getY();

        arrange();
    }

    public float getRadius() {
        return radius;
    }
}
