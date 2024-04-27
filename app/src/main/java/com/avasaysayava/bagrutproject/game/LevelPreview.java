package com.avasaysayava.bagrutproject.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.avasaysayava.bagrutproject.Constants;
import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.Static;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.GameMap;

public class LevelPreview extends Game {
    private GameMap map;

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
        super(context, attrs, defStyleAttr, defStyleRes, 0, Constants.PREVIEW_SCALE, new Paint());

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        holder.setFormat(PixelFormat.RGBA_8888);

        loadMap(Static.currentMap);

        this.textPaint.setColor(Color.WHITE);
        this.textPaint.setTextSize(20);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        Log.d("LevelPreview", "surfaceCreated");
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        Log.d("LevelPreview", "surfaceChanged[" + width + "x" + height + "]");
        loadMap();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        Log.d("LevelPreview", "surfaceDestroyed");
    }

    private void loadMap() {
        Log.d("LevelPreview", "loadMap");

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
        } catch (IllegalArgumentException e) {
            Log.e("LevelPreview", e.getMessage(), e);
        } finally {
            if (canvas != null) {
                try {
                    holder.unlockCanvasAndPost(canvas);
                } catch (Exception e) {
                    Log.e("LevelPreview", e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (canvas == null)
            return;

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
    }

    @Override
    public void update() {

    }

    @Override
    public void onStart() {
        Log.d("LevelPreview", "onStart");

        loadMap();
    }

    @Override
    public void onResume() {
        Log.d("LevelPreview", "onResume");

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

    public void loadMap(GameMap map) {
        if (map == null) this.map = null;
        else this.map = new GameMap(this, map.getMap(), map.TILE_SIZE, map.getX(), map.getY());
        loadMap();
    }
}
