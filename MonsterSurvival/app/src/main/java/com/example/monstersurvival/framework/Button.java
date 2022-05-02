package com.example.monstersurvival.framework;

import android.content.AttributionSource;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.example.monstersurvival.R;

public class Button extends AppCompatButton {
    public Button(@NonNull Context context) {
        super(context);
    }

    public Button(@NonNull Context context, AttributeSet attars) {
        super(context,attars);
        init();
    }

    private void init() {
        float fontSize = getResources().getDimension(R.dimen.textSize);
        setTextSize(fontSize);
        setBackgroundColor(Color.BLUE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN){
            setBackgroundColor(Color.RED);
            setTextColor(Color.WHITE);
        }else {setBackgroundColor(Color.BLUE);}
        invalidate();
        return true;
    }
}
