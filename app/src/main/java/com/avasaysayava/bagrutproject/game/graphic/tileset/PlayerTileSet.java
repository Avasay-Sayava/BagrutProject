package com.avasaysayava.bagrutproject.game.graphic.tileset;

import android.content.Context;
import android.graphics.PointF;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.game.collision.Collision;
import com.avasaysayava.bagrutproject.game.collision.Path;
import com.avasaysayava.bagrutproject.game.graphic.Tile;
import com.avasaysayava.bagrutproject.game.graphic.TileType;

public class PlayerTileSet extends TileSet {
    public PlayerTileSet(Context context) {
        super(context, R.drawable.player);
    }

    @Override
    protected Tile[] getTileArray() {
        Tile[] tiles = new Tile[5];
        Collision reg = new Collision(Path.polygon(new PointF(5, 33), new PointF(25, 33), new PointF(25, 47), new PointF(5, 47)));
        tiles[0] = getTile(5, 48, 0, 0, 2, 3, 16, reg, Collision.empty, Collision.empty, TileType.ENTITY);
        tiles[1] = getTile(5, 48, 2, 0, 2, 3, 16, reg, Collision.empty, Collision.empty, TileType.ENTITY);
        tiles[2] = getTile(5, 48, 4, 0, 2, 3, 16, reg, Collision.empty, Collision.empty, TileType.ENTITY);
        tiles[3] = getTile(5, 48, 6, 0, 2, 3, 16, reg, Collision.empty, Collision.empty, TileType.ENTITY);
        tiles[4] = getTile(5, 44, 8, 0, 2, 3, 16, reg, Collision.empty, Collision.empty, TileType.ENTITY);
        return tiles;
    }
}
