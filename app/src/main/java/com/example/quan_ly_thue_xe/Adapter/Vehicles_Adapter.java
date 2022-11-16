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

import com.example.quan_ly_thue_xe.DAO.CategoriesDAO;
import com.example.quan_ly_thue_xe.Fragment.Frag_categories;
import com.example.quan_ly_thue_xe.Fragment.Frag_vehicles;
import com.example.quan_ly_thue_xe.Model.Categories;
import com.example.quan_ly_thue_xe.Model.Vehicles;
import com.example.quan_ly_thue_xe.R;

import java.util.ArrayList;

public class Vehicles_Adapter extends ArrayAdapter<Vehicles> {
    private Context context;
    Frag_vehicles fragment;
    private ArrayList<Vehicles> list;
    TextView tvName,tvCate,tvPrice;
    ImageView imgDel,imgEdit;

    public Vehicles_Adapter(@NonNull Context context, Frag_vehicles fragment, ArrayList<Vehicles> list) {
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
            v = inflater.inflate(R.layout.item_vehicles,null);
        }
        final Vehicles item = list.get(position);
        if(item!=null){
            CategoriesDAO categoriesDAO = new CategoriesDAO(context);
            Categories categories = categoriesDAO.getId(item.getCategories_id()+"");
            tvName = v.findViewById(R.id.tvName);
            tvCate = v.findViewById(R.id.tvCate);
            tvPrice = v.findViewById(R.id.tvPrice);

            tvName.setText(item.getName());
            tvPrice.setText(item.getPrice()+"");
            tvCate.setText(categories.getName());
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
