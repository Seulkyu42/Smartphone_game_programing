package kr.ac.tukorea.ge.sgp02.s2017180003.crads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int[] BUTTON_IDS = new int[]{
            R.id.card00,R.id.card02,R.id.card03,R.id.card04,
            R.id.card04,R.id.card05,R.id.card06,R.id.card07,
            R.id.card09,R.id.card10,R.id.card11,R.id.card12,
            R.id.card13,R.id.card14,R.id.card15,R.id.card16,
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnRestart(View view) {
        Log.d(TAG,"onBtnRestart");
    }

    public void onBtnCard(View view) {
        int btnIndex = findButtonIndex(view.getId());
        Log.d(TAG,"onBtnCard" + view.getId());
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