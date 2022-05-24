package com.example.monstersurvival.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.game.MainGame;

public class GameActivity extends AppCompatActivity{

    private static final String TAG = GameView.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this, null));
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


    private void update() {
        float frameTime = MainGame.getInstance().frameTime;
    }
}
