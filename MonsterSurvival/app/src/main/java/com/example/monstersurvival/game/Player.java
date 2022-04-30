package com.example.monstersurvival.game;


import android.graphics.Bitmap;
import android.graphics.RectF;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.Metrics;
import com.example.monstersurvival.framework.Sprite;

public class Player extends Sprite {
    private Bitmap playerBitmap;
    private RectF playerRect = new RectF();

    private float px; //Player X
    private float py; //Player Y

    // 플레이어 스텟 및 재화
    private float speed;
    private int health;
    private int coin;

    private float xPos, xAccel, xVel = 0.0f;
    private float yPos, yAccel, yVel = 0.0f;
    private float xMax, yMax;
    private SensorManager sensorManager;


    // init
    public Player(float x,float y){
        super(x,y,R.dimen.player_radius, R.mipmap.player_image);
        coin = 0; // 바뀔건데 dimen에 넣어도 괜찮은가
        speed = R.dimen.player_speed; //dimen에 넣을예정
        health = R.dimen.player_health;

        xMax = Metrics.width;
        yMax = Metrics.height;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            xAccel = sensorEvent.values[0];
            yAccel = -sensorEvent.values[1];
            update();
        }
    }


    public void update(){
        float frameTime = MainGame.getInstance().frameTime;
        if (px == 0)
            return;

        xVel += (xAccel * frameTime);
        yVel += (yAccel * frameTime);

        float xS = (xVel / 2) * frameTime;
        float yS = (yVel / 2) * frameTime;

        px -= xS;
        py -= yS;

        if (xPos > xMax) {
            xPos = xMax;
        } else if (xPos < 0) {
            xPos = 0;
        }
        if (yPos > yMax) {
            yPos = yMax;
        } else if (yPos < 0) {
            yPos = 0;
        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap,px,py,null);
    }
}
