package com.example.quan_ly_thue_xe.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.quan_ly_thue_xe.Adapter.Categories_Adapter;
import com.example.quan_ly_thue_xe.Adapter.SpinAdapter_Categories;
import com.example.quan_ly_thue_xe.Adapter.Vehicles_Adapter;

import com.example.quan_ly_thue_xe.DAO.CategoriesDAO;
import com.example.quan_ly_thue_xe.DAO.VehiclesDAO;
import com.example.quan_ly_thue_xe.Model.Categories;
import com.example.quan_ly_thue_xe.Model.Vehicles;
import com.example.quan_ly_thue_xe.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class Frag_vehicles extends Fragment {
    ListView lv;
    FloatingActionButton flbtn;
    VehiclesDAO dao;
    EditText edName,edPrice;
    ArrayList<Vehicles> list;
    Button btnAccess,btnCancel;
    Vehicles item;
    Vehicles_Adapter adapter;
    Dialog dialog;
    Spinner spinner;
    SpinAdapter_Categories spinAdapter_categories;
    Categories categories;
    CategoriesDAO categoriesDAO;
    ArrayList<Categories> listCategories;
    int categories_id,position;

    public Frag_vehicles() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag_vehicles, container, false);
        lv = v.findViewById(R.id.listView);
        flbtn = v.findViewById(R.id.fltButton);
        dao =new VehiclesDAO(getActivity());

        capNhap();
        flbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0,null);
            }
        });
        return v;
    }

    public void openDialog(final Context context, final int type, Vehicles obj){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dig_vehicles);
        edName = dialog.findViewById(R.id.edName);
        edPrice = dialog.findViewById(R.id.edPrice);
        spinner = dialog.findViewById(R.id.spinner);
        btnAccess=dialog.findViewById(R.id.btnAccess);
        btnCancel = dialog.findViewById(R.id.btnCancel);

        categoriesDAO = new CategoriesDAO(context);
        listCategories = new ArrayList<Categories>();
        listCategories = (ArrayList<Categories>) categoriesDAO.getAll();
        spinAdapter_categories = new SpinAdapter_Categories(context,listCategories);
        spinner.setAdapter(spinAdapter_categories);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categories_id = listCategories.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (type !=0){
            edName.setText(obj.getName());
            edPrice.setText(obj.getPrice());
            for (int i =0 ; i<listCategories.size();i++){
                if(obj.getId() == (listCategories.get(i).getId())){
                    position = i;
                }
            }
            spinner.setSelection(position);
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()>0){
                    item = new Vehicles();
                    item.setName(edName.getText().toString());
                    item.setPrice(Integer.parseInt(edPrice.getText().toString()));
                    item.setCategories_id(categories_id);   
                    if(type==0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        obj.setName(edName.getText().toString());
                        obj.setPrice(Integer.parseInt(edPrice.getText().toString()));
                        obj.setCategories_id(categories_id);
                        if (dao.upadate(obj)>0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhap();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();

    }

    public void xoa(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(id);
                capNhap();
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

    void capNhap(){
        list = (ArrayList<Vehicles>) dao.getAll();
        adapter = new Vehicles_Adapter(getActivity(),this, (ArrayList<Vehicles>) list);
        lv.setAdapter(adapter);

    }

    public int validate(){
        int check = 1;
        if (edName.getText().length()==0||edPrice.getText().length()==0){
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
}