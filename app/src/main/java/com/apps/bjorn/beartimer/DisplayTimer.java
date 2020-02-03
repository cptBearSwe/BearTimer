package com.apps.bjorn.beartimer;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DisplayTimer extends Activity {

    Integer tmp = -1;
    String txt;
    String strPass;
    String strTime;
    String strSec;
    String strHrt;
    String strRpm;
    String strPos;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.timer_display);
        Log.v("BM","Check array: " +  GlobalParameters.getInstance().lstTimes.size());
        readData();
        extractData();
        showTimes(1);

    }

    CountDownTimer myTimer = null;

    public void readData(){
        String rader = null;
        try {
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            FileReader reader = new FileReader(path + "/" + "myfile.txt");
            BufferedReader bufferedreader = new BufferedReader(reader);
            while ((rader = bufferedreader.readLine()) != null){
                GlobalParameters.getInstance().lstPass.add(rader);
            }
            Toast.makeText(this,"AntaL rader: " + GlobalParameters.getInstance().lstPass.size(), Toast.LENGTH_LONG).show();
        }
        catch (FileNotFoundException e){
            Toast.makeText(this, "FileNotFoundException",Toast.LENGTH_LONG).show();
        }
        catch(IOException e){
            Toast.makeText(this, "IOException",Toast.LENGTH_LONG).show();
        }
    }

    public void extractData(){
        Integer a;
        Boolean bolUse = false;

        if (GlobalParameters.getInstance().lstTimes.size() < 1){
            bolUse = true;
        }
        for (String each : GlobalParameters.getInstance().lstPass){
            a = each.indexOf(";");
            strPass = each.substring(0,a);
            txt = each.substring(a +1, each.length());

            a = txt.indexOf(";");
            strTime = txt.substring(0,a);
            txt = txt.substring(a +1, txt.length());

            a = txt.indexOf(";");
            strSec = txt.substring(0,a);
            txt = txt.substring(a +1, txt.length());
            if (bolUse = true){
                GlobalParameters.getInstance().lstTimes.add(Integer.valueOf(strSec));
            }

            a = txt.indexOf(";");
            strHrt = txt.substring(0,a);
            txt = txt.substring(a +1, txt.length());

            a = txt.indexOf(";");
            strRpm = txt.substring(0,a);
            txt = txt.substring(a +1, txt.length());

            strPos = txt;

            Log.v("BM",strSec);
        }
    }
    
    public void showTimes(final Integer timer){
        myTimer = new CountDownTimer(timer,1000){
            TextView textView  = findViewById(R.id.textView);
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(""+String.format("%d m %d s",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
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



