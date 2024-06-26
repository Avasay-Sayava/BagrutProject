package com.avasaysayava.bagrutproject.game.property;

import android.graphics.Point;

import com.avasaysayava.bagrutproject.game.Game;
import com.avasaysayava.bagrutproject.game.entity.Entity;
import com.avasaysayava.bagrutproject.game.graphic.Tile;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.GameMap;
import com.avasaysayava.bagrutproject.util.Util;

    public class GlyphStoneProperty extends Property {
    public static final int GLYPH_GOAL = 3;
    private int glyphs;
    private Tile tile;

    public GlyphStoneProperty(Game game, GameMap map, Tile parent, int x, int y) {
        super(game, map, parent, x, y);

        this.glyphs = 0;
        this.tile = Tile.empty;
    }

    @Override
    // updates the given entities' glyphs by their position. happens periodically
    public void update(Entity... entities) {
        for (Entity e : entities) {
            Point p = e.getCords();
            if (this.x == p.x && this.y == p.y
                    && e.getZ() == this.parent.getZ()) {
                this.glyphs += e.getGlyphs(GLYPH_GOAL - this.glyphs);
                if (this.glyphs == GLYPH_GOAL && this.tile == Tile.empty) {
                    this.tile = this.game.structuresTileSet.getTile(25);
                    this.map.removeGlyphs(GLYPH_GOAL);
                    Util.randomElement(this.game.tileSoundPreloader.getSounds(this.tile.getType())).start();
                }
            }
        }
    }

    public Tile getTile() {
        return this.tile;
    }
}
