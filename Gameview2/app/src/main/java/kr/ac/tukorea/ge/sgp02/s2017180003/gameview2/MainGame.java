package kr.ac.tukorea.ge.sgp02.s2017180003.gameview2;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

public class MainGame {
    private int BALL_COUNT = 10;
    private ArrayList<GameObject> objects = new ArrayList<>();
    private FIghter fighter;

    private static MainGame singleton;


    public static MainGame getInstance(){
        if(singleton == null){
            singleton = new MainGame();
        }
        return singleton;
    }

    public float frameTime;

    // 싱글톤으로 이걸 넣어야 다른쪽이 부르면 터짐?
    private MainGame(){

    }
    
    public void init() {
        Random random = new Random();
        for(int i =0;i < BALL_COUNT; ++i){
            float dx = random.nextInt(500)+100; // 0~9 + 5 값 임
            float dy = random.nextInt(500)+100;
            Ball ball = new Ball(dx,dy);
            objects.add(ball);
        }

        fighter = new FIghter(Metrics.width/2,Metrics.height/2);
        objects.add(fighter);
    }

    public void update(int elapsedNanos) {
        frameTime = elapsedNanos / 1_000_000_000.0f; //  * 1e+9f;

        for(GameObject gameObject : objects){
            gameObject.update();
        }
    }

    public void draw(Canvas canvas) {
        for(GameObject gameObject : objects){
            gameObject.draw(canvas);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch(action){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int x = (int) event.getX();
                int y = (int) event.getY();


                fighter.setTargetPosition(x,y);
                return true;
        }
        return false;
    }
}
