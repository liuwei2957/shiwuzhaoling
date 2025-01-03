package com.example.myexamproject;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myexamproject.bean.Good;
import com.example.myexamproject.utils.MySQLiteOpenHelper;


public class AddGoodsActivity extends AppCompatActivity implements View.OnClickListener {
    //组件定义
    private EditText etGoodid;
    private EditText etGoodname;
    private EditText etGoodtime;
    private EditText etGoodnote;

    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods);
        //初始化界面
        initView();
    }

    //初始化界面
    private void initView() {
        etGoodid=(EditText)findViewById(R.id.et_goodid);
        etGoodname = (EditText) findViewById(R.id.et_goodname);
        etGoodtime = (EditText) findViewById(R.id.et_goodtime);
        etGoodnote = (EditText) findViewById(R.id.et_goodnote);

        btnAdd = (Button) findViewById(R.id.btn_add);
        //设置按钮的点击事件
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //当单击“添加”按钮时，获取添加信息
        String goodid=etGoodid.getText().toString().trim();
        String goodname = etGoodname.getText().toString().trim();
        String goodtime = etGoodtime.getText().toString().trim();
        String goodnote = etGoodnote.getText().toString();


        //检验信息是否正确
        if (TextUtils.isEmpty(goodid)) {
            Toast.makeText(this, "请输入游戏id", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(goodname)) {
            Toast.makeText(this, "请输入游戏名称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(goodtime)) {
            Toast.makeText(this, "请输入约玩时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(goodnote)) {
            Toast.makeText(this, "请输入备注", Toast.LENGTH_SHORT).show();
            return;
        }

        //添加信息
        Good good =new Good();
        good.goodid= goodid;
        good.goodname = goodname;
        good.goodtime = goodtime;
        good.goodnote= goodnote;

        //创建数据库访问对象
        MySQLiteOpenHelper dao = new MySQLiteOpenHelper(getApplicationContext());
        dao.open();
        long result = dao.addGoods(good);

        if (result > 0) {
            Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "发布失败", Toast.LENGTH_SHORT).show();
        }
        dao.close();
        finish();

    }
}