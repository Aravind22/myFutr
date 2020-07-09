package com.myfutr.myfutr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class QuestionAdapter extends ArrayAdapter {
    ArrayList<Question> questionList = new ArrayList();

    public QuestionAdapter(@NonNull Context context, int resource, ArrayList objects) {
        super(context, resource, objects);
        questionList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=layoutInflater.inflate(R.layout.question_grid_style, null);
        TextView txtView = v.findViewById(R.id.gridTxt);
        txtView.setText(questionList.get(position).getQuestion_number().toString());
        return v;
    }
}
