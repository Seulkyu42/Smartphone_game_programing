package com.example.monstersurvival.app;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.GameView;
import com.example.monstersurvival.framework.Metrics;
import com.example.monstersurvival.game.MainGame;
import com.example.monstersurvival.game.Player;

public class GameActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager sensorManager;

    private float xPos, xAccel, xVel = 0.0f;
    private float yPos, yAccel, yVel = 0.0f;
    private float xMax, yMax;

    private static final String TAG = GameView.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Point size = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);

        xMax = (float) size.x -100;
        yMax = (float) size.y -100;

        xPos = xMax / 2;
        yPos = yMax / 2;
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        setContentView(new GameView(this, null));
    }

    @Override
    protected void onStart() {
        super.onStart();
        //sensorManager.registerListener((SensorEventListener) this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    protected void onStop() {
        //sensorManager.unregisterListener((SensorEventListener) this);
        super.onStop();
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
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            xAccel = sensorEvent.values[0];
            yAccel = -sensorEvent.values[1];

        }
        update();
    }


    private void update(){
        float frameTime = MainGame.getInstance().frameTime;
        Player player = new Player(xPos,yPos);

        xVel += (xAccel * frameTime) * R.dimen.player_speed;
        yVel += (yAccel * frameTime) * R.dimen.player_speed;

        float xS = (xVel / 2);
        float yS = (yVel / 2);

        xPos -= xS;
        yPos -= yS;
//        if (xPos > xMax) {
//            xPos = xMax;
//        } else if (xPos < 0) {
//            xPos = 0;
//        }
//        if (yPos > yMax) {
//            yPos = yMax;
//        } else if (yPos < 0) {
//            yPos = 0;
//        }
        //player.update(xPos,yPos);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
