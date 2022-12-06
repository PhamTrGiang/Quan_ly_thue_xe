package com.example.quan_ly_thue_xe.Adapter;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quan_ly_thue_xe.DAO.CategoriesDAO;
import com.example.quan_ly_thue_xe.DAO.OrdersDAO;

import com.example.quan_ly_thue_xe.Framentkhachhang.FragmentCuahang;
import com.example.quan_ly_thue_xe.Framentkhachhang.FragmentOrders;
import com.example.quan_ly_thue_xe.Model.Categories;
import com.example.quan_ly_thue_xe.Model.Orders;
import com.example.quan_ly_thue_xe.Model.Vehicles;
import com.example.quan_ly_thue_xe.R;

import java.util.ArrayList;
import java.util.Calendar;


public class Shop_Adapter extends ArrayAdapter<Vehicles> {
    private Context context;
    FragmentCuahang fragment;
    TextView tvName,tvPrice,tvStatus;
    Button btnOrder,btnMore;
    ImageView imgImage;
    private ArrayList<Vehicles> list;
    private ArrayList<Orders> listOrders;
    FragmentOrders fragmentOrders = new FragmentOrders();

    OrdersDAO dao;

    public Shop_Adapter(@NonNull Context context, FragmentCuahang fragment, ArrayList<Vehicles> list) {
        super(context, 0,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        dao = new OrdersDAO(getContext());
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_shop,null);
        }
        final Vehicles item = list.get(position);
        if(item!=null){

            tvName = v.findViewById(R.id.tvName);
            tvPrice = v.findViewById(R.id.tvPrice);
            imgImage = v.findViewById(R.id.imgPhoto);
            tvStatus = v.findViewById(R.id.tvStatus);
            btnMore = v.findViewById(R.id.btnMore);
            btnOrder = v.findViewById(R.id.btnOrders);

            tvName.setText(item.getName());
            tvPrice.setText("Giá  : "+item.getPrice()+"/1h");
            OrdersDAO ordersDAO = new OrdersDAO(getContext());
            listOrders = (ArrayList<Orders>) ordersDAO.getVehicle(item.getId()+"");
            String status = "";
            if(listOrders.size() != 0 ){
                status = "Xe đang được thuê";
                tvStatus.setTextColor(Color.parseColor("#FF0000"));
            }else{
                status = "Còn xe";
                tvStatus.setTextColor(Color.parseColor("#0000FF"));
            }
            tvStatus.setText(status);


            byte[] image = item.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
            imgImage.setImageBitmap(bitmap);


            btnOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listOrders.size() !=0){
                        Toast.makeText(getContext(), "Hiện không còn xe", Toast.LENGTH_SHORT).show();
                    }else{
                        openDialog(item);
                    }

                }
            });
            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        return v;
    }

    Dialog dialog;
    Button btnAccess,btnCancel;
    EditText edName,edPrice,edTotal,edEnd;
    ImageView imgTime;

    public void openDialog(Vehicles obj){
        Orders item = new Orders();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minuteStart = calendar.get(Calendar.MINUTE);
        Toast.makeText(context, hour+":"+minuteStart, Toast.LENGTH_SHORT).show();
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dig_orders);
        edName = dialog.findViewById(R.id.edName);
        edPrice = dialog.findViewById(R.id.edPrice);
        edTotal = dialog.findViewById(R.id.edTotal);
        edEnd = dialog.findViewById(R.id.edEnd);
        imgTime = dialog.findViewById(R.id.imgTime);

        btnAccess=dialog.findViewById(R.id.btnAccess);
        btnCancel = dialog.findViewById(R.id.btnCancel);

        edName.setText(obj.getName());
        edPrice.setText(obj.getPrice()+" vnđ/1h");
        int gio = 0,phut=0;
        imgTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog =new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        edEnd.setText(hourOfDay+":"+minute);
                        int gioao = 0;
                        if(minute<minuteStart){
                            gioao = 0;
                        }else{
                            gioao = 1;
                        }
                        double tongtien = (hourOfDay-hour+((double)minute+60-(double)minuteStart)/60-1)*obj.getPrice();
                        edTotal.setText((int) Math.round(tongtien)+"");
                    }
                },gio,phut,true);
                timePickerDialog.show();
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getContext().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
                String user_id = pref.getString("id",null);
                item.setVehicles_id(obj.getId());
                item.setDate(day+"-"+month+"-"+year);
                item.setStatus(2);
                item.setStart_time(hour+":"+minuteStart);
                item.setEnd_time(edEnd.getText().toString());
                item.setUsers_id(user_id);
                item.setTotal(Integer.parseInt(edTotal.getText().toString()));
                item.setDate(day+"/"+month+"/"+year);
                if(dao.insert(item)>0){
                    Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
                }
                fragment.check();
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}
