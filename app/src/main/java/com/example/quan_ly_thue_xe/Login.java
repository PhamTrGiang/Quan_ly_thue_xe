package com.example.quan_ly_thue_xe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quan_ly_thue_xe.Sile.MainActivity2;

public class Login extends AppCompatActivity {

    EditText txtten,txtpasword;
    Button login,dk;
    String ten, mk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtten=findViewById(R.id.txtemail);
        txtpasword=findViewById(R.id.txtpassword);
        dk=findViewById(R.id.dangky);
        login=findViewById(R.id.Login);


        dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog= new Dialog(Login.this);
                dialog.setTitle("Hộp thoại xử lý");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_dk);
                EditText edtk=(EditText) dialog.findViewById(R.id.eddn);
                EditText edmk=(EditText) dialog.findViewById(R.id.edmk);
                Button yes=(Button) dialog.findViewById(R.id.yes);
                Button no=(Button) dialog.findViewById(R.id.no);
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ten=edtk.getText().toString().trim();
                        mk=edmk.getText().toString().trim();
                        txtten.setText(ten);
                        txtpasword.setText(mk);
                        dialog.cancel();

                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();


            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtten.getText().length() !=0 && txtpasword.getText().length() !=0){
                    if (txtten.getText().toString().equals(ten) && txtpasword.getText().toString().equals(mk)){
                        Toast.makeText(Login.this,"Đăng nhập thành công ",Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(Login.this, MainActivity2.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(Login.this, "đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Login.this,"Mời bạn nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}