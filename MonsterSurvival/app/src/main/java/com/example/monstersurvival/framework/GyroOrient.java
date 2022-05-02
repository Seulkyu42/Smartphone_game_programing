package com.example.monstersurvival.framework;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class GyroOrient implements SensorEventListener {
    private SensorManager manager;
    private Sensor accelermeter;
    private Sensor magnometer;

    private float[] accelOutput;
    public float[] getOrientation(){
        return orientation;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
