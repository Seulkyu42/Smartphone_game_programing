package com.example.monstersurvival;


import android.graphics.Bitmap;
import android.graphics.RectF;
import android.graphics.Canvas;

public class Player {
    private Bitmap playerBitmap;
    private RectF playerRect = new RectF();

    private float px; //Player X
    private float py; //Player Y

    private float speed;
    private int health;


    // init
    public Player(float x,float y){

        speed = 10; //dimen에 넣을예정
        health = 3;
    }

    public void update(){

    }

    public void draw(Canvas canvas){
        //canvas.drawBitmap(playerBitmap,);
    }

}
