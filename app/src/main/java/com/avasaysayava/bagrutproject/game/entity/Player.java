package com.avasaysayava.bagrutproject.game.entity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;

import androidx.core.content.ContextCompat;

import com.avasaysayava.bagrutproject.R;
import com.avasaysayava.bagrutproject.game.Game;
import com.avasaysayava.bagrutproject.game.audio.TileSound;
import com.avasaysayava.bagrutproject.game.collision.Collision;
import com.avasaysayava.bagrutproject.game.collision.Path;
import com.avasaysayava.bagrutproject.game.graphic.Tile;
import com.avasaysayava.bagrutproject.game.graphic.gamemap.GameMap;
import com.avasaysayava.bagrutproject.game.gui.Joystick;
import com.avasaysayava.bagrutproject.game.util.LineF;
import com.avasaysayava.bagrutproject.game.util.Util;

import java.util.List;

public class Player extends Entity {
    private final float maxSpeed;
    private final Shadow shadow;
    private final Collision collision;
    private double wobble;
    private double Vx, Vy;
    private double angle;
    private int z;
    private int lastId;
    private boolean playSound;

    public Player(Game game, float x, float y, int z) {
        super(game, .4, x, y);
        this.maxSpeed = 80;
        this.lastId = 0;
        this.collision = new Collision(Path.polygon(new PointF(5, 33), new PointF(25, 33), new PointF(25, 47), new PointF(5, 47)));
        this.shadow = new Shadow(this.game, this, this.game.playerTileSet.getTile(4));
        this.z = z;
        this.playSound = true;
    }

