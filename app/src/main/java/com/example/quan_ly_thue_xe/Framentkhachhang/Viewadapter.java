package com.example.quan_ly_thue_xe.Framentkhachhang;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Viewadapter extends FragmentStatePagerAdapter {
    public Viewadapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentCuahang();
            case 2:
                return new FragmentOrders();
            case 3:
                return new Fragment_settinh();
            default:
                return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
