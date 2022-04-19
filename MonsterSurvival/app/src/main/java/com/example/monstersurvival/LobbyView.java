package com.example.monstersurvival;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;

import androidx.annotation.Nullable;

public class LobbyView extends  View implements Choreographer.FrameCallback {

    public static GameView view;
    private boolean initialized;


    public LobbyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //initView();
    }

    private void initView() {

        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    public void doFrame(long l) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
    }
}
