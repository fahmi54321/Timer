package com.android.a13timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    CheckBox optSingleShot;
    Button btnStart,btnCancel;
    TextView txtCounter;
    Timer timer;
    MyTimeerTask myTimeerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.start);
        btnCancel = findViewById(R.id.cancel);
        txtCounter = findViewById(R.id.counter);
        optSingleShot = findViewById(R.id.singleshot);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timer!=null){
                    timer.cancel();
                }

                timer = new Timer();
                myTimeerTask = new MyTimeerTask();

                if (optSingleShot.isChecked()){
                    timer.schedule(myTimeerTask,1000);
                }else{
                    timer.schedule(myTimeerTask,1000,1000);  // delay 1s , repeat 1s
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timer!=null){
                    timer.cancel();
                    timer = null;
                }
            }
        });

    }


    class MyTimeerTask extends TimerTask{

        @Override
        public void run() {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
            final String strDate = simpleDateFormat.format(calendar.getTime());

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    txtCounter.setText(strDate);
                }
            });
        }
    }
}