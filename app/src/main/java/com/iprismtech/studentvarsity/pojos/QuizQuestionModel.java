package com.iprismtech.studentvarsity.pojos;

import java.util.ArrayList;

public class QuizQuestionModel {

    private String id;
    private String education_id;
    private String stream_id;
    private String subject_id;
    private String chapter_id;
    private String quizs_id;
    private String question;



    public int getQuestion_number() {
        return question_number;
    }

    public void setQuestion_number(int question_number) {
        this.question_number = question_number;
    }

    private int question_number;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    private String answer;

    public ArrayList<String> getArroptions() {
        return arroptions;
    }

    public void setArroptions(ArrayList<String> arroptions) {
        this.arroptions = arroptions;
    }

    private ArrayList<String> arroptions;

    public ArrayList<String> getArroptionsid() {
        return arroptionsid;
    }

    public void setArroptionsid(ArrayList<String> arroptionsid) {
        this.arroptionsid = arroptionsid;
    }

    private ArrayList<String> arroptionsid;

    public ArrayList<String> getArroptionsquiz_id() {
        return arroptionsquiz_id;
    }

    public void setArroptionsquiz_id(ArrayList<String> arroptionsquiz_id) {
        this.arroptionsquiz_id = arroptionsquiz_id;
    }

    private ArrayList<String> arroptionsquiz_id;

    public ArrayList<String> getArroptionsselected() {
        return arroptionsselected;
    }

    public void setArroptionsselected(ArrayList<String> arroptionsselected) {
        this.arroptionsselected = arroptionsselected;
    }

    private ArrayList<String> arroptionsselected;

    public QuizQuestionModel() {
    }


    public QuizQuestionModel(String quizs_id, String question, String answer, ArrayList<String> arroptions, ArrayList<String> arroptionsid, ArrayList<String> arroptionsquiz_id, ArrayList<String> arroptionsselected,int question_number,String subject_id,String chapter_id) {
        this.quizs_id = quizs_id;
        this.question = question;
        this.answer = answer;
        this.arroptions = new ArrayList<>();
        this.arroptions = arroptions;
        this.arroptionsid = new ArrayList<>();
        this.arroptionsid = arroptionsid;
        this.arroptionsquiz_id = new ArrayList<>();
        this.arroptionsquiz_id = arroptionsquiz_id;
        this.arroptionsselected = arroptionsselected;
        this.question_number = question_number;
        this.subject_id = subject_id;
        this.chapter_id = chapter_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEducation_id() {
        return education_id;
    }

    public void setEducation_id(String education_id) {
        this.education_id = education_id;
    }

    public String getStream_id() {
        return stream_id;
    }

    public void setStream_id(String stream_id) {
        this.stream_id = stream_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(String chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getQuizs_id() {
        return quizs_id;
    }

    public void setQuizs_id(String quizs_id) {
        this.quizs_id = quizs_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public QuizQuestionModel(String id, String education_id, String stream_id, String subject_id, String chapter_id, String quizs_id, String question) {
        this.id = id;
        this.education_id = education_id;
        this.stream_id = stream_id;
        this.subject_id = subject_id;
        this.chapter_id = chapter_id;
        this.quizs_id = quizs_id;
        this.question = question;
    }

    public QuizQuestionModel(String quizs_id, String question) {
        this.quizs_id = quizs_id;
        this.question = question;
    }

}
