package com.example.myexamproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myexamproject.utils.MySQLiteOpenHelper;

import java.util.List;
import java.util.Map;

public class QueryGoodsActivity extends AppCompatActivity {
    //定义组件
    private ListView listView;
    private EditText etSearch;
    private Spinner spinnerSearchField;
    private Button btnSearch;
    private String[] searchFields = {"编号", "物品名称", "分类"};
    private String[] dbFields = {"goodid", "goodname", "category"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_goods);
        setTitle("查看记录");
        //初始化界面
        initView();
    }

    private void initView() {
        listView = (ListView)findViewById(R.id.lst_orders);
        etSearch = (EditText)findViewById(R.id.et_search);
        spinnerSearchField = (Spinner)findViewById(R.id.spinner_search_field);
        btnSearch = (Button)findViewById(R.id.btn_search);

        // 设置搜索字段下拉列表
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, searchFields);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSearchField.setAdapter(adapter);

        // 加载所有物品
        loadAllGoods();

        // 设置搜索按钮点击事件
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = etSearch.getText().toString().trim();
                if (!query.isEmpty()) {
                    int selectedPosition = spinnerSearchField.getSelectedItemPosition();
                    String field = dbFields[selectedPosition];
                    searchGoods(query, field);
                } else {
                    Toast.makeText(QueryGoodsActivity.this, "请输入搜索关键词", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadAllGoods() {
        MySQLiteOpenHelper dao = new MySQLiteOpenHelper(getApplicationContext());
        dao.open();
        List<Map<String,Object>> mOrderData = dao.getAllGoods();
        updateListView(mOrderData);
        dao.close();
    }

    private void searchGoods(String query, String field) {
        MySQLiteOpenHelper dao = new MySQLiteOpenHelper(getApplicationContext());
        dao.open();
        List<Map<String,Object>> searchResults = dao.searchGoods(query, field);
        if (searchResults != null && !searchResults.isEmpty()) {
            updateListView(searchResults);
        } else {
            Toast.makeText(this, "未找到匹配的结果", Toast.LENGTH_SHORT).show();
            loadAllGoods();
        }
        dao.close();
    }

    private void updateListView(List<Map<String,Object>> data) {
        if (data != null) {
            String[] from = {"goodid", "goodname", "goodtime", "category", "goodnote"};
            int[] to = {R.id.tv_lst_goodid, R.id.tv_lst_goodname, R.id.tv_lst_goodtime, R.id.tv_lst_category, R.id.tv_lst_goodnote};
            SimpleAdapter listItemAdapter = new SimpleAdapter(QueryGoodsActivity.this, data, R.layout.item_list, from, to);
            listView.setAdapter(listItemAdapter);
        } else {
            Toast.makeText(this, "暂无数据", Toast.LENGTH_SHORT).show();
        }
    }
}