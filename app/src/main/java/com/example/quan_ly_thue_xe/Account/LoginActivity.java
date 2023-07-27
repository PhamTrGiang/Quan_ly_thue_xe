package com.example.quan_ly_thue_xe.Account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quan_ly_thue_xe.DAO.UsersDAO;
import com.example.quan_ly_thue_xe.FragmentQuanly.MainActivity;
import com.example.quan_ly_thue_xe.Framentkhachhang.Menu;
import com.example.quan_ly_thue_xe.Model.Users;
import com.example.quan_ly_thue_xe.R;
import com.example.quan_ly_thue_xe.StartScreenActivity;

public class LoginActivity extends AppCompatActivity {
    EditText edUsername,edPassword;
    Button btnLogin;
    UsersDAO dao;
    ImageView imgCallback;
    TextView tvTran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phanQuyen();
        tvTran = findViewById(R.id.tvTran);
        tvTran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), RegisterAccountActivity.class);
                startActivity(i);
                finish();
            }
        });
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnAccess);
        imgCallback = findViewById(R.id.idCallback);
        imgCallback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), StartScreenActivity.class);
                startActivity(i);
                finish();
            }
        });
        dao = new UsersDAO(this);
        dao.insert(new Users("Phạm Trường Giang","0123456789","04352346","admin","admin",3));
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

                SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("id",strUser);
                edit.commit();
                if (dao.getId(strUser).getStatus() == 3 || dao.getId(strUser).getStatus() == 2){
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }else{
                    Intent i = new Intent(getApplicationContext(), Menu.class);
                    startActivity(i);
                }

                finish();
            }else{
                Toast.makeText(this, "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public boolean phanQuyen(){
        if(Build.VERSION.SDK_INT>=23){
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
            ){
                return true;
            }else{
                ActivityCompat.requestPermissions(LoginActivity.this,new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                return false;
            }
        }else{
            return true;
        }
    }
}