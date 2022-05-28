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
import com.example.monstersurvival.game.MainGame;
import com.example.monstersurvival.game.Player;


public class Item2active extends Sprite implements BoxCollidable, Recyclable {
    public static final float FRAMES_PER_SECOND = 10.0f;
    private static final String TAG = Item2active.class.getSimpleName();
    public static float size;
    protected float dy;
    protected RectF boundingBox = new RectF();

    private float life;
    private float rotate;
    private Player player;
    private PointF objPosition = new PointF();
    private float frameTime = 0.0f;
    private float blink = 0.0f;
    private float speed = 0.0f;

    private Item2active(float x, float itemlife, float speed) {
        super(x,size,R.dimen.item1_radius, R.mipmap.item_1_active);
        set(x,itemlife,speed);
    }
//    protected static ArrayList<Enemy> recycleBin = new ArrayList<>();
    public static Item2active get(float x, float itemlife, float speed) {
        Item2active item = (Item2active) RecycleBin.get(Item2active.class);
        if (item != null) {
            item.set(x,itemlife, speed);
            return item;
        }
        return new Item2active(x ,itemlife, speed);
    }

    private void set(float x,float itemlife, float speed) {
        objPosition = player.getCurrPosition();
        life = itemlife;
        this.x = objPosition.x;
        this.y = objPosition.y;
        this.speed = speed;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void update() {

        if(rotate >= 360.0f){
            rotate = 0.0f;
        }else rotate += 5.0f;

        if (life <= 0.0f) {
            //MainGame.getInstance().remove(MainGame.Layer.enemy);
            MainGame.getInstance().remove(this);
            return;
        }
        else {
            frameTime = MainGame.getInstance().frameTime;
            life -= frameTime;
//            x =
//            y =
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
