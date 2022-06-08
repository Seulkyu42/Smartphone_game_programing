package com.example.monstersurvival.framework.interfaces;

import android.app.Activity;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class ResetData extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public void init(){
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void resetData(){
        editor.putInt("stat1Key",1);
        editor.putInt("stat2Key",1);
        editor.putInt("stat3Key",1);
        editor.putInt("stat4Key",1);
        editor.apply();
    }
}


