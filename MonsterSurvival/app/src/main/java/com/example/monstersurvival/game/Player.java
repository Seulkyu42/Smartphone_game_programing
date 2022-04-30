package com.example.monstersurvival.game;


import android.graphics.Bitmap;
import android.graphics.RectF;
import android.graphics.Canvas;

import com.example.monstersurvival.R;
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
 
    // init
    public Player(float x,float y){
        super(x,y,R.dimen.player_radius, R.mipmap.player_image);
        coin = 0; // 바뀔건데 dimen에 넣어도 괜찮은가
        speed = R.dimen.player_speed; //dimen에 넣을예정
        health = R.dimen.player_health;
    }



    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, null,dstRect,null);
    }

    public void update(){
        float frameTime = MainGame.getInstance().frameTime;
        if (px == 0)
            return;

        float px = this.px * frameTime;
    }

}
