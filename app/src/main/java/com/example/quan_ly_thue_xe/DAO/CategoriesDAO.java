package com.example.quan_ly_thue_xe.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quan_ly_thue_xe.DbHelper.DbHelper;
import com.example.quan_ly_thue_xe.Model.Categories;

import java.util.ArrayList;
import java.util.List;

public class CategoriesDAO {
    private SQLiteDatabase db;

    public CategoriesDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Categories obj){
        ContentValues values = new ContentValues();
        values.put("name",obj.getName());
        return db.insert("categories",null,values);
    }
    public int upadate(Categories obj){
        ContentValues values = new ContentValues();
        values.put("categories",obj.getName());

        return db.update("categories",values,"id=?",new String[]{String.valueOf(obj.getId())});
    }

    public int delete(String id){
        return db.delete("categories","id=?",new String[]{id});
    }

    public List<Categories> getAll(){
        String sql = "SELECT * FROM categories";
        return getData(sql);
    }

    public Categories getId(String id){
        String sql = "SELECT * FROM categories WHERE id="+id;
        List<Categories> list = getData(sql);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<Categories> getData(String sql, String...selectionArgs){
        List<Categories> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()){
            Categories obj = new Categories();
            obj.setId(Integer.parseInt(c.getString(c.getColumnIndex("id"))));
            obj.setName(c.getString(c.getColumnIndex("name"))); ;
            list.add(obj);
        }
        return list;
    }
}
