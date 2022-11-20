package com.example.quan_ly_thue_xe.Framentkhachhang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.quan_ly_thue_xe.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Menu extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        navigationView= findViewById(R.id.bootom_nav);
        viewPager=findViewById(R.id.view_pager2);
        setViewPager();


        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_hom:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.acti_caidat:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.action_donhang:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.action_cuhang:
                        viewPager.setCurrentItem(1);
                        break;
                }
                return true;
            }
        });



    }
    private void  setViewPager(){
        Viewadapter viewadapter= new Viewadapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewadapter);

    }
}