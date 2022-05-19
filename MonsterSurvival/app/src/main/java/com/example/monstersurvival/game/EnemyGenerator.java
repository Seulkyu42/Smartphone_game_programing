package com.example.monstersurvival.game;

import android.graphics.Canvas;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameObject;
import com.example.monstersurvival.framework.Metrics;

import java.util.Random;

public class EnemyGenerator implements GameObject {
    private static final float INITIAL_SPAWN_INTERVAL = 2.0f;
    private final float spawnInterval;
    private float elapsedTime;
    private static Random random = new Random();

    public EnemyGenerator() {
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
        for(int i =0; i<random.nextInt(10)+3; ++i) {
            Enemy enemy = Enemy.get(100+(150 * i), 300);
            enemy.setPlayer(MainGame.getInstance().getPlayer());

            MainGame.getInstance().add(MainGame.Layer.enemy, enemy);
        }
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
