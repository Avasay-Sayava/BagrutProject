package com.avasaysayava.bagrutproject.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.GameMap;
import com.avasaysayava.bagrutproject.util.Util;

public class LevelPreview extends Game {
    private GameMap map;
    private Bitmap vignetteBitmap;

    public LevelPreview(Context context) {
        this(context, null);
    }

    public LevelPreview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LevelPreview(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public LevelPreview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes, Constants.PREVIEW_UPS, Constants.PREVIEW_SCALE, new Paint());

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        holder.setFormat(PixelFormat.RGBA_8888);

        loadMap(null);

        this.textPaint.setColor(Color.WHITE);
        this.textPaint.setTextSize(20);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        this.vignetteBitmap = null;

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        this.vignetteBitmap = Util.generateVignette(width, height);
        loadMap();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
    }

    private void loadMap() {
        Canvas canvas = null;
        SurfaceHolder holder = getHolder();

        if (this.map != null) {
            this.map.clear();
        }

        // Trying to update & render the level
        try {
            canvas = holder.lockCanvas();
            synchronized (holder) {
                draw(canvas);
            }
        } catch (IllegalArgumentException ignored) {
        } finally {
            if (canvas != null) {
                try {
                    holder.unlockCanvasAndPost(canvas);
                } catch (Exception ignored) {
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (canvas == null) return;

        super.draw(canvas);
        canvas.drawColor(ContextCompat.getColor(getContext(), R.color.DimGray));

        if (this.map == null) {
            String text = getContext().getString(R.string.no_preview);
            float previewX = (getWidth() - this.textPaint.measureText(text)) / 2f;
            float previewY = 40 + (getHeight() + this.textPaint.getTextSize()) / 2f;
            canvas.drawText(text, previewX, previewY, this.textPaint);
        } else {
            float previewX = (getWidth() - this.SCALE * this.map.TILE_SIZE * this.map.getColumns()) / 2f;
            float previewY = (getHeight() - this.SCALE * this.map.TILE_SIZE * this.map.getRows()) / 2f;
            this.map.move(previewX, previewY);

            this.map.setGame(this);
            this.map.draw(canvas);
        }

        if (this.vignetteBitmap != null) {
            canvas.drawBitmap(this.vignetteBitmap, getLeft(), getTop(), null);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void onStart() {
        loadMap();
    }

    @Override
    public void onResume() {
        loadMap();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public boolean isDebug() {
        return false;
    }

    @Override
    public boolean isGraph() {
        return false;
    }

    @Override
    public void onCompleted() {

    }

    public void loadMap(GameMap map) {
        if (map == null) this.map = null;
        else this.map = new GameMap(this, map.getMap(), map.getLayers(), map.TILE_SIZE, map.getX(), map.getY());
        loadMap();
    }
}
