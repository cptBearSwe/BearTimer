package com.apps.bjorn.beartimer;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;

public class DisplayTimer extends Activity {

    Integer tmp = -1;
    String txt;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.timer_display);
        Log.v("BM","Check array: " +  GlobalParameters.getInstance().lstTimes.size());
        showTimes(1);

    }

    CountDownTimer myTimer = null;

    public void showTimes(final Integer timer){
        myTimer = new CountDownTimer(timer,1000){
            TextView textView  = findViewById(R.id.textView);
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(""+String.format("%d m %d s",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                Log.v("BM",timer.toString() + ", " + txt);
            }
            @Override
            public void onFinish() {
                TextView textView2  = findViewById(R.id.textView2);
                tmp = tmp + 1;
                textView2.setText("(" + (tmp + 1) + "/" + GlobalParameters.getInstance().lstTimes.size() + ")");
                Log.v("BM",tmp.toString());
                if (tmp < GlobalParameters.getInstance().lstTimes.size()){
                    if (tmp == 0){
                        showTimes(GlobalParameters.getInstance().lstTimes.get(tmp)  * 1000);
                    } else {
                        showTimes((GlobalParameters.getInstance().lstTimes.get(tmp) - GlobalParameters.getInstance().lstTimes.get(tmp - 1)) * 1000);
                    }
                } else {
                    textView2.setText("(" + tmp + "/" + GlobalParameters.getInstance().lstTimes.size() + ")");
                    textView.setText("DONE!");
                    finish();
                }
            }
        };
        myTimer.start();
    }


}



