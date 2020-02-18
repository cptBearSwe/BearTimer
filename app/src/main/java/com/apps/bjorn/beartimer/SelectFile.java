package com.apps.bjorn.beartimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectFile extends Activity implements View.OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.file_select);

        Button btnRead = (Button) findViewById(R.id.btnReadSelect);
        btnRead.setOnClickListener(this);

        Button btnEnd = (Button) findViewById(R.id.btnReadEnd);
        btnEnd.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnReadSelect:
                //Intent a0 = new Intent (this, MainActivity.class);
                //startActivity(a0);
                break;
            case R.id.btnReadEnd:
                finish();
                break;
        }
    }
}