package com.example.quan_ly_thue_xe.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quan_ly_thue_xe.R;

import java.util.ArrayList;

public class SpinAdapter_Position extends BaseAdapter {
    Context context;
    String[] list;
    TextView tvName;

    public SpinAdapter_Position(Context context, String[] list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return list[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = View.inflate(parent.getContext(), R.layout.item_spinner,null);
        }

        String item = list[position];

        tvName = v.findViewById(R.id.tvItemspinner);
        tvName.setText(item);

        return v;
    }
}
