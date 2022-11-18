package com.example.quan_ly_thue_xe.Sile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quan_ly_thue_xe.MainActivity;
import com.example.quan_ly_thue_xe.R;
import com.example.quan_ly_thue_xe.StartScreenActivity;

public class settinh extends AppCompatActivity {
    private ImageView imageView1,imageView2,imageView3;

    LinearLayout thongtin,matkhau,layout;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settinh);

        layout=findViewById(R.id.lnadmin);
        logout=findViewById(R.id.btnthoat);
        thongtin=findViewById(R.id.thongtin);
        matkhau=findViewById(R.id.tvmk);
        imageView1=findViewById(R.id.imgview1);
        imageView2=findViewById(R.id.imgview2);
        imageView3=findViewById(R.id.imgview3);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StartScreenActivity.class));
                finish();
            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);

            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Cuahang.class);
                startActivity(intent);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Hoadon.class);
                startActivity(intent);
            }
        });

    }
}