package com.example.my_api.Adapters;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.animation.content.Content;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.my_api.Activity.SplashScreen;
import com.example.my_api.Fragment.View_Product;
import com.example.my_api.Models.DeleteData;
import com.example.my_api.Models.Productdata_Show;
import com.example.my_api.R;
import com.example.my_api.Retrofit.Retro_Instance_Class;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAdatpter extends RecyclerView.Adapter<MyAdatpter.UserHolder>
{
    Context context;
    List<Productdata_Show> productDataList;
    SharedPreferences preferences;
    String Id;
    boolean all;
    public MyAdatpter(Context context, List<Productdata_Show> productDataList,boolean all) {
        this.context=context;
        this.productDataList=productDataList;
        this.all=all;
        preferences=SplashScreen.preferences;
    }



    @NonNull
    @Override
    public MyAdatpter.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_view_product_item,parent,false);
        UserHolder holder = new UserHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdatpter.UserHolder holder, int position) {

        holder.txtProName.setText(""+productDataList.get(position).getProName());
        holder.txtProDes.setText(""+productDataList.get(position).getProDes());
        holder.txtProPrice.setText(""+productDataList.get(position).getProPrice());

        String img="https://ishaniecommerce.000webhostapp.com/Mysite/"+productDataList.get(position).getProImage();

        Picasso.get()
                    .load(img)
                    .placeholder(R.drawable.animation)
                    .into(holder.imageView);


            holder.menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(context,holder.menu);
                    popupMenu.getMenuInflater().inflate(R.menu.edit_menu,popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            if(menuItem.getItemId()==R.id.item_update)
                            {

                            }
                            else if(menuItem.getItemId()==R.id.item_delete)
                            {
                                Id=SplashScreen.preferences.getString("id","");
                                Retro_Instance_Class.MyAPICalling().deleteProduct(Id).enqueue(new Callback<DeleteData>() {
                                    @Override
                                    public void onResponse(Call<DeleteData> call, Response<DeleteData> response) {
                                        if (response.body().getConnection() == 1 && response.body().getResult() == 1) {
                                            Toast.makeText(context, "Delete product success", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<DeleteData> call, Throwable t) {

                                    }
                                });
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                }
            });




    }


    @Override
    public int getItemCount() {
        return productDataList.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        TextView txtProName,txtProPrice,txtProDes;
        ImageView imageView,menu;
        public UserHolder(@NonNull View itemView)
        {
            super(itemView);
            txtProName=itemView.findViewById(R.id.list_proname);
            txtProPrice=itemView.findViewById(R.id.list_proprice);
            txtProDes=itemView.findViewById(R.id.list_prodes);
            imageView=itemView.findViewById(R.id.list_img);
            menu=itemView.findViewById(R.id.list);
        }
    }
}
