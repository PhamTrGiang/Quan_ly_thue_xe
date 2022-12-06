package com.example.quan_ly_thue_xe.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quan_ly_thue_xe.DbHelper.DbHelper;
import com.example.quan_ly_thue_xe.Model.Orders;


import java.util.ArrayList;
import java.util.List;

public class OrdersDAO {
    private SQLiteDatabase db;

    public OrdersDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Orders obj){
        ContentValues values = new ContentValues();
        values.put("vehicles_id",obj.getVehicles_id());
        values.put("users_id",obj.getUsers_id());
        values.put("start_time",obj.getStart_time());
        values.put("end_time",obj.getEnd_time());
        values.put("total",obj.getTotal());
        values.put("status",obj.getStatus());
        values.put("incutted",obj.getIncutted());
        values.put("date",obj.getDate());
        return db.insert("orders",null,values);
    }
    public int upadate(Orders obj){
        ContentValues values = new ContentValues();
        values.put("vehicles_id",obj.getVehicles_id());
        values.put("users_id",obj.getUsers_id());
        values.put("start_time",obj.getStart_time());
        values.put("end_time",obj.getEnd_time());
        values.put("total",obj.getTotal());
        values.put("status",obj.getStatus());
        values.put("incutted",obj.getIncutted());
        values.put("date",obj.getDate());
        values.put("giotraxe",obj.getGiotraxe());
        return db.update("orders",values,"id=?",new String[]{String.valueOf(obj.getId())});
    }

    public int delete(String id){
        return db.delete("orders","id=?",new String[]{id});
    }

    public List<Orders> getAll(){
        String sql = "SELECT * FROM orders";
        return getData(sql);
    }
    public List<Orders> getUser(String id){
        String sql = "SELECT * FROM orders WHERE users_id=?";
        return getData(sql,id);
    }
    public List<Orders> getVehicle(String id){
        String sql = "SELECT * FROM orders WHERE vehicles_id=? and status = 2";
        return getData(sql,id);
    }


    public Orders getId(String id){
        String sql = "SELECT * FROM orders WHERE id="+id;
        List<Orders> list = getData(sql);
        return list.get(0);
    }


    @SuppressLint("Range")
    private List<Orders> getData(String sql, String...selectionArgs){
        List<Orders> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()){
            Orders obj = new Orders();
            obj.setId(Integer.parseInt(c.getString(c.getColumnIndex("id"))));
            obj.setVehicles_id(Integer.parseInt(c.getString(c.getColumnIndex("vehicles_id"))));
            obj.setUsers_id(c.getString(c.getColumnIndex("users_id")));
            obj.setStart_time(c.getString(c.getColumnIndex("start_time")));
            obj.setEnd_time(c.getString(c.getColumnIndex("end_time")));
            obj.setTotal(Integer.parseInt(c.getString(c.getColumnIndex("total"))));
            obj.setStatus(Integer.parseInt(c.getString(c.getColumnIndex("status"))));
            obj.setIncutted(Integer.parseInt(c.getString(c.getColumnIndex("incutted"))));
            obj.setDate(c.getString(c.getColumnIndex("date")));
            obj.setGiotraxe(c.getString(c.getColumnIndex("giotraxe")));
            list.add(obj);
        }
        return list;
    }
}
