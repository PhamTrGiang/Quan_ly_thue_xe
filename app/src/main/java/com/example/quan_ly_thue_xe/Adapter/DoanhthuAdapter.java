package com.example.quan_ly_thue_xe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



import com.example.quan_ly_thue_xe.FragmentQuanly.Frag_doanhthu_thang;
import com.example.quan_ly_thue_xe.Model.Orders;

import com.example.quan_ly_thue_xe.R;

import java.util.ArrayList;

public class DoanhthuAdapter extends ArrayAdapter<Orders> {
    private Context context;
    Frag_doanhthu_thang fragment;
    private ArrayList<Orders> list;
    TextView tvUser,tvTotal;

    public DoanhthuAdapter(@NonNull Context context, Frag_doanhthu_thang fragment, ArrayList<Orders> list) {
        super(context, 0,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_doanhthu,null);
        }
        final Orders item = list.get(position);
        if(item!=null){
            tvUser = v.findViewById(R.id.tvUsers);
            tvTotal = v.findViewById(R.id.tvTotal);
            tvUser.setText("User: "+item.getUsers_id());
            tvTotal.setText("+ "+item.getTotal()+"vnđ");
        }
        return v;
    }
}
