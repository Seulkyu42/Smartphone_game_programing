package com.example.monstersurvival.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.interfaces.GameObject;
import com.example.monstersurvival.framework.objects.Sprite;
import com.example.monstersurvival.framework.res.Metrics;

public class ProgressBar implements GameObject {

    private static final String TAG = GameView.class.getSimpleName();
    protected RectF lifeRect = new RectF();
    //protected Bitmap bitmap;
    private Player player;
    protected Gauge gauge;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ProgressBar(){
        gauge = new Gauge(70,R.color.map_gauge_fg,100,
                R.color.map_gauge_bg,Metrics.width/1.1f);
        gauge.setValue(0.0f);
    }

    @Override
    public void update() {
        float frametime = MainGame.getInstance().frameTime;
        
    }

    public void draw(Canvas canvas){
        gauge.draw(canvas, Metrics.width/2, 80);
    }


}
