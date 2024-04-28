package com.avasaysayava.bagrutproject.game.graphic.tileset;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.avasaysayava.bagrutproject.game.audio.TileSound;
import com.avasaysayava.bagrutproject.game.collision.Collision;
import com.avasaysayava.bagrutproject.game.graphic.Tile;

public abstract class TileSet {
    private final Bitmap bitmap;
    private final Tile[] tiles;

    public TileSet(Context context, int id) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), id, options);
        this.tiles = getTileArray();
        for (int i = 0; i < this.tiles.length; i++) {
            if (this.tiles[i] != null) this.tiles[i].setId(i);
        }
    }

    protected abstract Tile[] getTileArray();

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public Tile getTile(int left, int down, int x, int y, int width, int height, Collision collision, Collision collisionTop, Collision collisionDown, TileSound type) {
        return new Tile(this, new Rect(x, y, x + width, y + height), collision, collisionTop, collisionDown, left, down, type);
    }

    public Tile getTile(int left, int down, int x, int y, int width, int height, int tileSize, Collision collision, Collision collisionTop, Collision collisionDown, TileSound type) {
        return getTile(left, down, tileSize * x, tileSize * y, tileSize * width, tileSize * height, collision, collisionTop, collisionDown, type);
    }

    public Tile getTile(int index) {
        return this.tiles[index];
    }
}
