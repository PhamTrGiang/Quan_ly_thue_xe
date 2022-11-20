package com.example.quan_ly_thue_xe.Framentkhachhang;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quan_ly_thue_xe.R;


public class FragmentOrders extends Fragment {

    public FragmentOrders() {
    }


    public static FragmentOrders newInstance() {
        FragmentOrders fragment = new FragmentOrders();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         return inflater.inflate(R.layout.fragment_orders, container, false);
    }
}