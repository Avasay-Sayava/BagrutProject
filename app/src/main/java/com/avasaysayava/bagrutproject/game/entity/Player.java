package com.avasaysayava.bagrutproject.game.entity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;

import androidx.core.content.ContextCompat;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.game.Game;
import com.avasaysayava.bagrutproject.game.Util;
import com.avasaysayava.bagrutproject.game.collision.Collision;
import com.avasaysayava.bagrutproject.game.collision.Path;
import com.avasaysayava.bagrutproject.game.graphic.Tile;
import com.avasaysayava.bagrutproject.game.gui.Joystick;
import com.avasaysayava.bagrutproject.game.sound.Audio;
import com.avasaysayava.bagrutproject.game.sound.PlayerAudio;

public class Player extends Entity {
    private final float maxSpeed;
    private final Shadow shadow;
    private final Collision collision;
    private double wobble;
    private double Vx, Vy;
    private int lastId;
    private Audio audio;

    public Player(Game game, float x, float y) {
        super(game, .6, x, y);
        this.maxSpeed = 80;
        this.lastId = 0;
        this.collision = new Collision(Path.polygon(new PointF(5, 33), new PointF(25, 33), new PointF(25, 47), new PointF(5, 47)));
        this.shadow = new Shadow(this.game, this, this.game.playerTileSet.getTile(4));
        this.audio = new PlayerAudio(this.game, this, this.game.getContext());
    }

    @Override
    public void draw(Canvas canvas) {
        if (this.game.isDebug()) {
            Paint paint = new Paint();

            // mark player's tile
            paint.setColor(ContextCompat.getColor(this.game.getContext(), R.color.Blue));
            canvas.drawLine((Math.round((this.x - this.game.getMap().getX()) / this.game.getMap().TILE_SIZE) * this.game.getMap().TILE_SIZE + this.game.getMap().getX()) * this.game.SCALE,
                    (Math.round((this.y - this.game.getMap().getY() + 24) / this.game.getMap().TILE_SIZE) * this.game.getMap().TILE_SIZE + this.game.getMap().getY()) * this.game.SCALE,
                    (Math.round((this.x - this.game.getMap().getX()) / this.game.getMap().TILE_SIZE + 1) * this.game.getMap().TILE_SIZE + this.game.getMap().getX()) * this.game.SCALE,
                    (Math.round((this.y - this.game.getMap().getY() + 24) / this.game.getMap().TILE_SIZE) * this.game.getMap().TILE_SIZE + this.game.getMap().getY()) * this.game.SCALE,
                    paint);
            canvas.drawLine((Math.round((this.x - this.game.getMap().getX()) / this.game.getMap().TILE_SIZE + 1) * this.game.getMap().TILE_SIZE + this.game.getMap().getX()) * this.game.SCALE,
                    (Math.round((this.y - this.game.getMap().getY() + 24) / this.game.getMap().TILE_SIZE) * this.game.getMap().TILE_SIZE + this.game.getMap().getY()) * this.game.SCALE,
                    (Math.round((this.x - this.game.getMap().getX()) / this.game.getMap().TILE_SIZE + 1) * this.game.getMap().TILE_SIZE + this.game.getMap().getX()) * this.game.SCALE,
                    (Math.round((this.y - this.game.getMap().getY() + 24) / this.game.getMap().TILE_SIZE + 1) * this.game.getMap().TILE_SIZE + this.game.getMap().getY()) * this.game.SCALE,
                    paint);
            canvas.drawLine((Math.round((this.x - this.game.getMap().getX()) / this.game.getMap().TILE_SIZE + 1) * this.game.getMap().TILE_SIZE + this.game.getMap().getX()) * this.game.SCALE,
                    (Math.round((this.y - this.game.getMap().getY() + 24) / this.game.getMap().TILE_SIZE + 1) * this.game.getMap().TILE_SIZE + this.game.getMap().getY()) * this.game.SCALE,
                    (Math.round((this.x - this.game.getMap().getX()) / this.game.getMap().TILE_SIZE) * this.game.getMap().TILE_SIZE + this.game.getMap().getX()) * this.game.SCALE,
                    (Math.round((this.y - this.game.getMap().getY() + 24) / this.game.getMap().TILE_SIZE + 1) * this.game.getMap().TILE_SIZE + this.game.getMap().getY()) * this.game.SCALE,
                    paint);
            canvas.drawLine((Math.round((this.x - this.game.getMap().getX()) / this.game.getMap().TILE_SIZE) * this.game.getMap().TILE_SIZE + this.game.getMap().getX()) * this.game.SCALE,
                    (Math.round((this.y - this.game.getMap().getY() + 24) / this.game.getMap().TILE_SIZE) * this.game.getMap().TILE_SIZE + this.game.getMap().getY()) * this.game.SCALE,
                    (Math.round((this.x - this.game.getMap().getX()) / this.game.getMap().TILE_SIZE) * this.game.getMap().TILE_SIZE + this.game.getMap().getX()) * this.game.SCALE,
                    (Math.round((this.y - this.game.getMap().getY() + 24) / this.game.getMap().TILE_SIZE + 1) * this.game.getMap().TILE_SIZE + this.game.getMap().getY()) * this.game.SCALE,
                    paint);
        }

        // draw player's sprite according to his direction
        int id = 2;
        Joystick joystick = this.game.getJoystick();
        double angle = joystick.getAngle();
        if (Util.within(-55, angle, 55))
            id = 3;
        else if (Util.between(55, angle, 125))
            id = 0;
        else if (Util.between(-125, angle, -55))
            id = 1;
        if (joystick.getDistance() == 0)
            id = this.lastId;

        Tile tile = this.game.playerTileSet.getTile(id).withScale(this.game.SCALE);
        double extraHeight = 2 * (-Math.cos(this.wobble) + 1);
        tile.draw(canvas, this.x * this.game.SCALE, this.y * this.game.SCALE, 0, extraHeight, null);
        this.lastId = id;
    }

