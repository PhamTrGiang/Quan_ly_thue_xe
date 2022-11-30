package com.example.quan_ly_thue_xe.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quan_ly_thue_xe.DAO.UsersDAO;
import com.example.quan_ly_thue_xe.Model.Users;
import com.example.quan_ly_thue_xe.R;
import com.example.quan_ly_thue_xe.StartScreenActivity;

public class RegisterInfoActivity extends AppCompatActivity {
    Button btnRegister;
    EditText edName ,edPhoneNumber,edIndentification;
    UsersDAO dao;
    ImageView imgCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_info);
        imgCallback = findViewById(R.id.idCallback);
        imgCallback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), StartScreenActivity.class);
                startActivity(i);
                finish();
            }
        });
        btnRegister = findViewById(R.id.btnRegister);
        edName = findViewById(R.id.edName);
        edPhoneNumber = findViewById(R.id.edPhoneNumber);
        edIndentification = findViewById(R.id.edIndentidication);
        dao = new UsersDAO(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                Bundle account = i.getBundleExtra("Account");
                String strUser = account.getString("Username");
                String strPass = account.getString("Password");

                if(validate()>0){
                    Users obj = new Users();
                    obj.setId(strUser);
                    obj.setPassword(strPass);
                    obj.setName(edName.getText().toString());
                    obj.setPhone_number(edPhoneNumber.getText().toString());
                    obj.setIndentification(edIndentification.getText().toString());
                    obj.setStatus(1);
                    if(dao.insert(obj)>0){
                        Toast.makeText(RegisterInfoActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        Intent home = new Intent(getBaseContext(), StartScreenActivity.class);
                        startActivity(home);
                        finish();

                    }else{
                        Toast.makeText(RegisterInfoActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    public int validate(){
        int check = 1;
        if(edName.getText().length()==0||edPhoneNumber.getText().length()==0||edIndentification.getText().length()==0){
            Toast.makeText(getBaseContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
}