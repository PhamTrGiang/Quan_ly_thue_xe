package com.example.quan_ly_thue_xe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.quan_ly_thue_xe.Fragment.Frag_categories;
import com.example.quan_ly_thue_xe.Fragment.Frag_home;
import com.example.quan_ly_thue_xe.Fragment.Frag_vehicles;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    View mHeaderView;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        navigationView=findViewById(R.id.nvView);

        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        ab.setDisplayHomeAsUpEnabled(true);
        Frag_home frg_home = new Frag_home();
        replaceFrg̣̣(frg_home);
        phanQuyen();
        //
//        Intent intent=getIntent();
//        String ursor=intent.getStringExtra("name");
//        if (ursor.equals("admin")){
//            navigationView.getMenu().findItem(R.id.tong1).setVisible(true);
//        }


        NavigationView nav = findViewById(R.id.nvView);
        mHeaderView = nav.getHeaderView(0);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigationdrawer_open,R.string.navigationdrawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        setTitle("Trang chủ");
                        Frag_home frag_home = new Frag_home();
                        replaceFrg̣̣(frag_home);
                        break;
                    case R.id.nav_categories:
                        setTitle("Quản lý loại xe");
                        Frag_categories frag_categories = new Frag_categories();
                        replaceFrg̣̣(frag_categories);
                        break;
                    case R.id.nav_vehicles:
                        setTitle("Quản lý xe");
                        Frag_vehicles frag_vehicles = new Frag_vehicles();
                        replaceFrg̣̣(frag_vehicles);

                        break;
                    case R.id.nav_orders:
                        setTitle("Quản lý phiếu mượn");


                        break;
                    case R.id.nav_Bangxephang:
                        setTitle("Bảng xếp hạng");


                        break;

                    case R.id.nav_users:
                        setTitle("Quản lý người dùng");


                        break;
                    case R.id.nav_Doimatkhau:
                        setTitle("Đổi mật khẩu");


                        break;
                    case R.id.nav_Dangxuat:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                        break;
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home)
            drawerLayout.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

    private void replaceFrg̣̣(Fragment frg){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.flContent,frg).commit();

    }

    public boolean phanQuyen(){
        if(Build.VERSION.SDK_INT>=23){
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                return true;
            }else{
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},1);
                return false;
            }
        }else{
            return true;
        }
    }
}