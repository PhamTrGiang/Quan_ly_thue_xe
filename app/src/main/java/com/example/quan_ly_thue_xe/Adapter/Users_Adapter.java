package com.example.quan_ly_thue_xe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.quan_ly_thue_xe.Fragment.Frag_users;

import com.example.quan_ly_thue_xe.Model.Categories;
import com.example.quan_ly_thue_xe.Model.Users;
import com.example.quan_ly_thue_xe.R;

import java.util.ArrayList;

public class Users_Adapter extends ArrayAdapter<Users> {
    private Context context;
    Frag_users fragment;
    private ArrayList<Users> list;
    TextView tvName;
    ImageView imgDel,imgEdit;

    public Users_Adapter(@NonNull Context context, Frag_users fragment, ArrayList<Users> list) {
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
            v = inflater.inflate(R.layout.item_categories,null);
        }
        final Users item = list.get(position);
        if(item!=null){
            tvName = v.findViewById(R.id.tvName);

            tvName.setText(item.getName());
            imgDel=v.findViewById(R.id.imgDel);
            imgEdit=v.findViewById(R.id.imgEdit);
        }

        return v;
    }


}
