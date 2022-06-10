package com.example.monstersurvival.game.items;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.RecycleBin;
import com.example.monstersurvival.framework.interfaces.BoxCollidable;
import com.example.monstersurvival.framework.interfaces.Recyclable;
import com.example.monstersurvival.framework.objects.Sprite;
import com.example.monstersurvival.framework.res.Metrics;
import com.example.monstersurvival.game.scenes.MainGame;
import com.example.monstersurvival.game.object.Player;


public class Item3active extends Sprite implements BoxCollidable, Recyclable {
    public static final float FRAMES_PER_SECOND = 10.0f;
    private static final String TAG = Item3active.class.getSimpleName();
    public static int size;
    static int multiply = 0;
    protected float dx,dy;
    protected RectF boundingBox = new RectF();
    public int addSize;
    private float life;
    private float rotate;
    private Player player;
    private PointF objPosition = new PointF();
    private float frameTime = 0.0f;
    private float blink = 0.0f;
    private float speed = 0.0f;

    public Item3active() {
        super(0,size,R.dimen.item3_radius, R.mipmap.item_3);
        set();

    }
//    protected static ArrayList<Enemy> recycleBin = new ArrayList<>();
    public static Item3active get() {
        Item3active item = (Item3active) RecycleBin.get(Item3active.class);
        if (item != null) {
            item.set();
            return item;
        }
        return new Item3active();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void set() {
        life = Metrics.getFloat(R.dimen.item3time);
        this.speed = Metrics.getFloat(R.dimen.item3speed);
    }

    @Override
    public void update(float frameTime) {
        super.update(frameTime);
        if(life == Metrics.getFloat(R.dimen.item3time)){
            objPosition = player.getCurrPosition();
            // 처음위치 받아오기
            this.x = objPosition.x;
            this.y = objPosition.y;

            // 처음 속도 잡아두기
            this.dx = speed;
            this.dy = speed;
        }

        if (life <= 0.0f) {
            //MainGame.getInstance().remove(MainGame.Layer.enemy);
            MainGame.getInstance().remove(this);
            return;
        }
        else {

            life -= frameTime;
            y -= dy * frameTime;

            addSize = (int) Metrics.size(R.dimen.item3_radius_add) * multiply;
            size = (int) Metrics.size(R.dimen.item3_radius);

            setDstRectWithRadius();
            dstRect.set(x - (size + addSize), y - (size + addSize),
                    x + (size + addSize), y + (size + addSize));
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

    public void statUp(int add){
        this.multiply += add;
        Log.d(TAG,"mult"+multiply);
    }
    public void addSize(int value) {
        this.multiply = value;
    }

    public int getSize(){
        return this.multiply;
    }


    @Override
    public void finish() {

    }

}
