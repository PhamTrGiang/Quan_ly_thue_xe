package com.example.quan_ly_thue_xe.Framentkhachhang;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.quan_ly_thue_xe.R;

public class Fragment_settinh extends Fragment {
LinearLayout admin1;


    public Fragment_settinh() {
    }


    public static Fragment_settinh newInstance() {
        Fragment_settinh fragment = new Fragment_settinh();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settinh, container, false);
//        admin1=container.findViewById(R.id.admin1);
//        admin1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(),);
//
//
//            }
//        });

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        admin1=view.findViewById(R.id.admin1);
        admin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);


            }
        });
    }
}