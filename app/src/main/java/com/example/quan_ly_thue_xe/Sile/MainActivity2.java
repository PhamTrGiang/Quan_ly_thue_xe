package com.example.quan_ly_thue_xe.Sile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

import com.example.quan_ly_thue_xe.Fragment.Frag_home;
import com.example.quan_ly_thue_xe.MainActivity;
import com.example.quan_ly_thue_xe.R;
import com.example.quan_ly_thue_xe.Sile.photo;
import com.example.quan_ly_thue_xe.Sile.photoadapter;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity2 extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private CircleIndicator3 mcCircleIndicator3;
    private List<photo> mlisphoto;
    Toolbar toolbar;


    private ImageView imageView1,imageView2,imageView3,imageView4;
    Handler mHandler= new Handler(Looper.getMainLooper());
    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            int currenPossition= viewPager2.getCurrentItem();
            if (currenPossition == mlisphoto.size() - 1){
                viewPager2.setCurrentItem(0);
            }else {
                viewPager2.setCurrentItem(currenPossition + 1);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView1=findViewById(R.id.imgview1);
        imageView2=findViewById(R.id.imgview2);
        imageView3=findViewById(R.id.imgview3);
        imageView4=findViewById(R.id.imgview4);
        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);

            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,settinh.class);
                startActivity(intent);
            }
        });


        // sile
        viewPager2=findViewById(R.id.view_pager2);
        mcCircleIndicator3=findViewById(R.id.CircleIndicator3);

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
        list.add(new photo(R.drawable.img_1));
        return list;
    }
    @Override
    protected  void  onPause(){
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable,3000);
    }

}