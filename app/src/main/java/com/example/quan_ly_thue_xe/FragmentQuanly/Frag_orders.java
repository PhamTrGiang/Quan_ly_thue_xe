package com.example.quan_ly_thue_xe.FragmentQuanly;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quan_ly_thue_xe.Adapter.Orders_Adapter;

import com.example.quan_ly_thue_xe.DAO.OrdersDAO;

import com.example.quan_ly_thue_xe.DAO.VehiclesDAO;
import com.example.quan_ly_thue_xe.Model.Orders;

import com.example.quan_ly_thue_xe.Model.Vehicles;
import com.example.quan_ly_thue_xe.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;


public class Frag_orders extends Fragment {
    ListView lv;
    ArrayList<Orders> list;
    OrdersDAO dao;
    Orders_Adapter adapter;

    public Frag_orders() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_orders, container, false);
        lv = v.findViewById(R.id.listView);
        dao =new OrdersDAO(getActivity());
        capNhap();
        return v;
    }
    public void capNhap(){
        list = (ArrayList<Orders>) dao.getAll();
        Collections.reverse(list);
        adapter = new Orders_Adapter(getActivity(),this, (ArrayList<Orders>) list);
        lv.setAdapter(adapter);
    }

    public void changeStatus(Orders obj){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        double tienthem = 0;


        if(obj.getStatus()==2){
            obj.setStart_time(hour+":"+minute);
            obj.setStatus(3);
        }else if(obj.getStatus()==3){
            obj.setStatus(1);
            obj.setGiotraxe(hour+":"+minute);
            String[] endtime = obj.getEnd_time().split(":");
            int hourEnd = Integer.parseInt(endtime[0]),minuteEnd = Integer.parseInt(endtime[1]);
            VehiclesDAO vehiclesDAO = new VehiclesDAO(getActivity());
            Vehicles vehicles = vehiclesDAO.getId(obj.getVehicles_id()+"");
            if(hourEnd<hour){
                if(minute>(minuteEnd+15)){
                    tienthem = (hour-hourEnd+((double)minute-15+60-(double)minuteEnd)/60-1)*vehicles.getPrice()*1.25+0.25*vehicles.getPrice();
                }else{
                    if(hour-hourEnd==1){
                        tienthem = 0;
                    }else{
                        tienthem = (hour-hourEnd+((double)minute-15+60-(double)minuteEnd)/60-1)*vehicles.getPrice()*1.25+0.25*vehicles.getPrice();
                    }
                }

            }else{
                tienthem = 0;
            }
            obj.setIncutted((int) Math.round(tienthem));
        };


        if(dao.upadate(obj)>0){
            Toast.makeText(getContext(), "Update success", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "Update fail", Toast.LENGTH_SHORT).show();
        }

        capNhap();
    }


}