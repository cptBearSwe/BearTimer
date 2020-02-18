package com.apps.bjorn.beartimer;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DisplayTimer extends Activity {

    ArrayList<Track> CykelPass = new ArrayList<Track>();

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
        Log.v("BM",GlobalParameters.getInstance().strPassName);
        //readData();
        //extractData();
        //showTimes(4000);
        readDataNew();
        showTimesNew(4000);

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

            Log.v("BM",GlobalParameters.getInstance().strPassName);
        }
    }
    
/*
    public void showTimes(final Integer timer){
        TextView textView3  = findViewById(R.id.textView3);
        textView3.setText(strPass);
        myTimer = new CountDownTimer(timer,1000){
            TextView textView  = findViewById(R.id.textView);
            @Override
           public void onTick(long millisUntilFinished) {
                textView.setText(""+String.format("%02d:%02d",
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
*/

    public void showTimesNew(final Integer timer){
        TextView textView3  = findViewById(R.id.textView3);
        textView3.setText(CykelPass.get(0).passname);
        myTimer = new CountDownTimer(timer,1000){
            TextView textView  = findViewById(R.id.textView);
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(""+String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }
            @Override
            public void onFinish() {
                TextView textView2  = findViewById(R.id.textView2);
                tmp = tmp + 1;
                textView2.setText("(" + (tmp + 1) + "/" + CykelPass.size() + ")");
                Log.v("BM",tmp.toString());
                if (tmp < CykelPass.size()){
                    if (tmp == 0){
                        showTimesNew(CykelPass.get(tmp).sec  * 1000);
                    } else {
                        showTimesNew((CykelPass.get(tmp).sec - CykelPass.get(tmp - 1).sec) * 1000);
                    }
                } else {
                    finish();
                }
            }
        };
        myTimer.start();
    }

    public void readDataNew(){
        Integer a;
        Integer raknare = 0;
        String rader = null;

        try {
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            FileReader reader = new FileReader(path + "/" + "myfile.txt");
            BufferedReader bufferedreader = new BufferedReader(reader);
            while ((rader = bufferedreader.readLine()) != null){
                raknare = raknare + 1;
                a = rader.indexOf(";");
                strPass = rader.substring(0,a);
                txt = rader.substring(a + 1, rader.length());

                a = txt.indexOf(";");
                strTime = txt.substring(0,a);
                txt = txt.substring(a +1, txt.length());

                a = txt.indexOf(";");
                strSec = txt.substring(0,a);
                txt = txt.substring(a +1, txt.length());

                a = txt.indexOf(";");
                strHrt = txt.substring(0,a);
                txt = txt.substring(a +1, txt.length());

                a = txt.indexOf(";");
                strRpm = txt.substring(0,a);
                txt = txt.substring(a +1, txt.length());

                strPos = txt;

                Track obj = new Track(raknare, strPass, strTime, strHrt,strRpm, strPos);
                CykelPass.add(obj);
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

}






