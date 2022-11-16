package com.example.quan_ly_thue_xe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    View mHeaderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        ab.setDisplayHomeAsUpEnabled(true);


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


                        break;
                    case R.id.nav_categories:
                        setTitle("Quản lý loại xe");


                        break;
                    case R.id.nav_vehicles:
                        setTitle("Quản lý xe");


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
}