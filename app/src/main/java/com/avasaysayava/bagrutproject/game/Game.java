package com.avasaysayava.bagrutproject.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.game.entity.Player;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.GameMap;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.PropsMap;
import com.avasaysayava.bagrutproject.game.graphic.tileset.FloorTileSet;
import com.avasaysayava.bagrutproject.game.graphic.tileset.GroundTileSet;
import com.avasaysayava.bagrutproject.game.graphic.tileset.PlayerTileSet;
import com.avasaysayava.bagrutproject.game.graphic.tileset.PropsTileSet;
import com.avasaysayava.bagrutproject.game.graphic.tileset.StructuresTileSet;
import com.avasaysayava.bagrutproject.game.gui.Joystick;
import com.avasaysayava.bagrutproject.game.gui.MovableJoystick;

@SuppressLint("ViewConstructor")
public class Game extends SurfaceView implements SurfaceHolder.Callback {
    public final int SCALE, UPS;
    public final PlayerTileSet playerTileSet = new PlayerTileSet(getContext());
    public final FloorTileSet floorTileSet = new FloorTileSet(getContext());
    public final GroundTileSet groundTileSet = new GroundTileSet(getContext());
    public final PropsTileSet propsTileSet = new PropsTileSet(getContext());
    public final StructuresTileSet structuresTileSet = new StructuresTileSet(getContext());

    private final Joystick joystick;
    private final Player player;
    private final GameMap map;
    //    private final Audio audio;
    private final double[] fpsGraph = new double[400];
    private final double[] upsGraph = new double[400];
    private Bitmap vignetteBitmap;
    private GameLoop gameLoop;
    private boolean debugMode, graphMode;
    private int fpsI = 0;
    private int upsI = 0;

    public Game(Context context, int ups, int scale, boolean debugMode, boolean graphMode) {
        super(context);

        this.UPS = ups;
        this.SCALE = scale;
        this.debugMode = debugMode;
        this.graphMode = graphMode;

        createLoop();

        // initialize the game objects
        int joystickRadius = 100;
        this.joystick = new MovableJoystick(this, 2 * joystickRadius, getHeight() - 2 * joystickRadius, joystickRadius);
        this.map = new PropsMap(this, 0, 0);
        this.player = new Player(this, -32, -32, 0);

        // play music
//        this.audio = new Audio(context);
        // TODO: find background music and play it

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        this.vignetteBitmap = null;

        setFocusable(true);
    }

//    private void loopMusic(int id) {
//        playMusic(id);
//        this.audio.loop();
//    }
//
//    private void playMusic(int id) {
//        this.audio.setFile(id);
//        this.audio.play();
//    }
//
//    private void stopMusic() {
//        this.audio.stop();
//        this.audio.kill();
//    }

    @Override
    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (this.joystick.isPressed(event)) {
                    this.joystick.enable(event);
                    return true;
                }
            case MotionEvent.ACTION_MOVE:
                if (this.joystick.isEnabled()) {
                    this.joystick.press(event);
                    return true;
                }
            case MotionEvent.ACTION_UP:
                if (this.joystick.isEnabled()) {
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
        Log.d("app/event", "surfaceCreated");

        // if the game loop is terminated, create a new one
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            createLoop();
        }

        // resume the game loop
        this.gameLoop.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        Log.d("app/event", "surfaceChanged[" + width + "x" + height + "]");

        // if screen size changed
        this.vignetteBitmap = Util.generateVignette(width, height);

        float joystickRadius = joystick.getRadius();
        this.joystick.move(2 * joystickRadius, getHeight() - 2 * joystickRadius);

