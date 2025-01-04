package com.example.myexamproject.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    //常量定义
    public static final String name = "db_good1.db";
    public static final int DB_VERSION = 2;
    //创建表
    public static final String CREATE_USERDATA1 = "create table tb_Good(goodid char(10)primary key,goodname varchar(20),goodtime varchar(20),goodnote varchar(20),category varchar(20))";
    public MyDBHelper(Context context) {
        super(context, name, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERDATA1);
        db.execSQL("insert into tb_Good(goodid,goodname,goodtime,goodnote,category)Values('001','小米9','5.28下午','难受','电子产品')");
        db.execSQL("insert into tb_Good(goodid,goodname,goodtime,goodnote,category)Values('002','绿水鬼','5.29上午','寻找手表','生活用品')");
        db.execSQL("insert into tb_Good(goodid,goodname,goodtime,goodnote,category)Values('003','JAVA','6.29上午','重赏','书籍')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE tb_Good ADD COLUMN category varchar(20)");
            db.execSQL("UPDATE tb_Good SET category='电子产品' WHERE goodid='001'");
            db.execSQL("UPDATE tb_Good SET category='生活用品' WHERE goodid='002'");
            db.execSQL("UPDATE tb_Good SET category='书籍' WHERE goodid='003'");
        }
    }
}