package kr.ac.tukorea.ge.sgp02.s2017180003.sgp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.subText);
        tv.setText("I am a good Programmer");

        Button btn = findViewById(R.id.btnPushMe);
        btn.setOnClickListener(this);

//        Ctrl+/ 로 주석치기 가능
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                tv.setText("Clicked !!!");
//            }
//        });

    }

    @Override
    public void onClick(View view) {
        TextView tv = findViewById(R.id.subText);
        tv.setText("Clicked !!!");
    }
}