package com.example.monstersurvival.game.items;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.RecycleBin;
import com.example.monstersurvival.framework.interfaces.BoxCollidable;
import com.example.monstersurvival.framework.interfaces.Recyclable;
import com.example.monstersurvival.framework.objects.Sprite;
import com.example.monstersurvival.framework.res.Metrics;
import com.example.monstersurvival.game.Enemy;
import com.example.monstersurvival.game.MainGame;
import com.example.monstersurvival.game.Player;


public class Coin1 extends Sprite implements BoxCollidable, Recyclable {
    public static final float FRAMES_PER_SECOND = 10.0f;
    private static final String TAG = Coin1.class.getSimpleName();
    public static float size;
    protected float dx,dy;
    protected RectF boundingBox = new RectF();

    private float life;
    private float rotate;
    private Player player;
    private Enemy enemy;

    private PointF objPosition = new PointF();
    private float frameTime = 0.0f;
    private float blink = 0.0f;
    private float speed = 0.0f;

    private Coin1(float x, float itemlife, float speed) {
        super(x,size,R.dimen.item2_radius, R.mipmap.coin);
        set(x,itemlife,speed);
    }
//    protected static ArrayList<Enemy> recycleBin = new ArrayList<>();
    public static Coin1 get(float x, float itemlife, float speed) {
        Coin1 item = (Coin1) RecycleBin.get(Coin1.class);
        if (item != null) {
            item.set(x,itemlife, speed);
            return item;
        }
        return new Coin1(x ,itemlife, speed);

    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setEnemy(Enemy enemy) {this.enemy = enemy;}

    private void set(float x,float itemlife, float speed) {
        life = itemlife;
        this.speed = speed;
    }

    @Override
    public void update() {
        frameTime = MainGame.getInstance().frameTime;
        if(life == Metrics.getFloat(R.dimen.item2time)){

            objPosition = enemy.getCurrPosition();
            this.x = objPosition.x;
            this.y = objPosition.y;


        }

        if (life <= 0.0f) {
            //MainGame.getInstance().remove(MainGame.Layer.enemy);
            MainGame.getInstance().remove(this);
            return;
        }
        else {
            life -= frameTime;

            setDstRectWithRadius();
            boundingBox.set(dstRect);
            boundingBox.inset(size / 16, size / 16);
            if (dstRect.top > Metrics.height) {
                MainGame.getInstance().remove(this);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate(rotate,x,y);

        super.draw(canvas);

        canvas.restore();

    }

    @Override
    public RectF getBoundingRect() {
        return boundingBox;
    }

    @Override
    public void finish() {

    }

}
