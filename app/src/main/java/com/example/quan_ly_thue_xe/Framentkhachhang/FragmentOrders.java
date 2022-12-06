package com.example.quan_ly_thue_xe.Framentkhachhang;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.quan_ly_thue_xe.Adapter.History_Adapter;
import com.example.quan_ly_thue_xe.DAO.OrdersDAO;
import com.example.quan_ly_thue_xe.Model.Orders;
import com.example.quan_ly_thue_xe.R;

import java.util.ArrayList;


public class FragmentOrders extends Fragment {
    ListView lv;
    History_Adapter adapter;
    OrdersDAO dao;
    SharedPreferences pref;
    private ArrayList<Orders> list;

    public FragmentOrders() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_orders, container, false);
        lv = v.findViewById(R.id.lv);
        dao = new OrdersDAO(getContext());
        pref = getContext().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        capnhap();
        return v;
    }
    void capnhap(){
        String id = pref.getString("id",null);
        list = (ArrayList<Orders>) dao.getUser(id);
        adapter = new History_Adapter(getContext(),this,list);
        lv.setAdapter(adapter);
    }
    public void check(){
        capnhap();
    }

}