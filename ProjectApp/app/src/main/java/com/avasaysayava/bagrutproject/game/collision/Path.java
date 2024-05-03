package com.avasaysayava.bagrutproject.game.collision;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.avasaysayava.bagrutproject.game.struct.LineF;
import com.avasaysayava.bagrutproject.util.Util;

public class Path {
    private final PointF[] points;
    private float dx, dy;

    public Path(PointF... points) {
        this.points = points;
        this.dx = this.dy = 0;
    }

    public static Path polygon(PointF... vertexes) {
        PointF[] path = new PointF[vertexes.length + 1];
        System.arraycopy(vertexes, 0, path, 0, vertexes.length);
        path[vertexes.length] = new PointF(vertexes[0].x, vertexes[0].y);
        return new Path(path);
    }

    public LineF getIntersector(Path path) {
        PointF[] pPoints = path.getPoints();
        for (int i = 1; i < this.points.length; i++)
            for (int j = 1; j < pPoints.length; j++)
                if (Util.doesIntersect(this.points[i - 1], this.points[i], pPoints[j - 1], pPoints[j]))
                    return new LineF(this.points[i], this.points[i - 1]);
        return null;
    }

    public void draw(Canvas canvas, Paint paint, int scale) {
        for (int i = 1; i < this.points.length; i++) {
            canvas.drawLine(this.points[i - 1].x * scale, this.points[i - 1].y * scale, this.points[i].x * scale, this.points[i].y * scale, paint);
        }
    }

    public void move(float x, float y) {
        for (PointF point : this.points)
            point.offset(x - this.dx, y - this.dy);
        this.dx = x;
        this.dy = y;
    }

    public PointF[] getPoints() {
        return this.points;
    }
}