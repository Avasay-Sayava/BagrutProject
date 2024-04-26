package com.avasaysayava.bagrutproject.game.entity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.avasaysayava.bagrutproject.game.Level;
import com.avasaysayava.bagrutproject.game.collision.Collision;
import com.avasaysayava.bagrutproject.game.graphic.Tile;

public class Shadow extends Entity {
    private final Tile tile;
    private final Entity entity;

    public Shadow(Level level, Entity entity, Tile tile) {
        super(level, 0, entity.getX(), entity.getY());
        this.tile = tile;
        this.entity = entity;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAlpha(25);
        this.tile.withScale(this.level.SCALE).draw(canvas, this.x * this.level.SCALE, this.y * this.level.SCALE, paint);
    }

    @Override
    public void update() {
        move(this.entity.getX(), this.entity.getY());
    }

    @Override
    public Collision getCollision() {
        return null;
    }

    @Override
    public int getLeft() {
        return this.tile.getLeft();
    }

    @Override
    public int getDown() {
        return this.tile.getDown();
    }

    @Override
    public Shadow getShadow() {
        return null;
    }

    @Override
    public int getZ() {
        return this.entity.getZ();
    }

    @Override
    public Point getPoint() {
        return this.entity.getPoint();
    }
}
