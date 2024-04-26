package com.avasaysayava.bagrutproject.game.entity;

import android.graphics.Canvas;
import android.graphics.Point;

import com.avasaysayava.bagrutproject.game.Level;
import com.avasaysayava.bagrutproject.game.collision.Collision;

public abstract class Entity {
    protected Level level;
    protected float x, y;
    protected double mass;
    private int glyphs = 1;

    public Entity(Level level, double mass, float x, float y) {
        this.level = level;
        this.mass = mass;
        this.x = x;
        this.y = y;
    }

    public void move(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void translate(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }

    public double getDistance(float x, float y) {
        return Math.sqrt((this.x - x) * (this.x - x) + (this.y - y) * (this.y - y));
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void addGlyphs(int count) {
        this.glyphs += count;
    }

    public int getGlyphs(int count) {
        int out = Math.min(count, this.glyphs);
        this.glyphs -= out;
        return out;
    }

    public int getGlyphCount() {
        return this.glyphs;
    }

    public abstract void draw(Canvas canvas);

    public abstract void update();

    public abstract Collision getCollision();

    public abstract int getLeft();

    public abstract int getDown();

    public abstract Shadow getShadow();

    public abstract int getZ();

    public abstract Point getPoint();
}
