package com.avasaysayava.bagrutproject.game.audio;

import androidx.annotation.RawRes;

import com.avasaysayava.bagrutproject.R;

public enum TileSound {
    GRASS(R.raw.grass1, R.raw.grass2),
    PATH(),
    FLOOR(),
    WALL(),
    ENTITY(),
    STAIR();

    public final int[] sounds;

    TileSound(@RawRes int... sounds) {
        this.sounds = sounds;
    }
}