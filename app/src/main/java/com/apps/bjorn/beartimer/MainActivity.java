package com.apps.bjorn.beartimer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    Integer raknare = 0;

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
                break;
            case R.id.btnRun:
                Intent a = new Intent (this, DisplayTimer.class);
                startActivity(a);
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

        EditText edtTimes = findViewById(R.id.edtTime);
        TextView txtTimes  = findViewById(R.id.txtTimes);

        raknare = raknare + 1;
        tmp = edtTimes.getText().toString().indexOf(":");
        if (tmp != -1) {
            strMin = edtTimes.getText().toString().substring(0,tmp);
            strSec = edtTimes.getText().toString().substring(tmp + 1);
            intTime = Integer.valueOf(strMin) * 60 + Integer.valueOf(strSec);
            GlobalParameters.getInstance().lstTimes.add(intTime);
            strMsg = raknare.toString() + ": " + edtTimes.getText().toString()+ "\n";
            txtTimes.setText(strMsg + txtTimes.getText());
            edtTimes.setText("");
        }

    }


}
