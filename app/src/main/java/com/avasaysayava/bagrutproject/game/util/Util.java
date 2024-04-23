package com.avasaysayava.bagrutproject.game.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.net.Uri;

import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;

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
        return lower < num && num < upper;
    }

    public static int withAlpha(int color, int alpha) {
        return color & (alpha << 24 | 0x00ffffff);
    }

    public static boolean isCounterClockWise(PointF A, PointF B, PointF C) {
        return (C.y - A.y) * (B.x - A.x) > (B.y - A.y) * (C.x - A.x);
    }

    // IMPORTANT: wont work for parallel lines
    public static boolean doesIntersect(PointF A, PointF B, PointF C, PointF D) {
        return isCounterClockWise(A, C, D) != isCounterClockWise(B, C, D) && isCounterClockWise(A, B, C) != isCounterClockWise(A, B, D);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public static void timeout(long nanos) {
        for (long time = System.nanoTime(); time + nanos < System.nanoTime(); )
            ;
    }

    public static @NonNull Bitmap generateVignette(int width, int height) {
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

    public static Uri ResIdToUri(@NonNull Context context, @AnyRes int resId) throws Resources.NotFoundException {
        Resources res = context.getResources();

        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + res.getResourcePackageName(resId)
                + '/' + res.getResourceTypeName(resId)
                + '/' + res.getResourceEntryName(resId));
    }

    public static <T> T randomElement(T[] arr) {
        return arr[(int) (Math.random() * arr.length)];
    }
}
