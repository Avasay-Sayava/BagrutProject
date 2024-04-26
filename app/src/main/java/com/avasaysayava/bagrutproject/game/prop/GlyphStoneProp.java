package com.avasaysayava.bagrutproject.game.prop;

import android.graphics.Point;

import com.avasaysayava.bagrutproject.game.Game;
import com.avasaysayava.bagrutproject.game.entity.Entity;
import com.avasaysayava.bagrutproject.game.graphic.Tile;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.GameMap;
import com.avasaysayava.bagrutproject.util.Util;

public class GlyphStoneProp extends Prop {
    private int glyphs, glyphsGoal;
    private Tile tile;

    public GlyphStoneProp(Game game, GameMap map, Tile parent, int x, int y) {
        super(game, map, parent, x, y);

        this.glyphs = 0;
        this.glyphsGoal = 3;
        this.tile = Tile.empty;
    }

    @Override
    public void update(Entity... entities) {
        for (Entity e : entities) {
            Point p = e.getPoint();
            if (this.x == p.x && this.y == p.y
                    && e.getZ() == this.parent.getZ()) {
                this.glyphs += e.getGlyphs(this.glyphsGoal - this.glyphs);
                if (this.glyphs == this.glyphsGoal && this.tile == Tile.empty) {
                    this.tile = this.game.structuresTileSet.getTile(25);
                    Util.randomElement(this.game.tileSoundPreloader.getSounds(this.tile.getType())).start();
                }
            }
        }
    }

    public Tile getTile() {
        return this.tile;
    }
}
