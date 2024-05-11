package com.avasaysayava.bagrutproject.game.graphic.gamemap;

import static com.avasaysayava.bagrutproject.game.graphic.gamemap.GameMap.getEvaluation;

import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Pair;

import com.avasaysayava.bagrutproject.game.entity.Entity;
import com.avasaysayava.bagrutproject.game.graphic.Tile;
import com.avasaysayava.bagrutproject.game.graphic.tileset.GlyphFloorTileSet;
import com.avasaysayava.bagrutproject.game.graphic.tileset.StructuresTileSet;
import com.avasaysayava.bagrutproject.game.graphic.tileset.WallsTileSet;
import com.avasaysayava.bagrutproject.game.property.GlyphProperty;
import com.avasaysayava.bagrutproject.game.property.GlyphStoneProperty;
import com.avasaysayava.bagrutproject.game.property.StairDownProperty;
import com.avasaysayava.bagrutproject.game.property.StairUpProperty;
import com.avasaysayava.bagrutproject.util.Util;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Layer {
    private final int z;
    private final GameMap gameMap;
    private final List<Tile>[][] map;
    private float x, y;

    public Layer(GameMap gameMap, List<Tile>[][] map, int z) {
        this.map = map;
        this.z = z;
        this.gameMap = gameMap;
        this.x = gameMap.getX();
        this.y = gameMap.getY();
    }

    public void arrange() {
        for (List<Tile>[] row : this.map) {
            for (List<Tile> list : row) {
                list.sort((o1, o2) -> getEvaluation(o1, o2, this.gameMap));
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void draw(Canvas canvas, Entity... entities) {
        Queue<Object> pq = new PriorityQueue<>((o1, o2) -> getEvaluation(o1, o2, this.gameMap));
        for (Entity e : entities) {
            Point cords = e.getCords();
            for (int i = Math.max(0, (int) Math.max(cords.y - 2,
                    Math.floor(((float) this.gameMap.getGame().getTop() / this.gameMap.TILE_SIZE - this.y) / this.gameMap.TILE_SIZE) - 1));
                 i <= Math.min(this.map.length - 1, (int) Math.min(cords.y + 1,
                         Math.floor(((float) this.gameMap.getGame().getBottom() / this.gameMap.getGame().SCALE - this.y) / this.gameMap.TILE_SIZE)));
                 i++) {
                for (int j = Math.max(0, (int) Math.max(cords.x - 1,
                        Math.floor(((float) this.gameMap.getGame().getLeft() / this.gameMap.TILE_SIZE - this.x) / this.gameMap.TILE_SIZE) - 1));
                     j <= Math.min(this.map[i].length - 1, (int) Math.min(cords.x + 1,
                             Math.floor(((float) this.gameMap.getGame().getRight() / this.gameMap.getGame().SCALE - this.x) / this.gameMap.TILE_SIZE)));
                     j++) {
                    for (Tile t : this.map[i][j]) {
                        pq.add(new Pair<>(t, new Point(j, i)));
                    }
                }
            }
            pq.add(e);
            pq.add(e.getShadow());
        }

        while (!pq.isEmpty()) {
            Object o = pq.poll();
            if (o instanceof Pair) {
                Pair<Tile, Point> pair = (Pair<Tile, Point>) o;
                Tile tile = pair.first;
                Point cords = pair.second;
                if (tile == Tile.empty) continue;
                tile.setDrawnLazy(true);
                tile.withScale(this.gameMap.getGame().SCALE).draw(canvas,
                        (this.x + cords.x * this.gameMap.TILE_SIZE) * this.gameMap.getGame().SCALE,
                        (this.y + cords.y * this.gameMap.TILE_SIZE) * this.gameMap.getGame().SCALE,
                        null);
                if (tile.getProp() != null && tile.getProp().getTile() != null) {
                    tile.getProp().getTile().withScale(this.gameMap.getGame().SCALE).draw(canvas,
                            (this.x + cords.x * this.gameMap.TILE_SIZE) * this.gameMap.getGame().SCALE,
                            (this.y + cords.y * this.gameMap.TILE_SIZE) * this.gameMap.getGame().SCALE,
                            null);
                }
            } else if (o instanceof Entity) {
                Entity entity = (Entity) o;
                entity.draw(canvas);
            }
        }

        for (int i = (int) Math.max(0,
                Math.floor(((float) this.gameMap.getGame().getTop() / this.gameMap.TILE_SIZE - this.y) / this.gameMap.TILE_SIZE) - 1);
             i <= Math.min(this.map.length - 1,
                     Math.floor(((float) this.gameMap.getGame().getBottom() / this.gameMap.getGame().SCALE - this.y) / this.gameMap.TILE_SIZE));
             i++) {
            for (int j = (int) Math.max(0,
                    Math.floor(((float) this.gameMap.getGame().getLeft() / this.gameMap.TILE_SIZE - this.x) / this.gameMap.TILE_SIZE) - 1);
                 j <= Math.min(this.map[i].length - 1,
                         Math.floor(((float) this.gameMap.getGame().getRight() / this.gameMap.getGame().SCALE - this.x) / this.gameMap.TILE_SIZE));
                 j++) {
                for (Tile tile : this.map[i][j]) {
                    if (tile == Tile.empty) continue;
                    if (tile.getDrawnLazy()) {
                        tile.setDrawnLazy(false);
                    } else {
                        tile.withScale(this.gameMap.getGame().SCALE).draw(canvas,
                                (this.x + j * this.gameMap.TILE_SIZE) * this.gameMap.getGame().SCALE,
                                (this.y + i * this.gameMap.TILE_SIZE) * this.gameMap.getGame().SCALE,
                                null);
                        if (tile.getProp() != null && tile.getProp().getTile() != null) {
                            tile.getProp().getTile().withScale(this.gameMap.getGame().SCALE).draw(canvas,
                                    (this.x + j * this.gameMap.TILE_SIZE) * this.gameMap.getGame().SCALE,
                                    (this.y + i * this.gameMap.TILE_SIZE) * this.gameMap.getGame().SCALE,
                                    null);
                        }
                    }
                }
            }
        }
    }

    public int getZ() {
        return this.z;
    }

    public void prepare(float x, float y) {
        move(x, y);
        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[i].length; j++) {
                for (Tile tile : this.map[i][j]) {
                    if (tile == Tile.empty) continue;
                    if (tile.getTileSet() instanceof StructuresTileSet
                            // glyph stones indexes
                            && (tile.getId() == 21 || tile.getId() == 5)) {
                        tile.setProperty(new GlyphStoneProperty(this.gameMap.getGame(), this.gameMap, tile, j, i));
                    } else if (tile.getTileSet() instanceof GlyphFloorTileSet) {
                        tile.setProperty(new GlyphProperty(this.gameMap.getGame(), this.gameMap, tile, j, i));
                    } else if (tile.getTileSet() instanceof WallsTileSet
                            // middle part of stairs indexes
                            && Util.within(85, tile.getId(), 90)) {
                        tile.setProperty(new StairUpProperty(this.gameMap.getGame(), this.gameMap, tile, j, i));
                    } else if (tile.getTileSet() instanceof WallsTileSet
                            // lower part of stairs indexes
                            && Util.within(94, tile.getId(), 99)) {
                        tile.setProperty(new StairDownProperty(this.gameMap.getGame(), this.gameMap, tile, j, i));
                    }
                }
            }
        }
        for (List<Tile>[] lists : this.map) {
            for (List<Tile> list : lists) {
                for (Tile tile : list) {
                    if (tile == Tile.empty) continue;
                    if (tile.getProp() != null) {
                        if (tile.getProp() instanceof GlyphStoneProperty) {
                            this.gameMap.addGlyphs(((GlyphStoneProperty) tile.getProp()).GLYPH_GOAL);
                        }
                    }
                }
            }
        }
    }

    public void update(Entity... entities) {
        for (List<Tile>[] lists : this.map) {
            for (List<Tile> list : lists) {
                for (Tile tile : list) {
                    if (tile == Tile.empty) continue;
                    if (tile.getProp() == null) continue;
                    tile.getProp().update(entities);
                }
            }
        }
    }

    public List<Tile> getTiles(int x, int y) {
        if (y < 0 || y >= this.map.length || x < 0 || x >= this.map[y].length)
            return Collections.emptyList();
        return this.map[y][x];
    }

    public void move(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void translate(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }
}
