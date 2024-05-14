package com.avasaysayava.bagrutproject.game.audio;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.HashMap;

public class TileSoundPreloader {
    private final HashMap<TileSound, MediaPlayer[]> soundMap;

    public TileSoundPreloader(Context context) {
        this.soundMap = new HashMap<>(TileSound.values().length);

        for (TileSound ts : TileSound.values()) {
            MediaPlayer[] mps = new MediaPlayer[ts.sounds.length];
            for (int i = 0; i < ts.sounds.length; i++) {
                mps[i] = MediaPlayer.create(context, ts.sounds[i]);
                mps[i].setVolume(.1f, .1f);
            }
            this.soundMap.put(ts, mps);
        }
    }

    public MediaPlayer[] getSounds(TileSound ts) {
        return this.soundMap.get(ts);
    }
}
