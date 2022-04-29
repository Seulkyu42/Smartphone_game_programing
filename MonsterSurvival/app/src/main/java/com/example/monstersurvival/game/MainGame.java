package com.example.monstersurvival.game;

import android.graphics.Canvas;

import com.example.monstersurvival.framework.GameObject;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.Metrics;

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

    /////////////////////////// 레이어
    protected ArrayList<ArrayList<GameObject>> layers;
    public enum Layer {
        bg1, player,ui,COUNT
    }

    public static void clear() {
        singleton = null;
    }

    public void init(){
        initLayers(Layer.COUNT.ordinal());
        objects.clear();

        float playerY = Metrics.height/2;
        player = new Player(Metrics.width/2, playerY);
        objects.add(player);
    }
    private void initLayers(int count) {
        layers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            layers.add(new ArrayList<>());
        }
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
