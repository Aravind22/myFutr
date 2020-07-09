package com.myfutr.myfutr;

public class email {
    private String email, personality, quiz_id;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }

    public email(String email, String personality, String quiz_id) {
        this.email = email;
        this.personality = personality;
        this.quiz_id = quiz_id;
    }
}
