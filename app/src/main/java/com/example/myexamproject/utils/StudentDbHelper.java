package com.example.myexamproject.utils;



import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.myexamproject.bean.Student;

import java.util.LinkedList;


public class StudentDbHelper extends SQLiteOpenHelper {

    //定义学生表
    public static final String DB_NAME = "tb_student";
    //创建表
    private static final String CREATE_STUDENT_DB = "create table tb_student(" +
            "id integer primary key autoincrement," +
            "stuNumber text," +
            "stuName text," +
            "stuMajor text," +
            "stuPhone text," +
            "stuQq text," +
            "stuAddress text)";

    public StudentDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //保存学生信息
    public void saveStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("stuNumber",student.getStuNumber());
        values.put("stuName",student.getStuName());
        values.put("stuMajor",student.getStuMajor());
        values.put("stuPhone",student.getStuPhone());
        values.put("stuQq",student.getStuQq());
        values.put("stuAddress",student.getStuAddress());
        db.insert(DB_NAME,null,values);
        values.clear();
    }

    //通过学号来读取信息
    @SuppressLint("Range")
    public LinkedList<Student> readStudents(String stuNumber) {
        LinkedList<Student> students = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_student where stuNumber=?",new String[]{stuNumber});
        if(cursor.moveToFirst()) {
            do {
                String stuName = cursor.getString(cursor.getColumnIndex("stuName"));
                String stuMajor = cursor.getString(cursor.getColumnIndex("stuMajor"));
                String stuPhone = cursor.getString(cursor.getColumnIndex("stuPhone"));
                String stuQq = cursor.getString(cursor.getColumnIndex("stuQq"));
                String stuAddress = cursor.getString(cursor.getColumnIndex("stuAddress"));
                Student student = new Student();
                student.setStuName(stuName);
                student.setStuMajor(stuMajor);
                student.setStuPhone(stuPhone);
                student.setStuQq(stuQq);
                student.setStuAddress(stuAddress);
                students.add(student);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return students;
    }

}

