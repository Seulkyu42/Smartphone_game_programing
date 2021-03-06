package com.example.dragonflight.game;

import android.graphics.Canvas;

import com.example.dragonflight.R;
import com.example.dragonflight.framework.GameObject;
import com.example.dragonflight.framework.Metrics;


public class EnemyGenerator implements GameObject {
    private static final float INITIAL_SPAWN_INTERVAL = 2.0f;
    private final float spawnInterval;
    private final float fallSpeed;
    private float elapsedTime;

    public EnemyGenerator() {
        this.spawnInterval = INITIAL_SPAWN_INTERVAL;
        this.fallSpeed = Metrics.size(R.dimen.enemy_initial_speed);

        float enemySize = Metrics.width / 5.0f;
        Enemy.setSize(enemySize);
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
        float tenth = Metrics.width / 10;
        for (int i = 1; i <= 9; i += 2) {
            float x = i * tenth;
            Enemy enemy = new Enemy(x, fallSpeed);
            MainGame.getInstance().add(enemy);
        }
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
