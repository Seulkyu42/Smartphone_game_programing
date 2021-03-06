package com.example.monstersurvival.framework;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.monstersurvival.framework.res.Metrics;

public class GameView extends View implements Choreographer.FrameCallback {

    public static GameView view;

    private Paint fpsPaint = new Paint();
    private long lastTimeNanos;
    private int framesPerSecond;
    private boolean isInitialized;
    private boolean running;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        view = this;
        Metrics.CURRENT_CONTEXT = context;
    }


    @Override
    protected  void onSizeChanged(int w,int h, int oldw, int oldh){
        super.onSizeChanged(w,h,oldw,oldh);

        Metrics.width = w;
        Metrics.height = h;

        if(!isInitialized){
            Metrics.INIT_TIME = System.currentTimeMillis();
            initView();
            isInitialized = true;
            running = true;
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    @Override
    public void doFrame(long currentTimeNanos) {
        if(!running){
            return;
        }
        long now = currentTimeNanos;
        if (lastTimeNanos == 0) {
            lastTimeNanos = now;
        }
        int elapsed = (int) (now - lastTimeNanos);
        if (elapsed != 0) {
            framesPerSecond = 1_000_000_000 / elapsed;
            lastTimeNanos = now;
            Scene game = Scene.getTopScene();
            game.update(elapsed);
            invalidate();
        }
        Choreographer.getInstance().postFrameCallback(this);
    }

    private void initView() {
        Scene.getTopScene().init();
        fpsPaint.setColor(Color.BLUE);
        fpsPaint.setTextSize(50);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return Scene.getTopScene().onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Scene.getTopScene().draw(canvas);

        //canvas.drawText("FPS:" + framesPerSecond, framesPerSecond * 10, 100, fpsPaint);
    }

    public void pauseGame() {
        running = false;
    }

    public void resumeGame() {
        if (isInitialized && !running) {
            running = true;
            lastTimeNanos = 0;
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    public Activity getActivity() {
        Context context = getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public Context returnContext(){
        return getContext();
    }

    public boolean onBackPressed() {
        return Scene.getTopScene().handleBackKey();
    }
}
