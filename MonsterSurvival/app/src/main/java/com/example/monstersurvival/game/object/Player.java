package com.example.monstersurvival.game.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.interfaces.BoxCollidable;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.GyroOrient;
import com.example.monstersurvival.framework.res.Metrics;
import com.example.monstersurvival.framework.objects.Sprite;
import com.example.monstersurvival.game.scenes.MainGame;

public class Player extends Sprite implements BoxCollidable {
    private static final String TAG = GameView.class.getSimpleName();
    private Bitmap playerBitmap;

    private float px; //Player X
    private float py; //Player Y

    // 플레이어 스텟 및 재화
    private float speed;
    private float health;

    private long frameTime;
    private GyroOrient orientationData;

    public RectF boundingBox = new RectF();
    public PointF currPosition = new PointF();

    private Paint rectPaint = new Paint();

    ////무적용////
    private boolean isInvincible;
    private float isInvincibleTime = 3.0f;
    private float checkTime;


    // init
    public Player(float x,float y){
        super(x,y,R.dimen.player_radius, R.mipmap.player_image);
        speed = R.dimen.player_speed; //dimen에 넣을예정
        health = MainGame.getInstance().getHealth();

        px = x;
        py = y;

        orientationData = new GyroOrient();
        orientationData.register();
        frameTime = System.currentTimeMillis();

        //
        rectPaint.setColor(Color.RED);
        rectPaint.setAlpha(50);

    }


    public void draw(Canvas canvas){

        canvas.drawBitmap(bitmap,null, dstRect,null);
    }

    @Override
    public void update(float frametime){

        if(frameTime < Metrics.INIT_TIME)
            frameTime = Metrics.INIT_TIME;
        int elapsedTime = (int)(System.currentTimeMillis() - frameTime);
        frameTime = System.currentTimeMillis();

        if(orientationData.getOrientation() != null && orientationData.getStartOrientation() != null) {
            float pitch = orientationData.getOrientation()[1] - orientationData.getStartOrientation()[1];
            float roll = orientationData.getOrientation()[2] - orientationData.getStartOrientation()[2];

            float xSpeed = 2 * roll * Metrics.width/1000f;
            float ySpeed = pitch *  Metrics.height/1000f;

            this.px += Math.abs(xSpeed * elapsedTime) > 5 ? xSpeed*elapsedTime : 0;
            this.py -= Math.abs(ySpeed * elapsedTime) > 5 ? ySpeed*elapsedTime : 0;
        }

        if(this.px < 0)
            this.px = 0;
        else if(this.px > Metrics.width)
            this.px = Metrics.width;
        if(this.py < 0)
            this.py = 0;
        else if(this.py > Metrics.height)
            this.py = Metrics.height;

        dstRect.set(px-radius,py-radius, px+radius,py+radius);
        boundingBox.set(px-radius,py-radius, px+radius,py+radius);

        currPosition.set(px,py);

        if(isInvincible == true){
            checkTime += frametime;
            if(checkTime > isInvincibleTime){
                checkTime -= isInvincibleTime;
                isInvincible = false;
                Log.d(TAG,"무적 오프");
            }
        }
    }

    public void getHit(){
        if(isInvincible == false) {
            Log.d(TAG,"무적 온" + health);
            health -= 1;
            isInvincible = true;
        }
        if(health <= 0){
            Log.d(TAG,"GameOverTest");
        }
    }

    @Override
    public RectF getBoundingRect() {
        return boundingBox;
    }

    public PointF getCurrPosition(){
        return currPosition;
    }

    public boolean getIsInvincible(){
        return isInvincible;
    }

    public float getHealth(){ return health; }

    public void debugTouch(float dx, float dy) {
        this.px = dx;
        this.py = dy;

        //dstRect.set(px-30,py-30,px+30,py+30);
        dstRect.set(px-radius,py-radius,
                px+radius,py+radius);
//        Log.d(TAG, "Touch"+dstRect);
    }

    public void getItem() {
    }
}
