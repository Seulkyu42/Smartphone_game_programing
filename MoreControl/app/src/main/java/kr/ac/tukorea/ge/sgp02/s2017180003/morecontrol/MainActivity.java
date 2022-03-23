package kr.ac.tukorea.ge.sgp02.s2017180003.morecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();;
    private CheckBox checkbox; // 필드가 멤버변수
    private TextView outputTextView;
    private EditText nameEdit1;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkbox = findViewById(R.id.checkbox1);
        outputTextView = findViewById(R.id.textview);
        nameEdit1 = findViewById(R.id.nameEdit);

        nameEdit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.v(TAG,"before");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.v(TAG,"before" + charSequence);
                outputTextView.setText("TextLength"+charSequence.length());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.v(TAG,"after");
            }
        });
    }

    public void onClickBox(View view) {
        CheckBox cb = (CheckBox) view;
        Log.d(TAG,"Checked");
    }

    public void onBtnClick(View view) {
        Log.d(TAG,"onBtnClick" + checkbox.isChecked());
        text = nameEdit1.getText().toString();
        outputTextView.setText(text);
    }


}