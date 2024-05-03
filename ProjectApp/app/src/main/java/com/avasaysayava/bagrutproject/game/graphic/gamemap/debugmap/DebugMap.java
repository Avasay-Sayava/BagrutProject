package com.avasaysayava.bagrutproject.game.graphic.gamemap.debugmap;

import com.avasaysayava.bagrutproject.game.Game;
import com.avasaysayava.bagrutproject.game.graphic.Tile;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.GameMap;

import java.util.List;

public abstract class DebugMap extends GameMap {
    public DebugMap(Game game, List<Tile>[][] map, int tileSize, float x, float y) {
        super(game, map, tileSize, x, y);
    }
}
