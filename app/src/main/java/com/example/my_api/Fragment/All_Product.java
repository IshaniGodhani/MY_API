package com.example.my_api.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.my_api.Activity.MainActivity;
import com.example.my_api.Adapters.MyAdatpter;
import com.example.my_api.Models.Productdata_Show;
import com.example.my_api.Models.ViewProductData;
import com.example.my_api.R;
import com.example.my_api.Retrofit.Retro_Instance_Class;
import com.example.my_api.Retrofit.Retro_Interface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class All_Product extends Fragment {
    RecyclerView recyclerView;
    MyAdatpter adatpter;
    List<Productdata_Show> productDataList=new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_all__product, container, false);
        recyclerView=view.findViewById(R.id.all_view_recyclerview);

        Retro_Instance_Class.MyAPICalling().viewallProduct().enqueue(new Callback<ViewProductData>() {
            @Override
            public void onResponse(Call<ViewProductData> call, Response<ViewProductData> response) {
                if (response.body().getConnection()==1)
                {
                    if (response.body().getResult()==1)
                    {
                        productDataList=response.body().getProductdata();
                        adatpter=new MyAdatpter(getContext(),productDataList,true);
                        LinearLayoutManager manager=new LinearLayoutManager(getContext());
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adatpter);
                    }
                    else if (response.body().getResult() == 2) {
                        Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ViewProductData> call, Throwable t) {
                Log.e("aaa", "onFailure: "+t.getLocalizedMessage());
            }
        });
        return view;
    }
}