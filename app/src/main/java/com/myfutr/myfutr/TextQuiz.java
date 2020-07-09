package com.myfutr.myfutr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class TextQuiz extends AppCompatActivity {
    private GridView QGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        Button proceed = findViewById(R.id.proceedIns);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initQuiz();
            }
        });
    }

    public void initQuiz(){
        setContentView(R.layout.activity_text_quiz);
        Intent i = getIntent();
        final ArrayList<Question> q = (ArrayList<Question>) i.getSerializableExtra("questions");
        QGrid = findViewById(R.id.questions_grid);
        QuestionAdapter qadapter = new QuestionAdapter(this, R.layout.question_grid_style, q);
        QGrid.setAdapter(qadapter);
        QGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent Qint = new Intent(TextQuiz.this, Quiz.class);
                Qint.putExtra("question", q.get(position));
                Qint.putExtra("quiz", q);
                startActivity(Qint);
            }
        });
    }
}
