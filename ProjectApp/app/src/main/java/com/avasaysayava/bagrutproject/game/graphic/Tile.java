package com.avasaysayava.bagrutproject.game.graphic;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.avasaysayava.bagrutproject.game.audio.TileSound;
import com.avasaysayava.bagrutproject.game.collision.Collision;
import com.avasaysayava.bagrutproject.game.entity.Entity;
import com.avasaysayava.bagrutproject.game.graphic.tileset.TileSet;
import com.avasaysayava.bagrutproject.game.property.Property;
import com.avasaysayava.bagrutproject.util.struct.LineF;

public class Tile {
    public static final Tile empty = null;
    private final int z;
    private final int down, left;
    private final int scale;
    private final TileSound type;
    private final TileSet tileSet;
    private final Rect bounds;
    private final Collision collision;
    private final Collision collisionTop;
    private final Collision collisionDown;
    private Property property;
    private int id;
    private boolean drawnLazy;

    public Tile(Tile tile) {
        this.id = tile.getId();
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
        this.property = tile.getProperty();
    }

    private Tile(Tile tile, int newScale, int newZ) {
        this.id = tile.getId();
        this.z = newZ;
        this.left = tile.getLeft();
        this.down = tile.getDown();
        this.tileSet = tile.getTileSet();
        this.bounds = tile.getBounds();
        this.collision = tile.getCollision();
        this.collisionTop = tile.getCollisionTop();
        this.collisionDown = tile.getCollisionDown();
        this.type = tile.getType();
        this.property = tile.getProperty();
        this.scale = newScale;
    }

    public Tile(TileSet tileSet, Rect bounds, Collision collision, Collision collisionTop, Collision collisionDown, int left, int down, TileSound type) {
        this.id = -1;
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
        this.property = null;
    }

    public Property getProperty() {
        return this.property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void draw(Canvas canvas, float x, float y, Paint paint) {
        draw(canvas, x, y, 0, 0, paint);
    }

    public void draw(Canvas canvas, float x, float y, double extraWidth, double extraHeight, Paint paint) {
        canvas.drawBitmap(this.tileSet.getBitmap(), this.bounds, new Rect((int) x, (int) (y - extraHeight * this.scale), (int) (x + getWidth() + extraWidth * this.scale), (int) (y + getHeight())), paint);
    }

    public LineF getIntersector(Entity e) {
        if (e.getZ() == this.z) {
            return this.collision != Collision.empty ? this.collision.getIntersector(e.getCollision()) : null;
        } else if (e.getZ() > this.z) {
            return this.collisionTop != Collision.empty ? this.collisionTop.getIntersector(e.getCollision()) : null;
        } else {
            return this.collisionDown != Collision.empty ? this.collisionDown.getIntersector(e.getCollision()) : null;
        }
    }

    public void move(float x, float y) {
        if (this.collision != Collision.empty) this.collision.move(x, y);
        if (this.collisionTop != Collision.empty) this.collisionTop.move(x, y);
        if (this.collisionDown != Collision.empty) this.collisionDown.move(x, y);
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

    public TileSound getType() {
        return this.type;
    }

    public Tile withScale(int scale) {
        return new Tile(this, scale, this.z);
    }

    public Tile withZ(int z) {
        return new Tile(this, this.scale, z);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDrawnLazy(boolean b) {
        this.drawnLazy = b;
    }

    public boolean getDrawnLazy() {
        return this.drawnLazy;
    }

    public Tile clone() {
        return this.withZ(this.z);
    }
}
