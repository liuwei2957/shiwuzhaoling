package com.example.myexamproject;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myexamproject.bean.Good;
import com.example.myexamproject.utils.MySQLiteOpenHelper;


public class DeleteGoodsActivity extends AppCompatActivity  implements View.OnClickListener{


    private EditText etGoodid;
    private Button btnSearch;
    private EditText etGoodname;
    private EditText etGoodtime;
    private EditText etGoodnote;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_goods);
        initView();
    }

    private void initView() {
        etGoodid=(EditText) findViewById(R.id.et_goodid);
        btnSearch=(Button) findViewById(R.id.btn_search);
        etGoodname=(EditText)findViewById(R.id.et_goodname);
        etGoodtime=(EditText)findViewById(R.id.et_goodtime);
        etGoodnote=(EditText)findViewById(R.id.et_goodnote);
        btnDelete= (Button) findViewById(R.id.btn_delete);


        btnSearch.setOnClickListener((View.OnClickListener) this);
        btnDelete.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_search:
                searchOrder();
                break;
            case R.id.btn_delete:
                deleteOrder();
                break;
        }
    }
    //查找信息
    private void searchOrder() {
        String studentid=etGoodid.getText().toString().trim();
        MySQLiteOpenHelper dao=new MySQLiteOpenHelper(getApplicationContext());
        dao.open();
        Good good =dao.getGoods(studentid);
        etGoodname.setText(good.goodname);
        etGoodtime.setText(good.goodtime);
        etGoodnote.setText(good.goodnote);

        dao.close();
    }
    private void deleteOrder() {
        Good good =new Good();
        good.goodid=etGoodid.getText().toString().trim();
        MySQLiteOpenHelper dao=new MySQLiteOpenHelper(getApplicationContext());
        dao.open();
        int result= dao.deletGoods(good);
        if(result>0) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent( DeleteGoodsActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
        }
        dao.close();
    }
}
