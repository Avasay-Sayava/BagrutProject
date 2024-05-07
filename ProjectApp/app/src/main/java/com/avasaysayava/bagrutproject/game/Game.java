package com.avasaysayava.bagrutproject.game;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.avasaysayava.bagrutproject.game.audio.TileSoundPreloader;
import com.avasaysayava.bagrutproject.game.graphic.tileset.FloorTileSet;
import com.avasaysayava.bagrutproject.game.graphic.tileset.GlyphFloorTileSet;
import com.avasaysayava.bagrutproject.game.graphic.tileset.GroundTileSet;
import com.avasaysayava.bagrutproject.game.graphic.tileset.PlayerTileSet;
import com.avasaysayava.bagrutproject.game.graphic.tileset.StructuresTileSet;
import com.avasaysayava.bagrutproject.game.graphic.tileset.TileSet;
import com.avasaysayava.bagrutproject.game.graphic.tileset.WallsTileSet;

import java.util.function.LongConsumer;

public abstract class Game extends SurfaceView implements SurfaceHolder.Callback {
    public final int SCALE, UPS;
    public final Paint textPaint;
    public final TileSoundPreloader tileSoundPreloader;
    public final TileSet playerTileSet, floorTileSet, glyphFloorTileSet, groundTileSet, structuresTileSet, wallsTileSet;
    protected LongConsumer onCompleteListener = time -> {
    };

    public Game(Context context, int UPS, int SCALE, Paint textPaint) {
        this(context, null, UPS, SCALE, textPaint);
    }

    public Game(Context context, AttributeSet attrs, int UPS, int SCALE, Paint textPaint) {
        this(context, attrs, 0, UPS, SCALE, textPaint);
    }

    public Game(Context context, AttributeSet attrs, int defStyleAttr, int UPS, int SCALE, Paint textPaint) {
        this(context, attrs, defStyleAttr, 0, UPS, SCALE, textPaint);
    }

    public Game(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, int UPS, int SCALE, Paint textPaint) {
        super(context, attrs, defStyleAttr, defStyleRes);

        this.UPS = UPS;
        this.SCALE = SCALE;
        this.textPaint = textPaint;

        this.tileSoundPreloader = new TileSoundPreloader(getContext());
        this.playerTileSet = new PlayerTileSet(getContext());
        this.floorTileSet = new FloorTileSet(getContext());
        this.glyphFloorTileSet = new GlyphFloorTileSet(getContext());
        this.groundTileSet = new GroundTileSet(getContext());
        this.structuresTileSet = new StructuresTileSet(getContext());
        this.wallsTileSet = new WallsTileSet(getContext());
    }

    @Override
    public abstract void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height);

    @Override
    public abstract void surfaceCreated(@NonNull SurfaceHolder holder);

    @Override
    public abstract void surfaceDestroyed(@NonNull SurfaceHolder holder);

    public void setOnCompleteListener(LongConsumer consumer) {
        this.onCompleteListener = consumer;
    }

    public abstract void update();

    public abstract void onStart();

    public abstract void onResume();

    public abstract void onPause();

    public abstract void onStop();

    public abstract void onDestroy();

    public abstract boolean isDebug();

    public abstract boolean isGraph();

    public abstract void onCompleted();
}
