package com.example.monstersurvival.app;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.game.items.Item1active;
import com.example.monstersurvival.game.object.Player;

public class StageSelectActivity extends AppCompatActivity{

    private static final String TAG = GameView.class.getSimpleName();
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GameView(this, null);
        setContentView(R.layout.stage_main);
    }

    public void onBackButtonClicked(View view){
        finish();
    }

    public void onStage1Select(View view) {
    }

    public void onStage2Select(View view) {
    }

    public void onStage3Select(View view) {
    }
}
