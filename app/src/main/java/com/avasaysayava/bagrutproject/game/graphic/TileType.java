package com.avasaysayava.bagrutproject.game.graphic;

import com.avasaysayava.bagrutproject.R;

public enum TileType {
    GRASS(R.raw.path1, R.raw.path2, R.raw.path3),
    PATH(R.raw.path1, R.raw.path2, R.raw.path3),
    FLOOR(R.raw.path1, R.raw.path2, R.raw.path3),
    WALL(R.raw.path1, R.raw.path2, R.raw.path3),
    ENTITY(R.raw.path1, R.raw.path2, R.raw.path3);

    final int[] sounds;
    TileType(int... sounds) {
        this.sounds = sounds;
    }

    public int getSound() {
        return sounds[(int) (Math.random() * this.sounds.length)];
    }
}
