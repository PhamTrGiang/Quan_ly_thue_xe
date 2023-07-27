package com.example.quan_ly_thue_xe.FragmentQuanly;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quan_ly_thue_xe.Adapter.Categories_Adapter;
import com.example.quan_ly_thue_xe.Adapter.DoanhthuAdapter;
import com.example.quan_ly_thue_xe.Adapter.Orders_Adapter;
import com.example.quan_ly_thue_xe.Adapter.SpinAdapter_Position;
import com.example.quan_ly_thue_xe.DAO.OrdersDAO;
import com.example.quan_ly_thue_xe.DAO.ThongkeDAO;
import com.example.quan_ly_thue_xe.Model.Categories;
import com.example.quan_ly_thue_xe.Model.Orders;
import com.example.quan_ly_thue_xe.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;


public class Frag_doanhthu_thang extends Fragment {
    DoanhthuAdapter adapter;
    ArrayList<Orders> list;
    ThongkeDAO dao;
    OrdersDAO ordersDAO;
    String[] listMonth = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    ListView lv;
    TextView tvDoanhthu;
    EditText edYear;
    Spinner spMonth;
    Button btDoanhthu;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int month = calendar.get(Calendar.MONTH)+1;
    int year = calendar.get(Calendar.YEAR);

    public Frag_doanhthu_thang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag_doanhthu_thang, container, false);
        lv = v.findViewById(R.id.listView);
        tvDoanhthu = v.findViewById(R.id.tvDoanhthu);
        edYear = v.findViewById(R.id.edYear);
        spMonth = v.findViewById(R.id.spMonth);
        btDoanhthu = v.findViewById(R.id.btDoanhthu);
        SpinAdapter_Position adapter_position = new SpinAdapter_Position(getContext(),listMonth);
        spMonth.setAdapter(adapter_position);
        spMonth.setSelection(month-1);
        edYear.setText(year+"");
        ordersDAO = new OrdersDAO(getContext());
        dao = new ThongkeDAO(getContext());
        capNhap(month,year);
        spMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = Integer.parseInt(listMonth[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btDoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = Integer.parseInt(edYear.getText().toString());
                list.clear();
                capNhap(month,year);
            }
        });
        return v;
    }

    void capNhap(int thang,int nam){
        String date=("\'%"+thang+"/"+nam+"\'");
        list = (ArrayList<Orders>) ordersDAO.getMonth(date);
        adapter = new DoanhthuAdapter(getActivity(),this, list);
        int doanhthu =dao.getDoanhthuthang(date) ;
        tvDoanhthu.setText("Doanh thu: "+doanhthu+"vnđ");
        lv.setAdapter(adapter);
    }
}