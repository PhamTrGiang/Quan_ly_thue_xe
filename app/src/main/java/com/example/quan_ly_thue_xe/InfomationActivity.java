package com.example.quan_ly_thue_xe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quan_ly_thue_xe.DAO.UsersDAO;
import com.example.quan_ly_thue_xe.Model.Users;

public class InfomationActivity extends AppCompatActivity {
    TextView edFullname , edPhonenumber,edIndentification;
    UsersDAO dao;
    ImageView imgCallback;
    Button btnEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        dao = new UsersDAO(this);
        imgCallback = findViewById(R.id.idCallback);
        imgCallback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        edFullname.setText(       "Fullname           : "+name);
        edIndentification.setText("Identification    : "+iden);
        edPhonenumber.setText(    "Phone number : "+phone);

        btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}