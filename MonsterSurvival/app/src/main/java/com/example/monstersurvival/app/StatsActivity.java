package com.example.monstersurvival.app;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.res.Metrics;
import com.example.monstersurvival.game.MainGame;
import com.example.monstersurvival.game.Player;
import com.example.monstersurvival.game.items.Item1;
import com.example.monstersurvival.game.items.Item1active;

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

    public void onStat1Clicked(View view) {
        Item1active item1active = Item1active.get();
        float tempLife = item1active.getLife();
        item1active.statUp(tempLife,1.0f);

        setText();
    }

    public void setText(){

        Player player = new Player(0,0);
        int coin = player.setCoin();

        TextView textView = (TextView) findViewById(R.id.playergold);
        textView.setText("Coin : " + coin);
    }

}
