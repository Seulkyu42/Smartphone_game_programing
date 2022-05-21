package com.example.monstersurvival.game;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.BitmapPool;
import com.example.monstersurvival.framework.BoxCollidable;
import com.example.monstersurvival.framework.Metrics;
import com.example.monstersurvival.framework.Recyclable;
import com.example.monstersurvival.framework.RecycleBin;
import com.example.monstersurvival.framework.Sprite;


public class Item1 extends Sprite implements BoxCollidable, Recyclable {
    public static final float FRAMES_PER_SECOND = 10.0f;
    private static final String TAG = Item1.class.getSimpleName();
    public static float size;
    protected float dy;
    protected RectF boundingBox = new RectF();

    private int life;
    private float rotate;
    private float speed;
    private Player player;
    private PointF objPosition = new PointF();


//    protected static ArrayList<Enemy> recycleBin = new ArrayList<>();
    public static Item1 get(float x, float speed) {
        Item1 item = (Item1) RecycleBin.get(Item1.class);
        if (item != null) {
            item.set(x, speed);
            return item;
        }
        return new Item1(x, speed);
    }

    private void set(float x, float speed) {
        bitmap = BitmapPool.get(R.mipmap.item_1);
        life = 1;
        this.speed = 30.0f;
        this.x = x;
        this.y = -size;
        this.dy = speed;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private Item1(float x, float speed) {
        super(x,size,R.dimen.player_radius, R.mipmap.item_1);
        set(x,speed);
        dy = speed;
    }

    @Override
    public void update() {
        objPosition = player.getCurrPosition();

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
