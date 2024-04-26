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
    protected float outerX, outerY;
    protected float innerX, innerY;
    private float X, Y;
    private boolean enabled;
    private boolean inverted;

    public Joystick(Level level, float x, float y, float radius) {
        this.X = this.innerX = this.outerX = x;
        this.Y = this.innerY = this.outerY = y;
        this.radius = radius;
        this.enabled = false;
        this.inverted = false;

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
        canvas.drawCircle(this.outerX,
                this.outerY,
                this.radius,
                this.outerPaint);

        // draw the inner circle
        canvas.drawCircle(this.innerX,
                this.innerY,
                this.radius / 2,
                this.innerPaint);
    }

    protected void arrange() {
        if (getDistance() > this.radius) {
            this.innerX = (float) ((this.innerX - this.outerX) * (this.radius / getDistance()) + this.outerX);
            this.innerY = (float) ((this.innerY - this.outerY) * (this.radius / getDistance()) + this.outerY);
        }
    }

    public double getDistance(MotionEvent event) {
        return Math.sqrt(((this.outerX - event.getX()) * (this.outerX - event.getX()))
                + ((this.outerY - event.getY()) * (this.outerY - event.getY())));
    }

    public double getPercentage(MotionEvent event) {
        return getDistance(event) / getRadius();
    }

    public double getRadians(MotionEvent event) {
        double dx = event.getX() - this.outerX;
        double dy = event.getY() - this.outerY;
        return this.inverted ? Math.PI - Math.atan2(dy, dx) : Math.atan2(dy, dx);
    }

    public double getAngle(MotionEvent event) {
        return getRadians(event) * 180 / Math.PI;
    }

    public double getSin(MotionEvent event) {
        return getDistance() == 0 ? 0 : this.inverted ? -(event.getY() - this.outerY) / getDistance(event) : (event.getY() - this.outerY) / getDistance(event);
    }

    public double getCos(MotionEvent event) {
        return getDistance() == 0 ? 0 : this.inverted ? -(event.getX() - this.outerX) / getDistance(event) : (event.getX() - this.outerX) / getDistance(event);
    }

    public double getDistance() {
        return Math.sqrt(((this.outerX - this.innerX) * (this.outerX - this.innerX))
                + ((this.outerY - this.innerY) * (this.outerY - this.innerY)));
    }

    public double getPercentage() {
        return getDistance() / getRadius();
    }

    public double getRadians() {
        double dx = this.innerX - this.outerX;
        double dy = this.innerY - this.outerY;
        return this.inverted ? Math.PI - Math.atan2(dy, dx) : Math.atan2(dy, dx);
    }

    public double getAngle() {
        return getRadians() * 180 / Math.PI;
    }

    public double getSin() {
        return getDistance() == 0 ? 0 : this.inverted ? -(this.innerY - this.outerY) / getDistance() : (this.innerY - this.outerY) / getDistance();
    }

    public double getCos() {
        return getDistance() == 0 ? 0 : this.inverted ? -(this.innerX - this.outerX) / getDistance() : (this.innerX - this.outerX) / getDistance();
    }

    public boolean isPressed(MotionEvent event) {
        return this.getDistance(event) <= this.radius;
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

    public void invert() {
        this.inverted = !this.inverted;
    }

    public boolean isInverted() {
        return this.inverted;
    }

    public float getRadius() {
        return radius;
    }

    public void move(float x, float y) {
        this.innerX += x - this.X;
        this.outerX += x - this.X;
        this.X = x;

        this.innerY += y - this.Y;
        this.outerY += y - this.Y;
        this.Y = y;
    }
}
