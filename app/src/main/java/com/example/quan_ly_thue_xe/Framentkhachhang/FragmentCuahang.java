package com.example.quan_ly_thue_xe.Framentkhachhang;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quan_ly_thue_xe.R;

public class FragmentCuahang extends Fragment {



    public FragmentCuahang() {
    }


    public static FragmentCuahang newInstance() {
        FragmentCuahang fragment = new FragmentCuahang();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cuahang, container, false);
    }
}