        // if the game loop is terminated, create a new one
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            createLoop();
        }
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        Log.d("app/event", "surfaceDestroyed");
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawColor(ContextCompat.getColor(getContext(), R.color.DimGray));

        this.map.draw(canvas, this.player);

        // apply vignette effect
        if (this.vignetteBitmap != null) {
            canvas.drawBitmap(this.vignetteBitmap, getLeft(), getTop(), null);
        }

        this.joystick.draw(canvas);

        Paint textPaint = new Paint();
        textPaint.setTextSize(50);
        textPaint.setColor(ContextCompat.getColor(getContext(), R.color.White));

        if (isDebug()) {
            drawUPS(canvas, textPaint);
            drawFPS(canvas, textPaint);
            drawBounds(canvas, textPaint);
            drawPlayerVelocity(canvas, textPaint);
        }
    }

    @SuppressLint("DefaultLocale")
    private void drawUPS(Canvas canvas, Paint paint) {
        String avgUPS = String.format("%.5f", this.gameLoop.getAvgUPS()) + ", " + String.format("%.5f", this.gameLoop.getFixedUPS());
        canvas.drawText("UPS: " + avgUPS, 10, 50, paint);

        this.upsGraph[this.upsI++] = this.gameLoop.getAvgUPS();
        this.upsI %= this.upsGraph.length;

        if (this.graphMode) {
            Paint graphPaint = new Paint();
            for (int i = this.upsI; i < this.upsGraph.length + this.upsI; i++) {
                int[] rgb = getGradientColor(this.upsGraph[i % this.upsGraph.length]);
                graphPaint.setARGB(120, rgb[0], rgb[1], rgb[2]);
                if (this.upsGraph[i % this.upsGraph.length] != 0)
                    canvas.drawLine(getRight() - this.upsGraph.length + i - this.upsI,
                            (float) (getBottom() - this.upsGraph[i % this.upsGraph.length] * 2 * 60.0 / this.UPS),
                            getRight() - this.upsGraph.length + i - this.upsI,
                            getBottom(),
                            graphPaint);
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private void drawFPS(Canvas canvas, Paint paint) {
        String avgFPS = String.format("%.5f", this.gameLoop.getAvgFPS()) + ", " + String.format("%.5f", this.gameLoop.getFixedFPS());
        canvas.drawText("FPS: " + avgFPS, 10, 100, paint);

        this.fpsGraph[this.fpsI++] = this.gameLoop.getAvgFPS();
        this.fpsI %= this.fpsGraph.length;

        if (this.graphMode) {
            Paint graphPaint = new Paint();
            for (int i = this.fpsI; i < this.fpsGraph.length + this.fpsI; i++) {
                int[] rgb = getGradientColor(this.fpsGraph[i % this.fpsGraph.length]);
                graphPaint.setARGB(120, rgb[0], rgb[1], rgb[2]);
                if (this.fpsGraph[i % this.fpsGraph.length] != 0)
                    canvas.drawLine(getRight() - this.fpsGraph.length + i - this.fpsI,
                            (float) (getBottom() - this.fpsGraph[i % this.fpsGraph.length] * 2 * 60.0 / this.UPS),
                            getRight() - this.fpsGraph.length + i - this.fpsI,
                            getBottom(),
                            graphPaint);
            }
        }
    }

    private int[] getGradientColor(double value) {
        value = Math.max(0, Math.min(this.UPS, value));
        value = Math.max(0, Math.min(this.UPS, value));

        int red = Math.min(Math.max((int) (255 * (2 * (1 - value / this.UPS))), 0), 255);
        int green = Math.min(Math.max((int) (255 * (2 * value / this.UPS)), 0), 255);

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
        this.player.update();
    }

    public boolean isDebug() {
        return this.debugMode;
    }

    public GameMap getMap() {
        return this.map;
    }

    public GameLoop getGameLoop() {
        return this.gameLoop;
    }

    public Joystick getJoystick() {
        return this.joystick;
    }

    public void onStart() {

    }

    public void onResume() {

    }

    public void onPause() {
        this.joystick.reset();
        this.gameLoop.pause();
    }

    public void onStop() {

    }

    public void onDestroy() {

    }

    public void createLoop() {
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFormat(PixelFormat.RGBA_8888);
        this.gameLoop = new GameLoop(this, surfaceHolder, this.UPS);
        Log.d("game/loop", "created");
    }
}
