package com.example.quan_ly_thue_xe.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quan_ly_thue_xe.Adapter.SpinAdapter_Position;
import com.example.quan_ly_thue_xe.Adapter.Users_Adapter;
import com.example.quan_ly_thue_xe.DAO.UsersDAO;
import com.example.quan_ly_thue_xe.Model.Users;
import com.example.quan_ly_thue_xe.R;

import java.util.ArrayList;
import java.util.List;

public class Frag_users extends Fragment {
    ListView lv;
    List<Users> list;
    UsersDAO dao;
    Users_Adapter adapter;

    Dialog dialog;

    Button btnDone;
    TextView tvUser,tvPhone,tvIden,tvName;
    Spinner spPosition;

    public Frag_users() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_user, container, false);
        lv = v.findViewById(R.id.listView);
        dao =new UsersDAO(getActivity());
        capNhap();

        return v;
    }


    public void openDialog(final Context context, Users obj){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dig_users);
        tvUser =dialog.findViewById(R.id.tvUser);
        tvName = dialog.findViewById(R.id.tvName);
        tvPhone = dialog.findViewById(R.id.tvPhone);
        tvIden = dialog.findViewById(R.id.tvIden);
        spPosition = dialog.findViewById(R.id.spPosition);
        btnDone=dialog.findViewById(R.id.btnDone);
        SpinAdapter_Position spAdapter = new SpinAdapter_Position(getContext());
        spPosition.setAdapter(spAdapter);
        tvUser.setText("Username        : "+obj.getId());
        tvName.setText("Fullname          : "+obj.getName());
        tvPhone.setText("Phonenumber : "+obj.getPhone_number());
        tvIden.setText("Identification   : "+obj.getIndentification());
        spPosition.setSelection(obj.getStatus()-1);


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    obj.setStatus(spPosition.getSelectedItemPosition()+1);
                    if (dao.upadate(obj)>0){
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        capNhap();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
            }
        });
        dialog.show();

    }


    void capNhap(){
        list = dao.getAll();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getStatus()==3){
                list.remove(i);
            }
        }
        adapter = new Users_Adapter(getActivity(),this, (ArrayList<Users>) list);
        lv.setAdapter(adapter);
    }
}