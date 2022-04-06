package kr.ac.tukorea.ge.sgp02.s2017180003.gameview2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class Ball implements GameObject {
    private static Bitmap bitmap;
    private static Rect srcRect = new Rect(); // static을 붙여서 클래스 하나만 가지고 있어도 여러객체 공유시키기
    private RectF dstRect = new RectF();
    private float deltaX,deltaY;


    // alt + insert 하면 컨스트럭트 자동으로 만들어줌
    public Ball(float deltaX, float deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;

        dstRect.set(0,0,200,200);

        // 이미지가 로드되어있으면 다시 로드시키지 않음
        if (bitmap == null)
        {
            Resources res = GameView.view.getResources();
            bitmap  = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
            srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        }
    }

    // alt + insert 로 setter
    public static void setBitmap(Bitmap bitmap) {
        Ball.bitmap = bitmap;
        srcRect.set(0,0,bitmap.getWidth(),bitmap.getHeight());
    }

    public void update() {
        MainGame game = MainGame.getInstance();
        float deltaX = this.deltaX * game.frameTime;
        float deltaY = this.deltaY * game.frameTime;


        dstRect.offset(deltaX, deltaY);
        if (deltaX >= 0) {
            if (dstRect.right > GameView.view.getWidth()) {
                this.deltaX = -this.deltaX;
            }
        }
        else{
            if(dstRect.left < 0){
                this.deltaX = -this.deltaX;
            }
        }
        if(deltaY > 0){
            if(dstRect.bottom > GameView.view.getHeight()){
                this.deltaY = -this.deltaY;
            }
        }
        else{
            if(dstRect.top <0){
                this.deltaY = -this.deltaY;
            }
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap,srcRect,dstRect,null);
    }
}
