package kr.ac.tukorea.ge.sgp02.s2017180003.morecontrol;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

    public class GameView extends View {
        private Paint paint = new Paint();
        private Bitmap soccerBitmap;
        private Rect soccerSrcRect = new Rect();
        private Rect soccerDstRect = new Rect();
        private Paint leftCirclePaint = new Paint();
        private Paint rightCirclePaint = new Paint();
        private Paint textPaint = new Paint();
        private Rect textExtentRect = new Rect();

        public GameView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            initView();
        }

        private void initView() {
            paint.setColor(0xFFCCCCCC);
            leftCirclePaint.setColor(Color.YELLOW);
            rightCirclePaint.setColor(Color.RED);
            rightCirclePaint.setStyle(Paint.Style.STROKE);
            rightCirclePaint.setStrokeWidth(10);
            textPaint.setColor(Color.RED);
            textPaint.setTextSize(100);
            Resources res = getResources();
            soccerBitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);
            //Paint paint = new Paint();
            int width = getWidth();
            int height = getHeight();
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();

            int contentWidth = width - paddingLeft - paddingRight;
            int contentHeight = height - paddingTop - paddingBottom;

            int size = Math.min(contentWidth, contentHeight);
            int ballRadius = size / 10;
            int centerX = paddingLeft + contentWidth / 2;
            int centerY = paddingTop + contentHeight / 2;

            drawBackground(canvas, paddingLeft, paddingTop, contentWidth, contentHeight);

//            for(int i =0;i<10;++i){
//                drawSoccerball(canvas, ballRadius, paddingLeft + (soccerBitmap.getWidth() * i), centerY);
//            }

            drawLeftCircle(canvas, paddingLeft, paddingTop, contentWidth, contentHeight, size);

            drawRightCircle(canvas, paddingTop, contentWidth, contentHeight, centerX, size);

            drawCenterText(canvas, contentHeight, centerX, centerY);


        }

        private void drawSoccerball(Canvas canvas, int ballRadius, int centerX, int centerY) {
            soccerSrcRect.set(0, 0, soccerBitmap.getWidth(), soccerBitmap.getHeight());
            soccerDstRect.set(centerX - ballRadius/2, centerY - ballRadius/2,
                    centerX + ballRadius/2, centerY + ballRadius/2);
            canvas.drawBitmap(soccerBitmap, soccerSrcRect, soccerDstRect, paint);
        }

        private void drawBackground(Canvas canvas, int paddingLeft, int paddingTop, int contentWidth, int contentHeight) {
            canvas.drawRoundRect(paddingLeft, paddingTop, paddingLeft + contentWidth, paddingTop + contentHeight, 30, 30, paint);
        }

        private void drawLeftCircle(Canvas canvas, int paddingLeft, int paddingTop, int contentWidth, int contentHeight, int size) {
            int centerLeft = paddingLeft + contentHeight / 16;
            int centerTop = paddingTop + contentHeight / 16;
            int centerRight = size/2;
            int centerBottom = size/2;
            int startAngle = 30;
            int sweepAngle = 290;
            //canvas.drawCircle(leftCenterX, leftCenterY, circleRadius, leftCirclePaint);


            canvas.drawArc(centerLeft,centerTop,centerRight,centerBottom,startAngle,sweepAngle,true,leftCirclePaint);
        }

        private void drawRightCircle(Canvas canvas, int paddingTop, int contentWidth, int contentHeight, int centerX, int size) {
            int rightCenterX = centerX - contentWidth/8;
            int rightCenterY = paddingTop + contentHeight / 4;
            int circleRadius = size / 48;
            int turm = size/10;
            for(int i=0; i<10; ++i) {
                canvas.drawCircle(rightCenterX+(turm*i), rightCenterY, circleRadius, rightCirclePaint);
            }
        }

        private void drawCenterText(Canvas canvas, int contentHeight, int centerX, int centerY) {
            String text = "PACMAN?";
            textPaint.getTextBounds(text, 0, text.length(), textExtentRect);
            int textX = centerX - textExtentRect.width() / 2;
            int textY = centerY + contentHeight / 4;
            canvas.drawText(text, textX, textY, textPaint);
        }
    }


