package com.apps.bjorn.beartimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        EditText edtPassName = (EditText) findViewById(R.id.edtReadFile);
        TextView txtReadResult = (TextView) findViewById(R.id.txtReadResult);

        switch (v.getId()){
            case R.id.btnReadSelect:
                GlobalParameters.getInstance().strPassName = edtPassName.getText().toString();
                txtReadResult.setText(GlobalParameters.getInstance().strPassName);
                //Intent a0 = new Intent (this, MainActivity.class);
                //startActivity(a0);
                break;
            case R.id.btnReadEnd:
                finish();
                break;
        }
    }
}