package com.example.my_api.Activity;

import static com.example.my_api.Activity.SplashScreen.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_api.Models.LogingData;
import com.example.my_api.R;
import com.example.my_api.Retrofit.Retro_Instance_Class;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_activity extends AppCompatActivity {
    EditText lemail,lpassword;
    TextView fp;
    Button login,sign;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lemail=findViewById(R.id.lemail);
        lpassword=findViewById(R.id.lpassword);
        login=findViewById(R.id.login);
        sign=findViewById(R.id.sign);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loingData();
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login_activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loingData()
    {
        Retro_Instance_Class.MyAPICalling().UserLogin(lemail.getText().toString(),lpassword.getText().toString()).enqueue(new Callback<LogingData>() {
            @Override
            public void onResponse(Call<LogingData> call, Response<LogingData> response)
            {
                Log.d("aaa", "onResponse: "+response.body());
                if(response.body().getConnection()==1&& response.body().getResult()==1)
                {
                    editor.putBoolean("LoginStatus", Boolean.parseBoolean("true"));
                    editor.putString("name",response.body().getUserdata().getName());
                    editor.putString("email",response.body().getUserdata().getEmail());
                    editor.commit();
                    Toast.makeText(Login_activity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login_activity.this,HomePage_Activity.class);
                    startActivity(intent);
                    finish();
                } else if (response.body().getResult()==0) {
                    Toast.makeText(Login_activity.this, "Not Logged in / Register first", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Login_activity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LogingData> call, Throwable t) {
                Log.e("aaa", "onResponse: "+t.getLocalizedMessage());
            }
        });
    }
}