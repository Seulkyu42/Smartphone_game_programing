package com.example.monstersurvival.game.items;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

import com.example.monstersurvival.R;
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
    public static float size;
    protected float dx,dy;
    protected RectF boundingBox = new RectF();

    private float life;
    private float rotate;
    private Player player;
    private PointF objPosition = new PointF();
    private float frameTime = 0.0f;
    private float blink = 0.0f;
    private float speed = 0.0f;

    private Item3active(float x, float itemlife, float speed) {
        super(x,size,R.dimen.item3_radius, R.mipmap.item_3);
        set(x,itemlife,speed);
    }
//    protected static ArrayList<Enemy> recycleBin = new ArrayList<>();
    public static Item3active get(float x, float itemlife, float speed) {
        Item3active item = (Item3active) RecycleBin.get(Item3active.class);
        if (item != null) {
            item.set(x,itemlife, speed);
            return item;
        }
        return new Item3active(x ,itemlife, speed);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void set(float x,float itemlife, float speed) {
        life = itemlife;
        this.speed = speed;
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
