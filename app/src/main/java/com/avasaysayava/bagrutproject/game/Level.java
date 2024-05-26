package com.avasaysayava.bagrutproject.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.game.entity.Player;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.GameMap;
import com.avasaysayava.bagrutproject.game.gui.Joystick;
import com.avasaysayava.bagrutproject.game.gui.MovableJoystick;
import com.avasaysayava.bagrutproject.game.thread.JobScheduler;
import com.avasaysayava.bagrutproject.util.Util;

public class Level extends Game {
    private final Player player;
    private final double[] fpsGraph, upsGraph;
    private GameMap map;
    private Joystick joystick;
    private Bitmap vignetteBitmap;
    private boolean debugMode, graphMode, paused;
    private JobScheduler jobScheduler;
    private int fpsIndex, upsIndex;
    private long t_total, t_start;

    public Level(Context context) {
        this(context, null);
    }

    public Level(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Level(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public Level(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context,
                attrs,
                defStyleAttr,
                defStyleRes,
                Constants.LEVEL_UPS,
                Util.bound(1, (int) (3.5f * context.getResources().getDisplayMetrics().density), Constants.LEVEL_SCALE),
                new Paint());

        this.textPaint.setTextSize(50);
        this.textPaint.setColor(ContextCompat.getColor(getContext(), R.color.White));

        this.debugMode = false;
        this.graphMode = false;
        this.paused = false;

        this.map = null;

        // Register callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFormat(PixelFormat.RGBA_8888);

        // initialize schedulers
        createJobScheduler();

        // initialize the level objects
        createJoystick();
        this.player = new Player(this, 0, 0, 0);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        this.vignetteBitmap = null;

        this.upsIndex = this.fpsIndex = 0;
        this.upsGraph = new double[400];
        this.fpsGraph = new double[400];

        this.t_start = System.currentTimeMillis();
        this.t_total = 0;

        setFocusable(true);
    }

    @Override
    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!this.paused && this.joystick.isPressed(event)) {
                    this.joystick.enable(event);
                    return true;
                }
            case MotionEvent.ACTION_MOVE:
                if (!this.paused && this.joystick.isEnabled()) {
                    this.joystick.press(event);
                    return true;
                }
            case MotionEvent.ACTION_UP:
                if (!this.paused && this.joystick.isEnabled()) {
                    this.joystick.disable();
                    this.joystick.reset();
                    return true;
                }
            default:
                return super.onTouchEvent(event);
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        // if the job scheduler is terminated, create a new one
        if (this.jobScheduler.getState().equals(Thread.State.TERMINATED)) {
            createJobScheduler();
        }

