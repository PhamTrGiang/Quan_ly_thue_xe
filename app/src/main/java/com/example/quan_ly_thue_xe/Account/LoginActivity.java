package com.example.quan_ly_thue_xe.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quan_ly_thue_xe.DAO.UsersDAO;
import com.example.quan_ly_thue_xe.MainActivity;
import com.example.quan_ly_thue_xe.Model.Users;
import com.example.quan_ly_thue_xe.R;

public class LoginActivity extends AppCompatActivity {
    EditText edUsername,edPassword;
    Button btnLogin;
    UsersDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnAccess);
        dao = new UsersDAO(this);
        dao.insert(new Users("admin","Phạm Trường Giang","04352346","0123456789","admin"));
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    public void checkLogin(){
        String strUser = edUsername.getText().toString();
        String strPass = edPassword.getText().toString();
        if(strUser.isEmpty()||strPass.isEmpty()){
            Toast.makeText(this, "Tên đăng nhập và mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        }else{
            if(dao.checkLogin(strUser,strPass)>0){
                Toast.makeText(this, "Login thành công", Toast.LENGTH_SHORT).show();
//                rememberUser(strUser,strPass,chkRememberPass.isChecked());
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("id",strUser);
                startActivity(i);
                finish();
            }else{
                Toast.makeText(this, "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
}