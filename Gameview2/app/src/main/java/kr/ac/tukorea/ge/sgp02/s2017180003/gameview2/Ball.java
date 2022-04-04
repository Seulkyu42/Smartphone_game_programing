package kr.ac.tukorea.ge.sgp02.s2017180003.gameview2;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class Ball {
    private static Bitmap bitmap;
    private static Rect srcRect = new Rect(); // static을 붙여서 클래스 하나만 가지고 있어도 여러객체 공유시키기
    private Rect dstRect = new Rect();
    private int deltaX,deltaY;

    Ball(int deltaX,int deltaY){
        // 초기화
        this.deltaX = deltaX;
        this.deltaY = deltaY;

        dstRect.set(0,0,200,200);

    }
}
