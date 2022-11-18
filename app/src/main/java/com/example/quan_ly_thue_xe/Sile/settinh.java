package com.example.quan_ly_thue_xe.Sile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quan_ly_thue_xe.R;

public class settinh extends AppCompatActivity {

    LinearLayout thongtin,matkhau;
    Button thoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settinh);
        thoat=findViewById(R.id.btnthoat);
        thongtin=findViewById(R.id.thongtin);
        matkhau=findViewById(R.id.tvmk);
        thongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Dialog dialog= new Dialog(settinh.this);
//                dialog.setContentView(R.layout.layout_dialogthongtin);
//                Window window = dialog.getWindow();
//                if(window == null) return;
//                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
//                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                Intent intent= new Intent(settinh.this,dialog.getClass());
//                startActivity(intent);
            }
        });
    }
}