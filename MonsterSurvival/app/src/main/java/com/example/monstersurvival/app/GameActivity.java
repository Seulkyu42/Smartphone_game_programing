package com.example.monstersurvival.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.Scene;
import com.example.monstersurvival.game.scenes.MainGame;

public class GameActivity extends AppCompatActivity{

    private static final String TAG = GameView.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int stageIndex = intent.getExtras().getInt(MainGame.PARAM_STAGE_INDEX);

        setContentView(new GameView(this, null));

        MainGame game = MainGame.getInstance();
        game.setMapIndex(stageIndex);

        Scene.push(game);
    }

    @Override
    protected void onPause() {
        GameView.view.pauseGame();
        super.onPause();
    };

    @Override
    protected void onResume() {
        super.onResume();
        GameView.view.resumeGame();
    }

    @Override
    protected void onDestroy() {
        GameView.view = null;
        MainGame.clear();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (GameView.view.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    private void update() {
        float frameTime = MainGame.getInstance().frameTime;
    }
}
