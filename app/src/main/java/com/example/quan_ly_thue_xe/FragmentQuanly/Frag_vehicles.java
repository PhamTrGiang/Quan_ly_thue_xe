package com.example.quan_ly_thue_xe.FragmentQuanly;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.quan_ly_thue_xe.Adapter.SpinAdapter_Categories;
import com.example.quan_ly_thue_xe.Adapter.Vehicles_Adapter;

import com.example.quan_ly_thue_xe.DAO.CategoriesDAO;
import com.example.quan_ly_thue_xe.DAO.VehiclesDAO;
import com.example.quan_ly_thue_xe.Model.Categories;
import com.example.quan_ly_thue_xe.Model.Vehicles;
import com.example.quan_ly_thue_xe.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


public class Frag_vehicles extends Fragment {
    ListView lv;
    FloatingActionButton flbtn;
    VehiclesDAO dao;
    EditText edName,edPrice,edAmount;
    ArrayList<Vehicles> list;
    Button btnAccess,btnCancel;
    Vehicles item;
    Vehicles_Adapter adapter;
    Dialog dialog;
    Spinner spinner;
    SpinAdapter_Categories spinAdapter_categories;
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
    ImageButton imgButton;

    public void openDialog(final Context context, final int type, Vehicles obj){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dig_vehicles);

        edName = dialog.findViewById(R.id.edName);
        edPrice = dialog.findViewById(R.id.edPrice);
        spinner = dialog.findViewById(R.id.spinner);
        btnAccess=dialog.findViewById(R.id.btnAccess);
        btnCancel = dialog.findViewById(R.id.btnCancel);
        imgButton = dialog.findViewById(R.id.imgButton);
        edAmount = dialog.findViewById(R.id.edAmount);


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
            edPrice.setText(obj.getPrice()+"");
            edAmount.setText(obj.getAmount()+"");
            for (int i =0 ; i<listCategories.size();i++){
                if(obj.getId() == (listCategories.get(i).getId())){
                    position = i;
                }
            }
            spinner.setSelection(position);
            byte[] image = obj.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
            imgButton.setImageBitmap(bitmap);
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
                    item.setAmount(Integer.parseInt(edAmount.getText().toString()));
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imgButton.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                    byte[] image = byteArray.toByteArray();
                    item.setImage(image);
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
                        obj.setImage(image);
                        obj.setAmount(Integer.parseInt(edAmount.getText().toString()));
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


        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogaddImg(context);
            }
        });
        dialog.show();
    }



    final int REQUEST_CODE_CAMERA = 123;
    final int REQUEST_CODE_FOLDER = 456;
    Button btnCamera,btnLibrary,btnCancel2;
    public void openDialogaddImg(Context context){
        Dialog dialogImg = new Dialog(context);
        dialogImg.setContentView(R.layout.dig_chose_image);
        btnCamera = dialogImg.findViewById(R.id.btnCamera);
        btnLibrary = dialogImg.findViewById(R.id.btnLibrary);
        btnCancel2 = dialogImg.findViewById(R.id.btnCancel);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i1,REQUEST_CODE_CAMERA);
                dialogImg.dismiss();
            }
        });

        btnLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(Intent.ACTION_PICK);
                i2.setType("image/*");
                startActivityForResult(i2,REQUEST_CODE_FOLDER);
                dialogImg.dismiss();
            }
        });

        btnCancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogImg.dismiss();

            }
        });

        dialogImg.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK && data!=null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgButton.setImageBitmap(bitmap);
        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == Activity.RESULT_OK && data!=null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgButton.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }

        super.onActivityResult(requestCode, resultCode, data);
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
        if (edName.getText().length()==0||edPrice.getText().length()==0||edAmount.getText().length()==0){
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
}