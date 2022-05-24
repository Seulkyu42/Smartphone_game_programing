package com.example.monstersurvival.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.objects.Sprite;

public class Life extends Sprite {

    private static final String TAG = GameView.class.getSimpleName();
    protected RectF lifeRect = new RectF();
    //protected Bitmap bitmap;
    private float lifeCount = 3.0f;
    private float lifeY;

    private Player player;

    public Life(float x, float y) {
        super(x,y,R.dimen.player_radius, R.mipmap.life);
        lifeY= y;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    @Override
    public void update() {
        lifeCount = player.getHealth();
    }

    public void draw(Canvas canvas){
        for(int i =0; i< (int) lifeCount; ++i) {
            float lifeX = radius + radius * i;
            dstRect.set(lifeX - radius/2,100-radius/2, lifeX+radius/2,100+radius/2);
            canvas.drawBitmap(bitmap, null, dstRect, null);
        }
    }


}
