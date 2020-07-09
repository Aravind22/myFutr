package com.myfutr.myfutr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signup extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText phone;
    private EditText password;
    private EditText cpassword;
    private Button register_btn;
    private ProgressBar progressBar;
    private APIService mAPIService;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.pass);
        cpassword = findViewById(R.id.cpass);
        register_btn = findViewById(R.id.register_btn);
        progressBar = findViewById(R.id.signupLoad);
        mAPIService = APIUtils.getAPIService();
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate(password.getText().toString(), cpassword.getText().toString())){
                    signup(name.getText().toString(), email.getText().toString(), password.getText().toString(), phone.getText().toString());
                } else {
                    Toast.makeText(signup.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        sharedPreferences = getSharedPreferences("myFutr", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public boolean validate(String password, String cpassword){
        if(password.equals(cpassword)){
            return true;
        } else {
            return false;
        }
    }

    public void signup(final String name, final String email, String password, final String phone){
        progressBar.setVisibility(View.VISIBLE);
        userModel user = new userModel(name, email, password, phone);
        mAPIService.signup(user).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Log.i("SIGNUP", response.body().toString());
                if(response.code() == 200){
                    progressBar.setVisibility(View.INVISIBLE);
                    editor.putString("user", email);
                    editor.putString("name", name);
                    editor.putString("phone", phone);
                    editor.commit();
                    Toast.makeText(signup.this, "Welcome "+name, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.i("SIGNUP", t.getMessage());
                Toast.makeText(signup.this, "Failure in getting response", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void get_login(View view) {
        startActivity(new Intent(getApplicationContext(), login.class));
    }

}
