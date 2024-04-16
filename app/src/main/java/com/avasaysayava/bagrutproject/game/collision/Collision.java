package com.avasaysayava.bagrutproject.game.collision;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Collision {
    public static final Collision empty = null;
    private final Path[] paths;

    public Collision(Path... paths) {
        this.paths = paths;
    }

    public boolean doesIntersect(Collision collision) {
        if (collision == Collision.empty) return false;
        for (Path p1 : this.paths) {
            for (Path p2 : collision.getPaths()) {
                if (p1.doesIntersect(p2)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void move(float x, float y) {
        for (Path p : this.paths) {
            p.move(x, y);
        }
    }

    public void translate(float dx, float dy) {
        for (Path p : this.paths) {
            p.translate(dx, dy);
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        for (Path p : this.paths) {
            p.draw(canvas, paint);
        }
    }

    public Path[] getPaths() {
        return this.paths;
    }
}
