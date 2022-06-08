package com.example.monstersurvival.app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.interfaces.ResetData;
import com.example.monstersurvival.game.items.Item2active;
import com.example.monstersurvival.game.object.Player;
import com.example.monstersurvival.game.items.Item1active;
import com.example.monstersurvival.game.scenes.MainGame;

public class StatsActivity extends AppCompatActivity{

    private static final String TAG = GameView.class.getSimpleName();
    private Player player;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private int stat1Level = 1;
    private int stat2Level = 1;
    private int stat3Level = 1;
    private int stat4Level = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GameView(this, null);
        setContentView(R.layout.stats_main);
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        stat1Level = pref.getInt("stat1Key",1);
        stat2Level = pref.getInt("stat2Key",1);
        stat3Level = pref.getInt("stat3Key",1);
        stat4Level = pref.getInt("stat4Key",1);
        Log.d(TAG,"Level" + stat1Level);

        stat1SetText();
        stat2SetText();
        stat3SetText();
        stat4SetText();
        setText();
    }

    public void onBackButtonClicked(View view){
        finish();
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    public void onStat1Clicked(View view) {

        if(stat1Level <= 3) {
            if (MainGame.getInstance().coin >= 300) {
                MainGame.getInstance().setHealth();
                MainGame.getInstance().coin -= 300;
                stat1Level += 1;

                editor.putInt("stat1Level",stat1Level);
                editor.apply();
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
            if (i <= 3 - stat1Level) {
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
            if (i <= 5 - stat2Level) {
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
        if(stat3Level <= 5) {
            Item2active item2active = Item2active.get();
            //float tempLife = item2active.getLife();

            if (MainGame.getInstance().coin >= 100) {
                //item2active.statUp(tempLife, 1.0f);
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
            if (i <= 5 - stat3Level) {
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

        if(stat4Level <= 5){
        Item1active item1active = Item1active.get();
        float tempLife = item1active.getLife();

        if(MainGame.getInstance().coin >= 100) {
            item1active.statUp(tempLife, 1.0f);
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


    @Override
    protected void onPause() {
        editor.putInt("stat1Key", stat1Level);
        editor.putInt("stat2Key", stat2Level);
        editor.putInt("stat3Key", stat3Level);
        editor.putInt("stat4Key", stat4Level);
        editor.apply();
        super.onPause();

    }
}
