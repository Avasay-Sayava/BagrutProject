package com.avasaysayava.bagrutproject.game.sound;

import android.content.Context;
import android.graphics.Point;

import com.avasaysayava.bagrutproject.game.Game;
import com.avasaysayava.bagrutproject.game.entity.Player;
import com.avasaysayava.bagrutproject.game.graphic.Tile;

public class PlayerAudio extends Audio {
    private final Player player;
    private final Game game;

    public PlayerAudio(Game game, Player player, Context context) {
        super(context);
        this.game = game;
        this.player = player;
    }

    @Override
    public void play() {
        Point pos = this.player.getPoint();
        // using try to disable crashing when the pos is out of bounds
        try {
            for (Tile t : this.game.getMap().getTiles(pos.y, pos.x)) {
                switch (t.getType()) {
                    case PATH:
                    case GRASS:
                    case FLOOR:
                        setFile(t.getType().getSound());
                        super.play();
                        return;
                }
            }
        } catch (Exception ignored) {
        }
    }
}
