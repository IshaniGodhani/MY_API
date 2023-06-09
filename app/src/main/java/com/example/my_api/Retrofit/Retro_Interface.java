package com.example.my_api.Retrofit;

import com.example.my_api.Models.DeleteData;
import com.example.my_api.Models.LogingData;
import com.example.my_api.Models.ProductData;
import com.example.my_api.Models.RegisterData;
import com.example.my_api.Models.ViewProductData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Retro_Interface
{
    @FormUrlEncoded
    @POST("Register.php")
    Call<RegisterData> UserRegister(@Field("name") String name, @Field("email") String email, @Field("password") String password);
    @FormUrlEncoded
    @POST("Login.php")
    Call<LogingData> UserLogin(@Field("email") String email, @Field("password")String password);

    @FormUrlEncoded
    @POST("addProduct.php")
    Call<ProductData> addproduct(@Field("userid")String userId,@Field("pname") String productName ,@Field("pprize") String productPrice,@Field("pdes") String productDes,@Field("productimage") String productImage);

    @FormUrlEncoded
    @POST("viewData.php")
    Call<ViewProductData> viewProduct(@Field("userid")String userId);

    @GET("viewallData.php")
    Call<ViewProductData> viewallProduct();

    @FormUrlEncoded
    @POST("deleteproduct.php")
    Call<DeleteData> deleteProduct(@Field("id")String Id);

    @FormUrlEncoded
    @POST("updateproduct.php")
    Call<DeleteData> updateProduct(@Field("id") String id,@Field("name") String name,@Field("price") String price,@Field("description") String description,@Field("imagedata") String imagedata,@Field("imagename") String imagename);




}