    @Override
    public void draw(Canvas canvas) {
        boolean debugMode = this.game.isDebug();

        Paint debugPaint = null;
        if (debugMode) {
            debugPaint = new Paint();
            debugPaint.setColor(ContextCompat.getColor(this.game.getContext(), R.color.Blue));
        }

        // mark player's tile
        if (debugMode) {
            canvas.drawLine((Math.round((this.x - this.game.getMap().getX()) / this.game.getMap().TILE_SIZE) * this.game.getMap().TILE_SIZE + this.game.getMap().getX()) * this.game.SCALE,
                    (Math.round((this.y - this.game.getMap().getY() + 24) / this.game.getMap().TILE_SIZE) * this.game.getMap().TILE_SIZE + this.game.getMap().getY()) * this.game.SCALE,
                    (Math.round((this.x - this.game.getMap().getX()) / this.game.getMap().TILE_SIZE + 1) * this.game.getMap().TILE_SIZE + this.game.getMap().getX()) * this.game.SCALE,
                    (Math.round((this.y - this.game.getMap().getY() + 24) / this.game.getMap().TILE_SIZE) * this.game.getMap().TILE_SIZE + this.game.getMap().getY()) * this.game.SCALE,
                    debugPaint);
            canvas.drawLine((Math.round((this.x - this.game.getMap().getX()) / this.game.getMap().TILE_SIZE + 1) * this.game.getMap().TILE_SIZE + this.game.getMap().getX()) * this.game.SCALE,
                    (Math.round((this.y - this.game.getMap().getY() + 24) / this.game.getMap().TILE_SIZE) * this.game.getMap().TILE_SIZE + this.game.getMap().getY()) * this.game.SCALE,
                    (Math.round((this.x - this.game.getMap().getX()) / this.game.getMap().TILE_SIZE + 1) * this.game.getMap().TILE_SIZE + this.game.getMap().getX()) * this.game.SCALE,
                    (Math.round((this.y - this.game.getMap().getY() + 24) / this.game.getMap().TILE_SIZE + 1) * this.game.getMap().TILE_SIZE + this.game.getMap().getY()) * this.game.SCALE,
                    debugPaint);
            canvas.drawLine((Math.round((this.x - this.game.getMap().getX()) / this.game.getMap().TILE_SIZE + 1) * this.game.getMap().TILE_SIZE + this.game.getMap().getX()) * this.game.SCALE,
                    (Math.round((this.y - this.game.getMap().getY() + 24) / this.game.getMap().TILE_SIZE + 1) * this.game.getMap().TILE_SIZE + this.game.getMap().getY()) * this.game.SCALE,
                    (Math.round((this.x - this.game.getMap().getX()) / this.game.getMap().TILE_SIZE) * this.game.getMap().TILE_SIZE + this.game.getMap().getX()) * this.game.SCALE,
                    (Math.round((this.y - this.game.getMap().getY() + 24) / this.game.getMap().TILE_SIZE + 1) * this.game.getMap().TILE_SIZE + this.game.getMap().getY()) * this.game.SCALE,
                    debugPaint);
            canvas.drawLine((Math.round((this.x - this.game.getMap().getX()) / this.game.getMap().TILE_SIZE) * this.game.getMap().TILE_SIZE + this.game.getMap().getX()) * this.game.SCALE,
                    (Math.round((this.y - this.game.getMap().getY() + 24) / this.game.getMap().TILE_SIZE) * this.game.getMap().TILE_SIZE + this.game.getMap().getY()) * this.game.SCALE,
                    (Math.round((this.x - this.game.getMap().getX()) / this.game.getMap().TILE_SIZE) * this.game.getMap().TILE_SIZE + this.game.getMap().getX()) * this.game.SCALE,
                    (Math.round((this.y - this.game.getMap().getY() + 24) / this.game.getMap().TILE_SIZE + 1) * this.game.getMap().TILE_SIZE + this.game.getMap().getY()) * this.game.SCALE,
                    debugPaint);
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

        if (debugMode) {
            canvas.drawLine((this.x + 16) * this.game.SCALE,
                    (this.y + 40) * this.game.SCALE,
                    (float) ((this.x + 16 + getSpeed() * this.game.UPS * Math.cos(this.angle) / 5) * this.game.SCALE),
                    (float) ((this.y + 40 + getSpeed() * this.game.UPS * Math.sin(this.angle) / 5) * this.game.SCALE), debugPaint);
        }
    }

    public double getSpeed() {
        return Math.sqrt(this.Vx * this.Vx + this.Vy * this.Vy);
    }

    private void updateVelocity(double Vx, double Vy) {
        // smooth velocity change
        double avgUPS = this.game.getOperationScheduler().getAvgUPS();
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
        double avgUPS = this.game.getOperationScheduler().getAvgUPS();
        if (avgUPS < 1) avgUPS = 1;

        Joystick joystick = this.game.getJoystick();
        // set velocity according to the joystick
        updateVelocity(joystick.getCos() * getPreferredSpeed(),
                joystick.getSin() * getPreferredSpeed());

        int scale = this.game.SCALE;

        // smooth camera
        Tile tile = this.game.playerTileSet.getTile(this.lastId).withScale(scale);
        float dx = this.game.getWidth() / 2f - tile.getWidth() / 2f - this.x * scale;
        float dy = this.game.getHeight() / 2f - tile.getHeight() / 2f - this.y * scale;
        float factor = (float) (1 / avgUPS);
        this.game.getMap().translate(dx * factor, dy * factor);
        translate(dx * factor, dy * factor);

        // move the player
        this.collision.move((float) (this.x + this.Vx + Math.signum(this.Vx)), (float) (this.y + this.Vy + Math.signum(this.Vy)));
        LineF intersector = this.game.getMap().getIntersector(this);

        if (intersector != null) {
            PointF A = intersector.first, B = intersector.second;
            double intersectorAngle = Math.atan2(A.y - B.y, A.x - B.x);

            if ((intersectorAngle * 2) % Math.PI != 0) {
                double playerAngle = joystick.getRadians();
                double speedPercentage = Math.cos(playerAngle - intersectorAngle);
                this.Vx = speedPercentage * getPreferredSpeed() * Math.cos(intersectorAngle);
                this.Vy = speedPercentage * getPreferredSpeed() * Math.sin(intersectorAngle);
            }

            this.collision.move((float) (this.x + this.Vx + Math.signum(this.Vx)), (float) (this.y + this.Vy + Math.signum(this.Vy)));
            if (this.game.getMap().getIntersector(this) != null) {
                this.collision.move((float) (this.x + this.Vx + Math.signum(this.Vx)), this.y);
                if (this.game.getMap().getIntersector(this) != null)
                    this.Vx = 0;
                this.collision.move((float) (this.x + this.Vx + Math.signum(this.Vx)), (float) (this.y + this.Vy + Math.signum(this.Vy)));
                if (this.game.getMap().getIntersector(this) != null)
                    this.Vy = 0;
            }
        }

        this.angle = Math.atan2(this.Vy, this.Vx);
        translate((float) this.Vx, (float) this.Vy);

        // fix collision caused by camera movement and floating points
        this.collision.move(this.x, this.y);
        if (this.game.getMap().getIntersector(this) != null) {
            for (int i = 1; i < this.maxSpeed; i++) {
                this.collision.move(this.x + i, this.y);
                if (this.game.getMap().getIntersector(this) == null) {
                    translate(i, 0);
                    break;
                }

                this.collision.move(this.x - i, this.y);
                if (this.game.getMap().getIntersector(this) == null) {
                    translate(-i, 0);
                    break;
                }

                this.collision.move(this.x, this.y + i);
                if (this.game.getMap().getIntersector(this) == null) {
                    translate(0, i);
                    break;
                }

                this.collision.move(this.x, this.y - i);
                if (this.game.getMap().getIntersector(this) == null) {
                    translate(0, -i);
                    break;
                }

                this.collision.move(this.x + i, this.y + i);
                if (this.game.getMap().getIntersector(this) == null) {
                    translate(i, i);
                    break;
                }

                this.collision.move(this.x + i, this.y - i);
                if (this.game.getMap().getIntersector(this) == null) {
                    translate(i, -i);
                    break;
                }

                this.collision.move(this.x - i, this.y + i);
                if (this.game.getMap().getIntersector(this) == null) {
                    translate(-i, i);
                    break;
                }

                this.collision.move(this.x - i, this.y - i);
                if (this.game.getMap().getIntersector(this) == null) {
                    translate(-i, -i);
                    break;
                }
            }
        }

        // using the joystick distance for making the player wobble faster when walking
        // because the velocity has smooth change, changing wobble speed is smooth
        this.wobble += 4 / avgUPS + getSpeed() / 8;
        this.wobble %= Math.PI * 2;

        if (Math.cos(this.wobble) < 0) {
            if (this.playSound && getSpeed() != 0) {
                this.playSound = false;

                GameMap map = this.game.getMap();
                Point p = getPositionOnMap(map);
                List<Tile> tiles = map.getTiles(p.y, p.x);

                for (Tile t : tiles) {
                    if (t.getType() == TileSound.GRASS) {
                        Util.randomElement(this.game.tileSounds.getSounds(t.getType())).start();
                        break;
                    }
                }
            }
        } else {
            this.playSound = true;
        }

        this.shadow.update();
    }

    private double getPreferredSpeed() {
        return this.maxSpeed * this.game.getJoystick().getPercentage() / this.game.getOperationScheduler().getAvgUPS();
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
        return this.z;
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

    private Point getPositionOnMap(GameMap map) {
        return new Point(
                Math.round((this.x - map.getX()) / map.TILE_SIZE),
                Math.round((this.y - map.getY() + 24) / map.TILE_SIZE)
        );
    }
}
