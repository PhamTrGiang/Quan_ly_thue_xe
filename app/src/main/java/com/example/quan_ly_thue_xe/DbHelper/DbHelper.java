package com.example.quan_ly_thue_xe.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "manager_vehicle";
    static final int DB_VISION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VISION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tb_users = "CREATE TABLE users (id TEXT PRIMARY KEY,name TEXT,indentification TEXT,phone_number TEXT,password TEXT,status INTEGER)";
        db.execSQL(tb_users);
        String tb_categories ="CREATE TABLE categories (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT)";
        db.execSQL(tb_categories);
        String tb_vehicles ="CREATE TABLE vehicles (id INTEGER PRIMARY KEY AUTOINCREMENT, categories_id INTEGER, name TEXT, price INTEGER, image BLOB, FOREIGN KEY (categories_id) REFERENCES categories (id))";
        db.execSQL(tb_vehicles);
        String tb_orders = "CREATE TABLE oeders (id INTEGER PRIMARY KEY AUTOINCREMENT,start_time INTEGER,end_time INTEGER,total INTEGER,incutted INTEGER,status INTEGER,vehicles_id INTEGER, users_id INTEGER, FOREIGN KEY (vehicles_id) REFERENCES vehicles (id), FOREIGN KEY (users_id) REFERENCES users (id))";
        db.execSQL(tb_orders);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS categories");
        db.execSQL("DROP TABLE IF EXISTS vehicles");
        db.execSQL("DROP TABLE IF EXISTS orders");
        onCreate(db);
    }
}
