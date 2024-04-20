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

    public static boolean isCounterClockWise(PointF A, PointF B, PointF C) {
        return (C.y - A.y) * (B.x - A.x) > (B.y - A.y) * (C.x - A.x);
    }

    public static boolean doesIntersect(PointF A, PointF B, PointF C, PointF D) {
        return isCounterClockWise(A, C, D) != isCounterClockWise(B, C, D) && isCounterClockWise(A, B, C) != isCounterClockWise(A, B, D);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public static void timeout(long nanos) {
        for (long time = System.nanoTime(); time + nanos <= System.nanoTime(); )
            ;
    }

    public static Bitmap generateVignette(int width, int height) {
        Bitmap vignetteBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(vignetteBitmap);

        // calculate the center and radius of the gradient
        float centerX = width / 2f;
        float centerY = height / 2f;
        float radius = Math.max(width, height) / 2f;

        // create the radial gradient
        Shader shader = new RadialGradient(centerX,
                centerY,
                radius,
                Color.TRANSPARENT,
                Util.withAlpha(Color.BLACK, 0x60),
                Shader.TileMode.CLAMP);

        // set up the paint for drawing the gradient
        Paint paint = new Paint();
        paint.setShader(shader);

        // draw the gradient onto the bitmap
        canvas.drawRect(0, 0, width, height, paint);

        return vignetteBitmap;
    }
}
