package com.avasaysayava.bagrutproject.game.graphic.tileset;

import android.content.Context;
import android.graphics.PointF;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.game.audio.TileSound;
import com.avasaysayava.bagrutproject.game.collision.Collision;
import com.avasaysayava.bagrutproject.game.collision.Path;
import com.avasaysayava.bagrutproject.game.graphic.Tile;

public class StructuresTileSet extends TileSet {
    public StructuresTileSet(Context context) {
        super(context, R.drawable.structures);
    }

    @Override
    protected Tile[] getTileArray() {
        Tile[] tiles = new Tile[54];
        Collision reg;

        tiles[0] = getTile(3, 60, 7, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[1] = getTile(4, 47, 9, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[2] = getTile(36, 47, 10, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(3, 32), new PointF(3, 22), new PointF(29, 22), new PointF(29, 32)));
        tiles[3] = getTile(3, 23, 12, 0, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[4] = getTile(4, 92, 14, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(Path.polygon(new PointF(3, 14), new PointF(28, 14), new PointF(28, 28), new PointF(3, 28)));
        tiles[5] = getTile(3, 28, 7, 1, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(32, 28), new PointF(4, 28), new PointF(4, 12), new PointF(32, 12)));
        tiles[6] = getTile(4, 15, 9, 1, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 28), new PointF(27, 28), new PointF(27, 12), new PointF(-1, 12)));
        tiles[7] = getTile(36, 15, 10, 1, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(3, -1), new PointF(3, 31), new PointF(29, 31), new PointF(29, -1)));
        tiles[8] = getTile(3, -12, 12, 1, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[9] = getTile(0, 60, 14, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[10] = getTile(3, 92, 7, 2, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[11] = getTile(0, 37, 9, 2, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[12] = getTile(-32, 37, 10, 2, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(32, 9), new PointF(29, 12), new PointF(29, 23), new PointF(32, 26)));
        tiles[13] = getTile(29, 13, 13, 2, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 10), new PointF(1, 8), new PointF(29, 8), new PointF(32, 11)), new Path(new PointF(-1, 25), new PointF(2, 28), new PointF(28, 28), new PointF(32, 24)));
        tiles[14] = getTile(29, 13, 14, 2, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 10), new PointF(1, 12), new PointF(1, 23), new PointF(-1, 25)));
        tiles[15] = getTile(29, 13, 15, 2, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[16] = getTile(3, 60, 7, 3, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(32, 4), new PointF(0, 4), new PointF(0, 26), new PointF(32, 26)));
        tiles[17] = getTile(0, 5, 9, 3, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 4), new PointF(31, 4), new PointF(31, 26), new PointF(-1, 26)));
        tiles[18] = getTile(-32, 5, 10, 3, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(3, 32), new PointF(3, 22), new PointF(29, 22), new PointF(29, 32)));
        tiles[19] = getTile(3, 63, 12, 3, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[20] = getTile(5, 46, 14, 3, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(Path.polygon(new PointF(3, 14), new PointF(28, 14), new PointF(28, 28), new PointF(3, 28)));
        tiles[21] = getTile(3, 28, 7, 4, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(3, -1), new PointF(3, 31), new PointF(29, 31), new PointF(29, -1)));
        tiles[22] = getTile(3, 31, 12, 4, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(Path.polygon(new PointF(5, 13), new PointF(26, 13), new PointF(26, 26), new PointF(5, 26)));
        tiles[23] = getTile(5, 14, 14, 4, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(Path.polygon(new PointF(3, 28), new PointF(3, 31), new PointF(29, 31), new PointF(29, 28)));
        tiles[24] = getTile(3, 29, 3, 5, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[25] = getTile(3, 28, 8, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.GLYPH);

        reg = new Collision(new Path(new PointF(0, 32), new PointF(0, 13), new PointF(31, 13), new PointF(31, 32)));
        tiles[26] = getTile(0, 14, 9, 5, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[27] = getTile(0, 68, 11, 5, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[28] = getTile(0, 0, 7, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.GLYPH);

        reg = new Collision(new Path(new PointF(0, -1), new PointF(0, 24), new PointF(31, 24), new PointF(31, -1)));
        tiles[29] = getTile(0, -18, 9, 6, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[30] = getTile(0, 36, 11, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[31] = getTile(0, 36, 13, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(Path.polygon(new PointF(0, 28), new PointF(0, 31), new PointF(26, 31), new PointF(26, 28)));
        tiles[32] = getTile(0, 29, 3, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[33] = getTile(1, 43, 7, 7, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[34] = getTile(1, 41, 9, 7, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(Path.polygon(new PointF(0, 3), new PointF(0, 25), new PointF(31, 25), new PointF(31, 3)));
        tiles[35] = getTile(1, 4, 11, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(Path.polygon(new PointF(0, 3), new PointF(0, 25), new PointF(31, 25), new PointF(31, 3)));
        tiles[36] = getTile(1, 4, 13, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(Path.polygon(new PointF(1, 10), new PointF(30, 10), new PointF(30, 23), new PointF(1, 23)));
        tiles[37] = getTile(1, 11, 7, 8, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(Path.polygon(new PointF(1, 8), new PointF(30, 8), new PointF(30, 23), new PointF(1, 23)));
        tiles[38] = getTile(1, 9, 9, 8, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[39] = getTile(0, 18, 11, 8, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[40] = getTile(0, 18, 12, 8, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[41] = getTile(0, 18, 13, 8, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[42] = getTile(3, 12, 7, 9, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[43] = getTile(1, 0, 11, 9, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[44] = getTile(1, 0, 12, 9, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[45] = getTile(1, 0, 13, 9, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(Path.polygon(new PointF(3, 12), new PointF(28, 12), new PointF(28, 23), new PointF(3, 23)));
        tiles[46] = getTile(3, 13, 7, 10, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[47] = getTile(1, 0, 11, 10, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[48] = getTile(1, 0, 12, 10, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[49] = getTile(1, 0, 13, 10, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(33, 18), new PointF(17, 18), new PointF(5, 31)));
        tiles[50] = getTile(3, 41, 13, 11, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(10, 20), new PointF(14, 20), new PointF(25, 31)));
        tiles[51] = getTile(3, 41, 14, 11, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(4, 0), new PointF(4, 8), new PointF(19, 23), new PointF(32, 23)));
        tiles[52] = getTile(3, 9, 13, 12, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 23), new PointF(11, 23), new PointF(26, 8), new PointF(26, 0)));
        tiles[53] = getTile(3, 9, 14, 12, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        return tiles;
    }
}
