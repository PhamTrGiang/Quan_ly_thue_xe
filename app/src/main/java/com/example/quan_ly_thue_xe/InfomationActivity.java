package com.example.quan_ly_thue_xe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quan_ly_thue_xe.DAO.UsersDAO;
import com.example.quan_ly_thue_xe.Model.Users;

public class InfomationActivity extends AppCompatActivity {
    EditText edFullname , edPhonenumber,edIndentification;
    UsersDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        dao = new UsersDAO(this);
        edFullname = findViewById(R.id.edFullname);
        edPhonenumber = findViewById(R.id.edPhonenumber);
        edIndentification = findViewById(R.id.edIdentification);
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String id = pref.getString("id",null);
        Users obj = new Users();
        obj = dao.getId(id);
        String name = obj.getName();
        String phone = obj.getPhone_number();
        String iden = obj.getIndentification();
        edFullname.setText(name);
        edIndentification.setText(iden);
        edPhonenumber.setText(phone);
    }
}