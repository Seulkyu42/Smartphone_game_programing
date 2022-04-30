package com.example.monstersurvival.game;

import android.graphics.Canvas;

import com.example.monstersurvival.framework.Metrics;
import com.example.monstersurvival.framework.Sprite;


public class HorizonScrollBackground extends Sprite {
    private final float speed;
    private final int width;
    public HorizonScrollBackground(int bitmapResId, float speed) {
        super(Metrics.width / 2, Metrics.height / 2,
                Metrics.width, Metrics.height, bitmapResId);
        this.width = bitmap.getHeight() * Metrics.width / bitmap.getWidth();
        setDstRect(Metrics.width, width);
        this.speed = speed;
    }

    @Override
    public void update() {
        this.x += speed * MainGame.getInstance().frameTime;
    }

    @Override
    public void draw(Canvas canvas) {
        int curr = (int)x % width;
        if (curr > 0) curr -= width;
        while (curr < Metrics.height) {
            dstRect.set(curr, 0, curr + width, Metrics.height);
            canvas.drawBitmap(bitmap, null, dstRect, null);
            curr += width;
        }
    }
}
