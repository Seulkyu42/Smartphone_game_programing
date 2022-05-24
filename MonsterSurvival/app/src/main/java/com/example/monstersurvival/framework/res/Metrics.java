package com.example.monstersurvival.framework.res;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import com.example.monstersurvival.framework.GameView;

public class Metrics {
    public static int width;
    public static int height;

    public static Context CURRENT_CONTEXT;
    public static long INIT_TIME;

    public static float size(int dimenResId) {
        Resources res = GameView.view.getResources();
        return res.getDimension(dimenResId);
    }

    public static float floatValue(int dimenResId) {
        Resources res = GameView.view.getResources();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            return res.getFloat(dimenResId);
//        }

        TypedValue outValue = new TypedValue();
        res.getValue(dimenResId, outValue, true);
        float value = outValue.getFloat();
        return value;
    }

    public static float getFloat(int dimenResourceId) {
        Resources res = GameView.view.getResources();
        TypedValue outValue = new TypedValue();
        res.getValue(dimenResourceId, outValue, true);

        return outValue.getFloat();
    }
}
