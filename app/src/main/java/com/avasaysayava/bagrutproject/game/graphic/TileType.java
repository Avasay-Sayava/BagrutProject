package com.avasaysayava.bagrutproject.game.graphic;

import androidx.annotation.RawRes;

import com.avasaysayava.bagrutproject.R;

public enum TileType {
    GRASS(R.raw.grass1, R.raw.grass2),
    PATH(),
    FLOOR(),
    WALL(),
    ENTITY(),
    STAIR();

    final @RawRes int[] sounds;

    TileType(@RawRes int... sounds) {
        this.sounds = sounds;
    }

    public @RawRes int getSound() {
        return sounds[(int) (Math.random() * this.sounds.length)];
    }
}
