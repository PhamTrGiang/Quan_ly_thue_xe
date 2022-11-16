package com.example.quan_ly_thue_xe.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quan_ly_thue_xe.Model.Categories;
import com.example.quan_ly_thue_xe.R;

import java.util.ArrayList;

public class SpinAdapter_Categories extends BaseAdapter {
    Context context;
    ArrayList<Categories> list;
    TextView tvName;

    public SpinAdapter_Categories(Context context, ArrayList<Categories> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        Categories obj = list.get(position);
        return list;
    }

    @Override
    public long getItemId(int position) {
        Categories obj = list.get(position);
        return obj.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = View.inflate(parent.getContext(), R.layout.item_spinner,null);
        }

        final Categories item = list.get(position);

        tvName = v.findViewById(R.id.tvItemspinner);

        tvName.setText(item.getName());

        return v;
    }
}
