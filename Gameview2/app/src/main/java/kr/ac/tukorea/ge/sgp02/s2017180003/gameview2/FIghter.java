package kr.ac.tukorea.ge.sgp02.s2017180003.gameview2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.lang.reflect.Type;

public class FIghter implements GameObject  {
    private static Bitmap bitmap;
    private static Rect srcRect = new Rect(); // static을 붙여서 클래스 하나만 가지고 있어도 여러객체 공유시키기
    private RectF dstRect = new RectF();

    private float x,y;
    private float dx,dy; //deltaX,Y
    private float tx,ty; //targetX,Y

    // alt + insert 하면 컨스트럭트 자동으로 만들어줌
    public FIghter(float x, float y) {
        this.x = x;
        this.y = y;

//        Resources res = GameView.view.getResources();
        float radius = Metrics.size(R.dimen.fighter_radius);
//        float radius_dp = 100;
//        DisplayMetrics displayMetrics = GameView.view.getResources().getDisplayMetrics();
//        float radius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,radius_dp,displayMetrics);
        dstRect.set(x-radius,y-radius,x+radius,y+radius);

        tx = x;
        ty = y;

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
        float angle = (float) Math.atan2(ty-y,tx-x); // 앵글
        float speed = Metrics.size(R.dimen.fighter_speed);
        float dist = speed * MainGame.getInstance().frameTime;

        dx = (float) (dist * Math.cos(angle));
        dy = (float) (dist * Math.sin(angle));

        // 타겟위치까지 도달하면 딱 멈추는 코드
        {
            if (dx > 0) {
                if (x + dx > tx) {
                    dx = tx - x;
                    x = tx;
                } else {
                    x += dx;
                }

            } else {
                if (x + dx < tx) {
                    dx = tx - x;
                    x = tx;
                } else {
                    x += dx;
                }
            }

            if (dy > 0) {
                if (y + dy > ty) {
                    dy = ty - y;
                    y = ty;
                } else {
                    y += dy;
                }

            } else {
                if (y + dy < ty) {
                    dy = ty - y;
                    y = ty;
                } else {
                    y += dy;
                }
            }
        }

        dstRect.offset(dx,dy);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap,srcRect,dstRect,null);
    }

    public void setTargetPosition(int x, int y) {
        tx = x;
        ty = y;

    }
}
