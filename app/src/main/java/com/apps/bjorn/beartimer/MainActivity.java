package com.apps.bjorn.beartimer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    Integer raknare = 0;
    String strPass;
    ArrayList<Track> CykelPass = new ArrayList<Track>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        Button btnRun = (Button) findViewById(R.id.btnRun);
        btnRun.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                saveTimes();
                showCykelPass();
                break;
            case R.id.btnRun:
                finish();
                //Intent a = new Intent (this, DisplayTimer.class);
                //startActivity(a);
                break;
        }
    }

    public void saveTimes(){
        Log.v("BM","saveTimes!");
        Integer tmp;
        Integer intTime;
        String strMin;
        String strSec;
        String strMsg;
        String strTime;
        String strHrt;
        String strRpm;
        String strPos;
        String msg;

        EditText edtPassname = findViewById(R.id.edtPassName);
        EditText edtTimes = findViewById(R.id.edtTime);
        EditText edtHrt = findViewById(R.id.edtHRT);
        EditText edtRpm = findViewById(R.id.edtRPM);
        EditText edtPos = findViewById(R.id.edtPos);

        TextView txtTimes  = findViewById(R.id.txtTimes);


        raknare = raknare + 1;
        strPass = edtPassname.getText().toString();
        tmp = edtTimes.getText().toString().indexOf(":");
        if (tmp != -1) {
            strTime = edtTimes.getText().toString();
            strHrt = edtHrt.getText().toString();
            strRpm = edtRpm.getText().toString();
            strPos = edtPos.getText().toString();
            strMin = edtTimes.getText().toString().substring(0,tmp);
            strSec = edtTimes.getText().toString().substring(tmp + 1);
            intTime = Integer.valueOf(strMin) * 60 + Integer.valueOf(strSec);

            Track obj = new Track(raknare, strPass, strTime, strHrt,strRpm, strPos);
            CykelPass.add(obj);
            GlobalParameters.getInstance().strPassName = strPass;

            GlobalParameters.getInstance().lstTimes.add(intTime);
            strMsg = raknare.toString() + ": " + edtTimes.getText().toString()+ "\n";
            txtTimes.setText(strMsg + txtTimes.getText());
            msg = strPass + ";" + strTime + ";" + intTime.toString() + ";" + strHrt + ";" + strRpm + ";" + strPos;
            writeToFile(msg);
            edtTimes.setText("");
            edtHrt.setText("");
            edtRpm.setText("");
            edtPos.setText("");
        }

    }

    public void writeToFile(String msg){
        try {
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(path, "/" + strPass + ".txt");
            file.createNewFile();
            FileWriter filewriter = new FileWriter(file, true);
            BufferedWriter out = new BufferedWriter(filewriter);

            out.write(msg);
            out.newLine();
            out.close();
            filewriter.close();
        } catch (Exception e){
            android.util.Log.d("Failed to save file", e.toString());
        }
    }

    public void showCykelPass(){
        for(Track row : CykelPass){
            Log.v("BM","Check track: Nr: " + row.no + "Pass: " + row.passname + "Times: " + row.length + " - " + row.sec + "Data: " + row.hrt + ", " + row.rpm + ", " + row.pos);
        }
    }
}
