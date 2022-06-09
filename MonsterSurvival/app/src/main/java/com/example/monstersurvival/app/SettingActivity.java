package com.example.monstersurvival.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monstersurvival.R;
import com.example.monstersurvival.game.object.CollisionChecker;
import com.example.monstersurvival.game.scenes.MainGame;

public class SettingActivity extends AppCompatActivity {

    private static final String TAG = CollisionChecker.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main);
    }

    public void onDataReset(View view) {
    }

    public void onSound(View view) {
    }

    public void onBackButtonClicked(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        ;
    }
}