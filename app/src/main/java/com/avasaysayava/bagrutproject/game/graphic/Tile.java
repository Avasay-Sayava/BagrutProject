package com.avasaysayava.bagrutproject.game.graphic;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.avasaysayava.bagrutproject.game.collision.Collision;
import com.avasaysayava.bagrutproject.game.entity.Entity;
import com.avasaysayava.bagrutproject.game.graphic.tileset.TileSet;

public class Tile {
    public static final Tile empty = null;

    protected final int z;
    protected final int down, left;
    protected final int scale;
    protected final TileType type;
    protected final TileSet tileSet;
    protected final Rect bounds;
    protected final Collision collision;
    protected final Collision collisionTop;
    protected final Collision collisionDown;

    public Tile(Tile tile) {
        this.z = tile.getZ();
        this.left = tile.getLeft();
        this.down = tile.getDown();
        this.tileSet = tile.getTileSet();
        this.bounds = tile.getBounds();
        this.scale = tile.getScale();
        this.collision = tile.getCollision();
        this.collisionTop = tile.getCollisionTop();
        this.collisionDown = tile.getCollisionDown();
        this.type = tile.getType();
    }

    private Tile(Tile tile, int newScale, int newZ) {
        this.z = newZ;
        this.left = tile.getLeft();
        this.down = tile.getDown();
        this.tileSet = tile.getTileSet();
        this.bounds = tile.getBounds();
        this.collision = tile.getCollision();
        this.collisionTop = tile.getCollisionTop();
        this.collisionDown = tile.getCollisionDown();
        this.type = tile.getType();
        this.scale = newScale;
    }

    public Tile(TileSet tileSet, Rect bounds, Collision collision, Collision collisionTop, Collision collisionDown, int left, int down, TileType type) {
        this.z = 0;
        this.left = left;
        this.down = down;
        this.collision = collision;
        this.collisionTop = collisionTop;
        this.collisionDown = collisionDown;
        this.tileSet = tileSet;
        this.bounds = bounds;
        this.scale = 1;
        this.type = type;
    }

    public void draw(Canvas canvas, float x, float y, Paint paint) {
        draw(canvas, x, y, 0, 0, paint);
    }

    public void draw(Canvas canvas, float x, float y, double extraWidth, double extraHeight, Paint paint) {
        canvas.drawBitmap(this.tileSet.getBitmap(),
                this.bounds,
                new Rect((int) x,
                        (int) (y - extraHeight * this.scale),
                        (int) (x + getWidth() + extraWidth * this.scale),
                        (int) (y + getHeight())),
                paint);
    }

    public boolean collides(Entity e) {
        if (e.getZ() == this.z) {
            return this.collision != Collision.empty && this.collision.doesIntersect(e.getCollision());
        } else if (e.getZ() > this.z) {
            return this.collisionTop != Collision.empty && this.collisionTop.doesIntersect(e.getCollision());
        } else {
            return this.collisionDown != Collision.empty && this.collisionDown.doesIntersect(e.getCollision());
        }
    }

    public boolean collides(Collision collision) {
        return this.collision.doesIntersect(collision);
    }

    public void move(float x, float y) {
        if (this.collision != Collision.empty)
            this.collision.move(x, y);
        if (this.collisionTop != Collision.empty)
            this.collisionTop.move(x, y);
        if (this.collisionDown != Collision.empty)
            this.collisionDown.move(x, y);
    }

    public int getWidth() {
        return this.scale * this.bounds.width();
    }

    public int getHeight() {
        return this.scale * this.bounds.height();
    }

    public TileSet getTileSet() {
        return this.tileSet;
    }

    public Rect getBounds() {
        return this.bounds;
    }

    public int getScale() {
        return this.scale;
    }

    public Collision getCollision() {
        return this.collision;
    }

    public Collision getCollisionTop() {
        return this.collisionTop;
    }

    public Collision getCollisionDown() {
        return this.collisionDown;
    }

    public int getDown() {
        return this.down;
    }

    public int getLeft() {
        return this.left;
    }

    public int getZ() {
        return this.z;
    }

    public TileType getType() {
        return this.type;
    }

    public Tile withScale(int scale) {
        return new Tile(this, scale, this.z);
    }

    public Tile withZ(int z) {
        return new Tile(this, this.scale, z);
    }
}
