package com.myfutr.myfutr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button login_btn;
    private APIService mAPIService;
    private ProgressBar loadingprogress;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.l_email);
        password = findViewById(R.id.l_pass);
        login_btn = findViewById(R.id.login_btn);
        loadingprogress = findViewById(R.id.loginLoad);

        mAPIService = APIUtils.getAPIService();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = email.getText().toString();
                String pass = password.getText().toString();
                if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pass)){
                    login(username, pass);
                }
            }
        });
        sharedPreferences = getSharedPreferences("myFutr",Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user", "default");
        if(!user.equals("default")){
            Log.i("LOGIN CHECK", user);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
    }

    public void get_signup(View view) {
        startActivity(new Intent(getApplicationContext(), signup.class));
    }

    public void login(final String email, final String password)  {
        loadingprogress.setVisibility(View.VISIBLE);
        userModel user = new userModel(email, password);
        mAPIService.savePost(user).enqueue(new Callback<userModel>() {
            @Override
            public void onResponse(Call<userModel> call, Response<userModel> response) {
                if(response.code() == 200){
                    editor = sharedPreferences.edit();
                    Log.i("LOGIN", String.valueOf(response.body()));
                    Log.i("PHONE", response.body().getPhone());
                    Log.i("EMAIL", String.valueOf(response.body().getEmail()));
                    Log.i("NAME", String.valueOf(response.body().getName()));
                    loadingprogress.setVisibility(View.INVISIBLE);
                    editor.putString("user",email);
                    editor.putString("phone", response.body().getPhone());
                    editor.putString("name", response.body().getName());
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<userModel> call, Throwable t) {
                loadingprogress.setVisibility(View.INVISIBLE);
                Log.i("FAILURE", Objects.requireNonNull(t.getMessage()));
                Toast.makeText(login.this, "Some Error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(count <=1){
            count ++;
            Toast.makeText(this, "Press back to exit application", Toast.LENGTH_SHORT).show();
            Log.i("COUNT", String.valueOf(count));
        } if(count == 2) {
            count = 0;
            finishAffinity();
            finish();
        }
    }
}
