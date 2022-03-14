package kr.ac.tukorea.ge.sgp02.s2017180003.sgp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView subTextView; // 이름이슈 shift+f6 누르면 한번이 이름 바뀜

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subTextView = findViewById(R.id.subText);
        subTextView.setText("I am a good Programmer");




    }

    public void onBtnPsuhMe(View view) {
        //TextView tv = findViewById(R.id.subText);
        subTextView.setText("Clicked !!!");
    }
}