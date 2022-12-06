package com.example.quan_ly_thue_xe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quan_ly_thue_xe.DAO.UsersDAO;
import com.example.quan_ly_thue_xe.Framentkhachhang.Menu;
import com.example.quan_ly_thue_xe.Model.Users;

public class ChangePassActivity extends AppCompatActivity {
    Button btnChange;
    EditText edPass,edNewpass,edRepass;
    UsersDAO dao;
    ImageView imgCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        btnChange = findViewById(R.id.btChange);
        imgCallback = findViewById(R.id.idCallback);
        imgCallback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edPass = findViewById(R.id.edPass);
        edNewpass = findViewById(R.id.edNewPass);
        edRepass = findViewById(R.id.edNewPass);
        dao = new UsersDAO(this);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()>0){
                    SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
                    String id = pref.getString("id",null);
                    Users users = dao.getId(id);
                    users.setPassword(edNewpass.getText().toString());
                    if(dao.changePass(users)>0){
                        Toast.makeText(ChangePassActivity.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edPass.setText("");
                        edNewpass.setText("");
                        edRepass.setText("");
                        Intent i = new Intent(getApplicationContext(), Menu.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(ChangePassActivity.this, "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public int validate(){
        int check = 1;
        if(edPass.getText().length()==0||edNewpass.getText().length()==0||edRepass.getText().length()==0){
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }else{
            SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
            String id = pref.getString("id",null);
            Users obj = dao.getId(id);
            String passOld = obj.getPassword();
            String passNew = edNewpass.getText().toString();
            String rePass = edRepass.getText().toString();
            if(!passOld.equals(edPass.getText().toString())){
                Toast.makeText(this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
                check=-1;
            }
            if(!passNew.equals(rePass)){
                Toast.makeText(this, "Nhập lại mật khẩu sai", Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }

        return check;
    }
}