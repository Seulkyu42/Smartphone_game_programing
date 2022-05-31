package com.example.monstersurvival.app;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.StatsView;
import com.example.monstersurvival.game.MainGame;

public class StatsActivity extends AppCompatActivity{

    private static final String TAG = GameView.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_main);
    }

    public void onBackButtonClicked(View view){
        finish();
    }

}
