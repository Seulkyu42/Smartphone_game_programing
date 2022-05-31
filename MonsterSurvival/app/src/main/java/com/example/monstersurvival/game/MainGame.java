package com.example.monstersurvival.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.example.monstersurvival.R;
import com.example.monstersurvival.app.StatsActivity;
import com.example.monstersurvival.framework.interfaces.GameObject;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.res.Metrics;
import com.example.monstersurvival.framework.objects.VertScrollBackground;
import com.example.monstersurvival.game.items.itemGenerator;

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
    private Enemy enemy;
    private Life life;

    /////////////////////////// 레이어
    protected ArrayList<ArrayList<GameObject>> layers;
    public enum Layer {
        bg1,enemy,item1,item2,item3,coin,
        activeitem,score,player, touchUi,health,controller,COUNT
    }

    public float size(float unit) {
        return Metrics.height / 9.5f * unit;
    }

    public static void clear() {
        singleton = null;
    }

    public void init(){
        Log.d(TAG,"INITIATING");
        initLayers(Layer.COUNT.ordinal());

        float playerY = Metrics.height/2;
        player = new Player(Metrics.width/2, playerY);
        add(Layer.player, player);
        add(Layer.controller, new EnemyGenerator());
        add(Layer.controller, new itemGenerator());
        add(Layer.controller, new CollisionChecker(player));
        ////// 배경 //////
        add(Layer.bg1, new VertScrollBackground(R.mipmap.background_1, Metrics.size(R.dimen.bg_speed_stage1)));
        ////// 배경 //////

        ////// 라이프 //////
        life = new Life(Metrics.width/2,Metrics.height/2);
        add(Layer.health, life);
        life.setPlayer(player);
        ////// 라이프 //////

        ////// 일시정지 버튼 //////
        float btn_x = size(4.75f);
        float btn_y = size(0.5f);
        float btn_w = size(0.75f);
        float btn_h = size(0.75f);
        add(Layer.touchUi, new Button(
                btn_x, btn_y, btn_w, btn_h, R.mipmap.pause_button, R.mipmap.pause_button,
                new Button.Callback() {
                    @Override
                    public boolean onTouch(Button.Action action) {
                        if (action != Button.Action.pressed) return false;
                        return true;
                    }
                }));
        ////// 일시정지 버튼 //////
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


    public Player getPlayer(){
        return player;
    }
    public Enemy getEnemy() { return enemy;}

}
