package com.example.my_api.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.my_api.Fragment.Add_Product;
import com.example.my_api.Fragment.View_Product;
import com.example.my_api.Fragment.All_Product;
import com.example.my_api.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;

public class HomePage_Activity extends AppCompatActivity {
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    TextView txt1,txt2;
    String email,name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        appBarLayout = findViewById(R.id.appbar);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawerLayout);

        View view=navigationView.getHeaderView(0);
        txt1=view.findViewById(R.id.txt1);
        txt2=view.findViewById(R.id.txt2);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        email=SplashScreen.preferences.getString("email","");
        name=SplashScreen.preferences.getString("name","");

        txt2.setText(""+name);
        txt1.setText(""+email);



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.add_product)
                {
                    replaceFragment(new Add_Product());
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                if (item.getItemId()==R.id.view_product)
                {
                    replaceFragment(new View_Product());
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                if (item.getItemId()==R.id.all_product)
                {
                    replaceFragment(new All_Product());
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                if (item.getItemId()==R.id.log_out)
                {
                    Intent intent=new Intent(HomePage_Activity.this,Login_activity.class);
                    startActivity(intent);
                    finish();
                }
                return true;
            }
        });



    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }
}