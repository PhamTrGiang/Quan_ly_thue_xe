package com.example.quan_ly_thue_xe.FragmentQuanly;

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
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quan_ly_thue_xe.DAO.UsersDAO;
import com.example.quan_ly_thue_xe.FragmentQuanly.Frag_categories;
import com.example.quan_ly_thue_xe.FragmentQuanly.Frag_home;
import com.example.quan_ly_thue_xe.FragmentQuanly.Frag_orders;
import com.example.quan_ly_thue_xe.FragmentQuanly.Frag_rank;
import com.example.quan_ly_thue_xe.FragmentQuanly.Frag_users;
import com.example.quan_ly_thue_xe.FragmentQuanly.Frag_vehicles;
import com.example.quan_ly_thue_xe.Framentkhachhang.Menu;
import com.example.quan_ly_thue_xe.Model.Users;
import com.example.quan_ly_thue_xe.R;
import com.example.quan_ly_thue_xe.StartScreenActivity;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    View mHeaderView;
    UsersDAO dao;
    TextView tvUser , tvPosition;
    ImageView imgInfo;

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
        setTitle("Quản lý phiếu mượn");
        Frag_orders frag_ordors = new Frag_orders();
        replaceFrg̣̣(frag_ordors);
        dao = new UsersDAO(this);
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String id = pref.getString("id",null);
        Users obj = dao.getId(id);

        NavigationView nav = findViewById(R.id.nvView);
        mHeaderView = nav.getHeaderView(0);
        tvUser = mHeaderView.findViewById(R.id.tvUser);
        tvPosition = mHeaderView.findViewById(R.id.tvPosition);
        imgInfo = mHeaderView.findViewById(R.id.imgInfo);
        tvUser.setText("Username : "+obj.getId());
        String Position;
        if(obj.getStatus()==1){
            Position = "Khách hàng";
        }else if (obj.getStatus()==2){
            Position = "Nhân viên";
        }else{
            Position = "Quản trị viên";
        }
        tvPosition.setText("Position : "+Position);
        if(obj.getImage()!=null){
            byte[] image = obj.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
            imgInfo.setImageBitmap(bitmap);
        }


        if(obj.getStatus()==3){
            nav.getMenu().findItem(R.id.iUser).setVisible(true);
        }else{
            nav.getMenu().findItem(R.id.iUser).setVisible(false);
        }


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigationdrawer_open,R.string.navigationdrawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        setTitle("Trang chủ");
                        Intent i = new Intent(getBaseContext(), Menu.class);
                        startActivity(i);
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
                        Frag_orders frag_ordors = new Frag_orders();
                        replaceFrg̣̣(frag_ordors);

                        break;
                    case R.id.nav_Bangxephang:
                        setTitle("Bảng xếp hạng");
                        Frag_rank frag_rank = new Frag_rank();
                        replaceFrg̣̣(frag_rank);
                        break;
                    case R.id.nav_doanhthu:
                        setTitle("Doanh thu");
                        Frag_doanhthu_thang frag_doanhthu = new Frag_doanhthu_thang();
                        replaceFrg̣̣(frag_doanhthu);
                        break;
                    case R.id.nav_users:
                        setTitle("Quản lý người dùng");
                        Frag_users frag_users = new Frag_users();
                        replaceFrg̣̣(frag_users);

                        break;
                    case R.id.logout:
                        Intent iLogout = new Intent(getBaseContext(), StartScreenActivity.class);
                        startActivity(iLogout);
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