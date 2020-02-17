package com.apps.bjorn.beartimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends Activity implements View.OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_main);

        Button btnCreate = (Button) findViewById(R.id.btnMainCreate);
        btnCreate.setOnClickListener(this);

        Button btnOpen = (Button) findViewById(R.id.btnMainOpen);
        btnOpen.setOnClickListener(this);

        Button btnStart = (Button) findViewById(R.id.btnMainStart);
        btnStart.setOnClickListener(this);

        Button btnEnd = (Button) findViewById(R.id.btnMainEnd);
        btnEnd.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMainCreate:
                Intent a0 = new Intent (this, MainActivity.class);
                startActivity(a0);
                break;
            case R.id.btnMainOpen:
                Intent a1 = new Intent (this, DisplayTimer.class);
                startActivity(a1);
                break;
            case R.id.btnMainStart:
                Intent a2 = new Intent (this, DisplayTimer.class);
                startActivity(a2);
                break;
            case R.id.btnMainEnd:
                finish();
                break;
        }
    }
}