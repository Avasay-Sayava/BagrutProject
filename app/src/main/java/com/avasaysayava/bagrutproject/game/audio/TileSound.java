package com.avasaysayava.bagrutproject.game.audio;

import androidx.annotation.RawRes;

import com.avasaysayava.bagrutproject.R;

public enum TileSound {
    GRASS(R.raw.grass1, R.raw.grass2), PATH(R.raw.floor1, R.raw.floor2, R.raw.grass1, R.raw.grass2), FLOOR(R.raw.floor1, R.raw.floor2), WALL(), ENTITY(), GLYPH(R.raw.glyph1, R.raw.glyph2), STAIR(R.raw.stairs1, R.raw.stairs2);

    public final int[] sounds;

    TileSound(@RawRes int... sounds) {
        this.sounds = sounds;
    }
}
