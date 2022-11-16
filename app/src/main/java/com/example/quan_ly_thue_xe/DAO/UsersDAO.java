package com.example.quan_ly_thue_xe.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quan_ly_thue_xe.DbHelper.DbHelper;
import com.example.quan_ly_thue_xe.Model.Users;


import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
    private SQLiteDatabase db;

    public UsersDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Users obj){
        ContentValues values = new ContentValues();
        values.put("name",obj.getName());
        values.put("cmt",obj.getCmt());
        values.put("phone_number",obj.getPhone_number());
        return db.insert("users",null,values);
    }
    public int upadate(Users obj){
        ContentValues values = new ContentValues();
        values.put("name",obj.getName());
        values.put("cmt",obj.getCmt());
        values.put("phone_number",obj.getPhone_number());
        return db.update("users",values,"id=?",new String[]{String.valueOf(obj.getId())});
    }

    public int delete(String id){
        return db.delete("users","id=?",new String[]{id});
    }

    public List<Users> getAll(){
        String sql = "SELECT * FROM users";
        return getData(sql);
    }

    public Users getId(String id){
        String sql = "SELECT * FROM users WHERE id=?";
        List<Users> list = getData(sql);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<Users> getData(String sql, String...selectionArgs){
        List<Users> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()){
            Users obj = new Users();
            obj.setId(Integer.parseInt(c.getString(c.getColumnIndex("id"))));
            obj.setName(c.getString(c.getColumnIndex("name")));
            obj.setCmt(c.getString(c.getColumnIndex("cmt")));
            obj.setPhone_number(c.getString(c.getColumnIndex("phone_number")));
            list.add(obj);
        }
        return list;
    }
}
