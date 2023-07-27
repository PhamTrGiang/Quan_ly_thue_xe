package com.example.quan_ly_thue_xe.FragmentQuanly;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.example.quan_ly_thue_xe.Adapter.Rank_Adapter;
import com.example.quan_ly_thue_xe.DAO.ThongkeDAO;
import com.example.quan_ly_thue_xe.Model.Top;
import com.example.quan_ly_thue_xe.R;

import java.util.ArrayList;


public class Frag_rank extends Fragment {
    ListView lv;
    ArrayList<Top> list;
    Rank_Adapter adapter;

    public Frag_rank() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_rank, container, false);
        lv = v.findViewById(R.id.listView);
        ThongkeDAO dao = new ThongkeDAO(getContext());
        list = (ArrayList<Top>) dao.getTop();
        adapter = new Rank_Adapter(getContext(),this,list);
        lv.setAdapter(adapter);
        return v;
    }

}