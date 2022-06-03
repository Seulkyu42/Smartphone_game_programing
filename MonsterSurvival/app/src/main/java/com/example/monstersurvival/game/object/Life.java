package com.example.monstersurvival.game.object;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.objects.Sprite;
import com.example.monstersurvival.game.scenes.GameOverScene;
import com.example.monstersurvival.game.scenes.MainGame;

public class Life extends Sprite {

    private static final String TAG = GameView.class.getSimpleName();
    protected RectF lifeRect = new RectF();
    //protected Bitmap bitmap;
    private float lifeCount = 3.0f;
    private float lifeY;

    private Player player;

    public Life(float x, float y) {
        super(x,y,R.dimen.life_radius, R.mipmap.life);
        lifeY= y;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    @Override
    public void update(float frameTime) {

        lifeCount = player.getHealth();

        if(lifeCount <= 0){
            MainGame.getInstance().push(GameOverScene.get());
        }
    }

    public void draw(Canvas canvas){
        for(int i =0; i< (int) lifeCount; ++i) {
            float lifeX = radius/2 + radius * i;
            dstRect.set(lifeX - radius/2,radius*1.5f-radius/2,
                    lifeX+radius/2,radius*1.5f+radius/2);
            canvas.drawBitmap(bitmap, null, dstRect, null);
        }
    }


}
