package com.example.myexamproject.utils;



import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;


import com.example.myexamproject.bean.Good;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MySQLiteOpenHelper {

    private Context context;
    private MyDBHelper dbHelper;
    private SQLiteDatabase db;

    //构造函数
    public MySQLiteOpenHelper(Context context) {
        this.context = context;
    }

    //打开数据库方法
    public void open() throws SQLiteException {
        dbHelper = new MyDBHelper(context);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
    }

    //关闭数据库方法
    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }


    //添加发布信息内容
    public long addGoods(Good good) {
        // 创建ContentValues对象
        ContentValues values = new ContentValues();
        // 向该对象中插入值
        values.put("goodid", good.goodid);
        values.put("goodname", good.goodname);
        values.put("goodtime", good.goodtime);
        values.put("goodnote", good.goodnote);
        values.put("category", good.category);

        // 通过insert()方法插入数据库中
        return db.insert("tb_Good", null, values);
    }

    //删除发布信息
    public int deletGoods(Good good) {
        return db.delete("tb_Good", "goodid=?", new String[]{String.valueOf(good.goodid)});
    }

    //修改发布信息
    public int updateGood(Good good) {
        ContentValues value = new ContentValues();
        value.put("goodname", good.goodname);
        value.put("goodtime", good.goodtime);
        value.put("goodnote", good.goodnote);
        value.put("category", good.category);
        return db.update("tb_Good", value, "goodid=?", new String[]{String.valueOf(good.goodid)});
    }

    //根据游戏id查找以发布内容
    @SuppressLint("Range")
    public Good getGoods(String goodid) {
        Cursor cursor = db.query("tb_Good", null, "goodid=?", new String[]{goodid}, null, null, null);
        Good good = new Good();
        while (cursor.moveToNext()) {
            good.goodid = cursor.getString(cursor.getColumnIndex("goodid"));
            good.goodname = cursor.getString(cursor.getColumnIndex("goodname"));
            good.goodtime = cursor.getString(cursor.getColumnIndex("goodtime"));
            good.goodnote = cursor.getString(cursor.getColumnIndex("goodnote"));
            good.category = cursor.getString(cursor.getColumnIndex("category"));
        }
        return good;
    }

    //查找所有发布信息
    @SuppressLint("Range")
    public ArrayList<Map<String, Object>> getAllGoods() {
        ArrayList<Map<String, Object>> listGoods = new ArrayList<Map<String, Object>>();
        Cursor cursor = db.query("tb_Good", null, null, null, null, null,null);

        int resultCounts = cursor.getCount();
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("goodid", cursor.getString(cursor.getColumnIndex("goodid")));
                map.put("goodname", cursor.getString(cursor.getColumnIndex("goodname")));
                map.put("goodtime", cursor.getString(cursor.getColumnIndex("goodtime")));
                map.put("goodnote", cursor.getString(cursor.getColumnIndex("goodnote")));
                map.put("category", cursor.getString(cursor.getColumnIndex("category")));
                listGoods.add(map);
            }
            return listGoods;
        }
    }

    //根据条件搜索物品
    @SuppressLint("Range")
    public ArrayList<Map<String, Object>> searchGoods(String query, String field) {
        ArrayList<Map<String, Object>> listGoods = new ArrayList<Map<String, Object>>();
        String selection = field + " LIKE ?";
        String[] selectionArgs = new String[]{"%" + query + "%"};
        Cursor cursor = db.query("tb_Good", null, selection, selectionArgs, null, null, null);

        int resultCounts = cursor.getCount();
        if (resultCounts == 0) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("goodid", cursor.getString(cursor.getColumnIndex("goodid")));
                map.put("goodname", cursor.getString(cursor.getColumnIndex("goodname")));
                map.put("goodtime", cursor.getString(cursor.getColumnIndex("goodtime")));
                map.put("goodnote", cursor.getString(cursor.getColumnIndex("goodnote")));
                map.put("category", cursor.getString(cursor.getColumnIndex("category")));
                listGoods.add(map);
            }
            return listGoods;
        }
    }
}