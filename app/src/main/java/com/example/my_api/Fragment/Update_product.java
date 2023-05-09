package com.example.my_api.Fragment;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.my_api.Activity.SplashScreen;
import com.example.my_api.Models.DeleteData;
import com.example.my_api.Models.Productdata_Show;
import com.example.my_api.R;
import com.example.my_api.Retrofit.Retro_Instance_Class;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Update_product extends Fragment {
    EditText e1,e2,e3;
    ImageView imageView;
    Button update;
    String Id,name,price,desc;
    String imagedata;
    String imagename;
    int position;

    List<Productdata_Show> productDataList;
    public Update_product(List<Productdata_Show> productDataList, int position) {
    this.productDataList=productDataList;
    this.position=position;
    }

    @SuppressLint("WrongThread")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_update_product, container, false);
        e1=view.findViewById(R.id.uppro_name);
        e2=view.findViewById(R.id.uppro_price);
        e3=view.findViewById(R.id.uppro_des);
        imageView=view.findViewById(R.id.uppro_Image);
        update=view.findViewById(R.id.btnupdate_pro);


        Id= productDataList.get(position).getId();
        name=productDataList.get(position).getProName();
        price=productDataList.get(position).getProPrice();
        desc=productDataList.get(position).getProDes();
        imagename=productDataList.get(position).getProImage();

        //"https://ishaniecommerce.000webhostapp.com/Mysite/"+

        e1.setText(""+name);
        e2.setText(""+price);
        e3.setText(""+desc);

        String img="https://ishaniecommerce.000webhostapp.com/Mysite/"+imagename;

        Picasso.get()
                .load(img)
                .placeholder(R.drawable.animation)
                .into(imageView);

               imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity()
                        .start(getContext(), Update_product.this);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateproduct();
            }
        });

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
    private void updateproduct()
    {

        name=e1.getText().toString();
        price=e2.getText().toString();
        desc=e3.getText().toString();


        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        byte[] imageInByte = baos.toByteArray();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            imagedata = Base64.getEncoder().encodeToString(imageInByte);
        }
        Retro_Instance_Class.MyAPICalling().updateProduct(Id,name,price,desc,imagedata,imagename).enqueue(new Callback<DeleteData>() {
            @Override
            public void onResponse(Call<DeleteData> call, Response<DeleteData> response) {
                if (response.body().getConnection() == 1 && response.body().getResult() == 1) {

                    Toast.makeText(Update_product.this.getActivity(), "Product update Successfully", Toast.LENGTH_SHORT).show();
                } else if (response.body().getResult() == 0) {
                    Toast.makeText(Update_product.this.getActivity(), "Product not update", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Update_product.this.getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteData> call, Throwable t) {
                Log.e("ddd", "onFailure: "+t.getLocalizedMessage());
                Toast.makeText(Update_product.this.getActivity(), "Something Wrong="+t.getLocalizedMessage(),Toast.LENGTH_LONG);
            }
        });

    }
}