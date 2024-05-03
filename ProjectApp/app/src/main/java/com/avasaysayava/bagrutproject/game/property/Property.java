package com.avasaysayava.bagrutproject.game.property;

import com.avasaysayava.bagrutproject.game.Game;
import com.avasaysayava.bagrutproject.game.entity.Entity;
import com.avasaysayava.bagrutproject.game.graphic.Tile;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.GameMap;

public abstract class Property {
    protected Game game;
    protected GameMap map;
    protected int x, y;
    protected Tile parent;

    public Property(Game game, GameMap map, Tile parent, int x, int y) {
        this.map = map;
        this.game = game;
        this.parent = parent;
        this.x = x;
        this.y = y;
    }

    public abstract void update(Entity... entities);

    public abstract Tile getTile();
}
