package com.example.quan_ly_thue_xe.Adapter;


import android.content.Context;

import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.quan_ly_thue_xe.DAO.OrdersDAO;
import com.example.quan_ly_thue_xe.DAO.VehiclesDAO;
import com.example.quan_ly_thue_xe.FragmentQuanly.Frag_orders;
import com.example.quan_ly_thue_xe.Model.Orders;
import com.example.quan_ly_thue_xe.Model.Vehicles;
import com.example.quan_ly_thue_xe.R;

import java.util.ArrayList;
import java.util.Calendar;

public class Orders_Adapter extends ArrayAdapter<Orders> {
    private Context context;
    Frag_orders fragment;
    TextView tvName,tvEnd,tvStart,tvTong,tvUser,tvDate,tvGiotra,tvTrathem;
    Button btnStatus;
    private ArrayList<Orders> list,listCheck;
    OrdersDAO dao;
    ListView lv;

    public Orders_Adapter(@NonNull Context context, Frag_orders fragment, ArrayList<Orders> list) {
        super(context, 0,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (v==null){
            v = inflater.inflate(R.layout.item_orders,null);
        }
        dao = new OrdersDAO(getContext());
        tvName = v.findViewById(R.id.tvName);
        tvUser = v.findViewById(R.id.tvUser);
        tvEnd = v.findViewById(R.id.tvEnd);
        tvStart = v.findViewById(R.id.tvStart);
        tvDate = v.findViewById(R.id.tvDate);
        tvTong = v.findViewById(R.id.tvTongtien);
        btnStatus = v.findViewById(R.id.btnStatus);
        tvGiotra = v.findViewById(R.id.tvGiotra);
        tvTrathem = v.findViewById(R.id.tvTienthem);
        Orders obj = list.get(position);

        VehiclesDAO vehiclesDAO = new VehiclesDAO(context);
        Vehicles vehicles = vehiclesDAO.getId(obj.getVehicles_id()+"");

        tvName.setText(vehicles.getName());
        tvUser.setText("Account : "+obj.getUsers_id());
        tvStart.setText("Giờ thuê : "+obj.getStart_time());
        tvEnd.setText("Giờ kết thúc : "+obj.getEnd_time());
        tvTrathem.setText("Tiền trả thêm : "+obj.getIncutted()+"vnđ");

        tvDate.setText("Ngày : "+obj.getDate());
        if(obj.getGiotraxe()==null){
            tvGiotra.setText("Giờ trả : --:--");
        }else {
            tvGiotra.setText("Giờ trả : " + obj.getGiotraxe());
        }
        tvTong.setText("Tổng tiền : "+obj.getTotal()+"vnđ");

        listCheck = (ArrayList<Orders>) dao.getVehicle(obj.getVehicles_id()+"");
        if(obj.getStatus()==1){
            btnStatus.setText("Đã trả xe");
            btnStatus.setBackgroundResource(R.drawable.backgroup_button_color);
            btnStatus.setTextColor(Color.parseColor("#FFFFFFFF"));
        }else if(obj.getStatus()==2){
            btnStatus.setText("Chờ xác nhận");
            btnStatus.setBackgroundResource(R.drawable.backgroup_button_nocolor);
            btnStatus.setTextColor(Color.parseColor("#FF3333"));
        }else{
            btnStatus.setText("Đang thuê xe");
            btnStatus.setBackgroundResource(R.drawable.backgroup_button_nocolor);
            btnStatus.setTextColor(Color.parseColor("#FF03DAC5"));
        }

        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(obj.getStatus()==2) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Xác nhận");
                    builder.setMessage("Xác nhận cho thuê xe ?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            fragment.changeStatus(obj);
                            tvGiotra.setText("Giờ trả : "+obj.getGiotraxe());
                            tvTrathem.setText("Tiền trả thêm : "+obj.getIncutted()+"vnđ");
                            int tong = obj.getTotal()+obj.getIncutted();
                            tvTong.setText("Tổng tiền : "+tong+"vnđ");
                            dialog.cancel();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    builder.show();
                }else if(obj.getStatus()==1){
                    Toast.makeText(fragment.getContext(), "Xe đã được trả", Toast.LENGTH_SHORT).show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Xác nhận");
                    builder.setMessage("Xác nhận xe đã trả ?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            fragment.changeStatus(obj);
                            tvGiotra.setText("Giờ trả : "+obj.getGiotraxe());
                            tvTrathem.setText("Tiền trả thêm : "+obj.getIncutted()+"vnđ");
                            int tong = obj.getTotal()+obj.getIncutted();
                            tvTong.setText("Tổng tiền : "+tong+"vnđ");
                            dialog.cancel();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    builder.show();
                }

            }
        });

        return v;
    }
}
