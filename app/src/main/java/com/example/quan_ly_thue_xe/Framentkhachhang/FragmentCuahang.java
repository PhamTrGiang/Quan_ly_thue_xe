package com.example.quan_ly_thue_xe.Framentkhachhang;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quan_ly_thue_xe.Adapter.Shop_Adapter;
import com.example.quan_ly_thue_xe.DAO.VehiclesDAO;
import com.example.quan_ly_thue_xe.Model.Categories;
import com.example.quan_ly_thue_xe.Model.Vehicles;
import com.example.quan_ly_thue_xe.R;

import java.util.ArrayList;

public class FragmentCuahang extends Fragment {
    ListView lv;
    ArrayList<Vehicles> list;
    VehiclesDAO dao;
    Shop_Adapter adapter;

    public FragmentCuahang() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_cuahang, container, false);
        lv = v.findViewById(R.id.listView);
        dao =new VehiclesDAO(getActivity());
        capNhap();
        return v;
    }


    void capNhap(){
        list = (ArrayList<Vehicles>) dao.getAll();
        adapter = new Shop_Adapter(getActivity(),this, (ArrayList<Vehicles>) list);
        lv.setAdapter(adapter);
    }

    public void check(){
        capNhap();
    }
}