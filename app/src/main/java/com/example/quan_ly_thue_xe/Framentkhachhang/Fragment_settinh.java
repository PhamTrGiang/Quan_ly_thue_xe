package com.example.quan_ly_thue_xe.Framentkhachhang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.quan_ly_thue_xe.DAO.UsersDAO;
import com.example.quan_ly_thue_xe.InfomationActivity;
import com.example.quan_ly_thue_xe.Model.Users;
import com.example.quan_ly_thue_xe.R;
import com.example.quan_ly_thue_xe.StartScreenActivity;

public class Fragment_settinh extends Fragment {
    LinearLayout admin1,linearLogout,linearInfo;
    UsersDAO dao;

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
        View v = inflater.inflate(R.layout.fragment_settinh, container, false);
        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String id = pref.getString("id",null);
        dao = new UsersDAO(getContext());
        Users obj = dao.getId(id);
        admin1= v.findViewById(R.id.admin1);
        if(obj.getStatus()==3||obj.getStatus()==2){
            admin1.setVisibility(View.VISIBLE);
        }else{
            admin1.setVisibility(View.INVISIBLE);
        }

        admin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);

            }
        });
        linearInfo = v.findViewById(R.id.thongtin);
        linearInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iInfo = new Intent(getContext(), InfomationActivity.class);
                startActivity(iInfo);
            }
        });
        linearLogout = v.findViewById(R.id.linearLogout);
        linearLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iLogout = new Intent(getContext(), StartScreenActivity.class);
                startActivity(iLogout);

            }
        });
        return v;


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}