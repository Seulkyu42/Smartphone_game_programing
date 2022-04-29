package com.example.monstersurvival;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View implements Choreographer.FrameCallback {

    public static GameView view;
    private boolean initialized;

    private boolean isInitialized;



    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //initView();
    }

    protected  void onSizeChanged(int w,int h, int oldw, int oldh){
        super.onSizeChanged(w,h,oldw,oldh);

        Metrics.width = w;
        Metrics.height = h;

        if(!isInitialized){
            initView();
            isInitialized = true;
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

    }

    @Override
    protected void onDraw(Canvas canvas) {
        MainGame.getInstance().draw(canvas);
    }
}
