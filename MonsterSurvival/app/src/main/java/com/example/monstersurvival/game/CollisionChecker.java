package com.example.monstersurvival.game;


import android.content.ClipData;
import android.graphics.Canvas;
import android.util.Log;

import com.example.monstersurvival.framework.interfaces.BoxCollidable;
import com.example.monstersurvival.framework.CollisionHelper;
import com.example.monstersurvival.framework.interfaces.GameObject;

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
        ArrayList<GameObject> items = game.objectsAt(MainGame.Layer.item);

        for(GameObject enemy: enemies){
            if(!(enemy instanceof Enemy)){
                continue;
            }
            if(player.getBoundingRect()!=null &&
                    ((Enemy) enemy).getBoundingRect() !=null &&
                    CollisionHelper.collides(player, (BoxCollidable) enemy)){

                if(player.getIsInvincible() == false){
                    Log.d(TAG, "S");
                    game.remove(enemy);
                }
                player.getHit();
            }
        }

        for(GameObject item: items){
            if(CollisionHelper.collides(player,(BoxCollidable) item)){
                game.remove(item);
            }
            player.getItem();
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }

}
