package com.example.quan_ly_thue_xe.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quan_ly_thue_xe.DbHelper.DbHelper;

import com.example.quan_ly_thue_xe.Model.Vehicles;

import java.util.ArrayList;
import java.util.List;

public class VehiclesDAO {
    private SQLiteDatabase db;

    public VehiclesDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Vehicles obj){
        ContentValues values = new ContentValues();
        values.put("categories_id",obj.getCategories_id());
        values.put("name",obj.getName());
        values.put("price",obj.getPrice());
        return db.insert("vehicles",null,values);
    }
    public int upadate(Vehicles obj){
        ContentValues values = new ContentValues();
        values.put("categories_id",obj.getCategories_id());
        values.put("name",obj.getName());
        values.put("price",obj.getPrice());

        return db.update("vehicles",values,"id=?",new String[]{String.valueOf(obj.getId())});
    }

    public int delete(String id){
        return db.delete("vehicles","id=?",new String[]{id});
    }

    public List<Vehicles> getAll(){
        String sql = "SELECT * FROM vehicles";
        return getData(sql);
    }

    public Vehicles getId(String id){
        String sql = "SELECT * FROM vehicles WHERE id="+id;
        List<Vehicles> list = getData(sql);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<Vehicles> getData(String sql, String...selectionArgs){
        List<Vehicles> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()){
            Vehicles obj = new Vehicles();
            obj.setId(Integer.parseInt(c.getString(c.getColumnIndex("id"))));
            obj.setCategories_id(Integer.parseInt(c.getString(c.getColumnIndex("categories_id"))));
            obj.setName(c.getString(c.getColumnIndex("name")));
            obj.setPrice(Integer.parseInt(c.getString(c.getColumnIndex("price"))));
            list.add(obj);
        }
        return list;
    }
}
