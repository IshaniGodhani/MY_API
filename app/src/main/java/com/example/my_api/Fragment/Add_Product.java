package com.example.my_api.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.my_api.Activity.SplashScreen;
import com.example.my_api.R;
import com.example.my_api.Retrofit.Retro_Instance_Class;


public class Add_Product extends Fragment {
EditText proname,proprice,prodes;
ImageView proimage;
Button addpro;
String userid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View view=inflater.inflate(R.layout.fragment_add_product, container, false);
         proname=view.findViewById(R.id.pro_name);
         proprice=view.findViewById(R.id.pro_price);
         prodes=view.findViewById(R.id.pro_des);
         proimage=view.findViewById(R.id.pro_Image);
         addpro=view.findViewById(R.id.btnadd_pro);

         userid= SplashScreen.preferences.getString("userid","");

         addpro.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

             }
         });

        return view;
    }
}