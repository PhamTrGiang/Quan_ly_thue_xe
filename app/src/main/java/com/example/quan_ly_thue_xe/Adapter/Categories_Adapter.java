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

import com.example.quan_ly_thue_xe.Fragment.Frag_categories;
import com.example.quan_ly_thue_xe.Model.Categories;
import com.example.quan_ly_thue_xe.R;

import java.util.ArrayList;

public class Categories_Adapter extends ArrayAdapter<Categories> {
    private Context context;
    Frag_categories fragment;
    private ArrayList<Categories> list;
    TextView tvName;
    ImageView imgDel,imgEdit;

    public Categories_Adapter(@NonNull Context context,Frag_categories fragment, ArrayList<Categories> list) {
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
        final Categories item = list.get(position);
        if(item!=null){
            tvName = v.findViewById(R.id.tvName);

            tvName.setText(item.getName());
            imgDel=v.findViewById(R.id.imgDel);
            imgEdit=v.findViewById(R.id.imgEdit);
        }
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.openDialog(getContext(),1,item);
            }
        });

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getId()));
            }
        });
        return v;
    }
}
