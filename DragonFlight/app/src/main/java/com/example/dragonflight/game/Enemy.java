package com.example.dragonflight.game;


import android.graphics.RectF;

import com.example.dragonflight.R;
import com.example.dragonflight.framework.BoxCollidable;
import com.example.dragonflight.framework.Metrics;
import com.example.dragonflight.framework.Sprite;

public class Enemy extends Sprite implements BoxCollidable {
    protected float dy;
    public Enemy(float x, float speed) {
//        super(x, 0, R.dimen.enemy_radius, R.mipmap.f_01_01);
        super(x, -size/2, size, size, R.mipmap.f_01_01);
        dy = speed;
    }

    private static float size;
    public static void setSize(float size) {
        Enemy.size = size;
    }

    @Override
    public void update() {
        float frameTime = MainGame.getInstance().frameTime;
        y += dy * frameTime;
        setDstRectWithRadius();
        if (dstRect.top > Metrics.height) {
            MainGame.getInstance().remove(this);
        }
    }

    @Override
    public RectF getBoundingRect() {
        return dstRect;
    }
}
