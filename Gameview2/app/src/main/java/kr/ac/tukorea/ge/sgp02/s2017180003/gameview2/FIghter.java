package kr.ac.tukorea.ge.sgp02.s2017180003.gameview2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class FIghter {
    private static Bitmap bitmap;
    private static Rect srcRect = new Rect(); // static을 붙여서 클래스 하나만 가지고 있어도 여러객체 공유시키기
    private Rect dstRect = new Rect();

    // alt + insert 하면 컨스트럭트 자동으로 만들어줌
    public FIghter() {
        dstRect.set(0,0,200,200);

        // 이미지가 로드되어있으면 다시 로드시키지 않음
        if (bitmap == null)
        {
            Resources res = GameView.view.getResources();
            bitmap  = BitmapFactory.decodeResource(res, R.mipmap.plane_240);
            srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        }
    }

    // alt + insert 로 setter
    public static void setBitmap(Bitmap bitmap) {
        FIghter.bitmap = bitmap;
        srcRect.set(0,0,bitmap.getWidth(),bitmap.getHeight());
    }

    public void update() {

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap,srcRect,dstRect,null);
    }

    public void setPosition(int x, int y) {
        int radius = dstRect.width() / 2;
        dstRect.set(x-radius,y-radius,x+radius,y+radius);
    }
}
