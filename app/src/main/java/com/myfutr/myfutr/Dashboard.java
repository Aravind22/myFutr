package com.myfutr.myfutr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity {
    private CardView testCard;
    private ProgressBar progressBar;
    private APIService mAPIService;
    ArrayList<Question> quest_resp = new ArrayList<Question>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        BottomNavigationView bottomNavigationView = findViewById(R.id.btm_navigation);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        progressBar = findViewById(R.id.dashboardLoad);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboard:
                        return true;
                    case  R.id.about:
                        startActivity(new Intent(getApplicationContext(), About.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                }
                return false;
            }
        });
        testCard = findViewById(R.id.testCard);

        testCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                mAPIService = APIUtils.getAPIService();
                Question question = new Question("s1");
                mAPIService.getQuestions(question).enqueue(new Callback<List<Question>>() {
                    @Override
                    public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                        Log.i("QUESTION RESP", String.valueOf(response.body()));
                        for(int i=0;i<response.body().size();i++){
                            quest_resp.add(response.body().get(i));
                        }
                        if(quest_resp.size()>0){
                            progressBar.setVisibility(View.GONE);
                            Intent i = new Intent(getApplicationContext(), TextQuiz.class);
                            i.putExtra("questions", quest_resp);
                            startActivity(i);
                        }
                        for(int i=0;i<quest_resp.size();i++){
                            Log.i("QUESTION", quest_resp.get(i).getQuestion());
                        }
                        quest_resp = null;
                    }

                    @Override
                    public void onFailure(Call<List<Question>> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Log.i("ERROR", t.getMessage());
                        Toast.makeText(Dashboard.this, "Some error occurred", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
