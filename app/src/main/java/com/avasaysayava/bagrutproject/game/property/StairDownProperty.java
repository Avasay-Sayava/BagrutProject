package com.avasaysayava.bagrutproject.game.property;

import android.graphics.Point;

import com.avasaysayava.bagrutproject.game.Game;
import com.avasaysayava.bagrutproject.game.entity.Entity;
import com.avasaysayava.bagrutproject.game.graphic.Tile;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.GameMap;

public class StairDownProperty extends Property {
    public StairDownProperty(Game game, GameMap map, Tile parent, int x, int y) {
        super(game, map, parent, x, y);
    }

    @Override
    public void update(Entity... entities) {
        for (Entity e : entities) {
            Point p = e.getPoint();
            if (this.x == p.x && this.y == p.y) {
                e.setZ(this.parent.getZ());
            }
        }
    }

    @Override
    public Tile getTile() {
        return null;
    }
}
