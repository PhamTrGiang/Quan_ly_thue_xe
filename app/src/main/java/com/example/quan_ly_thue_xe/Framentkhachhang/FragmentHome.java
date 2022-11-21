package com.example.quan_ly_thue_xe.Framentkhachhang;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quan_ly_thue_xe.R;
import com.example.quan_ly_thue_xe.Sile.photo;
import com.example.quan_ly_thue_xe.Sile.photoadapter;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;


public class FragmentHome extends Fragment {
    private ViewPager2 viewPager2;
    private CircleIndicator3 mcCircleIndicator3;
    private List<photo> mlisphoto;
    Handler mHandler= new Handler(Looper.getMainLooper());
    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            int currenPossition = viewPager2.getCurrentItem();
            if (currenPossition == mlisphoto.size() - 1) {
                viewPager2.setCurrentItem(0);
            } else {
                viewPager2.setCurrentItem(currenPossition + 1);
            }
        }
    };


    public FragmentHome() {
        // Required empty public constructor
    }

    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        viewPager2=view.findViewById(R.id.view_pager2);
        mcCircleIndicator3=view.findViewById(R.id.CircleIndicator3);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        CompositePageTransformer compositePageTransformer=new CompositePageTransformer() ;
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r =1 -Math.abs(position);
                page.setScaleY(0.85f + r *0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
        mlisphoto=getlisphoto();

        photoadapter photoadapter= new photoadapter(mlisphoto);
        viewPager2.setAdapter(photoadapter);
        mcCircleIndicator3.setViewPager(viewPager2);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable,3000);
            }
        });



    }
    private List<photo> getlisphoto(){
        List<photo> list = new ArrayList<>();
        list.add(new photo(R.drawable.img_1));
        list.add(new photo(R.drawable.img_1));
        list.add(new photo(R.drawable.img_1));
        return list;
    }
}