        // resume the level loop
        this.jobScheduler.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        // if screen size changed
        this.vignetteBitmap = Util.generateVignette(width, height);
        createJoystick();
        holder.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
    }

    @Override
    public void draw(Canvas canvas) {
        if (canvas == null || this.map == null) return;

        super.draw(canvas);

        canvas.drawColor(ContextCompat.getColor(getContext(), R.color.DimGray));

        this.map.draw(canvas, this.player);

        // apply vignette effect
        if (this.vignetteBitmap != null) {
            canvas.drawBitmap(this.vignetteBitmap, getLeft(), getTop(), null);
        }

        if (isDebug()) {
            drawUPS(canvas, this.textPaint);
            drawFPS(canvas, this.textPaint);
            drawBounds(canvas, this.textPaint);
            drawPlayerVelocity(canvas, this.textPaint);
        }

        this.joystick.draw(canvas);

        if (this.paused) {
            canvas.drawColor(Util.withAlpha(Color.BLACK, 0x80));
        }
    }

    @SuppressLint("DefaultLocale")
    private void drawUPS(Canvas canvas, Paint paint) {
        String avgUPS = String.format("%.5f", this.jobScheduler.getAvgUPS()) + ", " + String.format("%.5f", this.jobScheduler.getFramedUPS());
        canvas.drawText("UPS: " + avgUPS, 10, 50, paint);

        this.upsGraph[this.upsIndex++] = this.jobScheduler.getAvgUPS();
        this.upsIndex %= this.upsGraph.length;

        if (this.graphMode) {
            Paint graphPaint = new Paint();
            for (int i = this.upsIndex; i < this.upsGraph.length + this.upsIndex; i++) {
                int[] rgb = getGradientColor(this.upsGraph[i % this.upsGraph.length]);
                graphPaint.setARGB(120, rgb[0], rgb[1], rgb[2]);
                if (this.upsGraph[i % this.upsGraph.length] != 0)
                    canvas.drawLine(getRight() - this.upsGraph.length + i - this.upsIndex, (float) (getBottom() - this.upsGraph[i % this.upsGraph.length] * 2 * 60.0 / this.UPS), getRight() - this.upsGraph.length + i - this.upsIndex, getBottom(), graphPaint);
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private void drawFPS(Canvas canvas, Paint paint) {
        String avgFPS = String.format("%.5f", this.jobScheduler.getAvgFPS()) + ", " + String.format("%.5f", this.jobScheduler.getFramedFPS());
        canvas.drawText("FPS: " + avgFPS, 10, 100, paint);

        this.fpsGraph[this.fpsIndex++] = this.jobScheduler.getAvgFPS();
        this.fpsIndex %= this.fpsGraph.length;

        if (this.graphMode) {
            Paint graphPaint = new Paint();
            for (int i = this.fpsIndex; i < this.fpsGraph.length + this.fpsIndex; i++) {
                int[] rgb = getGradientColor(this.fpsGraph[i % this.fpsGraph.length]);
                graphPaint.setARGB(120, rgb[0], rgb[1], rgb[2]);
                if (this.fpsGraph[i % this.fpsGraph.length] != 0)
                    canvas.drawLine(getRight() - this.fpsGraph.length + i - this.fpsIndex, (float) (getBottom() - this.fpsGraph[i % this.fpsGraph.length] * 2 * 60.0 / this.UPS), getRight() - this.fpsGraph.length + i - this.fpsIndex, getBottom(), graphPaint);
            }
        }
    }

    private int[] getGradientColor(double value) {
        value = Util.bound(0, this.UPS, value);

        int red = Util.bound(0, (int) (255 * (2 * (1 - value / this.UPS))), 255);
        int green = Util.bound(0, (int) (255 * (2 * value / this.UPS)), 255);

        return new int[]{red, green, 0};
    }

    private void drawBounds(Canvas canvas, Paint paint) {
        canvas.drawText(getLeft() + "," + getTop() + "," + getRight() + "," + getBottom() + " (" + getWidth() + "x" + getHeight() + ")", 10, 150, paint);
    }

    @SuppressLint("DefaultLocale")
    private void drawPlayerVelocity(Canvas canvas, Paint paint) {
        canvas.drawText("Vx: " + String.format("%.5f", this.player.getVx()), 10, 200, paint);
        canvas.drawText("Vy: " + String.format("%.5f", this.player.getVy()), 10, 250, paint);
    }

    public void update() {
        if (!this.paused) {
            this.player.update();
            this.map.update(this.player);
        }
    }

    @Override
    public boolean isDebug() {
        return this.debugMode;
    }

    public GameMap getMap() {
        return this.map;
    }

    public JobScheduler getJobScheduler() {
        return this.jobScheduler;
    }

    public Joystick getJoystick() {
        return this.joystick;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        this.joystick.reset();
    }

    @Override
    public void onPause() {
        pause();
        this.jobScheduler.pause();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    private void createJobScheduler() {
        this.jobScheduler = new JobScheduler(this, this.UPS);
    }

    private void createJoystick() {
        float joystickRadius = 60 * getResources().getDisplayMetrics().density;
        this.joystick = new MovableJoystick(this, 2 * joystickRadius, getHeight() - 2 * joystickRadius, joystickRadius);
    }

    public boolean isPaused() {
        return this.paused;
    }

    public void pause() {
        this.paused = true;
        this.t_total = System.currentTimeMillis() - this.t_start;
    }

    public void resume() {
        this.joystick.reset();
        this.paused = false;
        this.t_start = System.currentTimeMillis() - this.t_total;
        this.t_total = 0;
    }

    public void toggleDebug() {
        this.debugMode = !this.debugMode;
    }

    public void toggleGraph() {
        this.graphMode = !this.graphMode;
    }

    @Override
    public boolean isGraph() {
        return this.graphMode;
    }

    @Override
    public void onCompleted() {
        if (this.onCompleteListener != null) {
            if (this.t_total == 0) this.t_total = System.currentTimeMillis() - this.t_start;
            this.onCompleteListener.accept(this.t_total);
        }
    }

    public void loadMap(GameMap map) {
        this.map = map;
        this.map.setGame(this);
        this.map.clear();
        this.map.prepare();
    }
}
