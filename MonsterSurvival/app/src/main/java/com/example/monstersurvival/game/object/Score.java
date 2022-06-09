package com.example.monstersurvival.game.object;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.interfaces.GameObject;
import com.example.monstersurvival.framework.res.Metrics;
import com.example.monstersurvival.game.scenes.MainGame;

public class Score implements GameObject{
    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();
    private int score, displayScore;

    private Paint scorePaint = new Paint();

    private float x,y;

    public Score(float x,float y) {
        scorePaint.setTextSize(100);
        scorePaint.setColor(Color.WHITE);
        this.x = x;
        this.y = y;
    }

    public void set(int score) {
        this.score = MainGame.getInstance().getResultCoin();;
        this.displayScore = score;
    }

    @Override
    public void update(float frameTime) {
        this.score = MainGame.getInstance().getResultCoin();;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(" Coin : "+ this.score,x,y, scorePaint);
    }


}