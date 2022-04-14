package com.example.dragonflight.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.example.dragonflight.R;
import com.example.dragonflight.framework.BoxCollidable;
import com.example.dragonflight.framework.CollisionHelper;
import com.example.dragonflight.framework.GameObject;
import com.example.dragonflight.framework.GameView;
import com.example.dragonflight.framework.Metrics;

import java.util.ArrayList;


public class MainGame {
    private Paint collisionPaint;
    public static MainGame getInstance() {
        if (singleton == null) {
            singleton = new MainGame();
        }
        return singleton;
    }

    public float frameTime;

    private MainGame() {
    }

    private static MainGame singleton;

    private static final int BALL_COUNT = 10;
    private ArrayList<GameObject> objects = new ArrayList<>();
    private Fighter fighter;

    public static void clear() {
        singleton = null;
    }

    public void init() {

        objects.clear();

        objects.add(new EnemyGenerator());

        float fighterY = Metrics.height - Metrics.size(R.dimen.fighter_y_offset);
        fighter = new Fighter(Metrics.width / 2, fighterY);
        objects.add(fighter);

        collisionPaint = new Paint();
        collisionPaint.setColor(Color.RED);
        collisionPaint.setStyle(Paint.Style.STROKE);
    }

    public void update(int elapsedNanos) {
        frameTime = elapsedNanos * 1e-9f; // 1_000_000_000.0f;
        for (GameObject gobj : objects) {
            gobj.update();
        }

        checkCollision();
    }

    private void checkCollision() {
        for (GameObject o1: objects) {
            if (!(o1 instanceof Enemy)) {
                continue;
            }
            Enemy enemy = (Enemy) o1;
            for (GameObject o2: objects) {
                if (!(o2 instanceof Bullet)) {
                    continue;
                }
                Bullet bullet = (Bullet) o2;
                if (CollisionHelper.collides(enemy, bullet)) {
                    remove(enemy);
                    remove(bullet);
                    break;
                }
            }
        }
    }

    public void draw(Canvas canvas) {
        for (GameObject gobj : objects) {
            gobj.draw(canvas);
            if (gobj instanceof BoxCollidable) {
                RectF rect = ((BoxCollidable) gobj).getBoundingRect();
                canvas.drawRect(rect, collisionPaint);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int x = (int) event.getX();
                int y = (int) event.getY();
                fighter.setTargetPosition(x, y);
                return true;
        }
        return false;
    }
    public void add(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                objects.add(gameObject);
            }
        });
    }

    public void remove(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                objects.remove(gameObject);
            }
        });
    }

    public int objectCount() {
        return objects.size();
    }
}
