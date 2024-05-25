package com.avasaysayava.bagrutproject.game.entity;

import android.graphics.Canvas;
import android.graphics.Point;

import com.avasaysayava.bagrutproject.game.Level;
import com.avasaysayava.bagrutproject.game.collision.Collision;

public abstract class Entity {
    protected Level level;
    protected float x, y;
    protected int z;
    protected double mass;
    private int glyphs;

    public Entity(Level level, double mass, float x, float y, int z) {
        this.level = level;
        this.mass = mass;
        this.glyphs = 0;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void move(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void translate(float dx, float dy) {
        this.x += dx;
        this.y += dy;
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

    public abstract void draw(Canvas canvas);

    public abstract void update();

    public abstract Collision getCollision();

    public abstract int getLeft();

    public abstract int getDown();

    public abstract Shadow getShadow();

    public int getZ() {
        return this.z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public abstract Point getCords();
}
