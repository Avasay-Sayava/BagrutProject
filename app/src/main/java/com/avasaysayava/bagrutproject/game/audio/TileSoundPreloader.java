package com.avasaysayava.bagrutproject.game.audio;

import android.content.Context;
import android.media.MediaPlayer;

import com.avasaysayava.bagrutproject.R;

import java.util.HashMap;

    public class TileSoundPreloader {
    private final HashMap<TileSound, MediaPlayer[]> soundMap;

    // creates MediaPlayer objects for each TileSound value, and stores these MediaPlayer
    // objects in a HashMap called soundMap with the key being the TileSound enum and value being an array of MediaPlayers.
    public TileSoundPreloader(Context context) {
        this.soundMap = new HashMap<>(TileSound.values().length);

        for (TileSound ts : TileSound.values()) {
            MediaPlayer[] mps = new MediaPlayer[ts.sounds.length];
            for (int i = 0; i < ts.sounds.length; i++) {
                mps[i] = MediaPlayer.create(context, ts.sounds[i]);
                if (ts.sounds[i] == R.raw.grass1
                        || ts.sounds[i] == R.raw.grass2
                        || ts.sounds[i] == R.raw.glyph1
                        || ts.sounds[i] == R.raw.glyph2)
                    mps[i].setVolume(.1f, .1f);
            }
            this.soundMap.put(ts, mps);
        }
    }

    public MediaPlayer[] getSounds(TileSound ts) {
        return this.soundMap.get(ts);
    }
}
