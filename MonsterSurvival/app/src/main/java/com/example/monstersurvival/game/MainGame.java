package com.example.monstersurvival.game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import com.example.monstersurvival.R;
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

    private ArrayList<GameObject> objects = new ArrayList<>();
    private static final String TAG = GameView.class.getSimpleName();

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

        float playerY = Metrics.height/2;
        player = new Player(Metrics.width/2, playerY);
        add(Layer.player, player);

        //add(Layer.ui, null);

        add(Layer.bg1, new HorizonScrollBackground(R.mipmap.background_high, Metrics.size(R.dimen.bg_speed_lobby)));

    }

    private void initLayers(int count) {
        layers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            layers.add(new ArrayList<>());
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();

                player.debugTouch(x,y);
                return true;
        }
        return false;
    }

    public void draw(Canvas canvas) {
        for (ArrayList<GameObject> gameObjects : layers) {
            for (GameObject gobj : gameObjects) { gobj.draw(canvas); }
        }
    }
    public void update(int elapsedNanos) {
        frameTime = (float) (elapsedNanos / 1_000_000_000f);
        for (ArrayList<GameObject> gameObjects : layers) {
            for (GameObject gobj : gameObjects) { gobj.update(); }
        }
    }

    public ArrayList<GameObject> objectsAt(Layer layer) {
        return layers.get(layer.ordinal());
    }

    public void add(Layer layer, GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> gameObjects = layers.get(layer.ordinal());
                gameObjects.add(gameObject);
            }
        });
    }

    public void remove(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                for (ArrayList<GameObject> gameObjects : layers) {
                    boolean removed = gameObjects.remove(gameObject);
                    if (!removed) continue;
//                    if (gameObject instanceof Recyclable) {
//                        RecycleBin.add((Recyclable) gameObject);
//                    }
                    break;
                }
            }
        });
    }

    public int objectCount() {
        int count = 0;
        for (ArrayList<GameObject> gameObjects : layers) {
            count += gameObjects.size();
        }
        return count;
    }
}
