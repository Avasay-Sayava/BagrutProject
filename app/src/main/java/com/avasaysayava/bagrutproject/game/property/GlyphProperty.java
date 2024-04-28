package com.avasaysayava.bagrutproject.game.property;

import android.graphics.Point;

import com.avasaysayava.bagrutproject.game.Game;
import com.avasaysayava.bagrutproject.game.entity.Entity;
import com.avasaysayava.bagrutproject.game.graphic.Tile;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.GameMap;
import com.avasaysayava.bagrutproject.util.Util;

public class GlyphProperty extends Property {
    private Tile tile;

    public GlyphProperty(Game game, GameMap map, Tile parent, int x, int y) {
        super(game, map, parent, x, y);
        this.tile = this.game.structuresTileSet.getTile(28);
    }

    @Override
    public void update(Entity... entities) {
        for (Entity e : entities) {
            Point p = e.getPoint();
            if (this.x == p.x && this.y == p.y && e.getZ() == this.parent.getZ()) {
                e.addGlyphs(3);
                if (this.tile != Tile.empty) {
                    Util.randomElement(this.game.tileSoundPreloader.getSounds(this.tile.getType())).start();
                    this.tile = Tile.empty;
                }
            }
        }
    }

    public Tile getTile() {
        return this.tile;
    }
}
