package com.example.monstersurvival.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.Sound;
import com.example.monstersurvival.game.object.CollisionChecker;
import com.example.monstersurvival.game.scenes.MainGame;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = CollisionChecker.class.getSimpleName();
    SharedPreferences pref;
    SharedPreferences.Editor editor;

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
        Sound.playEffect(this,R.raw.ui_click);
        Intent intent = new Intent(getApplicationContext(), StageSelectActivity.class);
        startActivity(intent);
    }

    public void onStats(View view) {
        Sound.playEffect(this,R.raw.ui_click);
        Intent intent = new Intent(getApplicationContext(), StatsActivity.class);
        startActivity(intent);
    }

    public void onBtnLanguage(View view) {
        Sound.playEffect(this,R.raw.ui_click);
        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();;
        super.onBackPressed();
    }
}