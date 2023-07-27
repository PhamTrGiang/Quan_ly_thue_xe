package com.example.quan_ly_thue_xe.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.quan_ly_thue_xe.FragmentQuanly.Frag_users;


import com.example.quan_ly_thue_xe.Model.Users;
import com.example.quan_ly_thue_xe.R;

import java.util.ArrayList;

public class Users_Adapter extends ArrayAdapter<Users> {
    private Context context;
    Frag_users fragment;
    private ArrayList<Users> list;
    TextView tvUsername,tvPosition;
    LinearLayout lnUser;


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
            v = inflater.inflate(R.layout.item_users,null);
        }
        final Users item = list.get(position);
        if(item!=null){
            String Position;
            tvUsername = v.findViewById(R.id.tvUser);
            tvPosition = v.findViewById(R.id.tvPosition);
            lnUser = v.findViewById(R.id.itemUser);
            if(item.getStatus()==1){
                Position = "Khách hàng";
            }else if (item.getStatus()==2){
                Position = "Nhân viên";
            }else{
                Position = "Quản trị viên";
            }
            tvUsername.setText("Username : "+item.getId());
            tvPosition.setText("Chức vụ : "+Position);
            lnUser.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    fragment.openDialog(getContext(),item);
                    return false;
                }
            });
        }
        return v;
    }




}
