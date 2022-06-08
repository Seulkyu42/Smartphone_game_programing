package com.example.monstersurvival.app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.game.items.Item2active;
import com.example.monstersurvival.game.object.Player;
import com.example.monstersurvival.game.items.Item1active;
import com.example.monstersurvival.game.scenes.MainGame;

public class StatsActivity extends AppCompatActivity{

    private static final String TAG = GameView.class.getSimpleName();
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GameView(this, null);
        setContentView(R.layout.stats_main);
        setText();
    }

    public void onBackButtonClicked(View view){
        finish();
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    private int stat1Level = 1;
    public void onStat1Clicked(View view) {
        Item1active item1active = Item1active.get();
        float tempLife = item1active.getLife();

        if(MainGame.getInstance().coin >= 300) {
            item1active.statUp(tempLife, 1.0f);
            MainGame.getInstance().coin -= 300;
            stat1Level += 1;
        }

        String saveText = "";
        for(int i =0; i<3; ++i){
            if(i<= 3 - stat1Level){
                saveText += "□";
            } else{
                saveText += "■";
            }
        }
        Log.d(TAG,"Text"+saveText);
        TextView textView = (TextView) findViewById(R.id.stat1);
        textView.setText(saveText);
    }
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    private int stat2Level = 1;
    public void onStat2Clicked(View view) {
        setText();
        Item1active item1active = Item1active.get();
        float tempLife = item1active.getLife();

        if(MainGame.getInstance().coin >= 100) {
            item1active.statUp(tempLife, 1.0f);
            MainGame.getInstance().coin -= 100;
            stat2Level += 1;
        }

        setText();

        String saveText = "";
        for(int i =0; i<5; ++i){
            if(i<= 5 - stat2Level){
                saveText += "□";
            } else{
                saveText += "■";
            }
        }
        Log.d(TAG,"Text"+saveText);
        TextView textView = (TextView) findViewById(R.id.stat2);
        textView.setText(saveText);
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    private int stat3Level = 1;
    public void onStat3Clicked(View view) {
        Item2active item2active = Item2active.get();
        //float tempLife = item2active.getLife();

        if(MainGame.getInstance().coin >= 100) {
            //item2active.statUp(tempLife, 1.0f);
            MainGame.getInstance().coin -= 100;
            stat3Level += 1;
        }

        String saveText = "";
        for(int i =0; i<5; ++i){
            if(i<= 5 - stat3Level){
                saveText += "□";
            } else{
                saveText += "■";
            }
        }
        Log.d(TAG,"Text"+saveText);
        TextView textView = (TextView) findViewById(R.id.stat3);
        textView.setText(saveText);
    }
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    private int stat4Level = 1;
    public void onStat4Clicked(View view) {
        setText();
        Item1active item1active = Item1active.get();
        float tempLife = item1active.getLife();
        item1active.statUp(tempLife,1.0f);

        if(MainGame.getInstance().coin >= 100) {
            item1active.statUp(tempLife, 1.0f);
            MainGame.getInstance().coin -= 100;
            stat4Level += 1;
        }

        setText();
        String saveText = "";
        for(int i =0; i<5; ++i){
            if(i<= 5 - stat4Level){
                saveText += "□";
            } else{
                saveText += "■";
            }
        }
        Log.d(TAG,"Text"+saveText);
        TextView textView = (TextView) findViewById(R.id.stat4);
        textView.setText(saveText);
    }

    public void setText(){
        int coin = MainGame.getInstance().getCoin();

        TextView textView = (TextView) findViewById(R.id.playergold);
        textView.setText("Coin : " + coin);

    }
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

}
