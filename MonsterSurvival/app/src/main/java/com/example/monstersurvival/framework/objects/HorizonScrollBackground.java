package com.example.monstersurvival.framework.objects;

import android.graphics.Canvas;

import com.example.monstersurvival.framework.res.Metrics;
import com.example.monstersurvival.game.scenes.MainGame;


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
    public void update(float frameTime) {
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