    public double getSpeed() {
        return Math.sqrt(this.Vx * this.Vx + this.Vy * this.Vy);
    }

    private void updateVelocity(double Vx, double Vy) {
        // smooth velocity change
        double avgUPS = this.game.getGameLoop().getAvgUPS();
        double totalFactor = Math.pow(1, 60 / avgUPS);
        double continuityFactor = Math.pow(1 - this.mass * this.mass, 60 / avgUPS);
        this.Vx = ((totalFactor - continuityFactor) * Vx + continuityFactor * this.Vx) / totalFactor;
        this.Vy = ((totalFactor - continuityFactor) * Vy + continuityFactor * this.Vy) / totalFactor;

        if (Util.within(-.1, this.Vx, .1))
            this.Vx = 0;
        if (Util.within(-.1, this.Vy, .1))
            this.Vy = 0;
    }

    @Override
    public void update() {
        double avgUPS = this.game.getGameLoop().getAvgUPS();
        if (avgUPS < 1) avgUPS = 1;

        Joystick joystick = this.game.getJoystick();
        // set velocity according to the joystick
        updateVelocity(this.maxSpeed * joystick.getCos() * Math.pow(joystick.getPercentage(), Math.sqrt(.5)) / avgUPS,
                this.maxSpeed * joystick.getSin() * Math.pow(joystick.getPercentage(), Math.sqrt(.5)) / avgUPS);

        int scale = this.game.SCALE;

        // smooth camera
        Tile tile = this.game.playerTileSet.getTile(this.lastId).withScale(scale);
        float dx = (float) this.game.getWidth() / 2 - (float) tile.getWidth() / 2 - this.x * scale;
        float dy = (float) this.game.getHeight() / 2 - (float) tile.getHeight() / 2 - this.y * scale;
        float factor = (float) (1 / avgUPS);
        this.game.getMap().translate(dx * factor, dy * factor);
        translate(dx * factor, dy * factor);

        // fix collision
        this.collision.move(this.x, this.y);
        if (this.game.getMap().collides(this)) {
            for (int i = 1; true; i++) {
                this.collision.move(this.x + i, this.y);
                if (!this.game.getMap().collides(this)) {
                    translate(i, 0);
                    break;
                }

                this.collision.move(this.x - i, this.y);
                if (!this.game.getMap().collides(this)) {
                    translate(-i, 0);
                    break;
                }

                this.collision.move(this.x, this.y + i);
                if (!this.game.getMap().collides(this)) {
                    translate(0, i);
                    break;
                }

                this.collision.move(this.x, this.y - i);
                if (!this.game.getMap().collides(this)) {
                    translate(0, -i);
                    break;
                }

                this.collision.move(this.x + i, this.y + i);
                if (!this.game.getMap().collides(this)) {
                    translate(i, i);
                    break;
                }

                this.collision.move(this.x + i, this.y - i);
                if (!this.game.getMap().collides(this)) {
                    translate(i, -i);
                    break;
                }

                this.collision.move(this.x - i, this.y + i);
                if (!this.game.getMap().collides(this)) {
                    translate(-i, i);
                    break;
                }

                this.collision.move(this.x - i, this.y - i);
                if (!this.game.getMap().collides(this)) {
                    translate(-i, -i);
                    break;
                }
            }
        }

        // move the player
        this.collision.move((float) (this.x + this.Vx + Math.signum(this.Vx)), this.y);
        if (this.game.getMap().collides(this))
            this.Vx = 0;
        this.collision.move((float) (this.x + this.Vx + Math.signum(this.Vx)), (float) (this.y + this.Vy + Math.signum(this.Vy)));
        if (this.game.getMap().collides(this))
            this.Vy = 0;
        translate((float) this.Vx, (float) this.Vy);

        // using the joystick distance for making the player wobble faster when walking
        // because the velocity is smooth, changing wobble speed is smooth
        this.wobble += 4 / avgUPS + getSpeed() / 8;
        this.wobble %= 2 * Math.PI;

        this.shadow.update();
    }

    @Override
    public Collision getCollision() {
        return this.collision;
    }

    @Override
    public int getLeft() {
        return this.game.playerTileSet.getTile(this.lastId).getLeft();
    }

    @Override
    public int getDown() {
        return this.game.playerTileSet.getTile(this.lastId).getDown();
    }

    @Override
    public Shadow getShadow() {
        return this.shadow;
    }

    @Override
    public int getZ() {
        return 0;
    }

    @Override
    public Point getPoint() {
        return new Point(Math.round((this.x - this.game.getMap().getX()) / this.game.getMap().TILE_SIZE),
                Math.round((this.y - this.game.getMap().getY() + 24) / this.game.getMap().TILE_SIZE));
    }

    public double getVx() {
        return this.Vx;
    }

    public double getVy() {
        return this.Vy;
    }
}
