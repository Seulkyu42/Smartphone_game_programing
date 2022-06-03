package com.example.monstersurvival.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.example.monstersurvival.R;
import com.example.monstersurvival.app.StatsActivity;
import com.example.monstersurvival.framework.Scene;
import com.example.monstersurvival.framework.Sound;
import com.example.monstersurvival.framework.interfaces.GameObject;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.interfaces.Touchable;
import com.example.monstersurvival.framework.res.Metrics;
import com.example.monstersurvival.framework.objects.VertScrollBackground;
import com.example.monstersurvival.game.items.itemGenerator;

import java.util.ArrayList;

public class MainGame extends Scene {

    public static final String PARAM_STAGE_INDEX = "stage_index";
    private static MainGame singleton;
    public static MainGame getInstance() {
        if (singleton == null) {
            singleton = new MainGame();
        }
        return singleton;
    }

    public float frameTime;

    private static final String TAG = GameView.class.getSimpleName();


    private Player player;
    private Enemy enemy;
    private Life life;
    private ProgressBar bar;

    /////////////////////////// 레이어
    public enum Layer {
        bg1,
        coin,enemy,item1,item2,item3,
        activeitem,player,
        touchUi,Ui,health,
        controller,COUNT
    }

    public float size(float unit) {
        return Metrics.height / 9.5f * unit;
    }

    public void setMapIndex(int mapIndex) {
        this.mapIndex = mapIndex;
    }

    protected int mapIndex;


    public void init(){
        super.init();

        Log.d(TAG,"INITIATING");
        initLayers(Layer.COUNT.ordinal());

        float playerY = Metrics.height/2;
        player = new Player(Metrics.width/2, playerY);
        add(Layer.player.ordinal(), player);
        add(Layer.controller.ordinal(), new EnemyGenerator());
        add(Layer.controller.ordinal(), new itemGenerator());
        add(Layer.controller.ordinal(), new CollisionChecker(player));
        ////// 배경 //////
        add(Layer.bg1.ordinal(), new VertScrollBackground(R.mipmap.background_1, Metrics.size(R.dimen.bg_speed_stage1)));
        ////// 배경 //////

        ////// 라이프 //////
        life = new Life(Metrics.width/2,Metrics.height/2);
        add(Layer.health.ordinal(), life);
        life.setPlayer(player);
        ////// 라이프 //////

        ////// 진행바 //////
        bar = new ProgressBar();
        add(Layer.Ui.ordinal(), bar);
        ////// 진행바 //////

        ////// 일시정지 버튼 //////
        float btn_x = size(4.75f);
        float btn_y = size(1.0f);
        float btn_w = size(0.75f);
        float btn_h = size(0.75f);

        add(Layer.touchUi.ordinal(), new Button(
                btn_x, btn_y, btn_w, btn_h, R.mipmap.pause_button, R.mipmap.pause_button_on,
                new Button.Callback() {
                    @Override
                    public boolean onTouch(Button.Action action) {
                        Log.d(TAG,"pause button on");
                        if (action != Button.Action.pressed) return false;
                        push(PausedScene.get());
                        return true;
                    }
                }));
        ////// 일시정지 버튼 //////
    }

//    public boolean onTouchEvent(MotionEvent event) {
//        int action = event.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//            case MotionEvent.ACTION_MOVE:
//                float x = event.getX();
//                float y = event.getY();
//
//                player.debugTouch(x,y);
//                return true;
//        }
//        return false;
//    }

    @Override
    public boolean handleBackKey() {
        push(PausedScene.get());
        return true;
    }

    @Override
    protected int getTouchLayerIndex() {
        return Layer.touchUi.ordinal();
    }

    @Override
    public void start() {
        //Sound.playMusic();
    }

    @Override
    public void pause() {
        Sound.pauseMusic();
    }

    @Override
    public void resume() {
        Sound.resumeMusic();
    }

    @Override
    public void end() {
        Sound.stopMusic();
    }

    public Player getPlayer(){
        return player;
    }
    public Enemy getEnemy() { return enemy;}

}


//        스테이지 선택
//        스텟 2개 성장
//        스텟상점이랑 코인이랑 연동
//        소리 넣기
//        스테이지 클리어
//        플레이어 게임 오버
//        스테이지 진행정도 바