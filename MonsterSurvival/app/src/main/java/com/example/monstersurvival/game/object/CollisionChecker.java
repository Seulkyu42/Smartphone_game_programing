package com.example.monstersurvival.game.object;


import android.graphics.Canvas;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.interfaces.BoxCollidable;
import com.example.monstersurvival.framework.CollisionHelper;
import com.example.monstersurvival.framework.interfaces.GameObject;
import com.example.monstersurvival.framework.res.Metrics;
import com.example.monstersurvival.game.scenes.MainGame;
import com.example.monstersurvival.game.items.Item1;
import com.example.monstersurvival.game.items.Item1active;
import com.example.monstersurvival.game.items.Item2;
import com.example.monstersurvival.game.items.Item2active;
import com.example.monstersurvival.game.items.Coin1;
import com.example.monstersurvival.game.items.Item3active;
import com.example.monstersurvival.game.items.itemGenerator;

import java.util.ArrayList;
import java.util.Random;

public class CollisionChecker implements GameObject {
    private static final String TAG = CollisionChecker.class.getSimpleName();
    private final Player player;

    public CollisionChecker(Player player) {
        this.player = player;
    }
    public itemGenerator generator;

    private static Random random = new Random();
    private Item1active item1active;

    @Override
    public void update(float frameTime) {

        MainGame game = MainGame.getInstance();
        ArrayList<GameObject> enemies = game.objectsAt(MainGame.Layer.enemy.ordinal());
        ArrayList<GameObject> items1 = game.objectsAt(MainGame.Layer.item1.ordinal());
        ArrayList<GameObject> items2 = game.objectsAt(MainGame.Layer.item2.ordinal());
        ArrayList<GameObject> items3 = game.objectsAt(MainGame.Layer.item3.ordinal());
        ArrayList<GameObject> activeItems = game.objectsAt(MainGame.Layer.activeitem.ordinal());
        ArrayList<GameObject> coins = game.objectsAt(MainGame.Layer.coin.ordinal());

        for(GameObject enemy: enemies){
            if(!(enemy instanceof BoxCollidable)){
                continue;
            }
            if(CollisionHelper.collides(player, (BoxCollidable) enemy)){
                if(player.getIsInvincible() == false){
                    game.remove(enemy);
                }
                player.getHit();
            }

            for(GameObject activeitem: activeItems) {
                if (CollisionHelper.collides((BoxCollidable) enemy, (BoxCollidable) activeitem)){
                    Coin1 coin1 = Coin1.get(500,Metrics.getFloat(R.dimen.item2time),1);
                    coin1.setEnemy((Enemy) enemy);
                    MainGame.getInstance().add(MainGame.Layer.coin.ordinal(), coin1);
                    game.remove(enemy);
                }
            }
        }

        for(GameObject coin: coins){
            if(!(coin instanceof Coin1)){
                continue;
            }
            if(CollisionHelper.collides(player, (BoxCollidable) coin)){
                game.remove(coin);
                game.setCoin();
            }
        }


        for(GameObject item1: items1){
            if(!(item1 instanceof Item1)){
                continue;
            }
            if(CollisionHelper.collides(player,(BoxCollidable) item1)){
                game.remove(item1);
                player.getItem();

                Item1active item1active = Item1active.get();
                item1active.setPlayer(MainGame.getInstance().getPlayer());
                MainGame.getInstance().add(MainGame.Layer.activeitem.ordinal(), item1active);
            }
        }
        for(GameObject item2: items2){
            if(!(item2 instanceof Item2)){
                continue;
            }
            if(CollisionHelper.collides(player,(BoxCollidable) item2)){
                game.remove(item2);
                player.getItem();
                for(int i =0; i < 1; ++i) {
                    Item2active item2active = Item2active.get();
                    item2active.setPlayer(MainGame.getInstance().getPlayer());
                    MainGame.getInstance().add(MainGame.Layer.activeitem.ordinal(), item2active);
                }
            }
        }
        for(GameObject item3: items3){
            if(CollisionHelper.collides(player,(BoxCollidable) item3)){
                game.remove(item3);
                player.getItem();

                Item3active item3active = Item3active.get();
                item3active.setPlayer(MainGame.getInstance().getPlayer());
                MainGame.getInstance().add(MainGame.Layer.activeitem.ordinal(), item3active);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }

}
