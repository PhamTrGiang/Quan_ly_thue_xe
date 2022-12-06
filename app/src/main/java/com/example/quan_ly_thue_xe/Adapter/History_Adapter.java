package com.example.quan_ly_thue_xe.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.quan_ly_thue_xe.DAO.UsersDAO;
import com.example.quan_ly_thue_xe.DAO.VehiclesDAO;
import com.example.quan_ly_thue_xe.Framentkhachhang.FragmentOrders;
import com.example.quan_ly_thue_xe.Model.Orders;
import com.example.quan_ly_thue_xe.Model.Users;
import com.example.quan_ly_thue_xe.Model.Vehicles;
import com.example.quan_ly_thue_xe.R;


import java.util.ArrayList;

public class History_Adapter extends ArrayAdapter<Orders> {
    private Context context;
    FragmentOrders fragment;
    TextView tvName,tvStatus,tvEnd,tvStart,tvTong;
    private ArrayList<Orders> list;

    public History_Adapter(@NonNull Context context,FragmentOrders fragment,ArrayList<Orders> list) {
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
            v = inflater.inflate(R.layout.item_history,null);
        }
        tvName = v.findViewById(R.id.tvName);
        tvStatus = v.findViewById(R.id.tvStatus);
        tvEnd = v.findViewById(R.id.tvEnd);
        tvStart = v.findViewById(R.id.tvStart);
        tvTong = v.findViewById(R.id.tvTongtien);
        Orders obj = list.get(position);

        VehiclesDAO vehiclesDAO = new VehiclesDAO(context);
        Vehicles vehicles = vehiclesDAO.getId(obj.getVehicles_id()+"");

        tvName.setText(vehicles.getName());
        tvStart.setText("Giờ thuê : "+obj.getStart_time());
        tvEnd.setText("Giờ trả : "+obj.getEnd_time());
        String status = "";
        if(obj.getStatus()==2){
            status = "Đang thuê xe";
            tvStatus.setTextColor(Color.parseColor("#FF0000"));
        }else{
            status = "Đã trả xe";
            tvStatus.setTextColor(Color.parseColor("#0000FF"));
        }
        tvStatus.setText(status);
        tvTong.setText("Tổng tiền : "+obj.getTotal()+"vnđ");

        return v;
    }
}
