package com.example.my_api.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.my_api.R;

public class SplashScreen extends AppCompatActivity {

    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    Boolean isLogin=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        preferences=getSharedPreferences("myPref",MODE_PRIVATE);
        editor=preferences.edit();
        isLogin=preferences.getBoolean("LoginStatus",false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                System.out.println("isLogin"+isLogin);
                if(isLogin)
                {
                    Intent intent =new Intent(SplashScreen.this,HomePage_Activity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(SplashScreen.this, Login_activity.class);
                    startActivity(intent);
                }
                finish();
            }
        },1000);
    }
}