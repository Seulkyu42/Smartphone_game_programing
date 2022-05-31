package com.example.monstersurvival.game;

import android.graphics.Canvas;
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

    private ProgressBar(){
        gauge = new Gauge(
                Metrics.size(R.dimen.enemy_gauge_fg_width), R.color.enemy_gauge_fg,
                Metrics.size(R.dimen.enemy_gauge_bg_width), R.color.enemy_gauge_bg,
                size * 0.9f
        );
        gauge.setValue(1.0f);
    }

    @Override
    public void update() {

    }

    public void draw(Canvas canvas){
        gauge.draw(canvas, 50, 50 + size * 0.5f);
    }


}
