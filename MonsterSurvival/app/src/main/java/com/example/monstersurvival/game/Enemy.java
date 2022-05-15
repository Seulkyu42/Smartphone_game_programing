package com.example.monstersurvival.game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.AnimSprite;
import com.example.monstersurvival.framework.BitmapPool;
import com.example.monstersurvival.framework.BoxCollidable;
import com.example.monstersurvival.framework.Metrics;
import com.example.monstersurvival.framework.Recyclable;
import com.example.monstersurvival.framework.RecycleBin;
import com.example.monstersurvival.framework.Sprite;


public class Enemy extends Sprite implements BoxCollidable, Recyclable {
    public static final float FRAMES_PER_SECOND = 10.0f;
    private static final String TAG = Enemy.class.getSimpleName();
    public static float size;
    protected int level;
    protected float life, maxLife;
    protected Gauge gauge;
    protected float dy;
    protected RectF boundingBox = new RectF();
    public static final int MIN_LEVEL = 1;
    public static final int MAX_LEVEL = 1;

//    protected static ArrayList<Enemy> recycleBin = new ArrayList<>();
    public static Enemy get(int level, float x, float speed) {
        Enemy enemy = (Enemy) RecycleBin.get(Enemy.class);
        if (enemy != null) {
//            Enemy enemy = recycleBin.remove(0);
            enemy.set(level, x, speed);
            return enemy;
        }
        return new Enemy(level, x, speed);
    }

    private void set(int level, float x, float speed) {
        bitmap = BitmapPool.get(R.mipmap.enemy_image);
        this.x = x;
        this.y = -size;
        this.dy = speed;
        this.level = level;
        life = maxLife = level * 10;
        gauge.setValue(1.0f);
    }
    private Enemy(int level, float x, float speed) {
        super(x,size,R.dimen.player_radius, R.mipmap.enemy_image);
        this.level = level;
//        y -= radius;
//        setDstRectWithRadius();
        dy = speed;
        life = maxLife = level * 10;
//        gauge = new Gauge(
//                Metrics.size(R.dimen.enemy_gauge_fg_width), R.color.enemy_gauge_fg,
//                Metrics.size(R.dimen.enemy_gauge_bg_width), R.color.enemy_gauge_bg,
//                size * 0.9f
//        );
        //gauge.setValue(1.0f);
        Log.d(TAG, "Created: " + this);
    }

    @Override
    public void update() {
        //super.update();

        float frameTime = MainGame.getInstance().frameTime;
        y += dy * frameTime;
        setDstRectWithRadius();
        boundingBox.set(dstRect);
        boundingBox.inset(size/16, size/16);
        if (dstRect.top > Metrics.height) {
            MainGame.getInstance().remove(this);
            //recycleBin.add(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        //gauge.draw(canvas, x, y + size * 0.5f);
    }

    @Override
    public RectF getBoundingRect() {
        return boundingBox;
    }

    @Override
    public void finish() {

    }

    public int getScore() {
        return level * level * 100;
    }

    public boolean decreaseLife(float power) {
        life -= power;
        if (life <= 0) return true;

        gauge.setValue((float)life / maxLife);
        return false;
    }
}
