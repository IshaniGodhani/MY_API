package com.example.my_api.Fragment;
import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.my_api.Activity.Login_activity;
import com.example.my_api.Activity.SplashScreen;
import com.example.my_api.Models.ProductData;
import com.example.my_api.R;
import com.example.my_api.Retrofit.Retro_Instance_Class;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Add_Product extends Fragment {
    EditText et1,et2,et3;
    ImageView imageView;
    Button addpro;
    String userid;
    String proname,proprice,prodes,imagedata;





    @SuppressLint("WrongThread")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View view=inflater.inflate(R.layout.fragment_add_product, container, false);
         et1=view.findViewById(R.id.pro_name);
         et2=view.findViewById(R.id.pro_price);
         et3=view.findViewById(R.id.pro_des);
         imageView=view.findViewById(R.id.pro_Image);
         addpro=view.findViewById(R.id.btnadd_pro);

         imageView.setOnClickListener(view1 -> CropImage.activity()
                 .setGuidelines(CropImageView.Guidelines.ON)
                 .start(Add_Product.this.getActivity()));




         addpro.setOnClickListener(view12 -> addproduct());
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                imageView.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    private void addproduct() {

        proname = et1.getText().toString();
        proprice = et2.getText().toString();
        prodes = et3.getText().toString();
        try {


            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
            byte[] imageInByte = baos.toByteArray();
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                imagedata = Base64.getEncoder().encodeToString(imageInByte);
            }

            Retro_Instance_Class.MyAPICalling().addproduct(proname, proprice, prodes, imagedata).enqueue(new Callback<ProductData>() {
                @Override
                public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                    if (response.body().getConnection() == 1 && response.body().getProductaddd() == 1) {
                        userid = SplashScreen.preferences.getString("userid", "");
                        Toast.makeText(Add_Product.this.getActivity(), "Product Add Successfully", Toast.LENGTH_SHORT).show();
                    } else if (response.body().getProductaddd() == 0) {
                        Toast.makeText(Add_Product.this.getActivity(), "Product not Added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Add_Product.this.getActivity(), "Something wnt wrong", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ProductData> call, Throwable t) {

                }
            });
        }
        catch (NullPointerException exception)
        {
            Toast.makeText(getActivity(), "Please add any image to proceed", Toast.LENGTH_SHORT).show();
        }
        catch (ClassCastException exception)
        {
            Toast.makeText(getActivity(), "Please add any image to proceed", Toast.LENGTH_SHORT).show();
        }
    }
}