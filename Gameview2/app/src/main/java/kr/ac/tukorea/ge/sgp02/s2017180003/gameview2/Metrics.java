package kr.ac.tukorea.ge.sgp02.s2017180003.gameview2;

import android.content.res.Resources;

public class Metrics {
    public static int width;
    public static int height;

    public static float size(int dimenResId){
            Resources res = GameView.view.getResources();
            return res.getDimension(dimenResId);
        }
}
