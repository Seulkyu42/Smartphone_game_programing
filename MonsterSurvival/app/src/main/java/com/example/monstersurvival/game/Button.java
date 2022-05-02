package com.example.monstersurvival.game;

import android.graphics.Canvas;
import android.service.autofill.OnClickAction;
import android.util.Log;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.Metrics;
import com.example.monstersurvival.framework.Sprite;

public class Button extends Sprite {
    private static final String TAG = GameView.class.getSimpleName();

    public Button(float x, float y) {
        super(x,y, R.dimen.player_radius, R.mipmap.pause_button);
    }

    @Override
    public void update() {
        dstRect.set(Metrics.width*0.9f-radius,Metrics.height*0.05f-radius,
                Metrics.width*0.9f+radius,Metrics.height*0.05f+radius);
        Log.d(TAG, "dstRect : " + dstRect);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap,null,dstRect,null);
    }


}
