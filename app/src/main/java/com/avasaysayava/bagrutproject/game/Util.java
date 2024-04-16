package com.avasaysayava.bagrutproject.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.Shader;

public class Util {
    public static double random(double lower, double upper) {
        return Math.random() * (upper - lower) + lower;
    }

    public static double random(double upper) {
        return random(0, upper);
    }

    public static boolean within(double lower, double num, double upper) {
        return lower <= num && num <= upper;
    }

    public static boolean between(double lower, double num, double upper) {
        return lower <= num && num <= upper;
    }

    public static int withAlpha(int color, int alpha) {
        return color & (alpha << 24 | 0x00ffffff);
    }

    public static boolean doesIntersect(PointF A, PointF B, PointF C, PointF D) {
        float a1 = A.y - B.y, b1 = A.x - B.x, c1 = A.y * b1 - A.x * a1;
        float a2 = C.y - D.y, b2 = C.x - D.x, c2 = C.y * b2 - D.x * a2;

        PointF P = new PointF((b1 * c2 - b2 * c1) / (b2 * a1 - b1 * a2), (a1 * c2 - a2 * c1) / (a1 * b2 - a2 * b1));

        float tx = (P.x - C.x) / (D.x - C.x), sx = (P.x - A.x) / (B.x - A.x);
        float ty = (P.y - C.y) / (D.y - C.y), sy = (P.y - A.y) / (B.y - A.y);
        if (!Float.isFinite(tx))
            tx = ty;
        if (!Float.isFinite(sx))
            sx = sy;

        return Util.within(0, tx, 1) && Util.within(0, sx, 1);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public static void sleep(long nanos) {
        for (long time = System.nanoTime(); time + nanos <= System.nanoTime(); )
            ;
    }

    public static Bitmap generateVignette(int width, int height) {
        Bitmap vignetteBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(vignetteBitmap);

        // calculate the center and radius of the gradient
        float centerX = width / 2f;
        float centerY = height / 2f;
        float radius = Math.min(width, height) * 2;

        // create the radial gradient
        Shader shader = new RadialGradient(centerX,
                centerY,
                radius,
                Color.TRANSPARENT,
                Color.BLACK,
                Shader.TileMode.CLAMP);

        // set up the paint for drawing the gradient
        Paint paint = new Paint();
        paint.setShader(shader);

        // draw the gradient onto the bitmap
        canvas.drawRect(0, 0, width, height, paint);

        return vignetteBitmap;
    }
}
