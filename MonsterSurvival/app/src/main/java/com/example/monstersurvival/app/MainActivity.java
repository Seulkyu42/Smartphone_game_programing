package com.example.monstersurvival.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.monstersurvival.R;
import com.example.monstersurvival.game.scenes.MainGame;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnStart(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(MainGame.PARAM_STAGE_INDEX, 0);
        startActivity(intent);
    }
    public void onStageSelect(View view) {
        Intent intent = new Intent(getApplicationContext(), StatsActivity.class);
        startActivity(intent);
    }

    public void onStats(View view) {
    }
}