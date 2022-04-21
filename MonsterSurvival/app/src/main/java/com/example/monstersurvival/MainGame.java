package com.example.monstersurvival;

import android.graphics.Canvas;

import java.util.ArrayList;

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

    private ArrayList<GameObject> objects = new ArrayList<>();

    private static MainGame singleton;
    private Player player;

    public void init(){
        objects.clear();

        float playerY = Metrics.height/2;
        player = new Player(Metrics.width/2, playerY);
        objects.add(player);
    }
    public void update(){

    }

    public void draw(Canvas canvas){
        for(GameObject gobj : objects){
            gobj.draw(canvas);
        }
    }

    public void add(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                objects.add(gameObject);
            }
        });
    }

    public void remove(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                objects.remove(gameObject);
            }
        });
    }

}
