package com.example.monstersurvival.game;


import android.graphics.Bitmap;
import android.graphics.RectF;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.util.Log;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.Metrics;
import com.example.monstersurvival.framework.Sprite;

public class Player extends Sprite {
    private static final String TAG = GameView.class.getSimpleName();
    private Bitmap playerBitmap;

    private float px; //Player X
    private float py; //Player Y

    // 플레이어 스텟 및 재화
    private float speed;
    private int health;
    private int coin;

    // init
    public Player(float x,float y){
        super(x,y,R.dimen.player_radius, R.mipmap.player_image);
        coin = 0; // 바뀔건데 dimen에 넣어도 괜찮은가
        speed = R.dimen.player_speed; //dimen에 넣을예정
        health = R.dimen.player_health;
        px = x;
        py = y;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap,null, dstRect,null);
        //Log.d(TAG, "draw "+dstRect);
    }


    // 교수님께 질문 -> 각 값들을 받고, 변경되는거 까지 확인했는데 dstRect.set이 작동을 안함
    public void update(float dx, float dy){
        this.px += dx;
        this.py += dy;

        Log.d(TAG, "x : " + px);
        Log.d(TAG, "y : " + py);

        dstRect.set(px-radius,py-radius,
                px+radius,py+radius);
//        dstRect.offset(px,py);
    }


    public void debugTouch(float dx, float dy) {
        this.px = dx;
        this.py = dy;

        //dstRect.set(px-30,py-30,px+30,py+30);
        dstRect.set(px-radius,py-radius,
                px+radius,py+radius);
        Log.d(TAG, "Touch"+dstRect);
    }
}
