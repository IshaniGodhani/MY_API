package com.example.my_api.Retrofit;

import com.example.my_api.Models.RegisterData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Retro_Interface {
    @FormUrlEncoded
    @POST("Register.php")
    Call<RegisterData> UserRegister(@Field("name") String name, @Field("email") String email, @Field("password") String password);
}
