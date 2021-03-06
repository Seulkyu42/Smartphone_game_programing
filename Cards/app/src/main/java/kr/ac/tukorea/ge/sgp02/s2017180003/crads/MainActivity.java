package kr.ac.tukorea.ge.sgp02.s2017180003.crads;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

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
    ////////////////////////////////////////////////////////////////////////////////////////////////


    private ImageButton previousBtn;
    private int flips;
    private int openCount;
    private TextView scoreTextView;


    ////////////////////////////////////////////////////////////////////////////////////////////////

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTextView = findViewById(R.id.textView);
        startGame();
    }

    ///////////////////////////////////////////////// STRATGAME ////////////////////////////////////
    private void startGame() {
        Random random = new Random();
        for(int i = 0; i< resIds.length; ++i){
            int t = random.nextInt(resIds.length);
            int id = resIds[i];
            resIds[i] = resIds[t];
            resIds[t] = id;

        }


        for (int i = 0; i< BUTTON_IDS.length; ++i){
            int resId = resIds[i];

            ImageButton btn = findViewById(BUTTON_IDS[i]);
            btn.setTag(resId);
            btn.setVisibility(View.VISIBLE);
            btn.setImageResource(R.mipmap.card_blue_back);
        }

        previousBtn = null;
        openCount = resIds.length; // ????????? 16???
        setScore(0);
    } // ????????? ??????????????? ??? ???????????? ???
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void onBtnRestart(View view) {
        Log.d(TAG,"onBtnRestart");
        askRetry();
    }
    /////////////////////////////////////////////////ASKRETRY///////////////////////////////////////

    private void askRetry() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.restart)
                .setMessage(R.string.restart_alert_msg)
                .setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startGame();
                    }
                })
                .setNegativeButton(R.string.no,null)
                .create()
                .show();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void onBtnCard(View view) {
        if (!(view instanceof ImageButton)){
            Log.e(TAG,"Not an ImageButton" + view); // ????????? ????????? ?????? ????????? ????????????
            return;
        }
        ImageButton imageButton = (ImageButton) view;

        if (imageButton == previousBtn){
            Log.v(TAG,"Same Button");
            Toast.makeText(this, R.string.same_card_toast, Toast.LENGTH_SHORT).show();
            return; // ???????????? ????????? ??????????????? ????????? ????????????
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
            openCount -=2;
            if(openCount == 0){
                askRetry();
            }
            previousBtn = null;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private void setScore(int flips) {
        this.flips = flips;


        Resources res = getResources();
        String fmt = res.getString(R.string.flips_format);
        String text = String.format(fmt,flips);
        scoreTextView.setText(text);
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