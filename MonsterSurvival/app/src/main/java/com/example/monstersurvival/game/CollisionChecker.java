package com.example.monstersurvival.game;


import android.graphics.Canvas;

import com.example.monstersurvival.framework.BoxCollidable;
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
//        Player player = (Player) game.objectsAt(MainGame.Layer.player.ordinal()).get(0);
        //<GameObject> items = game.objectsAt(MainGame.Layer.item.ordinal());
//        for (GameObject item: items) {
//            if (!(item instanceof BoxCollidable)) {
//                continue;
//            }
//            if (CollisionHelper.collides(player, (BoxCollidable) item)) {
//                //Log.d(TAG, "Collision: " + item);
//                if (item instanceof JellyItem) {
//                    JellyItem jelly = (JellyItem) item;
//                    if (!jelly.valid) continue;
//                    Sound.playEffect(jelly.soundId());
//                    if (jelly.index == 26) {
//                        Log.d(TAG, "Collision: " + jelly);
//                        player.changeBitmap();
//                    }
//                }
//                game.remove(item);
//            }
//        }
    }

    @Override
    public void draw(Canvas canvas) {

    }

}
