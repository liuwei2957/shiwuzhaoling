package com.example.myexamproject;



import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.myexamproject.utils.MySQLiteOpenHelper;

import java.util.List;
import java.util.Map;

public class QueryGoodsActivity extends AppCompatActivity {
    //定义组件
    ListView listView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_goods);
        setTitle("查看记录");
        //初始化界面
        initView();
    }

    private void initView() {
        MySQLiteOpenHelper dao=new MySQLiteOpenHelper(getApplicationContext());
        dao.open();
        List<Map<String,Object>> mOrderData=dao.getAllGoods();
        listView=(ListView)findViewById(R.id.lst_orders);
        String[] from={"goodid","goodname","goodtime","goodnote"};
        int[] to={R.id.tv_lst_goodid,R.id.tv_lst_goodname,R.id.tv_lst_goodtime,R.id.tv_lst_goodnote};
        SimpleAdapter listItemAdapter=new SimpleAdapter(QueryGoodsActivity.this,mOrderData,R.layout.item_list,from,to);
        listView.setAdapter(listItemAdapter);
        dao.close();
    }
}