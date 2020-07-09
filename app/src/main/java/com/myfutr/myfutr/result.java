package com.myfutr.myfutr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class result extends AppCompatActivity {
    private APIService mAPIService;
    private Button subscribe;
    private ProgressBar progressBar;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;
    private String personality;
    private static final ArrayList<String> personalityList = new ArrayList<>(Arrays.asList("Conventional", "Realistic", "Investigative", "Artistic", "Social", "Enterprising"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mAPIService = APIUtils.getAPIService();
        subscribe = findViewById(R.id.subscribe);
        progressBar = findViewById(R.id.resultLoad);
        sharedPreferences = getSharedPreferences("myFutr", Context.MODE_PRIVATE);
        Intent intent = getIntent();
        String personality_index = intent.getStringExtra("personality_index");
        Log.i("PERSONALITY_INDEX", personality_index);
        personality = personalityList.get(Integer.parseInt(personality_index));

        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String user = sharedPreferences.getString("user", "default");
                if(user != "default"){
                    Log.i("USER", user);
                    email em = new email(user, personality, "s1");
                    mAPIService.sendResp(em).enqueue(new Callback<Token>() {
                        @Override
                        public void onResponse(Call<Token> call, Response<Token> response) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(result.this, "Your response is successfully mailed to you!", Toast.LENGTH_LONG).show();
//                            startActivity(new Intent(result.this, MainActivity.class));
                        }

                        @Override
                        public void onFailure(Call<Token> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(result.this, "Some error occurred", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
