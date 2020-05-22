package com.example.ground;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;


//팝업 xml
public class popup_multiplication extends AppCompatActivity {
    private TextView count;
    private int counttime = 10;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            counttime -= 1;
            count.setText(counttime+"");//1초 간격

            if (counttime <= 1) {
                handler.removeCallbacks(runnable);
            } else {
                handler.postDelayed(runnable, 1000);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_multiplication);

        count = findViewById(R.id.popup_multiplication_count);
        handler.post(runnable);
    }



}
