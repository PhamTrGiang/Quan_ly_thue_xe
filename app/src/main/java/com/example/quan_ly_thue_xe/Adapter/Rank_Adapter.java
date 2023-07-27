package com.example.quan_ly_thue_xe.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quan_ly_thue_xe.FragmentQuanly.Frag_rank;
import com.example.quan_ly_thue_xe.Model.Top;
import com.example.quan_ly_thue_xe.R;

import java.util.ArrayList;


public class Rank_Adapter extends ArrayAdapter<Top> {
    private Context context;
    Frag_rank fragment;
    private ArrayList<Top> list;
    TextView tvTop,tvName,tvSoluong;
    ImageView img;

    public Rank_Adapter(@NonNull Context context, Frag_rank fragment, ArrayList<Top> list) {
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
            v = inflater.inflate(R.layout.item_rank,null);
        }
        final Top item = list.get(position);
        tvName = v.findViewById(R.id.tvName);
        tvSoluong = v.findViewById(R.id.tvSoluong);
        tvTop = v.findViewById(R.id.tvTop);
        img = v.findViewById(R.id.imgPicture);

        tvName.setText(item.getTen());
        tvSoluong.setText("Số lượt thuê: "+item.getSoLuong());
        tvTop.setText(item.getTop()+"");

        byte[] image = item.getHinhanh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        img.setImageBitmap(bitmap);

        return v;
    }
}
