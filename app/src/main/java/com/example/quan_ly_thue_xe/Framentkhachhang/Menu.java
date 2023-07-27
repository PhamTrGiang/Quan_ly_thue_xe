package com.example.quan_ly_thue_xe.Framentkhachhang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;


import com.example.quan_ly_thue_xe.FragmentQuanly.Frag_home;
import com.example.quan_ly_thue_xe.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Menu extends AppCompatActivity {

    private BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Frag_home frg_home = new Frag_home();
        replaceFrg̣̣(frg_home);
        navigationView= findViewById(R.id.bootom_nav);


        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_hom:
                        Frag_home frag_home = new Frag_home();
                        replaceFrg̣̣(frag_home);
                        break;
                    case R.id.acti_caidat:
                        Fragment_settinh frag_setting = new Fragment_settinh();
                        replaceFrg̣̣(frag_setting);
                        break;
                    case R.id.action_donhang:
                        FragmentOrders frag_orders = new FragmentOrders();
                        replaceFrg̣̣(frag_orders);
                        break;
                    case R.id.action_cuhang:
                        FragmentCuahang frag_shop = new FragmentCuahang();
                        replaceFrg̣̣(frag_shop);
                        break;
                }
                return true;
            }
        });



    }

    private void replaceFrg̣̣(Fragment frg){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.flContent,frg).commit();

    }


}