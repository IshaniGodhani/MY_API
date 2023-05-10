package com.example.my_api.Fragment;

import static com.example.my_api.Activity.SplashScreen.editor;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.my_api.Activity.PaymentActivity;
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

        View view=inflater.inflate(R.layout.fragment_view__product, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);
       String userId=SplashScreen.preferences.getString("userid","");

            Retro_Instance_Class.MyAPICalling().viewProduct(userId).enqueue(new Callback<ViewProductData>() {
                @Override
                public void onResponse(Call<ViewProductData> call, Response<ViewProductData> response) {
//                Log.d("mmm", "onResponse: "+response.body().getResult());
//               Log.d("mmm", "onResponse: "+response.body().getProductdata().get(0).getProName());
                    productDataList=response.body().getProductdata();
                    //productDataList.addAll()
                  // Log.d("mmm", "onResponse: "+productDataList.size());

                    MyAdatpter myAdatpter=new MyAdatpter(getActivity(),productDataList,false);
                    LinearLayoutManager manager=new LinearLayoutManager(getContext());
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(myAdatpter);
//                    recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
//                        @Override
//                        public boolean onLongClick(View view) {
//                            Toast.makeText(getContext(), "LongClicked", Toast.LENGTH_SHORT).show();
//                            Intent intent=new Intent(getContext(), PaymentActivity.class);
//                            getContext().startActivity(intent);
//                            return false;
//                        }
//                    });
//                    recyclerView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Toast.makeText(getContext(), "LongClicked", Toast.LENGTH_SHORT).show();
//                            Intent intent=new Intent(getContext(), PaymentActivity.class);
//                            getContext().startActivity(intent);
//                        }
//                    });

                }

                @Override
                public void onFailure(Call<ViewProductData> call, Throwable t) {
                    Log.e("mmm", "onResponse: "+t.getLocalizedMessage());
                }
            });


        return view;


    }
}