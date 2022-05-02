package com.example.monstersurvival.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.BitmapPool;
import com.example.monstersurvival.framework.GameObject;
import com.example.monstersurvival.framework.Sprite;

public class Life implements GameObject {

    protected RectF lifeRect = new RectF();
    protected Bitmap bitmap;
    int lifeCount = 0;

    float lifeY;

    public Life(float x, float y) {
        bitmap = BitmapPool.get(R.mipmap.life);
        lifeCount = R.dimen.player_health;
        lifeY= y;
    }


    // 체력 부분 실시간으로 갱신되게 게임내에서 확인
    // 교수님께 질문 -> Dimen으로 뺀 값들을 런타임 중 조정 가능한지
    @Override
    public void update() {
        lifeCount = R.dimen.player_health;
    }

    public void draw(Canvas canvas){
        for(int i =0; i<lifeCount; ++i) {
            lifeRect.offset(30 * i + 10, lifeY);
            canvas.drawBitmap(bitmap, null, lifeRect, null);
        }
    }


}
