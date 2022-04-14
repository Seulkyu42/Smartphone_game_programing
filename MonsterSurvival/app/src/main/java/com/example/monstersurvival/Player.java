package com.example.monstersurvival;


import android.graphics.Bitmap;
import android.graphics.RectF;
import android.graphics.Canvas;

public class Player {
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
        coin = 0;
        speed = 10; //dimen에 넣을예정
        health = 3;
    }

    public void update(){

    }

    public void draw(Canvas canvas){
        //canvas.drawBitmap(playerBitmap,);
    }

}
