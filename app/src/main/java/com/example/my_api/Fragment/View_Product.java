package com.example.my_api.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.my_api.Activity.SplashScreen;
import com.example.my_api.Adapters.MyAdatpter;
import com.example.my_api.Models.Productdata_Show;
import com.example.my_api.Models.ViewProductData;
import com.example.my_api.R;
import com.example.my_api.Retrofit.Retro_Instance_Class;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class View_Product extends Fragment
{

    RecyclerView recyclerView;
    List<Productdata_Show> productDataList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_view__product, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);

        Retro_Instance_Class.MyAPICalling().viewProduct(SplashScreen.preferences.getString("userid","")).enqueue(new Callback<ViewProductData>() {
            @Override
            public void onResponse(Call<ViewProductData> call, Response<ViewProductData> response) {
//                Log.d("mmm", "onResponse: "+response.body().getResult());
//                Log.d("mmm", "onResponse: "+response.body().getProductdata().get(0).getProName());
                productDataList=response.body().getProductdata();
                //productDataList.addAll()
                Log.d("mmm", "onResponse: "+productDataList.size());
                MyAdatpter myAdatpter=new MyAdatpter(View_Product.this,productDataList);
                LinearLayoutManager manager=new LinearLayoutManager(getContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(myAdatpter);

            }

            @Override
            public void onFailure(Call<ViewProductData> call, Throwable t) {
                Log.e("mmm", "onResponse: "+t.getLocalizedMessage());
            }
        });

        return view;


    }
}