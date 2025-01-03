package com.example.myexamproject;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_updatee;
    private Button bt_deletee;
    private Button bt_read;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageButton IbPersonalCenter = findViewById(R.id.bt_personal);
        //跳转到个人中心界面
        IbPersonalCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PersonalCenterActivity.class);
                startActivity(intent);
            }
        });

        ImageButton IbAdd = findViewById(R.id.bt_add);
        IbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddGoodsActivity.class);
                startActivity(intent);
            }
        });

        bt_updatee = findViewById(R.id.bt_updatee);
        bt_updatee.setOnClickListener(this);

        bt_deletee = findViewById(R.id.bt_deletee);
        bt_deletee.setOnClickListener(this);

        bt_read = findViewById(R.id.bt_read);
        bt_read.setOnClickListener(this);

    }

    @Override

    public void onClick(View v) {
        //通过switch方法跳转到相应界面
        switch (v.getId()) {
            case R.id.bt_updatee:
                Intent intent_query = new Intent();
                intent_query.setClass(MainActivity.this, UpdateGoodsActivity.class);
                startActivity(intent_query);
                break;

            case R.id.bt_deletee:
                Intent intent_update = new Intent();
                intent_update.setClass(MainActivity.this, DeleteGoodsActivity.class);
                startActivity(intent_update);
                break;

            case R.id.bt_read:
                Intent intent_delete = new Intent();
                intent_delete.setClass(MainActivity.this, QueryGoodsActivity.class);
                startActivity(intent_delete);
                break;


        }
    }


}