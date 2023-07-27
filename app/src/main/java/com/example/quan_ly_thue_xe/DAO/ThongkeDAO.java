package com.example.quan_ly_thue_xe.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quan_ly_thue_xe.DbHelper.DbHelper;
import com.example.quan_ly_thue_xe.Model.Top;
import com.example.quan_ly_thue_xe.Model.Vehicles;

import java.util.ArrayList;
import java.util.List;

public class ThongkeDAO {
    private SQLiteDatabase db;
    private Context context;

    DbHelper dbHelper;
    public ThongkeDAO(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();

    }

    @SuppressLint("Range")
    public List<Top> getTop(){
        String sql = "SELECT vehicles_id, count(vehicles_id) as count FROM orders GROUP BY vehicles_id ORDER BY count DESC";
        List<Top> list = new ArrayList<>();
        VehiclesDAO vehiclesDAO = new VehiclesDAO(context);
        Cursor c = db.rawQuery(sql, null);
        int i=1;
        while (c.moveToNext()){
            Top top = new Top();
            Vehicles vehicles = vehiclesDAO.getId(c.getString(0));
            top.setTen(vehicles.getName());
            top.setSoLuong(Integer.parseInt(c.getString(1)));
            top.setHinhanh(vehicles.getImage());
            top.setTop(i++);
            list.add(top);
        }
        return list;
    }

    @SuppressLint("Range")
    public int getDoanhthuNgay(String day){
        String sql = "SELECT SUM(total) AS doanhthu FROM orders WHERE status = 1 and date=?";
        List<Integer> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,new String[]{day});
        while (c.moveToNext()){
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhthu"))));
            }catch (Exception ex){
                list.add(0);
            }
        }
        return list.get(0);
    }
    @SuppressLint("Range")
    public int getDoanhthuthang(String daybegin){
        String sql = "SELECT SUM(total) AS doanhthu FROM orders WHERE status = 1 and date LIKE"+daybegin;
        List<Integer> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,null);
        while (c.moveToNext()){
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhthu"))));
            }catch (Exception ex){
                list.add(0);
            }
        }
        return list.get(0);
    }
}
