package com.avasaysayava.bagrutproject.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.Shader;

import androidx.annotation.NonNull;

import com.avasaysayava.bagrutproject.game.graphic.gamemap.GameMap;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.levelmap.Level1Map;

public class Util {

    public static boolean within(double lower, double num, double upper) {
        return lower <= num && num <= upper;
    }

    public static boolean between(double lower, double num, double upper) {
        return lower < num && num < upper;
    }

    public static int withAlpha(int color, int alpha) {
        return color & (alpha << 24 | 0x00ffffff);
    }

    public static boolean isCounterClockwise(PointF A, PointF B, PointF C) {
        return (C.y - A.y) * (B.x - A.x) > (B.y - A.y) * (C.x - A.x);
    }

    // IMPORTANT: wont work for parallel lines
    public static boolean doesIntersect(PointF A, PointF B, PointF C, PointF D) {
        return isCounterClockwise(A, C, D) != isCounterClockwise(B, C, D) && isCounterClockwise(A, B, C) != isCounterClockwise(A, B, D);
    }

    public static @NonNull Bitmap generateVignette(int width, int height) {
        Bitmap vignetteBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(vignetteBitmap);

        // calculate the center and radius of the gradient
        float centerX = width / 2f;
        float centerY = height / 2f;
        float radius = Math.max(width, height) / 2f;

        // create the radial gradient
        Shader shader = new RadialGradient(centerX, centerY, radius, Color.TRANSPARENT, Util.withAlpha(Color.BLACK, 0x60), Shader.TileMode.CLAMP);

        // set up the paint for drawing the gradient
        Paint paint = new Paint();
        paint.setShader(shader);

        // draw the gradient onto the bitmap
        canvas.drawRect(0, 0, width, height, paint);

        return vignetteBitmap;
    }

    public static <T> T randomElement(T[] arr) {
        return arr[(int) (Math.random() * arr.length)];
    }

    @SuppressLint("DefaultLocale")
    public static String timeToString(long millis) {
        long hours = millis / (1000 * 60 * 60);
        long minutes = (millis / (1000 * 60)) % 60;
        long seconds = (millis / 1000) % 60;
        millis %= 1000;
        if (hours > 99) return "99:59:59.999";
        return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, millis);
    }

    public static int getLevel(GameMap map) {
        if (map instanceof Level1Map) return 1;
        else return 2;
    }

    public static long stringToTime(String timeString) {
        String[] parts = timeString.split(":");
        String[] secondsAndMillis = parts[2].split("\\.");
        long hours = Long.parseLong(parts[0]);
        long minutes = Long.parseLong(parts[1]);
        long seconds = Long.parseLong(secondsAndMillis[0]);
        long millis = Long.parseLong(secondsAndMillis[1]);
        return ((hours * 60 + minutes) * 60 + seconds) * 1000 + millis;
    }
}
