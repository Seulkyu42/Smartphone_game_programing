package com.example.monstersurvival.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.Scene;
import com.example.monstersurvival.framework.Sound;
import com.example.monstersurvival.game.scenes.MainGame;

public class GameActivity extends AppCompatActivity{

    private static final String TAG = GameView.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int stageIndex = intent.getExtras().getInt(MainGame.PARAM_STAGE_INDEX);
        {
            Sound.playEffect(GameView.view.getContext(), R.raw.snd_c_reg_fire);
            Sound.playEffect(GameView.view.getContext(), R.raw.snd_skl_m_108_sed);
            Sound.playEffect(GameView.view.getContext(), R.raw.snd_skl_p_109_sbg);
            Sound.playEffect(GameView.view.getContext(), R.raw.gelderdrop2);
            Sound.playEffect(GameView.view.getContext(), R.raw.etc_item1);
            Sound.playEffect(GameView.view.getContext(), R.raw.dmg_wind_e);
            Sound.playEffect(this, R.raw.etc_warp);
            Sound.playEffect(this, R.raw.ui_click);
        }
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
