package kr.ac.tukorea.ge.sgp02.s2017180003.crads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int[] BUTTON_IDS = new int[]{
            R.id.card00,R.id.card02,R.id.card03,R.id.card04,
            R.id.card04,R.id.card05,R.id.card06,R.id.card07,
            R.id.card09,R.id.card10,R.id.card11,R.id.card12,
            R.id.card13,R.id.card14,R.id.card15,R.id.card16,
    };

    private ImageButton previousBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnRestart(View view) {
        Log.d(TAG,"onBtnRestart");
    }

    public void onBtnCard(View view) {
        if (!(view instanceof ImageButton)){
            Log.e(TAG,"Not an ImageButton" + view); // 타입이 다를때 미리 자르고 들어가기
            return;
        }
        ImageButton imageButton = (ImageButton) view;

        if (imageButton == previousBtn){
            Log.v(TAG,"Same Button");
            return; // 방금눌린 버튼이 이전버튼과 같으면 무시하기
        }
        int btnIndex = findButtonIndex(imageButton.getId());
        Log.d(TAG,"onBtnCard : " + btnIndex);

        previousBtn = imageButton;
    }

    private int findButtonIndex(int id) {
        for (int i = 0; i<BUTTON_IDS.length; i++){
            if (id == BUTTON_IDS[i]){
                return i;
            }
        }
        return -1;
    }
}