package com.example.myexamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myexamproject.bean.Good;
import com.example.myexamproject.utils.MySQLiteOpenHelper;

public class UpdateGoodsActivity extends AppCompatActivity implements View.OnClickListener{
    //组件定义
    private EditText etGoodid;
    private EditText etGoodname;
    private EditText etGoodtime;
    private EditText etGoodnote;
    private Spinner spinnerCategory;
    private Button btnSearch;
    private Button btnEdit;

    private String[] categories = {"电子产品", "生活用品", "衣服", "书籍", "其他"};

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
        spinnerCategory = findViewById(R.id.spinner_category);
        btnEdit = findViewById(R.id.btn_edit);

        // 设置分类下拉列表
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        btnSearch.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_search:
                searchOrder();
                break;
            case R.id.btn_edit:
                updateOrder();
                break;
        }
    }

    private void searchOrder() {
        String goodid = etGoodid.getText().toString().trim();
        MySQLiteOpenHelper dao = new MySQLiteOpenHelper(getApplicationContext());
        dao.open();
        Good good = dao.getGoods(goodid);
        etGoodname.setText(good.goodname);
        etGoodtime.setText(good.goodtime);
        etGoodnote.setText(good.goodnote);
        
        // 设置分类
        for (int i = 0; i < categories.length; i++) {
            if (categories[i].equals(good.category)) {
                spinnerCategory.setSelection(i);
                break;
            }
        }

        dao.close();
    }

    private void updateOrder() {
        Good good = new Good();
        good.goodid = etGoodid.getText().toString().trim();
        good.goodname = etGoodname.getText().toString().trim();
        good.goodtime = etGoodtime.getText().toString().trim();
        good.goodnote = etGoodnote.getText().toString().trim();
        good.category = spinnerCategory.getSelectedItem().toString();

        MySQLiteOpenHelper dao = new MySQLiteOpenHelper(getApplicationContext());
        dao.open();
        long result = dao.updateGood(good);
        if(result > 0) {
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
        }
        dao.close();
    }
}
