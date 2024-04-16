package com.avasaysayava.bagrutproject.game.graphic.tileset;

import android.content.Context;
import android.graphics.PointF;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.game.collision.Collision;
import com.avasaysayava.bagrutproject.game.collision.Path;
import com.avasaysayava.bagrutproject.game.graphic.Tile;
import com.avasaysayava.bagrutproject.game.graphic.TileType;

public class WallsTileSet extends TileSet {
    public WallsTileSet(Context context) {
        super(context, R.drawable.walls);
    }

    @Override
    protected Tile[] getTileArray() {
        Tile[] tiles = new Tile[66];
        Collision reg, down;

        reg = new Collision(new Path(new PointF(7, 32), new PointF(7, 7), new PointF(32, 7)));
        down = new Collision(new Path(new PointF(2, 32), new PointF(2, 31), new PointF(32, 31)));
        tiles[0] = getTile(0, 7, 1, 1, 1, 1, 32, reg, Collision.empty, down, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 7), new PointF(32, 7)));
        down = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[1] = getTile(0, 7, 2, 1, 1, 1, 32, reg, Collision.empty, down, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 7), new PointF(24, 7), new PointF(24, 32)));
        down = new Collision(new Path(new PointF(-1, 31), new PointF(29, 31), new PointF(29, 32)));
        tiles[2] = getTile(0, 7, 3, 1, 1, 1, 32, reg, Collision.empty, down, TileType.WALL);

        reg = new Collision(new Path(new PointF(32, 3), new PointF(24, 3), new PointF(24, 32)));
        tiles[3] = getTile(24, 31, 4, 1, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        tiles[4] = getTile(0, 31, 5, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.WALL);

        tiles[5] = getTile(0, 31, 6, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.WALL);

        tiles[6] = getTile(0, 31, 7, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 3), new PointF(7, 3), new PointF(7, 32)));
        tiles[7] = getTile(0, 31, 8, 1, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(7, -1), new PointF(7, 32)));
        down = new Collision(new Path(new PointF(2, -1), new PointF(2, 32)));
        tiles[8] = getTile(0, 31, 9, 1, 1, 1, 32, reg, Collision.empty, down, TileType.WALL);

        reg = new Collision(new Path(new PointF(24, -1), new PointF(24, 32)));
        down = new Collision(new Path(new PointF(29, -1), new PointF(29, 32)));
        tiles[9] = getTile(24, 31, 10, 1, 1, 1, 32, reg, Collision.empty, down, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 7), new PointF(32, 7)));
        down = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[10] = getTile(0, 7, 12, 1, 1, 1, 32, reg, Collision.empty, down, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 7), new PointF(32, 7)));
        down = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[11] = getTile(0, 7, 13, 1, 1, 1, 32, reg, Collision.empty, down, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 7), new PointF(32, 7)));
        down = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[12] = getTile(0, 7, 14, 1, 1, 1, 32, reg, Collision.empty, down, TileType.WALL);

        reg = new Collision(new Path(new PointF(7, -1), new PointF(7, 32)));
        down = new Collision(new Path(new PointF(2, -1), new PointF(2, 32)));
        tiles[13] = getTile(0, 31, 1, 2, 1, 1, 32, reg, Collision.empty, down, TileType.WALL);

        reg = new Collision(new Path(new PointF(24, -1), new PointF(24, 32)));
        down = new Collision(new Path(new PointF(29, -1), new PointF(29, 32)));
        tiles[14] = getTile(24, 31, 3, 2, 1, 1, 32, reg, Collision.empty, down, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[15] = getTile(0, 31, 5, 2, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[16] = getTile(0, 31, 6, 2, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[17] = getTile(0, 31, 7, 2, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(7, -1), new PointF(7, 32)));
        down = new Collision(new Path(new PointF(2, -1), new PointF(2, 32)));
        tiles[18] = getTile(0, 31, 9, 2, 1, 1, 32, reg, Collision.empty, down, TileType.WALL);

        reg = new Collision(new Path(new PointF(24, -1), new PointF(24, 32)));
        down = new Collision(new Path(new PointF(29, -1), new PointF(29, 32)));
        tiles[19] = getTile(24, 31, 10, 2, 1, 1, 32, reg, Collision.empty, down, TileType.WALL);

        reg = new Collision(new Path(new PointF(7, 32), new PointF(7, 7), new PointF(32, 7)));
        down = new Collision(new Path(new PointF(2, 32), new PointF(2, 31), new PointF(32, 31)));
        tiles[20] = getTile(0, 7, 12, 2, 1, 1, 32, reg, Collision.empty, down, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 7), new PointF(24, 7), new PointF(24, 32)));
        down = new Collision(new Path(new PointF(-1, 31), new PointF(29, 31), new PointF(29, 32)));
        tiles[21] = getTile(0, 7, 13, 2, 1, 1, 32, reg, Collision.empty, down, TileType.WALL);

        reg = new Collision(new Path(new PointF(2, -1), new PointF(2, 32)));
        tiles[22] = getTile(2, 31, 1, 3, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        tiles[23] = getTile(0, 31, 2, 3, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(29, -1), new PointF(29, 32)));
        tiles[24] = getTile(0, 31, 3, 3, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(7, -1), new PointF(7, 32)));
        down = new Collision(new Path(new PointF(2, -1), new PointF(2, 32)));
        tiles[25] = getTile(0, 31, 9, 3, 1, 1, 32, reg, Collision.empty, down, TileType.WALL);

        reg = new Collision(new Path(new PointF(24, -1), new PointF(24, 32)));
        down = new Collision(new Path(new PointF(29, -1), new PointF(29, 32)));
        tiles[26] = getTile(24, 31, 10, 3, 1, 1, 32, reg, Collision.empty, down, TileType.WALL);

        reg = new Collision(new Path(new PointF(2, -1), new PointF(2, 32)));
        tiles[27] = getTile(2, 31, 12, 3, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(29, -1), new PointF(29, 32)));
        tiles[28] = getTile(0, 31, 13, 3, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(2, -1), new PointF(2, 31), new PointF(32, 31)));
        tiles[29] = getTile(2, 31, 1, 4, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[30] = getTile(0, 31, 2, 4, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(29, 31), new PointF(29, -1)));
        tiles[31] = getTile(0, 31, 3, 4, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(24, -1), new PointF(24, 7), new PointF(32, 7)));
        down = new Collision(new Path(new PointF(29, -1), new PointF(29, 31), new PointF(32, 31)));
        tiles[32] = getTile(24, 7, 4, 4, 1, 1, 32, reg, Collision.empty, down, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 7), new PointF(7, 7), new PointF(7, -1)));
        down = new Collision(new Path(new PointF(2, -1), new PointF(2, 31), new PointF(-1, 31)));
        tiles[33] = getTile(0, 7, 8, 4, 1, 1, 32, reg, Collision.empty, down, TileType.WALL);

        reg = new Collision(new Path(new PointF(2, -1), new PointF(2, 31), new PointF(32, 31)));
        tiles[34] = getTile(2, 31, 12, 4, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(29, 31), new PointF(29, -1)));
        tiles[35] = getTile(0, 31, 13, 4, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        tiles[36] = getTile(0, 31, 1, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.WALL);

        tiles[37] = getTile(0, 31, 2, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.WALL);

        tiles[38] = getTile(0, 31, 3, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.WALL);

        tiles[39] = getTile(0, 31, 4, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.WALL);

        tiles[40] = getTile(0, 31, 6, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(7, -1), new PointF(7, 0), new PointF(32, 0)));
        tiles[41] = getTile(0, 31, 8, 6, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 0), new PointF(32, 0)));
        tiles[42] = getTile(0, 31, 9, 6, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 0), new PointF(32, 0)));
        tiles[43] = getTile(0, 31, 10, 6, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 0), new PointF(32, 0)));
        tiles[44] = getTile(0, 31, 11, 6, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 0), new PointF(32, 0)));
        tiles[45] = getTile(0, 31, 12, 6, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(24, -1), new PointF(24, 0), new PointF(-1, 0)));
        tiles[46] = getTile(0, 31, 13, 6, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[47] = getTile(0, 31, 1, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[48] = getTile(0, 31, 2, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[49] = getTile(0, 31, 3, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[50] = getTile(0, 31, 4, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[51] = getTile(0, 31, 6, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(7, -1), new PointF(7, 0), new PointF(32, 0)));
        tiles[52] = getTile(0, 31, 8, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 0), new PointF(32, 0)));
        tiles[53] = getTile(0, 31, 9, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 0), new PointF(32, 0)));
        tiles[54] = getTile(0, 31, 10, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 0), new PointF(32, 0)));
        tiles[55] = getTile(0, 31, 11, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(-1, 0), new PointF(32, 0)));
        tiles[56] = getTile(0, 31, 12, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        reg = new Collision(new Path(new PointF(24, -1), new PointF(24, 0), new PointF(-1, 0)));
        tiles[57] = getTile(0, 31, 13, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileType.WALL);

        return tiles;
    }
}
