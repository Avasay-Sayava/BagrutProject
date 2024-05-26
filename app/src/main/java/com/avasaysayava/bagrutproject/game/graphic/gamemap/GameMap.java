package com.avasaysayava.bagrutproject.game.graphic.gamemap;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Pair;

import androidx.core.content.ContextCompat;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.game.Game;
import com.avasaysayava.bagrutproject.game.collision.Collision;
import com.avasaysayava.bagrutproject.game.entity.Entity;
import com.avasaysayava.bagrutproject.game.graphic.Tile;
import com.avasaysayava.bagrutproject.util.struct.LineF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameMap {
    public final int TILE_SIZE;
    protected final List<Layer> layers;
    protected final List<Tile>[][] map;
    protected Game game;
    protected float x, y;
    private int glyphs;

    public GameMap(Game game, List<Tile>[][] map, int tileSize, float x, float y) {
        this.TILE_SIZE = tileSize;
        this.game = game;
        this.glyphs = 0;

        this.map = map;
        this.layers = new ArrayList<>();
        generateLayers(map);

        move(x, y);
        arrange();
    }

    public GameMap(Game game, List<Tile>[][] map, List<Layer> layers, int tileSize, float x, float y) {
        this.TILE_SIZE = tileSize;
        this.game = game;
        this.glyphs = 0;

        this.map = map;
        this.layers = layers;

        move(x, y);
        arrange();
    }

    // a comparator for sorting the layers by their properties
    public static int getEvaluation(Object o1, Object o2, GameMap gameMap) {
        GameMap.Prioritised p1 = getPrioritised(o1, gameMap), p2 = getPrioritised(o2, gameMap);

        if (p1.z != p2.z) {
            return p1.z - p2.z;
        } else {
            return p1.y + p1.down - p2.y - p2.down;
        }
    }

    @SuppressWarnings("unchecked")
    // returns a Prioritised for the given object, GameMap that can be
    // compared with other Prioritiseds
    private static Prioritised getPrioritised(Object o, GameMap gameMap) {
        GameMap.Prioritised p = new GameMap.Prioritised();
        if (o instanceof Entity) {
            Entity entity = (Entity) o;
            p.z = entity.getZ();
            p.down = entity.getDown() * gameMap.getGame().SCALE;
            p.left = entity.getLeft() * gameMap.getGame().SCALE;
            p.x = Math.round(entity.getX() * gameMap.getGame().SCALE);
            p.y = Math.round(entity.getY() * gameMap.getGame().SCALE);
        } else if (o instanceof  Pair) {
            Pair<Tile, Point> pair = (Pair<Tile, Point>) o;
            p.z = pair.first.getZ();
            p.down = pair.first.getDown() * gameMap.getGame().SCALE;
            p.left = pair.first.getLeft() * gameMap.getGame().SCALE;
            p.x = Math.round((pair.second.x * gameMap.TILE_SIZE + gameMap.getX()) * gameMap.getGame().SCALE);
            p.y = Math.round((pair.second.y * gameMap.TILE_SIZE + gameMap.getY()) * gameMap.getGame().SCALE);
        } else {
            Tile tile = (Tile) o;
            p.z = tile.getZ();
            p.down = tile.getDown() * gameMap.getGame().SCALE;
            p.left = tile.getLeft() * gameMap.getGame().SCALE;
            p.x = 0;
            p.y = 0;
        }

        return p;
    }

    // generates layers into {this.layers} my given map
    private void generateLayers(List<Tile>[][] map) {
        this.layers.clear();
        int maxZ = getMaxZ();
        for (int i = 0; i <= maxZ; i++) {
            this.layers.add(generateLayer(map, i));
        }
    }

    // find the max z value among all tiles in {this.map}
    private int getMaxZ() {
        int maxZ = Integer.MIN_VALUE;
        for (List<Tile>[] lists : this.map)
            for (List<Tile> list : lists)
                for (Tile tile : list) maxZ = Math.max(maxZ, tile.getZ());
        return maxZ;
    }

    @SuppressWarnings("unchecked")
    // generates layer by z from map
    private Layer generateLayer(List<Tile>[][] map, int z) {
        List<Tile>[][] layerMap = new List[map.length][];
        for (int i = 0; i < map.length; i++) {
            layerMap[i] = new List[map[i].length];
            for (int j = 0; j < map[i].length; j++) {
                layerMap[i][j] = new ArrayList<>();
                for (Tile tile : map[i][j]) {
                    if (tile.getZ() == z) {
                        layerMap[i][j].add(tile);
                    }
                }
            }
        }
        return new Layer(this, layerMap, z);
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    // arranges all layers, so they can be drawn in the correct order
    public void arrange() {
        for (Layer layer : this.layers) {
            layer.arrange();
        }
        this.layers.sort(Comparator.comparingInt(Layer::getZ));
    }

    // moves all layers and the map itself
    public void move(float x, float y) {
        this.x = x;
        this.y = y;
        for (Layer layer : this.layers) {
            layer.move(x, y);
        }
    }

    // translates all layers and the map itself
    public void translate(float dx, float dy) {
        this.x += dx;
        this.y += dy;
        for (Layer layer : this.layers) {
            layer.translate(dx, dy);
        }
    }

    // updates all layers. happens periodically
    public void update(Entity... entities) {
        for (Layer layer : this.layers) {
            layer.update(entities);
        }
    }

    // draws all layers
    public void draw(Canvas canvas, Entity... entities) {
        for (Layer layer : this.layers) {
            layer.draw(canvas, entities);
        }

        // draw collisions
        if (this.game.isDebug()) {
            Paint paint = new Paint();
            paint.setColor(ContextCompat.getColor(this.game.getContext(), R.color.Red));
            for (int i = 0; i < this.map.length; i++) {
                for (int j = 0; j < this.map[i].length; j++) {
                    for (Tile t : this.map[i][j]) {
                        if (t != Tile.empty) {
                            t.move(this.x + j * this.TILE_SIZE, this.y + i * this.TILE_SIZE);
                            if (t.getCollision() != Collision.empty)
                                t.getCollision().draw(canvas, paint, this.game.SCALE);
                            if (t.getCollisionTop() != Collision.empty)
                                t.getCollisionTop().draw(canvas, paint, this.game.SCALE);
                            if (t.getCollisionDown() != Collision.empty)
                                t.getCollisionDown().draw(canvas, paint, this.game.SCALE);
                            canvas.drawText(t.getId() + "",
                                    (this.x + j * this.TILE_SIZE + this.TILE_SIZE / 2f) * this.game.SCALE - this.game.textPaint.measureText(t.getId() + "") / 2,
                                    (this.y + i * this.TILE_SIZE + this.TILE_SIZE / 2f) * this.game.SCALE + this.game.textPaint.getTextSize() / 2,
                                    this.game.textPaint);
                        }
                    }
                }
            }

            for (Entity e : entities) {
                e.getCollision().move(e.getX(), e.getY());
                e.getCollision().draw(canvas, paint, this.game.SCALE);
            }
        }
    }

    // return the first intersector between the given entity and the map
    public LineF getIntersector(Entity e) {
        for (int i = Math.max(Math.round((e.getY() - this.y) / this.TILE_SIZE) - 1, 0); i <= Math.min(Math.round((e.getY() - this.y) / this.TILE_SIZE) + 1, this.map.length - 1); i++) {
            for (int j = Math.max(Math.round((e.getX() - this.x) / this.TILE_SIZE) - 1, 0); j <= Math.min(Math.round((e.getX() - this.x) / this.TILE_SIZE) + 1, this.map[i].length - 1); j++) {
                for (Tile tile : this.map[i][j]) {
                    if (tile == Tile.empty) continue;
                    tile.move(this.x + j * this.TILE_SIZE, this.y + i * this.TILE_SIZE);
                    LineF intersector = tile.getIntersector(e);
                    if (intersector != null) {
                        return intersector;
                    }
                }
            }
        }

        return null;
    }

    // returns all tiles in the given position
    public List<Tile> getTiles(int x, int y) {
        if (y < 0 || y >= this.map.length || x < 0 || x >= this.map[y].length)
            return Collections.emptyList();
        return this.map[y][x];
    }

    // returns all tiles in the given position and z
    public List<Tile> getTiles(int x, int y, int z) {
        if (y < 0 || y >= this.map.length || x < 0 || x >= this.map[y].length)
            return Collections.emptyList();
        return this.layers.get(z).getTiles(x, y);
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    // returns the number of columns in the map
    public int getColumns() {
        int columns = 0;
        for (List<Tile>[] row : this.map)
            columns = Math.max(columns, row.length);
        return columns;
    }

    // returns the number of rows in the map
    public int getRows() {
        return this.map.length;
    }

    public List<Layer> getLayers() {
        return this.layers;
    }

    // removes every property within a tile in the map, and replaces it with null
    public void clear() {
        for (List<Tile>[] lists : this.map) {
            for (List<Tile> list : lists) {
                for (Tile tile : list) {
                    if (tile == Tile.empty) continue;
                    tile.setProperty(null);
                }
            }
        }
    }

    // prepares all layers for starting a level
    public void prepare() {
        this.glyphs = 0;
        for (Layer layer : this.layers) {
            layer.prepare(this.x, this.y);
        }
    }

    // add glyphs to the map to set the glyph goal
    public void addGlyphs(int glyphs) {
        this.glyphs += glyphs;
    }

    // add glyphs to the map to reach the glyph goal
    public void removeGlyphs(int count) {
        this.glyphs -= count;
        if (this.glyphs <= 0) {
            onNoGlyphs();
        }
    }

    // happens when all glyphs are in the glyphstones, and the goal is reached
    public void onNoGlyphs() {
        this.game.onCompleted();
    }

    public List<Tile>[][] getMap() {
        return this.map;
    }

    public static class Prioritised {
        public int z;
        public int down;
        public int left;
        public int x;
        public int y;
    }
}
