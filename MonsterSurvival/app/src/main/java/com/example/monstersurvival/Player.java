package com.example.monstersurvival;


import android.graphics.Bitmap;
import android.graphics.RectF;
import android.graphics.Canvas;

import com.example.monstersurvival.R;

public class Player extends Sprite {
    private Bitmap playerBitmap;
    private RectF playerRect = new RectF();

    private float px; //Player X
    private float py; //Player Y

    // 플레이어 스텟 및 재화
    private float speed;
    private int health;
    private int coin;
 
    // init
    public Player(float x,float y){
//        super(x,y,R.dimen.player_radius, R.mipmap.player_image);
        super(x,y,R.dimen.player_radius, R.mipmap.player_image);
        coin = 0;
        speed = R.dimen.player_speed; //dimen에 넣을예정
        health = 3;
    }

    public void update(){
        
    }

    public void draw(Canvas canvas){
        //canvas.drawBitmap(playerBitmap,);
        canvas.drawBitmap(bitmap, null,dstRect,null);
    }

}
