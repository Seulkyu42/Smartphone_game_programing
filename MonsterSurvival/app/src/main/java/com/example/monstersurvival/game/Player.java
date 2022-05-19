package com.example.monstersurvival.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.BoxCollidable;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.GyroOrient;
import com.example.monstersurvival.framework.Metrics;
import com.example.monstersurvival.framework.Sprite;

public class Player extends Sprite implements BoxCollidable {
    private static final String TAG = GameView.class.getSimpleName();
    private Bitmap playerBitmap;

    private float px; //Player X
    private float py; //Player Y

    // 플레이어 스텟 및 재화
    private float speed;
    private int health;
    private int coin;

    private long frameTime;
    private GyroOrient orientationData;

    protected RectF boundingBox = new RectF();
    protected PointF currPosition = new PointF();

    private Paint rectPaint = new Paint();
    // init
    public Player(float x,float y){
        super(x,y,R.dimen.player_radius, R.mipmap.player_image);
        //coin = 0; // 바뀔건데 dimen에 넣어도 괜찮은가
        speed = R.dimen.player_speed; //dimen에 넣을예정
        health = R.dimen.player_health;

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
        canvas.drawRect(boundingBox, rectPaint);
    }

    public void update(){

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

//            Log.d(TAG, "xSpeed : " + xSpeed);
//            Log.d(TAG, "ySpeed : " + ySpeed);

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
        Log.d(TAG, "Locate" + currPosition);
    }

    @Override
    public RectF getBoundingRect() {
        return boundingBox;
    }

    public PointF getCurrPosition(){
        return currPosition;
    }


    public void debugTouch(float dx, float dy) {
        this.px = dx;
        this.py = dy;

        //dstRect.set(px-30,py-30,px+30,py+30);
        dstRect.set(px-radius,py-radius,
                px+radius,py+radius);
//        Log.d(TAG, "Touch"+dstRect);
    }
}
