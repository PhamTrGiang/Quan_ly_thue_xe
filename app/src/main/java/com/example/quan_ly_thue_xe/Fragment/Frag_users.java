package com.example.quan_ly_thue_xe.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.quan_ly_thue_xe.Adapter.Categories_Adapter;
import com.example.quan_ly_thue_xe.Adapter.Users_Adapter;
import com.example.quan_ly_thue_xe.DAO.CategoriesDAO;
import com.example.quan_ly_thue_xe.DAO.UsersDAO;
import com.example.quan_ly_thue_xe.Model.Categories;
import com.example.quan_ly_thue_xe.Model.Users;
import com.example.quan_ly_thue_xe.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Frag_users extends Fragment {
    ListView lv;
    List<Users> list;
    UsersDAO dao;
    Users_Adapter adapter;

    public Frag_users() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_user, container, false);
        lv = v.findViewById(R.id.listView);
        dao =new UsersDAO(getActivity());
        capNhap();

        return v;
    }

    void capNhap(){
        list = dao.getAll();
        adapter = new Users_Adapter(getActivity(),this, (ArrayList<Users>) list);
        lv.setAdapter(adapter);
    }
}