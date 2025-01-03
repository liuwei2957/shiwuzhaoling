package com.example.myexamproject;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BackActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_backfalse;
    private Button bt_backtrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);

        bt_backfalse = findViewById(R.id.bt_backfalse);
        bt_backfalse.setOnClickListener(this);

        bt_backtrue = findViewById(R.id.bt_backtrue);
        bt_backtrue.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_backfalse:
                Intent intent_insert = new Intent();
                intent_insert.setClass(BackActivity.this, PersonalCenterActivity.class);
                startActivity(intent_insert);
                break;

            case R.id.bt_backtrue:
                Intent intent_query = new Intent();
                intent_query.setClass(BackActivity.this, LoginActivity.class);
                startActivity(intent_query);
                break;

        }
    }
}