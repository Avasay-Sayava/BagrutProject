package com.avasaysayava.bagrutproject.game.graphic.tileset;

import android.content.Context;
import android.graphics.PointF;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.game.collision.Collision;
import com.avasaysayava.bagrutproject.game.collision.Path;
import com.avasaysayava.bagrutproject.game.graphic.Tile;
import com.avasaysayava.bagrutproject.game.graphic.TileType;

public class PropsTileSet extends TileSet {
    public PropsTileSet(Context context) {
        super(context, R.drawable.props);
    }

    @Override
    protected Tile[] getTileArray() {
        Tile[] tiles = new Tile[103];
        Collision reg, top, down;

        tiles[0] = getTile(3, 60, 7, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.WALL);

        tiles[1] = getTile(4, 59, 9, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.WALL);

        tiles[2] = getTile(36, 59, 10, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(3, 32), new PointF(3, 22), new PointF(29, 22), new PointF(29, 32)));
        tiles[3] = getTile(3, 62, 12, 0, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        tiles[4] = getTile(4, 92, 14, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(Path.polygon(new PointF(3, 14), new PointF(28, 14), new PointF(28, 28), new PointF(3, 28)));
        tiles[5] = getTile(3, 28, 7, 1, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(32, 28), new PointF(4, 28), new PointF(4, 14), new PointF(32, 14)));
        tiles[6] = getTile(4, 27, 9, 1, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 28), new PointF(27, 28), new PointF(27, 14), new PointF(-1, 14)));
        tiles[7] = getTile(36, 27, 10, 1, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(3, -1), new PointF(3, 31), new PointF(29, 31), new PointF(29, -1)));
        tiles[8] = getTile(3, 30, 12, 1, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        tiles[9] = getTile(0, 60, 14, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(32, 9), new PointF(29, 12), new PointF(29, 23), new PointF(32, 26)));
        tiles[10] = getTile(29, 28, 13, 2, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 10), new PointF(1, 8), new PointF(29, 8), new PointF(32, 11)),
                new Path(new PointF(-1, 25), new PointF(2, 28), new PointF(28, 28), new PointF(32, 24)));
        tiles[11] = getTile(29, 28, 14, 2, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 10), new PointF(1, 12), new PointF(1, 23), new PointF(-1, 25)));
        tiles[12] = getTile(29, 28, 15, 2, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(0, 0), new PointF(31, 31)));
        tiles[13] = getTile(0, 0, 3, 8, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(31, -1), new PointF(31, 31)));
        tiles[14] = getTile(0, 0, 3, 9, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        return tiles;
    }
}
