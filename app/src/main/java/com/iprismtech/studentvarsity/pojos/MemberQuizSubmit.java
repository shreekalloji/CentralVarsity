package com.iprismtech.studentvarsity.pojos;

import org.json.JSONObject;

import java.util.List;

public class MemberQuizSubmit {

    private String token;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getQuizs_id() {
        return quizs_id;
    }

    public void setQuizs_id(String quizs_id) {
        this.quizs_id = quizs_id;
    }


    private String user_id;
    private String quizs_id;

    public List<JSONObject> getAnswers() {
        return answers;
    }

    public void setAnswers(List<JSONObject> answers) {
        this.answers = answers;
    }

    private List<JSONObject> answers;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
