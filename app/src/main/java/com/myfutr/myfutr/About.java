package com.myfutr.myfutr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class About extends AppCompatActivity {
    private TextView name, email, phone;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("myFutr", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_about);
        name = findViewById(R.id.profile_name);
        email = findViewById(R.id.profile_email);
        phone = findViewById(R.id.profile_phone);
        BottomNavigationView bottomNavigationView = findViewById(R.id.btm_navigation);
        bottomNavigationView.setSelectedItemId(R.id.about);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        overridePendingTransition(0,0);
                        return true;
                    case  R.id.about:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                }
                return false;
            }
        });
        String user = sharedPreferences.getString("user", "john@doe.com");
        if(user != "default"){
            name.setText(sharedPreferences.getString("name", "John Doe"));
            email.setText(user);
            phone.setText(sharedPreferences.getString("phone", "123123123"));
        }
    }

    public void logout(View view) {
        editor = sharedPreferences.edit();
        editor.putString("name", "default");
        editor.putString("user", "default");
        editor.putString("phone", "default");
        editor.apply();
        finish();
        startActivity(new Intent(getApplicationContext(), login.class));
    }
}
