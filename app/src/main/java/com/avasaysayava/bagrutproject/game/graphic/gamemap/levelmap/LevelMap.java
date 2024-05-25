package com.avasaysayava.bagrutproject.game.graphic.gamemap.levelmap;

import com.avasaysayava.bagrutproject.game.Game;
import com.avasaysayava.bagrutproject.game.graphic.Tile;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.GameMap;

import java.util.List;

public abstract class LevelMap extends GameMap {
    protected LevelMap(Game game, List<Tile>[][] map, int tileSize, float x, float y) {
        super(game, map, tileSize, x, y);
    }
}
