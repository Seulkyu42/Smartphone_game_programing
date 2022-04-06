package kr.ac.tukorea.ge.sgp02.s2017180003.gameview2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View implements Choreographer.FrameCallback {
    private static final String TAG = GameView.class.getSimpleName();
    private final Paint paint = new Paint();

    private Handler handler = new Handler();
    private long previousTime;
    private int framePerSecond; // 멤버변수로 바꾸면 이름길게쓰라
    private Paint fpsPaint = new Paint();

    public static GameView view;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();

    }

    private void initView() {
        view = this;

        fpsPaint.setTextSize(100);
        paint.setColor(0xFFCCCCCC);

        MainGame game = MainGame.getInstance();
        game.init();


        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    public void doFrame(long currentTimeNanos) {
        long now = currentTimeNanos;
//        long now = System.currentTimeMillis();
        int elapsed = (int) (now - previousTime);
        if(elapsed != 0) {
            framePerSecond = 1_000_000_000 / elapsed;
            //Log.d(TAG, "Elapsed: " + elapsed + "FPS: " + fps);
            previousTime = now;
            MainGame.getInstance().update(elapsed);
            invalidate();
        }
        Choreographer.getInstance().postFrameCallback(this);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText("Fps :"+framePerSecond,framePerSecond*10,100,fpsPaint);
        MainGame.getInstance().draw(canvas);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return MainGame.getInstance().onTouchEvent(event);
    }
}

