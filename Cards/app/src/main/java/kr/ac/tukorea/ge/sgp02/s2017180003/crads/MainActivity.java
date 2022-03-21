package kr.ac.tukorea.ge.sgp02.s2017180003.crads;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int[] BUTTON_IDS = new int[]{
            R.id.card00,R.id.card01,R.id.card02,R.id.card03,
            R.id.card04,R.id.card05,R.id.card06,R.id.card07,
            R.id.card09,R.id.card10,R.id.card11,R.id.card12,
            R.id.card13,R.id.card14,R.id.card15,R.id.card16,
    };
    private  int[] resIds = new int[]{
            R.mipmap.card_as, R.mipmap.card_2c,R.mipmap.card_3d,R.mipmap.card_4h,
            R.mipmap.card_5s,R.mipmap.card_jc,R.mipmap.card_qh,R.mipmap.card_kd,
            R.mipmap.card_as, R.mipmap.card_2c,R.mipmap.card_3d,R.mipmap.card_4h,
            R.mipmap.card_5s,R.mipmap.card_jc,R.mipmap.card_qh,R.mipmap.card_kd,
    };


    private ImageButton previousBtn;
    private int flips;
    private TextView scoreTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTextView = findViewById(R.id.textView);
        startGame();
    }

    private void startGame() {
        for (int i = 0; i< BUTTON_IDS.length; ++i){
            int resId = resIds[i];

            ImageButton btn = findViewById(BUTTON_IDS[i]);
            btn.setTag(resId);
        }
        setScore(0);
    } // 게임이 시작되었을 때 해야하는 일

    public void onBtnRestart(View view) {
        Log.d(TAG,"onBtnRestart");
        askRetry();
    }

    private void askRetry() {
        new AlertDialog.Builder(this)
                .setTitle("Restart")
                .setMessage("Do you Really want to restart the game?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startGame();
                    }
                })
                .setNegativeButton("No",null)
                .create()
                .show();
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

        int prevResId = 0;
        if (previousBtn != null){
            prevResId = (Integer) previousBtn.getTag();
        }

        int resId = (Integer) imageButton.getTag();

        if (resId != prevResId){
            imageButton.setImageResource(resId);
            if(previousBtn != null) {
                previousBtn.setImageResource(R.mipmap.card_blue_back);
            }

            previousBtn = imageButton;
            setScore(flips+1);
        }
        else{
            imageButton.setVisibility(view.INVISIBLE);
            previousBtn.setVisibility(view.INVISIBLE);

            previousBtn = null;
        }


    }

    private void setScore(int flips) {
        this.flips = flips;

        scoreTextView.setText("Flips : " + flips);
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