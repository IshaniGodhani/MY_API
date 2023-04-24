package com.example.my_api.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_api.Models.RegisterData;
import com.example.my_api.R;
import com.example.my_api.Retrofit.Retro_Instance_Class;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText name,email,password;
    TextView rlogin;
    Button register;

    String Name,Email,Password;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);
//        rlogin=findViewById(R.id.rlogin);

//        rlogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,Login_activity.class);
//                startActivity(intent);
//            }
//        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitApiCalling();
            }
        });



    }
    private void RetrofitApiCalling()
    {
        Name=name.getText().toString();
        Email=email.getText().toString();
        Password=password.getText().toString();
        if (name.getText().toString().equals("") && email.getText().toString().equals("") && password.getText().toString().equals(""))
        {
            name.setError("Enter name");
            email.setError("Enter email");
            password.setError("Enter password");
        } else if (name.getText().toString().equals("")) {
            name.setError("Enter name");
        } else if ( email.getText().toString().equals("")) {
            email.setError("Enter email");
        } else if (password.getText().toString().equals("")) {
            password.setError("Enter password");
        }
        else
        {
            Retro_Instance_Class.MyAPICalling().UserRegister(Name,Email,Password).enqueue(new Callback<RegisterData>() {
                @Override
                public void onResponse(Call<RegisterData> call, Response<RegisterData> response) {
                    if (response.body().getConnection() == 1) {
                        if (response.body().getResult() == 1) {
                            Toast.makeText(MainActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                        } else if (response.body().getResult() == 2) {
                            Toast.makeText(MainActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<RegisterData> call, Throwable t) {

                }
            });
        }

    }
}