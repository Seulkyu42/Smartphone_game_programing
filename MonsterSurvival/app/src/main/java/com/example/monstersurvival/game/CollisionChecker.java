package com.example.monstersurvival.game;


import android.graphics.Canvas;
import android.util.Log;

import com.example.monstersurvival.framework.BoxCollidable;
import com.example.monstersurvival.framework.CollisionHelper;
import com.example.monstersurvival.framework.GameObject;

import java.util.ArrayList;

public class CollisionChecker implements GameObject {
    private static final String TAG = CollisionChecker.class.getSimpleName();
    private final Player player;

    public CollisionChecker(Player player) {
        this.player = player;
    }

    @Override
    public void update() {
        MainGame game = MainGame.getInstance();
        ArrayList<GameObject> enemies = game.objectsAt(MainGame.Layer.enemy);


        for(GameObject o1: enemies){
            if(!(o1 instanceof Enemy)){
                continue;
            }
            if(CollisionHelper.collides(player,(BoxCollidable) o1)){
                Log.d(TAG, "Collision: ");
            }
        }

//            for (GameObject o1 : enemies) {
//                if (!(o1 instanceof Enemy)) {
//                    continue;
//                }
//                Enemy enemy = (Enemy) o1;
//                boolean collided = false;
//                for (GameObject o2 : bullets) {
//                    if (!(o2 instanceof Bullet)) {
//                        continue;
//                    }
//                    Bullet bullet = (Bullet) o2;
//                    if (CollisionHelper.collides(enemy, bullet)) {
//                        Log.d(TAG, "Collision !!");
//                        game.remove(bullet);
//                        boolean dead = enemy.decreaseLife(bullet.getPower());
//                        if (dead) {
//                            game.remove(enemy);
//                            game.score.add(enemy.getScore());
//                        }
//                        collided = true;
//                        break;
//                    }
//                }
//                if (collided) {
//                    continue;
//                }
//                // check enemy vs fighter
//            }
//        }

    }

    @Override
    public void draw(Canvas canvas) {

    }

}
