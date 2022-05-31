package com.example.monstersurvival.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.interfaces.GameObject;
import com.example.monstersurvival.framework.objects.Sprite;

public class ProgressBar implements GameObject {

    private static final String TAG = GameView.class.getSimpleName();
    protected RectF lifeRect = new RectF();
    //protected Bitmap bitmap;
    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    @Override
    public void update() {

    }

    public void draw(Canvas canvas){

    }


}
