package com.example.monstersurvival.app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.Sound;
import com.example.monstersurvival.game.items.Item2active;
import com.example.monstersurvival.game.items.Item3active;
import com.example.monstersurvival.game.object.Player;
import com.example.monstersurvival.game.items.Item1active;
import com.example.monstersurvival.game.scenes.MainGame;

public class StatsActivity extends AppCompatActivity{

    private static final String TAG = GameView.class.getSimpleName();
    private Player player;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private int stat1Level = 0;
    private int stat2Level = 0;
    private int stat3Level = 0;
    private int stat4Level = 0;

    private static StatsActivity instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GameView(this, null);
        setContentView(R.layout.stats_main);
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();
        instance = this;

        { // SharedReference 초기 세팅 //
            stat1Level = pref.getInt("stat1Key", 0);
            stat2Level = pref.getInt("stat2Key", 0);
            stat3Level = pref.getInt("stat3Key", 0);
            stat4Level = pref.getInt("stat4Key", 0);
            stat1SetText();
            stat2SetText();
            stat3SetText();
            stat4SetText();
        }
        Log.d(TAG,"stat" + stat1Level);
        statInit();

        setText();
    }

    public static StatsActivity getInstance() {
        return instance;
    }

    public void statInit(){
        MainGame.getInstance().addHealth(stat1Level);
        Item1active item1 = new Item1active();
        item1.addLife(stat2Level);
    }

    public void onBackButtonClicked(View view){
        finish();
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    public void onStat1Clicked(View view) {
        Sound.playEffect(this,R.raw.etc_item4);

        if(stat1Level <= 3) {
            if (MainGame.getInstance().coin >= 300) {
                MainGame.getInstance().coin -= 300;
                stat1Level += 1;
                MainGame.getInstance().setHealth();
                Log.d(TAG,"stat" + stat1Level);
                setText();
            }

            stat1SetText();
        } else
        {
            Toast.makeText(getApplicationContext(), "Stat1 Max Level", Toast.LENGTH_SHORT).show();
        }
    }
    private void stat1SetText() {
        String saveText = "";
        for (int i = 0; i < 3; ++i) {
            if (i <= 2 - stat1Level) {
                saveText += "□";
            } else {
                saveText += "■";
            }
        }
        TextView textView = (TextView) findViewById(R.id.stat1);
        textView.setText(saveText);

    }
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    public void onStat2Clicked(View view) {
        Sound.playEffect(this,R.raw.etc_item4);

        if(stat2Level <= 5) {
            Item1active item1active = Item1active.get();
            float tempLife = item1active.getLife();

            if (MainGame.getInstance().coin >= 100) {
                item1active.statUp(tempLife, 1.0f);
                MainGame.getInstance().coin -= 100;
                stat2Level += 1;

                setText();
            }

            stat2SetText();
        } else
        {
            Toast.makeText(getApplicationContext(), "Stat2 Max Level", Toast.LENGTH_SHORT).show();
        }
    }
    private void stat2SetText() {
        String saveText = "";
        for (int i = 0; i < 5; ++i) {
            if (i <= 4 - stat2Level) {
                saveText += "□";
            } else {
                saveText += "■";
            }
        }
        Log.d(TAG, "Text" + saveText);
        TextView textView = (TextView) findViewById(R.id.stat2);
        textView.setText(saveText);
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    public void onStat3Clicked(View view) {
        Sound.playEffect(this,R.raw.etc_item4);
        if(stat3Level <= 5) {
            Item2active item2active = Item2active.get();
            float tempSpeed = item2active.getSpeed();

            if (MainGame.getInstance().coin >= 100) {
                item2active.statUp(tempSpeed, 100.0f);
                MainGame.getInstance().coin -= 100;
                stat3Level += 1;
                setText();
            }

            stat3SetText();
        } else {
            Toast.makeText(getApplicationContext(), "Stat3 Max Level", Toast.LENGTH_SHORT).show();
        }
    }
    private void stat3SetText() {
        String saveText = "";
        for (int i = 0; i < 5; ++i) {
            if (i <= 4 - stat3Level) {
                saveText += "□";
            } else {
                saveText += "■";
            }
        }
        Log.d(TAG, "Text" + saveText);
        TextView textView = (TextView) findViewById(R.id.stat3);
        textView.setText(saveText);
    }
    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    public void onStat4Clicked(View view) {

        Sound.playEffect(this,R.raw.etc_item4);
        if(stat4Level <= 5){
            Item3active item3active = Item3active.get();
        if(MainGame.getInstance().coin >= 100) {
            item3active.statUp(1);
            MainGame.getInstance().coin -= 100;
            stat4Level += 1;

            setText();
        }

            stat4SetText();
        } else {
            Toast.makeText(getApplicationContext(), "Stat4 Max Level", Toast.LENGTH_SHORT).show();
        }
    }
    private void stat4SetText() {
        String saveText = "";
        for(int i =0; i<5; ++i){
            if(i<= 4 - stat4Level){
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


    @Override
    protected void onPause() {
        Sound.playEffect(this,R.raw.ui_user_close);
        Log.d(TAG,"stat" + stat1Level);
        editor.putInt("stat1Key", stat1Level);
        editor.putInt("stat2Key", stat2Level);
        editor.putInt("stat3Key", stat3Level);
        editor.putInt("stat4Key", stat4Level);
        editor.apply();

        super.onPause();

    }
}
