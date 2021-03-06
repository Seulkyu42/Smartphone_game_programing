package com.example.monstersurvival.game.object;

import android.graphics.Canvas;

import com.example.monstersurvival.framework.interfaces.GameObject;
import com.example.monstersurvival.framework.res.Metrics;
import com.example.monstersurvival.game.scenes.MainGame;

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
    public void update(float frameTime) {

        elapsedTime += frameTime;
        if (elapsedTime > spawnInterval) {
            spawn();
            elapsedTime -= spawnInterval;
        }
    }

    private void spawn() {
        int enemyNums = 0;

        if (MainGame.getInstance().stageIndex == 1) {
            enemyNums = random.nextInt(10) + 5;
        } else if (MainGame.getInstance().stageIndex == 2) {
            enemyNums = random.nextInt(20) + 5;
        } else {
            enemyNums = random.nextInt(20) + 5;
        }

        for (int i = 0; i < enemyNums; ++i) {
            Enemy enemy = Enemy.get(100 + (150 * i), random.nextInt(500) + 300);
            enemy.setPlayer(MainGame.getInstance().getPlayer());

            MainGame.getInstance().add(MainGame.Layer.enemy.ordinal(), enemy);
        }
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
