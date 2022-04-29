package com.example.monstersurvival.framework;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.monstersurvival.game.MainGame;

public class GameView extends View implements Choreographer.FrameCallback {

    public static GameView view;

    private boolean isInitialized;
    private boolean running;


    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        view = this;
        //initView();
    }

    protected  void onSizeChanged(int w,int h, int oldw, int oldh){
        super.onSizeChanged(w,h,oldw,oldh);

        Metrics.width = w;
        Metrics.height = h;

        if(!isInitialized){
            initView();
            running = true;
            isInitialized = true;
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    private void initView() {
        view = this;

        MainGame game = MainGame.getInstance();
        game.init();

        Choreographer.getInstance().postFrameCallback(this);

    }

    @Override
    public void doFrame(long currentTimeNanos) {
        if(!running){
            // 로그부분 패스
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        MainGame.getInstance().draw(canvas);
    }

    public void pauseGame() {
        running = false;
    }

    public void resumeGame() {
        if (isInitialized && !running) {
            running = true;
            Choreographer.getInstance().postFrameCallback(this);
        }
    }
}
