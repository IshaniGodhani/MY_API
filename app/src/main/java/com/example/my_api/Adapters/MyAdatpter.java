package com.example.my_api.Adapters;

import static java.security.AccessController.getContext;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.animation.content.Content;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.my_api.Fragment.View_Product;
import com.example.my_api.Models.Productdata_Show;
import com.example.my_api.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdatpter extends RecyclerView.Adapter<MyAdatpter.UserHolder>
{
    View_Product context;
    List<Productdata_Show> productDataList;
    public MyAdatpter(View_Product context, List<Productdata_Show> productDataList) {
        this.context=context;
        this.productDataList=productDataList;
    }

    @NonNull
    @Override
    public MyAdatpter.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context.getContext()).inflate(R.layout.fragment_view_product_item,parent,false);
        UserHolder holder = new UserHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdatpter.UserHolder holder, int position) {

        holder.txtProName.setText(""+productDataList.get(position).getProName());
        holder.txtProDes.setText(""+productDataList.get(position).getProDes());
        holder.txtProPrice.setText(""+productDataList.get(position).getProPrice());

        String img="https://ishaniecommerce.000webhostapp.com/Mysite/"+productDataList.get(position).getProImage();
//        Glide.with(context.getContext()).load(img)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
//                .into(holder.imageView);
        Picasso.get()
                    .load(img)
                    .placeholder(R.drawable.animation)
                    .into(holder.imageView);

        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
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
