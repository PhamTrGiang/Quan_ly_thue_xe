package com.example.quan_ly_thue_xe.Sile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_thue_xe.R;

import java.util.List;

public class photoadapter extends RecyclerView.Adapter<photoadapter.PhotoViewHolder>{

    private List<photo> listphoto;

    public photoadapter(List<photo> listphoto) {
        this.listphoto = listphoto;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo,parent,false);

        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        photo photo= listphoto.get(position);
        if (photo == null){
            return;
        }
        holder.imgphoto.setImageResource(photo.getResoutceId());

    }

    @Override
    public int getItemCount() {
        if (listphoto != null){
            return listphoto.size();
        }
        return 0;
    }


    public static class PhotoViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imgphoto;
        public PhotoViewHolder(@NonNull View itemview) {
            super(itemview);
            imgphoto= itemview.findViewById(R.id.img_photo);
        }
    }
}
