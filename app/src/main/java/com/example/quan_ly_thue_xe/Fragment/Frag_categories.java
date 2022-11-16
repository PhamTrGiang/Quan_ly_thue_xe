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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quan_ly_thue_xe.Adapter.Categories_Adapter;
import com.example.quan_ly_thue_xe.DAO.CategoriesDAO;
import com.example.quan_ly_thue_xe.Model.Categories;
import com.example.quan_ly_thue_xe.Model.Vehicles;
import com.example.quan_ly_thue_xe.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class Frag_categories extends Fragment {
    ListView lv;
    FloatingActionButton flbtn;
    CategoriesDAO dao;
    EditText edName;
    Button btnAccess,btnCancel;
    Categories item;
    Categories_Adapter adapter;
    Dialog dialog;
    List<Categories> list;




    public Frag_categories() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_categories, container, false);
        lv = v.findViewById(R.id.listView);
        flbtn = v.findViewById(R.id.fltButton);
        dao =new CategoriesDAO(getActivity());
        capNhap();
        flbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0,null);
            }
        });
        return v;
    }
    public void openDialog(final Context context, final int type, Categories obj){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dig_categories);
        edName = dialog.findViewById(R.id.edName);
        btnAccess=dialog.findViewById(R.id.btnAccess);
        btnCancel = dialog.findViewById(R.id.btnCancel);
        if (type !=0){
            edName.setText(obj.getName());
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
                    item = new Categories();
                    item.setName(edName.getText().toString());
                    if(type==0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        obj.setName(edName.getText().toString());
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
        list = dao.getAll();
        adapter = new Categories_Adapter(getActivity(),this, (ArrayList<Categories>) list);
        lv.setAdapter(adapter);
    }

    public int validate(){
        int check = 1;
        if (edName.getText().length()==0){
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
}