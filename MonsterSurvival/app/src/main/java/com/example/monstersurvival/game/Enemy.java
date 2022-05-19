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
    protected float dy;
    protected RectF boundingBox = new RectF();
    public static final int LEVEL = 1;

    private int life;
    private float rotate;
    private float speed;
    private Player player;



//    protected static ArrayList<Enemy> recycleBin = new ArrayList<>();
    public static Enemy get(float x, float speed) {
        Enemy enemy = (Enemy) RecycleBin.get(Enemy.class);
        if (enemy != null) {
            enemy.set(x, speed);
            return enemy;
        }
        return new Enemy(x, speed);
    }

    private void set(float x, float speed) {
        bitmap = BitmapPool.get(R.mipmap.enemy_image);
        life = 1;
        speed = 30.0f;
        this.x = x;
        this.y = -size;
        this.dy = speed;
    }

    private Enemy(float x, float speed) {
        super(x,size,R.dimen.player_radius, R.mipmap.enemy_image);
        set(x,speed);
        dy = speed;
    }

    @Override
    public void update() {
        if (life <= 0) {
            //MainGame.getInstance().remove(MainGame.Layer.enemy);
            return;
        }

        float frameTime = MainGame.getInstance().frameTime;
        y += dy * frameTime;
        setDstRectWithRadius();
        boundingBox.set(dstRect);
        boundingBox.inset(size/16, size/16);
        if (dstRect.top > Metrics.height) {
            MainGame.getInstance().remove(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public RectF getBoundingRect() {
        return boundingBox;
    }

    @Override
    public void finish() {

    }

}
