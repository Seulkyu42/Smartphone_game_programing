package com.example.monstersurvival;

import android.graphics.Canvas;

public class MainGame {
    public static MainGame getInstance() {
        if (singleton == null) {
            singleton = new MainGame();
        }
        return singleton;
    }

    public float frameTime;

    private MainGame(){
    }

    private static MainGame singleton;
    private Player player;

    private void init(){

    }
    private void update(){

    }

    private void draw(Canvas canvas){

    }



}
