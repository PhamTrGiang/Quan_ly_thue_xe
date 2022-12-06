package com.example.quan_ly_thue_xe.Framentkhachhang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;


import com.example.quan_ly_thue_xe.Fragment.Frag_home;
import com.example.quan_ly_thue_xe.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Menu extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        phanQuyen();
        Frag_home frg_home = new Frag_home();
        replaceFrg̣̣(frg_home);
        navigationView= findViewById(R.id.bootom_nav);
//        viewPager=findViewById(R.id.view_pager2);
//        setViewPager();


        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_hom:
//                        viewPager.setCurrentItem(0);
                        Frag_home frag_home = new Frag_home();
                        replaceFrg̣̣(frag_home);
                        break;
                    case R.id.acti_caidat:
//                        viewPager.setCurrentItem(3);
                        Fragment_settinh frag_setting = new Fragment_settinh();
                        replaceFrg̣̣(frag_setting);
                        break;
                    case R.id.action_donhang:
//                        viewPager.setCurrentItem(2);
                        FragmentOrders frag_orders = new FragmentOrders();
                        replaceFrg̣̣(frag_orders);
                        break;
                    case R.id.action_cuhang:
//                        viewPager.setCurrentItem(1);
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

    private void  setViewPager(){
        Viewadapter viewadapter= new Viewadapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewadapter);

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
                ActivityCompat.requestPermissions(Menu.this,new String[]{
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