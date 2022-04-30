package com.example.monstersurvival.game;

import android.content.pm.ActivityInfo;
import android.hardware.SensorManager;
import android.os.Bundle;

public class GyroController extends Player{

    public GyroController(float x, float y){
        super(x,y);
    }

    private float xPos, xAccel, xVel = 0.0f;
    private float yPos, yAccel, yVel = 0.0f;
    private float xMax, yMax;
    private SensorManager sensorManager;

}
