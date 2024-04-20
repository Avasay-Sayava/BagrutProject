package com.avasaysayava.bagrutproject.game.graphic.gamemap;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Pair;

import androidx.core.content.ContextCompat;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.game.Game;
import com.avasaysayava.bagrutproject.game.LineF;
import com.avasaysayava.bagrutproject.game.collision.Collision;
import com.avasaysayava.bagrutproject.game.entity.Entity;
import com.avasaysayava.bagrutproject.game.graphic.Tile;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public abstract class GameMap {
    public final int TILE_SIZE;
    protected final List<Tile>[][] map;
    protected Game game;
    protected float x, y;

    public GameMap(Game game, List<Tile>[][] map, int tileSize, float x, float y) {
        this.TILE_SIZE = tileSize;
        this.game = game;
        this.map = map;

        move(x, y);
    }

    public void move(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void translate(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }

    @SuppressWarnings("unchecked")
    public void draw(Canvas canvas, Entity... entities) {
        PriorityQueue<Object> pq = new PriorityQueue<>(this::getEvaluation);

        pq.addAll(Arrays.asList(entities));
        for (Entity e : entities)
            pq.add(e.getShadow());

        for (int i = (int) Math.max(0, Math.floor(((float) this.game.getTop() / this.TILE_SIZE - this.y) / this.TILE_SIZE) - 1);
             i <= Math.min(this.map.length - 1, Math.floor(((float) this.game.getBottom() / this.game.SCALE - this.y) / this.TILE_SIZE));
             i++) {
            for (int j = (int) Math.max(0, Math.floor(((float) this.game.getLeft() / this.TILE_SIZE - this.x) / this.TILE_SIZE) - 1);
                 j <= Math.min(this.map[i].length - 1, Math.floor(((float) this.game.getRight() / this.game.SCALE - this.x) / this.TILE_SIZE));
                 j++) {
                for (Tile t : this.map[i][j]) {
                    pq.add(new Pair<>(t, new Point(j, i)));
                }
            }
        }

        while (!pq.isEmpty()) {
            Object o = pq.poll();
            if (o instanceof Pair) {
                Pair<Tile, Point> pair = (Pair<Tile, Point>) o;
                Tile tile = pair.first;
                Point cords = pair.second;
                if (tile == Tile.empty)
                    continue;
                tile.withScale(this.game.SCALE).draw(canvas,
                        (this.x + cords.x * this.TILE_SIZE) * this.game.SCALE,
                        (this.y + cords.y * this.TILE_SIZE) * this.game.SCALE,
                        null);
            } else if (o instanceof Entity) {
                Entity entity = (Entity) o;
                entity.draw(canvas);
            }
        }

        // draw collisions
        if (this.game.isDebug()) {
            Paint paint = new Paint();
            paint.setColor(ContextCompat.getColor(this.game.getContext(), R.color.Red));
            for (int i = 0; i < this.map.length; i++) {
                for (int j = 0; j < this.map[i].length; j++) {
                    for (Tile t : this.map[i][j]) {
                        if (t != Tile.empty) {
                            t.move(this.x + j * this.TILE_SIZE, 500 + this.y + i * this.TILE_SIZE);
                            if (t.getCollision() != Collision.empty)
                                t.getCollision().draw(canvas, paint);
                            if (t.getCollisionTop() != Collision.empty)
                                t.getCollisionTop().draw(canvas, paint);
                            if (t.getCollisionDown() != Collision.empty)
                                t.getCollisionDown().draw(canvas, paint);
                        }
                    }
                }
            }

            for (Entity e : entities) {
                e.getCollision().move(e.getX(), 500 + e.getY());
                e.getCollision().draw(canvas, paint);
            }
        }
    }

    private int getEvaluation(Object o1, Object o2) {
        Prioritised p1 = getPrioritised(o1), p2 = getPrioritised(o2);

        if (p1.z != p2.z) {
            return p1.z - p2.z;
        } else {
            return p1.y + p1.down - p2.y - p2.down;
        }
    }

    @SuppressWarnings("unchecked")
    private Prioritised getPrioritised(Object o) {
        Prioritised p = new Prioritised();
        if (o instanceof Entity) {
            Entity entity = (Entity) o;
            p.z = entity.getZ();
            p.down = entity.getDown() * this.game.SCALE;
            p.left = entity.getLeft() * this.game.SCALE;
            p.x = Math.round(entity.getX() * this.game.SCALE);
            p.y = Math.round(entity.getY() * this.game.SCALE);
        } else {
            Pair<Tile, Point> pair = (Pair<Tile, Point>) o;
            p.z = pair.first.getZ();
            p.down = pair.first.getDown() * this.game.SCALE;
            p.left = pair.first.getLeft() * this.game.SCALE;
            p.x = Math.round((pair.second.x * this.TILE_SIZE + this.x) * this.game.SCALE);
            p.y = Math.round((pair.second.y * this.TILE_SIZE + this.y) * this.game.SCALE);
        }

        return p;
    }

    public LineF getIntersector(Entity e) {
        for (int i = Math.max(Math.round((e.getY() - this.y) / this.TILE_SIZE) - 1, 0);
             i <= Math.min(Math.round((e.getY() - this.y) / this.TILE_SIZE) + 1, this.map.length - 1); i++) {
            for (int j = Math.max(Math.round((e.getX() - this.x) / this.TILE_SIZE) - 1, 0);
                 j <= Math.min(Math.round((e.getX() - this.x) / this.TILE_SIZE) + 1, this.map[i].length - 1);
                 j++) {
                for (Tile tile : this.map[i][j]) {
                    if (tile == Tile.empty)
                        continue;
                    tile.move(this.x + j * this.TILE_SIZE,
                            this.y + i * this.TILE_SIZE);
                    LineF intersector = tile.getIntersector(e);
                    if (intersector != null) {
                        return intersector;
                    }
                }
            }
        }

        return null;
    }

    public List<Tile> getTiles(int i, int j) {
        return this.map[i][j];
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    private static class Prioritised {
        public int z;
        public int down;
        public int left;
        public int x;
        public int y;
    }
}
