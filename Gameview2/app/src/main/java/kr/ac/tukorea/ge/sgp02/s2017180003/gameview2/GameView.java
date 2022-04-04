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
//    private Bitmap soccerBitmap;
//    private final Rect soccerSrcRect = new Rect();
//    private final Rect soccerDstRect1 = new Rect();
//    private final Rect soccerDstRect2 = new Rect();
//    private int ballDx1,ballDy1;
//    private int ballDx2,ballDy2;

    private ArrayList<Ball> balls = new ArrayList<>();
    private FIghter fighter;

    private Handler handler = new Handler();
    private long previousTime;
    private int framePerSecond; // 멤버변수로 바꾸면 이름길게쓰라
    private Paint fpsPaint = new Paint();

    public static GameView view;
    private int BALL_COUNT = 10;



    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();

    }

    private void initView() {
        view = this;

        fpsPaint.setTextSize(100);
        paint.setColor(0xFFCCCCCC);

        Random random = new Random();
        for(int i =0;i < BALL_COUNT; ++i){
            int dx = random.nextInt(10)+5; // 0~9 + 5 값 임
            int dy = random.nextInt(10)+5;
            Ball ball = new Ball(dx,dy);
            balls.add(ball);
        }

        fighter = new FIghter();

        Choreographer.getInstance().postFrameCallback(this);
        //updateFrame();
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
            update();
            invalidate();
        }
        Choreographer.getInstance().postFrameCallback(this);
    }

    private void update() {
//        ball1.update();
//        ball2.update();
        for(Ball ball : balls){
            ball.update();
        }
//        soccerDstRect1.offset(ballDx1, ballDy1);
//        if (ballDx1 >= 0) {
//            if (soccerDstRect1.right > getWidth()) {
//                ballDx1 = -ballDx1;
//            }
//        }
//        else{
//            if(soccerDstRect1.left < 0){
//                ballDx1 = -ballDx1;
//            }
//        }
//        if(ballDy1 > 0){
//            if(soccerDstRect1.bottom > getHeight()){
//                ballDy1 = -ballDy1;
//            }
//        }
//        else{
//            if(soccerDstRect1.top <0){
//                ballDy1 = -ballDy1;
//            }
//        }

//        soccerDstRect2.offset(ballDx2, ballDy2);
//        if (ballDx2 >= 0) {
//            if (soccerDstRect2.right > getWidth()) {
//                ballDx2 = -ballDx2;
//            }
//        }
//        else{
//            if(soccerDstRect2.left < 0){
//                ballDx2 = -ballDx2;
//            }
//        }
//        if(ballDy2 > 0){
//            if(soccerDstRect2.bottom > getHeight()){
//                ballDy2 = -ballDy2;
//            }
//        }
//        else{
//            if(soccerDstRect2.top <0){
//                ballDy2 = -ballDy2;
//            }
//        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText("Fps :"+framePerSecond,framePerSecond*10,100,fpsPaint);
        for(Ball ball : balls){
            ball.draw(canvas);
        }
        fighter.draw(canvas);

        //ball1.draw(canvas);
        //ball2.draw(canvas);
//        canvas.drawBitmap(Ball.bitmap, Ball.srcRect, Ball.dstRect, null);
//        canvas.drawBitmap(soccerBitmap, soccerSrcRect, soccerDstRect1, null); // 비트맵은 null도 넣어도됨
//        canvas.drawBitmap(soccerBitmap, soccerSrcRect, soccerDstRect2, null); // 비트맵은 null도 넣어도됨
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        //if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE){
          switch(action){
              case MotionEvent.ACTION_DOWN:
              case MotionEvent.ACTION_MOVE:
            int x = (int) event.getX();
            int y = (int) event.getY();


            fighter.setPosition(x,y);
            return true;
          }
        return super.onTouchEvent(event);
    }
}

