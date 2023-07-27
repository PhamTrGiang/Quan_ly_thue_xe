package com.example.quan_ly_thue_xe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quan_ly_thue_xe.DAO.UsersDAO;
import com.example.quan_ly_thue_xe.Model.Users;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class EditInfomationActivity extends AppCompatActivity {
    EditText edFullname , edPhonenumber,edIndentification;
    UsersDAO dao;
    ImageView imgCallback,imgInfo;
    Button btnEdit;
    Users obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_infomation);
        imgCallback = findViewById(R.id.idCallback);
        imgCallback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dao = new UsersDAO(this);
        edFullname = findViewById(R.id.edFullname);
        edPhonenumber = findViewById(R.id.edPhonenumber);
        edIndentification = findViewById(R.id.edIdentification);
        imgInfo = findViewById(R.id.imgInfo);
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String id = pref.getString("id",null);
        obj = dao.getId(id);
        String name = obj.getName();
        String phone = obj.getPhone_number();
        String iden = obj.getIndentification();
        edFullname.setText(name);
        edIndentification.setText(iden);
        edPhonenumber.setText(phone);

        if(obj.getImage()!=null){
            byte[] image = obj.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
            imgInfo.setImageBitmap(bitmap);
        }
        imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogaddImg(EditInfomationActivity.this);
            }
        });

        btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgInfo.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] image = byteArray.toByteArray();
                obj.setImage(image);
                obj.setIndentification(edIndentification.getText().toString());
                obj.setName(edFullname.getText().toString());
                obj.setPhone_number(edPhonenumber.getText().toString());
                if(dao.upadate(obj)>0){
                    finish();
                }else{
                    Toast.makeText(EditInfomationActivity.this, "Update lỗi", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    final int REQUEST_CODE_CAMERA = 123;
    final int REQUEST_CODE_FOLDER = 456;
    Button btnCamera,btnLibrary,btnCancel2;
    Dialog dialogImg;
    public void openDialogaddImg(Context context){
        dialogImg = new Dialog(context);
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
            imgInfo.setImageBitmap(bitmap);
        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == Activity.RESULT_OK && data!=null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getBaseContext().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgInfo.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}