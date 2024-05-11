package com.avasaysayava.bagrutproject.game.collision;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.avasaysayava.bagrutproject.util.struct.LineF;

public class Collision {
    public static final Collision empty = null;
    private final Path[] paths;

    public Collision(Path... paths) {
        this.paths = paths;
    }

    public LineF getIntersector(Collision collision) {
        if (collision == Collision.empty) return null;
        for (Path p1 : this.paths) {
            for (Path p2 : collision.getPaths()) {
                LineF intersector = p1.getIntersector(p2);
                if (intersector != null) {
                    return intersector;
                }
            }
        }

        return null;
    }

    public void move(float x, float y) {
        for (Path p : this.paths) {
            p.move(x, y);
        }
    }

    public void draw(Canvas canvas, Paint paint, int scale) {
        for (Path p : this.paths) {
            p.draw(canvas, paint, scale);
        }
    }

    public Path[] getPaths() {
        return this.paths;
    }
}
