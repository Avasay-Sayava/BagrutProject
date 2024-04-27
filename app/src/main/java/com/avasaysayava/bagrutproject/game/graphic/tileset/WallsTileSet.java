package com.avasaysayava.bagrutproject.game.graphic.tileset;

import android.content.Context;
import android.graphics.PointF;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.game.audio.TileSound;
import com.avasaysayava.bagrutproject.game.collision.Collision;
import com.avasaysayava.bagrutproject.game.collision.Path;
import com.avasaysayava.bagrutproject.game.graphic.Tile;

public class WallsTileSet extends TileSet {
    public WallsTileSet(Context context) {
        super(context, R.drawable.structures);
    }

    @Override
    protected Tile[] getTileArray() {
        Tile[] tiles = new Tile[103];
        Collision reg, top, down;

        reg = new Collision(new Path(new PointF(7, 32), new PointF(7, 7), new PointF(32, 7)));
        down = new Collision(new Path(new PointF(2, 32), new PointF(2, 31), new PointF(32, 31)));
        tiles[0] = getTile(0, 7, 1, 1, 1, 1, 32, reg, Collision.empty, down, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 7), new PointF(32, 7)));
        down = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[1] = getTile(0, 7, 2, 1, 1, 1, 32, reg, Collision.empty, down, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 7), new PointF(24, 7), new PointF(24, 32)));
        down = new Collision(new Path(new PointF(-1, 31), new PointF(29, 31), new PointF(29, 32)));
        tiles[2] = getTile(0, 7, 3, 1, 1, 1, 32, reg, Collision.empty, down, TileSound.WALL);

        reg = new Collision(new Path(new PointF(32, 3), new PointF(24, 3), new PointF(24, 32)));
        tiles[3] = getTile(24, 7, 4, 1, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[4] = getTile(0, 31, 5, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[5] = getTile(0, 31, 6, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[6] = getTile(0, 31, 7, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 3), new PointF(7, 3), new PointF(7, 32)));
        tiles[7] = getTile(0, 7, 8, 1, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(7, -1), new PointF(7, 32)));
        down = new Collision(new Path(new PointF(2, -1), new PointF(2, 32)));
        tiles[8] = getTile(0, 0, 9, 1, 1, 1, 32, reg, Collision.empty, down, TileSound.WALL);

        reg = new Collision(new Path(new PointF(24, -1), new PointF(24, 32)));
        down = new Collision(new Path(new PointF(29, -1), new PointF(29, 32)));
        tiles[9] = getTile(24, 0, 10, 1, 1, 1, 32, reg, Collision.empty, down, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 7), new PointF(32, 7)));
        down = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[10] = getTile(0, 7, 12, 1, 1, 1, 32, reg, Collision.empty, down, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 7), new PointF(32, 7)));
        down = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[11] = getTile(0, 7, 13, 1, 1, 1, 32, reg, Collision.empty, down, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 7), new PointF(32, 7)));
        down = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[12] = getTile(0, 7, 14, 1, 1, 1, 32, reg, Collision.empty, down, TileSound.WALL);

        reg = new Collision(new Path(new PointF(7, -1), new PointF(7, 32)));
        down = new Collision(new Path(new PointF(2, -1), new PointF(2, 32)));
        tiles[13] = getTile(0, 0, 1, 2, 1, 1, 32, reg, Collision.empty, down, TileSound.WALL);

        reg = new Collision(new Path(new PointF(24, -1), new PointF(24, 32)));
        down = new Collision(new Path(new PointF(29, -1), new PointF(29, 32)));
        tiles[14] = getTile(24, 0, 3, 2, 1, 1, 32, reg, Collision.empty, down, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[15] = getTile(0, 31, 5, 2, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[16] = getTile(0, 31, 6, 2, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[17] = getTile(0, 31, 7, 2, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(7, -1), new PointF(7, 32)));
        down = new Collision(new Path(new PointF(2, -1), new PointF(2, 32)));
        tiles[18] = getTile(0, 0, 9, 2, 1, 1, 32, reg, Collision.empty, down, TileSound.WALL);

        reg = new Collision(new Path(new PointF(24, -1), new PointF(24, 32)));
        down = new Collision(new Path(new PointF(29, -1), new PointF(29, 32)));
        tiles[19] = getTile(24, 0, 10, 2, 1, 1, 32, reg, Collision.empty, down, TileSound.WALL);

        reg = new Collision(new Path(new PointF(7, 32), new PointF(7, 7), new PointF(32, 7)));
        down = new Collision(new Path(new PointF(2, 32), new PointF(2, 31), new PointF(32, 31)));
        tiles[20] = getTile(0, 7, 12, 2, 1, 1, 32, reg, Collision.empty, down, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 7), new PointF(24, 7), new PointF(24, 32)));
        down = new Collision(new Path(new PointF(-1, 31), new PointF(29, 31), new PointF(29, 32)));
        tiles[21] = getTile(0, 7, 13, 2, 1, 1, 32, reg, Collision.empty, down, TileSound.WALL);

        reg = new Collision(new Path(new PointF(2, -1), new PointF(2, 32)));
        tiles[22] = getTile(2, 0, 1, 3, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[23] = getTile(0, 31, 2, 3, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(29, -1), new PointF(29, 32)));
        tiles[24] = getTile(0, 0, 3, 3, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(7, -1), new PointF(7, 32)));
        down = new Collision(new Path(new PointF(2, -1), new PointF(2, 32)));
        tiles[25] = getTile(0, 0, 9, 3, 1, 1, 32, reg, Collision.empty, down, TileSound.WALL);

        reg = new Collision(new Path(new PointF(24, -1), new PointF(24, 32)));
        down = new Collision(new Path(new PointF(29, -1), new PointF(29, 32)));
        tiles[26] = getTile(24, 0, 10, 3, 1, 1, 32, reg, Collision.empty, down, TileSound.WALL);

        reg = new Collision(new Path(new PointF(2, -1), new PointF(2, 32)));
        tiles[27] = getTile(2, 0, 12, 3, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(29, -1), new PointF(29, 32)));
        tiles[28] = getTile(0, 0, 13, 3, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(2, -1), new PointF(2, 31), new PointF(32, 31)));
        tiles[29] = getTile(2, 0, 1, 4, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[30] = getTile(0, 31, 2, 4, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(29, 31), new PointF(29, -1)));
        tiles[31] = getTile(0, 0, 3, 4, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(24, -1), new PointF(24, 7), new PointF(32, 7)));
        down = new Collision(new Path(new PointF(29, -1), new PointF(29, 31), new PointF(32, 31)));
        tiles[32] = getTile(24, 7, 4, 4, 1, 1, 32, reg, Collision.empty, down, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 7), new PointF(7, 7), new PointF(7, -1)));
        down = new Collision(new Path(new PointF(2, -1), new PointF(2, 31), new PointF(-1, 31)));
        tiles[33] = getTile(0, 7, 8, 4, 1, 1, 32, reg, Collision.empty, down, TileSound.WALL);

        reg = new Collision(new Path(new PointF(2, -1), new PointF(2, 31), new PointF(32, 31)));
        tiles[34] = getTile(2, 0, 12, 4, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(29, 31), new PointF(29, -1)));
        tiles[35] = getTile(0, 0, 13, 4, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[36] = getTile(0, 31, 1, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[37] = getTile(0, 31, 2, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[38] = getTile(0, 31, 3, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[39] = getTile(0, 31, 4, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[40] = getTile(0, 31, 6, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(7, -1), new PointF(7, 3), new PointF(32, 3)));
        tiles[41] = getTile(0, 31, 8, 6, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 3), new PointF(32, 3)));
        tiles[42] = getTile(0, 31, 9, 6, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 3), new PointF(32, 3)));
        tiles[43] = getTile(0, 31, 10, 6, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 3), new PointF(32, 3)));
        tiles[44] = getTile(0, 31, 11, 6, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 3), new PointF(32, 3)));
        tiles[45] = getTile(0, 31, 12, 6, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(24, -1), new PointF(24, 3), new PointF(-1, 3)));
        tiles[46] = getTile(0, 31, 13, 6, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[47] = getTile(0, 31, 1, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[48] = getTile(0, 31, 2, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[49] = getTile(0, 31, 3, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[50] = getTile(0, 31, 4, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[51] = getTile(0, 31, 6, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(7, -1), new PointF(7, 3), new PointF(32, 3)));
        tiles[52] = getTile(0, 31, 8, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 3), new PointF(32, 3)));
        tiles[53] = getTile(0, 31, 9, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 3), new PointF(32, 3)));
        tiles[54] = getTile(0, 31, 10, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 3), new PointF(32, 3)));
        tiles[55] = getTile(0, 31, 11, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 3), new PointF(32, 3)));
        tiles[56] = getTile(0, 31, 12, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(24, -1), new PointF(24, 3), new PointF(-1, 3)));
        tiles[57] = getTile(0, 31, 13, 7, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[58] = getTile(0, 31, 1, 9, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[59] = getTile(0, 31, 2, 9, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[60] = getTile(0, 31, 4, 9, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[61] = getTile(0, 31, 5, 9, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[62] = getTile(0, 63, 8, 9, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[63] = getTile(0, 63, 9, 9, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[64] = getTile(0, 63, 11, 9, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[65] = getTile(0, 63, 12, 9, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[66] = getTile(0, 31, 1, 10, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[67] = getTile(0, 31, 2, 10, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[68] = getTile(0, 31, 4, 10, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(32, 31)));
        tiles[69] = getTile(0, 31, 5, 10, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(16, 31), new PointF(16, 27), new PointF(-1, 27)));
        tiles[70] = getTile(0, 31, 8, 10, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(32, 27), new PointF(15, 27), new PointF(15, 31), new PointF(32, 31)));
        tiles[71] = getTile(0, 31, 9, 10, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 31), new PointF(16, 31), new PointF(16, 27), new PointF(-1, 27)));
        tiles[72] = getTile(0, 31, 11, 10, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(32, 27), new PointF(15, 27), new PointF(15, 31), new PointF(32, 31)));
        tiles[73] = getTile(0, 31, 12, 10, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[74] = getTile(0, 0, 1, 12, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        tiles[75] = getTile(0, 0, 2, 12, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        tiles[76] = getTile(0, 0, 3, 12, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        tiles[77] = getTile(0, 0, 4, 12, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        tiles[78] = getTile(0, 0, 5, 12, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        tiles[79] = getTile(0, 0, 6, 12, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        tiles[80] = getTile(0, 0, 7, 12, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        tiles[81] = getTile(0, 0, 8, 12, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        reg = new Collision(new Path(new PointF(32, 3), new PointF(24, 3), new PointF(24, 32)));
        tiles[82] = getTile(24, 7, 9, 12, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 3), new PointF(7, 3), new PointF(7, 32)));
        tiles[83] = getTile(0, 7, 10, 12, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        reg = new Collision(new Path(new PointF(-1, 3), new PointF(7, 3), new PointF(7, 32)));
        tiles[84] = getTile(0, 7, 11, 12, 1, 1, 32, reg, Collision.empty, Collision.empty, TileSound.WALL);

        tiles[85] = getTile(0, 0, 1, 13, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        tiles[86] = getTile(0, 0, 2, 13, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        tiles[87] = getTile(0, 0, 3, 13, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        tiles[88] = getTile(0, 0, 5, 13, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        tiles[89] = getTile(0, 0, 6, 13, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        tiles[90] = getTile(0, 0, 7, 13, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        reg = top = new Collision(new Path(new PointF(24, -1), new PointF(24, 32)), new Path(new PointF(31, -1), new PointF(31, 32)));
        tiles[91] = getTile(24, 0, 9, 13, 1, 1, 32, reg, top, Collision.empty, TileSound.WALL);

        reg = top = new Collision(new Path(new PointF(0, -1), new PointF(0, 32)), new Path(new PointF(7, -1), new PointF(7, 32)));
        tiles[92] = getTile(0, 0, 10, 13, 1, 1, 32, reg, top, Collision.empty, TileSound.WALL);

        reg = top = new Collision(new Path(new PointF(0, -1), new PointF(0, 32)), new Path(new PointF(7, -1), new PointF(7, 32)));
        tiles[93] = getTile(0, 0, 11, 13, 1, 1, 32, reg, top, Collision.empty, TileSound.WALL);

        tiles[94] = getTile(0, 0, 1, 14, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        tiles[95] = getTile(0, 0, 2, 14, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        tiles[96] = getTile(0, 0, 3, 14, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        tiles[97] = getTile(0, 0, 5, 14, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        tiles[98] = getTile(0, 0, 6, 14, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        tiles[99] = getTile(0, 0, 7, 14, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.STAIR);

        reg = top = new Collision(new Path(new PointF(24, -1), new PointF(24, 31), new PointF(31, 31), new PointF(31, -1)));
        tiles[100] = getTile(24, 0, 9, 14, 1, 1, 32, reg, top, Collision.empty, TileSound.WALL);

        reg = top = new Collision(new Path(new PointF(0, -1), new PointF(0, 31), new PointF(7, 31), new PointF(7, -1)));
        tiles[101] = getTile(0, 0, 10, 14, 1, 1, 32, reg, top, Collision.empty, TileSound.WALL);

        reg = top = new Collision(new Path(new PointF(0, -1), new PointF(0, 31), new PointF(7, 31), new PointF(7, -1)));
        tiles[102] = getTile(0, 0, 11, 14, 1, 1, 32, reg, top, Collision.empty, TileSound.WALL);

        return tiles;
    }
}
