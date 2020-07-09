package com.myfutr.myfutr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Quiz extends AppCompatActivity {
    private TextView question;
    private Button option1, option2, option3, option4, option5,next, skip;
    Question ques = null;
    ArrayList<Question> quiz= null;
    public static ArrayList<String> answers = new ArrayList<String>();
    private APIService mAPIService;
    SharedPreferences sharedPreferences ;
    private ArrayList<Integer> ansList = new ArrayList<>();
    private static ArrayList<Integer> ansSumList = new ArrayList<>(Arrays.asList(0,0,0,0,0,0));
    private int ans = 1;
    private int quesID = 1;
    private static boolean isAnswered = false;
    private static Button clickedbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
         sharedPreferences = getSharedPreferences("myFutr", Context.MODE_PRIVATE);
        mAPIService = APIUtils.getAPIService();
        question = findViewById(R.id.QuizQuestion);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        option5 = findViewById(R.id.option5);
        next = findViewById(R.id.next);

        Intent intent = getIntent();
        ques = (Question) intent.getSerializableExtra("question");
        quiz = (ArrayList<Question>)  intent.getSerializableExtra("quiz");
        ArrayList<String> options = (ArrayList<String>) ques.getOption();
        if(ques.getQuestion_number() == quiz.size()){
            next.setText(getResources().getString(R.string.finish));
        }
        question.setText(ques.getQuestion());

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int thisQ = ques.getQuestion_number();
                setAnswer(option1, thisQ,1);
                answers.add(thisQ + ":1");
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int thisQ = ques.getQuestion_number();
                setAnswer(option2, thisQ,2);
                answers.add(thisQ + ":2");
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int thisQ = ques.getQuestion_number();
                setAnswer(option3, thisQ,3);
                answers.add(thisQ + ":3");
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int thisQ = ques.getQuestion_number();
                setAnswer(option4, thisQ,4);
                answers.add(thisQ + ":4");
            }
        });

        option5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int thisQ = ques.getQuestion_number();
                setAnswer(option5, thisQ,5);
                answers.add(thisQ + ":5");
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indes = quesID%6;
                ansSumList.set(indes, ansSumList.get(indes)+ans);
                int thisQ = ques.getQuestion_number();
                if(quiz.size() != thisQ){
                    Intent Qint = new Intent(Quiz.this, Quiz.class);
                    Qint.putExtra("question", quiz.get(thisQ));
                    Qint.putExtra("quiz", quiz);
                    startActivity(Qint);
                }
                if(ques.getQuestion_number() == quiz.size()){
                    Log.i("ANSWER", String.valueOf(answers));
                    String _user = sharedPreferences.getString("user", "default");
                    if(!_user.equals("default")){
                        userModel userResp = new userModel(_user, answers, ques.getQuizId());
                        mAPIService.saveResponse(userResp).enqueue(new Callback<Token>() {
                            @Override
                            public void onResponse(Call<Token> call, Response<Token> response) {
                                Log.i("SUMLIST", String.valueOf(ansSumList));
                                Toast.makeText(Quiz.this, "Response Saved", Toast.LENGTH_SHORT).show();
                                Intent resultInt = new Intent(Quiz.this, result.class);
                                resultInt.putExtra("personality_index",String.valueOf(ansSumList.indexOf(Collections.max(ansSumList))));
                                startActivity(resultInt);
                            }

                            @Override
                            public void onFailure(Call<Token> call, Throwable t) {
                                Toast.makeText(Quiz.this, "Failed to get Response", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });
    }

    public void setAnswer(Button button, int _quesID, int _ans){
        ans = _ans;
        quesID = _quesID;
        if(clickedbutton != null){
            clickedbutton.setBackground(getResources().getDrawable(R.drawable.round_txtview));
        }
        clickedbutton = button;
        button.setBackground(getResources().getDrawable(R.drawable.round_valid_txtview));
    }
}
