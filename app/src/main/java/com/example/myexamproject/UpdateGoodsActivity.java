package com.example.myexamproject;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myexamproject.bean.Good;
import com.example.myexamproject.utils.MySQLiteOpenHelper;


public class UpdateGoodsActivity extends AppCompatActivity implements View.OnClickListener{
    //组件定义
    private EditText etGoodid;
    private EditText etGoodname;
    private EditText etGoodtime;
    private EditText etGoodnote;
    private Button btnSearch;
    private Button btnEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_goods);
        initView();
    }

    private void initView() {
        etGoodid = findViewById(R.id.et_goodid);
        btnSearch = findViewById(R.id.btn_search);
        etGoodname = findViewById(R.id.et_goodname);
        etGoodtime = findViewById(R.id.et_goodtime);
        etGoodnote = findViewById(R.id.et_goodnote);
        btnEdit=  findViewById(R.id.btn_edit);


        btnSearch.setOnClickListener((View.OnClickListener) this);
        btnEdit.setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_search:
                searchOrder();
                break;
            case R.id.btn_edit:
                updateOrder();
                break;
        }
    }
    private void searchOrder() {
        String goodid=etGoodid.getText().toString().trim();
        MySQLiteOpenHelper dao=new MySQLiteOpenHelper(getApplicationContext());
        dao.open();
        Good good =dao.getGoods(goodid);
        etGoodname.setText(good.goodname);
        etGoodtime.setText(good.goodtime);
        etGoodnote.setText(good.goodnote);


        dao.close();
    }

    private void updateOrder() {
        Good good =new Good();
        good.goodid=etGoodid.getText().toString().trim();
        good.goodname=etGoodname.getText().toString().trim();
        good.goodtime=etGoodtime.getText().toString().trim();
        good.goodnote=etGoodnote.getText().toString().trim();

        MySQLiteOpenHelper dao=new MySQLiteOpenHelper(getApplicationContext());
        dao.open();
        long result= dao.updateGood(good);
        if(result>0) {
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
        }
        dao.close();
    }
}
