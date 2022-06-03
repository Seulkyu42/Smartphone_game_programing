package com.example.monstersurvival.game.object;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.interfaces.GameObject;
import com.example.monstersurvival.framework.objects.Gauge;
import com.example.monstersurvival.framework.res.Metrics;
import com.example.monstersurvival.game.scenes.GameClearScene;
import com.example.monstersurvival.game.scenes.MainGame;

public class ProgressBar implements GameObject {

    private static final String TAG = GameView.class.getSimpleName();
    protected RectF lifeRect = new RectF();
    //protected Bitmap bitmap;
    private Player player;
    protected Gauge gauge;

    public void setPlayer(Player player) {
        this.player = player;
    }
    private float checkTime;
    private float value;


    public ProgressBar(){
        gauge = new Gauge(70,R.color.map_gauge_fg,100,
                R.color.map_gauge_bg,Metrics.width/1.1f);
        value = 0f;
        gauge.setValue(0.0f);
    }

    @Override
    public void update(float frameTime) {

        checkTime+=frameTime;
        if(checkTime >= 1.0f){
            value += 1.0f;
            gauge.setValue(value / MainGame.getInstance().gameTime);
            checkTime = 0.0f;
        }
        if(value >= MainGame.getInstance().gameTime){
            MainGame.getInstance().push(GameClearScene.get());
        }

    }

    public void draw(Canvas canvas){
        gauge.draw(canvas, Metrics.width/2, 80);
    }


}
