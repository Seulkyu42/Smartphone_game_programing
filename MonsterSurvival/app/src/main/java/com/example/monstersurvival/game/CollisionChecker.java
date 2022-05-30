package com.example.monstersurvival.game;


import android.content.ClipData;
import android.graphics.Canvas;
import android.util.Log;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.interfaces.BoxCollidable;
import com.example.monstersurvival.framework.CollisionHelper;
import com.example.monstersurvival.framework.interfaces.GameObject;
import com.example.monstersurvival.framework.res.Metrics;
import com.example.monstersurvival.game.items.Item1;
import com.example.monstersurvival.game.items.Item1active;
import com.example.monstersurvival.game.items.Item2;
import com.example.monstersurvival.game.items.Item2active;
import com.example.monstersurvival.game.items.Item3active;
import com.example.monstersurvival.game.items.itemGenerator;

import java.util.ArrayList;

public class CollisionChecker implements GameObject {
    private static final String TAG = CollisionChecker.class.getSimpleName();
    private final Player player;


    public CollisionChecker(Player player) {
        this.player = player;
    }
    public itemGenerator generator;

    private Item1active item1active;

    @Override
    public void update() {
        MainGame game = MainGame.getInstance();
        ArrayList<GameObject> enemies = game.objectsAt(MainGame.Layer.enemy);
        ArrayList<GameObject> items1 = game.objectsAt(MainGame.Layer.item1);
        ArrayList<GameObject> items2 = game.objectsAt(MainGame.Layer.item2);
        ArrayList<GameObject> items3 = game.objectsAt(MainGame.Layer.item3);
        ArrayList<GameObject> activeItems = game.objectsAt(MainGame.Layer.activeitem);

        for(GameObject enemy: enemies){
            if(!(enemy instanceof Enemy)){
                continue;
            }
            if(player.getBoundingRect()!=null &&
                    ((Enemy) enemy).getBoundingRect() !=null &&
                    CollisionHelper.collides(player, (BoxCollidable) enemy)){

                if(player.getIsInvincible() == false){
                    game.remove(enemy);
                }
                player.getHit();
            }

        }



        for(GameObject item1: items1){
            if(!(item1 instanceof Item1)){
                continue;
            }
            if(CollisionHelper.collides(player,(BoxCollidable) item1)){
                game.remove(item1);
                player.getItem();

                Item1active item1active = Item1active.get(0, Metrics.getFloat(R.dimen.item1time));
                item1active.setPlayer(MainGame.getInstance().getPlayer());
                MainGame.getInstance().add(MainGame.Layer.activeitem, item1active);
            }
        }
        for(GameObject item2: items2){
            if(!(item2 instanceof Item2)){
                continue;
            }
            if(CollisionHelper.collides(player,(BoxCollidable) item2)){
                game.remove(item2);
                player.getItem();

                Item2active item2active = Item2active.get(0, Metrics.getFloat(R.dimen.item2time),
                        Metrics.getFloat(R.dimen.item2speed));
                item2active.setPlayer(MainGame.getInstance().getPlayer());
                MainGame.getInstance().add(MainGame.Layer.activeitem, item2active);
            }
        }
        for(GameObject item3: items3){
            if(CollisionHelper.collides(player,(BoxCollidable) item3)){
                game.remove(item3);
                player.getItem();

                Item3active item3active = Item3active.get(0, Metrics.getFloat(R.dimen.item3time),
                        Metrics.getFloat(R.dimen.item3speed));
                item3active.setPlayer(MainGame.getInstance().getPlayer());
                MainGame.getInstance().add(MainGame.Layer.activeitem, item3active);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }

}
