package com.example.monstersurvival.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.Sound;
import com.example.monstersurvival.game.items.Item1active;
import com.example.monstersurvival.game.object.Player;
import com.example.monstersurvival.game.scenes.MainGame;

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
        Sound.playEffect(this,R.raw.etc_warp);
        MainGame.getInstance().stageIndex = 1;
        Toast.makeText(getApplicationContext(), "Stage 1 Selected", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(MainGame.PARAM_STAGE_INDEX, 0);
        startActivity(intent);
        finish();
    }

    public void onStage2Select(View view) {
        Sound.playEffect(this,R.raw.etc_warp);
        MainGame.getInstance().stageIndex = 2;
        Toast.makeText(getApplicationContext(), "Stage 2 Selected", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(MainGame.PARAM_STAGE_INDEX, 0);
        startActivity(intent);
        finish();
    }

    public void onStage3Select(View view) {
        Sound.playEffect(this,R.raw.etc_warp);
        MainGame.getInstance().stageIndex = 3;
        Toast.makeText(getApplicationContext(), "Infinite Selected", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(MainGame.PARAM_STAGE_INDEX, 0);
        startActivity(intent);
        finish();
    }

}
