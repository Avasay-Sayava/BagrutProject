package com.avasaysayava.bagrutproject.game.graphic.tileset;

import android.content.Context;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.game.audio.TileSound;
import com.avasaysayava.bagrutproject.game.collision.Collision;
import com.avasaysayava.bagrutproject.game.graphic.Tile;

public class FloorTileSet extends TileSet {
    public FloorTileSet(Context context) {
        super(context, R.drawable.floor);
    }

    @Override
    protected Tile[] getTileArray() {
        Tile[] tiles = new Tile[50];
        tiles[0] = getTile(0, 0, 0, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[1] = getTile(0, 0, 1, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[2] = getTile(0, 0, 2, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[3] = getTile(0, 0, 3, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[4] = getTile(0, 0, 4, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[5] = getTile(0, 0, 5, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[6] = getTile(0, 0, 6, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[7] = getTile(0, 0, 7, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[8] = getTile(0, 0, 0, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[9] = getTile(0, 0, 1, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[10] = getTile(0, 0, 2, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[11] = getTile(0, 0, 3, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[12] = getTile(0, 0, 4, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[13] = getTile(0, 0, 5, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[14] = getTile(0, 0, 6, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[15] = getTile(0, 0, 7, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[16] = getTile(0, 0, 0, 2, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[17] = getTile(0, 0, 1, 2, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[18] = getTile(0, 0, 2, 2, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[19] = getTile(0, 0, 3, 2, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[20] = getTile(0, 0, 4, 2, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[21] = getTile(0, 0, 5, 2, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[22] = getTile(0, 0, 6, 2, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[23] = getTile(0, 0, 7, 2, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[24] = getTile(0, 0, 0, 3, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[25] = getTile(0, 0, 1, 3, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[26] = getTile(0, 0, 2, 3, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[27] = getTile(0, 0, 3, 3, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[28] = getTile(0, 0, 4, 3, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[29] = getTile(0, 0, 6, 3, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[30] = getTile(0, 0, 5, 4, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[31] = getTile(0, 0, 6, 4, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[32] = getTile(0, 0, 7, 4, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[33] = getTile(0, 0, 0, 5, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[34] = getTile(0, 0, 1, 5, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[35] = getTile(0, 0, 2, 5, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[36] = getTile(0, 0, 6, 5, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[37] = getTile(0, 0, 0, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[38] = getTile(0, 0, 2, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[39] = getTile(0, 0, 4, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[40] = getTile(0, 0, 5, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[41] = getTile(0, 0, 6, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[42] = getTile(0, 0, 7, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[43] = getTile(0, 0, 0, 7, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[44] = getTile(0, 0, 1, 7, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[45] = getTile(0, 0, 2, 7, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[46] = getTile(0, 0, 4, 7, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[47] = getTile(0, 0, 5, 7, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[48] = getTile(0, 0, 6, 7, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        tiles[49] = getTile(0, 0, 7, 7, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileSound.FLOOR);
        return tiles;
    }
}
