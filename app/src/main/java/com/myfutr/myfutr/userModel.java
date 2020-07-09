package com.myfutr.myfutr;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class userModel {
    private String email, password, name, quizId, personality;
    private String phone;
    private ArrayList<String> ans;
    private String Qversion;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.email = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getAns() { return ans; }

    public void setAns(ArrayList<String> ans) { this.ans = ans; }

    public String getQversion() { return Qversion; }

    public void setQversion(String qversion) { Qversion = qversion; }

    public String getQuizId() { return quizId; }

    public void setQuizId(String quizId) { this.quizId = quizId; }

    public String getPersonality() { return personality; }

    public void setPersonality(String personality) { this.personality = personality; }

    public userModel(String name, String password) {
        this.email = name;
        this.password = password;
    }

    public userModel(String name, String email, String password, String phone){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public userModel(String email, ArrayList<String> ans, String qversion) {
        this.email = email;
        this.ans = ans;
        Qversion = qversion;
    }
    public userModel(String name, String email, String phone){
        this.name = name;
        this.email = email;
        this.phone = phone;
    }



    @NonNull
    @Override
    public String toString() {
        return name+":"+email+":"+phone;
    }
}
