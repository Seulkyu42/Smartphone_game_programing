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
import com.example.monstersurvival.game.scenes.MainGame;
import com.example.monstersurvival.game.object.Player;

import java.util.Random;


public class Item2active extends Sprite implements BoxCollidable, Recyclable {
    public static final float FRAMES_PER_SECOND = 10.0f;
    private static final String TAG = Item2active.class.getSimpleName();
    public static float size;
    protected float dx,dy;
    protected RectF boundingBox = new RectF();

    private static Random random = new Random();
    private float life;
    private float rotate;
    private Player player;
    private PointF objPosition = new PointF();
    static float setSpeed = 1000.0f;
    private float speed = 0.0f;
    static int count = 1;

    private Item2active() {
        super(10,size,R.dimen.item2_radius, R.mipmap.item_2_active);
        set();
    }
    //    protected static ArrayList<Enemy> recycleBin = new ArrayList<>();
    public static Item2active get() {
        Item2active item = (Item2active) RecycleBin.get(Item2active.class);
        if (item != null) {
            return item;
        }
        return new Item2active();
    }

    public void set(){
        this.life = Metrics.getFloat(R.dimen.item2time);
        this.speed = setSpeed;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void update(float frameTime) {
        super.update(frameTime);
        if(life == Metrics.getFloat(R.dimen.item2time)){
            objPosition = player.getCurrPosition();
            // 처음위치 받아오기
            this.x = objPosition.x;
            this.y = objPosition.y;

            Log.d(TAG, "좌표"+x+y+objPosition.x+objPosition+y);

            // 처음 속도 잡아두기

            if(random.nextInt(2)+1 == 1) {
                this.dx = -speed;
            }else this.dx = speed;
            if(random.nextInt(2)+1 == 1) {
                this.dy = speed;
            }else this.dy = -speed;
        }

        if(rotate >= 360.0f){
            rotate = 0.0f;
        }else rotate += 5.0f;

        if (life <= 0.0f) {
            //MainGame.getInstance().remove(MainGame.Layer.enemy);
            MainGame.getInstance().remove(this);
            return;
        }
        else {
            life -= frameTime;

            x += dx * frameTime;
            y += dy * frameTime;

            if(x < 0 || x > Metrics.width){
                dx = -dx;
            } else if(y < 0 || y > Metrics.height){
                dy = -dy;
            }


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

    public void statUp(int add){
        this.count += add;
    }
    public int getCount(){
        return this.count;
    }


    @Override
    public RectF getBoundingRect() {
        return boundingBox;
    }

    public void statUp(float life, float add){
        this.setSpeed = life + add;
    }
    public void addSpeed(float value) {
        this.setSpeed = 1000.0f + value;
    }
    public float getSpeed(){
        return this.setSpeed;
    }

    @Override
    public void finish() {

    }

}
