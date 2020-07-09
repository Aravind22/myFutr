package com.myfutr.myfutr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
    @SerializedName("option")
    @Expose
    private List<String> option = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("option_count")
    @Expose
    private Integer optionCount;
    @SerializedName("quiz_id")
    @Expose
    private String quizId;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("question_number")
    @Expose
    private Integer question_number;

    public void setQuestion_number(Integer question_number) {
        this.question_number = question_number;
    }

    public List<String> getOption() {
        return option;
    }

    public void setOption(List<String> option) {
        this.option = option;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getOptionCount() {
        return optionCount;
    }

    public void setOptionCount(Integer optionCount) {
        this.optionCount = optionCount;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public Integer getQuestion_number() { return question_number; }

    public Question(String quizId) {
        this.quizId = quizId;
    }

    public Question(List<String> option, String id, String question, Integer optionCount, String quizId, Integer v, Integer question_number) {
        this.option = option;
        this.id = id;
        this.question = question;
        this.optionCount = optionCount;
        this.quizId = quizId;
        this.v = v;
        this.question_number = question_number;
    }
}
