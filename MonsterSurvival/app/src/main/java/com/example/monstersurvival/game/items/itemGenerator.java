package com.example.monstersurvival.game.items;

import android.graphics.Canvas;

import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.interfaces.GameObject;
import com.example.monstersurvival.framework.res.Metrics;
import com.example.monstersurvival.game.object.Enemy;
import com.example.monstersurvival.game.scenes.MainGame;

import java.util.Random;

public class itemGenerator implements GameObject {

    private static final String TAG = GameView.class.getSimpleName();
    private static final float INITIAL_SPAWN_INTERVAL = 5f;
    private final float spawnInterval;
    private float elapsedTime;
    private static Random random = new Random();
    private int itemNumber;


    public itemGenerator() {
        this.spawnInterval = INITIAL_SPAWN_INTERVAL;
        Enemy.size = Metrics.width / 5.0f * 0.9f;
    }

    @Override
    public void update(float frameTime) {
        elapsedTime += frameTime;
        if (elapsedTime > spawnInterval) {
            spawn();
            elapsedTime -= spawnInterval;
        }
    }

    private void spawn() {
       itemNumber = random.nextInt(3)+1;

        if(itemNumber == 1){
            Item1 item1 = Item1.get(random.nextInt(Metrics.width),random.nextInt(500) + 300);
            item1.setPlayer(MainGame.getInstance().getPlayer());
            MainGame.getInstance().add(MainGame.Layer.item1.ordinal(), item1);

        }else if(itemNumber == 2){
                Item2 item2 = Item2.get(random.nextInt(Metrics.width),random.nextInt(500) + 300);
                item2.setPlayer(MainGame.getInstance().getPlayer());
                MainGame.getInstance().add(MainGame.Layer.item2.ordinal(), item2);

        }else if(itemNumber == 3){
            Item3 item3 = Item3.get(random.nextInt(Metrics.width),random.nextInt(500) + 300);
            item3.setPlayer(MainGame.getInstance().getPlayer());
            MainGame.getInstance().add(MainGame.Layer.item3.ordinal(), item3);
        }
    }

    @Override
    public void draw(Canvas canvas) {
    }

}
