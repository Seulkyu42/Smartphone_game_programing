package com.example.monstersurvival.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.Sound;
import com.example.monstersurvival.game.items.Item1active;
import com.example.monstersurvival.game.items.Item2active;
import com.example.monstersurvival.game.items.Item3active;
import com.example.monstersurvival.game.object.CollisionChecker;
import com.example.monstersurvival.game.scenes.MainGame;

public class SettingActivity extends AppCompatActivity {

    private static final String TAG = CollisionChecker.class.getSimpleName();
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main);
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void onDataReset(View view) {
        Sound.playEffect(this,R.raw.ui_click);
        editor.putInt("stat1Key", 0);
        editor.putInt("stat2Key", 0);
        editor.putInt("stat3Key", 0);
        editor.putInt("stat4Key", 0);
        editor.apply();
    }

    public void onSound(View view) {
        Sound.playEffect(this,R.raw.ui_click);
    }

    public void onBackButtonClicked(View view) {
        Sound.playEffect(this,R.raw.ui_click);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        ;
    }
    @Override
    protected void onPause() {
        super.onPause();
        Sound.playEffect(this, R.raw.ui_user_close);
    }
}