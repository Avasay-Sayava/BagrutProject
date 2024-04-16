package com.avasaysayava.bagrutproject.game.entity;

import android.graphics.Canvas;
import android.graphics.Point;

import com.avasaysayava.bagrutproject.game.Game;
import com.avasaysayava.bagrutproject.game.collision.Collision;

public abstract class Entity {
    protected Game game;
    protected float x, y;
    protected double mass;

    public Entity(Game game, double mass, float x, float y) {
        this.game = game;
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

    public abstract void draw(Canvas canvas);
    public abstract void update();
    public abstract Collision getCollision();
    public abstract int getLeft();
    public abstract int getDown();
    public abstract Shadow getShadow();
    public abstract int getZ();
    public abstract Point getPoint();
}
