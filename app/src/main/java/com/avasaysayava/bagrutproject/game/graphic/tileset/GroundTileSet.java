package com.avasaysayava.bagrutproject.game.graphic.tileset;

import android.content.Context;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.game.collision.Collision;
import com.avasaysayava.bagrutproject.game.graphic.Tile;
import com.avasaysayava.bagrutproject.game.graphic.TileType;

public class GroundTileSet extends TileSet {
    public GroundTileSet(Context context) {
        super(context, R.drawable.ground);
    }

    @Override
    protected Tile[] getTileArray() {
        Tile[] tiles = new Tile[64];

        tiles[0] = getTile(0, 0, 0, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[1] = getTile(0, 0, 1, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[2] = getTile(0, 0, 2, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[3] = getTile(0, 0, 3, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[4] = getTile(0, 0, 4, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[5] = getTile(0, 0, 5, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[6] = getTile(0, 0, 6, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[7] = getTile(0, 0, 7, 0, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[8] = getTile(0, 0, 0, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[9] = getTile(0, 0, 1, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[10] = getTile(0, 0, 2, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[11] = getTile(0, 0, 3, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[12] = getTile(0, 0, 4, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[13] = getTile(0, 0, 5, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[14] = getTile(0, 0, 6, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[15] = getTile(0, 0, 7, 1, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[16] = getTile(0, 0, 0, 2, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[17] = getTile(0, 0, 1, 2, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[18] = getTile(0, 0, 2, 2, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[19] = getTile(0, 0, 3, 2, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[20] = getTile(0, 0, 4, 2, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[21] = getTile(0, 0, 5, 2, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[22] = getTile(0, 0, 6, 2, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[23] = getTile(0, 0, 7, 2, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[24] = getTile(0, 0, 0, 3, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[25] = getTile(0, 0, 1, 3, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[26] = getTile(0, 0, 2, 3, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[27] = getTile(0, 0, 3, 3, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[28] = getTile(0, 0, 4, 3, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[29] = getTile(0, 0, 5, 3, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[30] = getTile(0, 0, 6, 3, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[31] = getTile(0, 0, 7, 3, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[32] = getTile(0, 0, 0, 4, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[33] = getTile(0, 0, 1, 4, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[34] = getTile(0, 0, 2, 4, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[35] = getTile(0, 0, 3, 4, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[36] = getTile(0, 0, 4, 4, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[37] = getTile(0, 0, 5, 4, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[38] = getTile(0, 0, 6, 4, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[39] = getTile(0, 0, 7, 4, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[40] = getTile(0, 0, 0, 5, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[41] = getTile(0, 0, 1, 5, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[42] = getTile(0, 0, 2, 5, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[43] = getTile(0, 0, 3, 5, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[44] = getTile(0, 0, 4, 5, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[45] = getTile(0, 0, 5, 5, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[46] = getTile(0, 0, 6, 5, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[47] = getTile(0, 0, 7, 5, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[48] = getTile(0, 0, 0, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[49] = getTile(0, 0, 1, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[50] = getTile(0, 0, 2, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[51] = getTile(0, 0, 3, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[52] = getTile(0, 0, 4, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[53] = getTile(0, 0, 5, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[54] = getTile(0, 0, 6, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[55] = getTile(0, 0, 7, 6, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[56] = getTile(0, 0, 0, 7, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[57] = getTile(0, 0, 1, 7, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[58] = getTile(0, 0, 2, 7, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[59] = getTile(0, 0, 3, 7, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[60] = getTile(0, 0, 4, 7, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[61] = getTile(0, 0, 5, 7, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.PATH);
        tiles[62] = getTile(0, 0, 6, 7, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);
        tiles[63] = getTile(0, 0, 7, 7, 1, 1, 32, Collision.empty, Collision.empty, Collision.empty, TileType.GRASS);

        return tiles;
    }
}
