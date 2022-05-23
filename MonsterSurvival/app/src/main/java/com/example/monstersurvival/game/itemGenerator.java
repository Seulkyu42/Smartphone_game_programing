package com.example.monstersurvival.game;

import android.graphics.Canvas;

import com.example.monstersurvival.framework.GameObject;
import com.example.monstersurvival.framework.Metrics;

import java.util.Random;

public class itemGenerator implements GameObject {
    private static final float INITIAL_SPAWN_INTERVAL = 2.0f;
    private final float spawnInterval;
    private float elapsedTime;
    private static Random random = new Random();
    private int itemNumber;


    public itemGenerator() {
        this.spawnInterval = INITIAL_SPAWN_INTERVAL;
        Enemy.size = Metrics.width / 5.0f * 0.9f;
    }

    @Override
    public void update() {
        float frameTime = MainGame.getInstance().frameTime;
        elapsedTime += frameTime;
        if (elapsedTime > spawnInterval) {
            spawn();
            elapsedTime -= spawnInterval;
        }
    }

    private void spawn() {
        itemNumber = random.nextInt(3)+1;

        if(itemNumber == 1){
            Item1 item1 = Item1.get(400,random.nextInt(500) + 300);
            item1.setPlayer(MainGame.getInstance().getPlayer());
            MainGame.getInstance().add(MainGame.Layer.item, item1);
        }else if(itemNumber == 2){
            Item2 item2 = Item2.get(600,random.nextInt(500) + 300);
            item2.setPlayer(MainGame.getInstance().getPlayer());
            MainGame.getInstance().add(MainGame.Layer.item, item2);
        }else{
            Item3 item3 = Item3.get(800,random.nextInt(500) + 300);
            item3.setPlayer(MainGame.getInstance().getPlayer());
            MainGame.getInstance().add(MainGame.Layer.item, item3);
        }

//        for(int i =0; i<random.nextInt(10)+3; ++i) {
//            Enemy enemy = Enemy.get(100+(150 * i), random.nextInt(500) + 300);
//            enemy.setPlayer(MainGame.getInstance().getPlayer());
//
//            MainGame.getInstance().add(MainGame.Layer.enemy, enemy);
//        }
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
