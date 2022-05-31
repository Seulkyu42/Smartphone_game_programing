package com.example.monstersurvival.game.items;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.RecycleBin;
import com.example.monstersurvival.framework.interfaces.BoxCollidable;
import com.example.monstersurvival.framework.interfaces.Recyclable;
import com.example.monstersurvival.framework.objects.Sprite;
import com.example.monstersurvival.framework.res.BitmapPool;
import com.example.monstersurvival.framework.res.Metrics;
import com.example.monstersurvival.game.MainGame;
import com.example.monstersurvival.game.Player;


public class Item1active extends Sprite implements BoxCollidable, Recyclable {
    public static final float FRAMES_PER_SECOND = 10.0f;
    private static final String TAG = Item1active.class.getSimpleName();
    public static float size;
    protected float dy;
    protected RectF boundingBox = new RectF();

    static float life = 5.0f;
    private float rotate;
    private Player player;
    private PointF objPosition = new PointF();
    private float frameTime = 0.0f;

    private Item1active() {
        super(10,size,R.dimen.item1_radius, R.mipmap.item_1_active);
    }
//    protected static ArrayList<Enemy> recycleBin = new ArrayList<>();
    public static Item1active get() {
        Item1active item = (Item1active) RecycleBin.get(Item1active.class);
        if (item != null) {
            return item;
        }
        return new Item1active();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void update() {
        objPosition = player.getCurrPosition();

        if(rotate >= 360.0f){
            rotate = 0.0f;
        }else rotate += 5.0f;

        if (life <= 0.0f) {
            MainGame.getInstance().remove(this);
            return;
        }
        else {
            frameTime = MainGame.getInstance().frameTime;
            life -= frameTime;

            x = objPosition.x;
            y = objPosition.y;
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

    public void statUp(float life, float add){
        this.life = life + add;
        Log.d(TAG,"Life Set" + this.life+"life"+ life+"add" + add);
    }
    public float getLife(){
        Log.d(TAG,"Life Set" + this.life);
        return this.life;
    }

    @Override
    public void finish() {

    }


}
