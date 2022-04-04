package kr.ac.tukorea.ge.sgp02.s2017180003.gameview2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Ball {
    private static Bitmap bitmap;
    private static Rect srcRect = new Rect(); // static을 붙여서 클래스 하나만 가지고 있어도 여러객체 공유시키기
    private Rect dstRect = new Rect();
    private int deltaX,deltaY;


    // alt + insert 하면 컨스트럭트 자동으로 만들어줌
    public Ball(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;

        dstRect.set(0,0,200,200);
    }

    // alt + insert 로 setter
    public static void setBitmap(Bitmap bitmap) {
        Ball.bitmap = bitmap;
        srcRect.set(0,0,bitmap.getWidth(),bitmap.getHeight());
    }

    public void update() {
        dstRect.offset(deltaX, deltaY);
        if (deltaX >= 0) {
            if (dstRect.right > GameView.view.getWidth()) {
                deltaX = -deltaX;
            }
        }
        else{
            if(dstRect.left < 0){
                deltaX = -deltaX;
            }
        }
        if(deltaY > 0){
            if(dstRect.bottom > GameView.view.getHeight()){
                deltaY = -deltaY;
            }
        }
        else{
            if(dstRect.top <0){
                deltaY = -deltaY;
            }
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap,srcRect,dstRect,null);
    }
}