package com.example.quan_ly_thue_xe.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quan_ly_thue_xe.DAO.UsersDAO;
import com.example.quan_ly_thue_xe.R;

public class RegisterAccountActivity extends AppCompatActivity {
    Button btnNext;
    EditText edUser ,edPass,edRepass;
    UsersDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnNext = findViewById(R.id.btnRegister);
        edUser = findViewById(R.id.edUsername);
        edPass = findViewById(R.id.edPassword);
        edRepass = findViewById(R.id.edRepassword);
        dao = new UsersDAO(this);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUser = edUser.getText().toString();
                if(validate()>0){
                    if(dao.check(strUser)>0){
                        Intent i = new Intent(getBaseContext(),RegisterInfoActivity.class);
                        Bundle account = new Bundle();
                        account.putString("Username",edUser.getText().toString());
                        account.putString("Password",edPass.getText().toString());
                        i.putExtra("Account",account);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(RegisterAccountActivity.this, "Username đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
    public int validate(){
        int check = 1;
        if(edUser.getText().toString().length()==0
                ||edPass.getText().toString().length()==0
                ||edRepass.getText().toString().length()==0){
            Toast.makeText(this, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }else{
            String pass = edPass.getText().toString();
            String repass = edRepass.getText().toString();
            if(!pass.equals(repass)){
                Toast.makeText(this, "Mật khẩu nhập lại sai", Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return check;
    }
}