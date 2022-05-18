package com.example.monstersurvival.game;

import android.graphics.Canvas;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameObject;
import com.example.monstersurvival.framework.Metrics;

import java.util.Random;

public class EnemyGenerator implements GameObject {
    private static final float INITIAL_SPAWN_INTERVAL = 2.0f;
    private final float spawnInterval;
    private final float fallSpeed;
    private float elapsedTime;
    private int wave;

    public EnemyGenerator() {
        this.spawnInterval = INITIAL_SPAWN_INTERVAL;
        this.fallSpeed = Metrics.size(R.dimen.enemy_initial_speed);
        Enemy.size = Metrics.width / 5.0f * 0.9f;
        wave = 0;
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

    }

    @Override
    public void draw(Canvas canvas) {
    }
}
