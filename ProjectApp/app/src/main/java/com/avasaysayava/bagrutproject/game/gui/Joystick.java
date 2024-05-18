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

    protected void arrange() {
        if (getDistance() > this.radius) {
            this.innerX = (float) ((this.innerX - this.outerX) * (this.radius / getDistance()) + this.outerX);
            this.innerY = (float) ((this.innerY - this.outerY) * (this.radius / getDistance()) + this.outerY);
        }
    }

    public double getDistance(MotionEvent event) {
        return Math.sqrt(((this.outerX - event.getX()) * (this.outerX - event.getX())) + ((this.outerY - event.getY()) * (this.outerY - event.getY())));
    }

    public double getDistance() {
        return Math.sqrt(((this.outerX - this.innerX) * (this.outerX - this.innerX)) + ((this.outerY - this.innerY) * (this.outerY - this.innerY)));
    }

    public double getPercentage() {
        return getDistance() / getRadius();
    }

    public double getRadians() {
        double dx = this.innerX - this.outerX;
        double dy = this.innerY - this.outerY;
        return Math.atan2(dy, dx);
    }

    public double getAngle() {
        return getRadians() * 180 / Math.PI;
    }

    public double getSin() {
        return getDistance() == 0 ? 0 : (this.innerY - this.outerY) / getDistance();
    }

    public double getCos() {
        return getDistance() == 0 ? 0 : (this.innerX - this.outerX) / getDistance();
    }

    public boolean isPressed(MotionEvent event) {
        return getDistance(event) <= this.radius;
    }

    public void enable(MotionEvent ignored) {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void reset() {
        this.innerX = this.outerX = this.X;
        this.innerY = this.outerY = this.Y;
    }

    public void press(MotionEvent event) {
        float dx = event.getX() - this.outerX;
        float dy = event.getY() - this.outerY;
        this.innerX = this.outerX + dx;
        this.innerY = this.outerY + dy;

        arrange();
    }

    public float getRadius() {
        return radius;
    }
}
