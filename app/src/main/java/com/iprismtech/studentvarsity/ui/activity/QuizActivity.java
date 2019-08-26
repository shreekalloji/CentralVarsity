package com.iprismtech.studentvarsity.ui.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.ExpandableListViewAdapter;
import com.iprismtech.studentvarsity.adapters.QuizActvityAdapter;
import com.iprismtech.studentvarsity.adapters.QuizsAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.activity.NotesActivityContract;
import com.iprismtech.studentvarsity.mvp.contract.activity.QuizActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.NotesActivitylmpl;
import com.iprismtech.studentvarsity.mvp.presenter.activity.QuizActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.QuizsListPOJO;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;
import com.iprismtech.studentvarsity.utils.Variables;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizActivity extends BaseAbstractActivity<QuizActivitylmpl> implements QuizActivityContract.IView, APIResponseCallback {
    APIResponseCallback apiResponseCallback;
    RecyclerView recycle_quizlist;
    ImageView im_back;

    private RecyclerView rv_quizs;
    private QuizsAdapter adapter;
    private ExpandableListViewAdapter expandableListViewAdapter;
    private ExpandableListView expandableListView;
    private QuizsListPOJO departmentPOJO;
    private List<QuizsListPOJO.ResponseBean> quizList;
    private List<String> parentHeaderInformation;
    private HashMap<String, List<String>> allChildItems = new HashMap<>();
    private TextView tv_title;
    String subtitle;
    private NestedScrollView ll_nested_scroll_quiz;

    private String token, KEY_EDUCATION_ID, KEY_YEARS, KEY_STREAM_ID, KEY_SUBJECTS, KEY_USERID, KEY_UserName, KEY_MOBILENO, KEY_PROFILE, KEY_UNIVERSITY, KEY_COUNTRY_ID, KEY_CITY_ID, KEY_BIO;

    private String chapter_id, subject_id;
    private boolean response_status = false;
    private int page_number = 0;
    String topic;
    int read_count, topic_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        //   setContentView(R.layout.activity_quiz);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        apiResponseCallback = this;
        if (getIntent().getExtras() != null) {
            chapter_id = getIntent().getStringExtra("chapter_id");
            subject_id = getIntent().getStringExtra("subject_id");
            topic = getIntent().getStringExtra("topic");
            read_count = getIntent().getIntExtra("read_count", 0);
            topic_count = getIntent().getIntExtra("topic_count", 0);
            //do here
        }


        rv_quizs = findViewById(R.id.rv_quizs);
        tv_title = findViewById(R.id.tv_title);
        im_back = findViewById(R.id.im_back);
        tv_title.setText(topic);
        ll_nested_scroll_quiz = findViewById(R.id.ll_nested_scroll_quiz);


        apiResponseCallback = this;
        tv_title.setText(topic + "(" + read_count + "/" + topic_count + ")");

        userSession = new UserSession(this);
        token = userSession.getUserDetails().get("token");
        KEY_EDUCATION_ID = userSession.getUserDetails().get("education_id");
        KEY_STREAM_ID = userSession.getUserDetails().get("stream_id");
        KEY_YEARS = userSession.getUserDetails().get("years");
        KEY_SUBJECTS = userSession.getUserDetails().get("subjects");
        KEY_USERID = userSession.getUserDetails().get("id");
        KEY_UserName = userSession.getUserDetails().get("name");
        KEY_MOBILENO = userSession.getUserDetails().get("mobileno");
        KEY_PROFILE = userSession.getUserDetails().get("profileImg");
        KEY_UNIVERSITY = userSession.getUserDetails().get("university");
        KEY_COUNTRY_ID = userSession.getUserDetails().get("country_id");
        KEY_CITY_ID = userSession.getUserDetails().get("city_id");
        KEY_BIO = userSession.getUserDetails().get("bio");


        quizList = new ArrayList<>();
        callQuizFiltedWs();

        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

/*
        if (KEY_USERID != null) {

//            {
//                "token":
//                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
//                        "education_id":"3",
//                    "stream_id":"4",
//                    "subject_ids":"1,2,3"
//            }

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            requestBody.put("user_id", KEY_USERID);
            requestBody.put("education_id", KEY_EDUCATION_ID);
            requestBody.put("stream_id", KEY_STREAM_ID);
            requestBody.put("subject_ids", KEY_SUBJECTS);
            requestBody.put("count", String.valueOf(page_number));

           */
/* if (Variables.search_text != null) {
                requestBody.put("search", Variables.search_text);
            }*//*

            presenter.quizs(getActivity(), apiResponseCallback, requestBody);
        }
*/


    }

    private void callQuizFiltedWs() {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("education_id", KEY_EDUCATION_ID);
        requestBody.put("stream_id", KEY_STREAM_ID);
        requestBody.put("subject_ids", subject_id);
        requestBody.put("chapter_id", chapter_id);
        presenter.userRegister(this, apiResponseCallback, requestBody);

    }

    private void perfirmPagination() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("education_id", KEY_EDUCATION_ID);
        requestBody.put("stream_id", KEY_STREAM_ID);
        requestBody.put("subject_ids", subject_id);
        requestBody.put("chapter_id", " ");
        requestBody.put("count", String.valueOf(page_number));

        if (Variables.search_text != null) {
            requestBody.put("search", Variables.search_text);
        }
        presenter.userRegister(context, apiResponseCallback, requestBody);

    }


    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_quiz, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new QuizActivitylmpl(this, this);

    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, context);
            } else if (NetworkConstants.RequestCode.QUIZS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    rv_quizs.setVisibility(View.VISIBLE);
                    response_status = true;
                    Util.getInstance().cusToast(context, jsonObject.optString("message"));
                    departmentPOJO = new Gson().fromJson(responseString, QuizsListPOJO.class);

                    quizList.addAll(departmentPOJO.getResponse());


                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
                    //setting horizontal list
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rv_quizs.setLayoutManager(mLayoutManager);
                    rv_quizs.setItemAnimator(new DefaultItemAnimator());

                    //Adding Adapter to recyclerView
                    adapter = new QuizsAdapter(quizList, context);
                    rv_quizs.setAdapter(adapter);


                    if (response_status) {
                        ll_nested_scroll_quiz.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                            @Override
                            public void onScrollChanged() {
                                View view = (View) ll_nested_scroll_quiz.getChildAt(ll_nested_scroll_quiz.getChildCount() - 1);

                                int diff = (view.getBottom() - (ll_nested_scroll_quiz.getHeight() + ll_nested_scroll_quiz
                                        .getScrollY()));

                                if (diff == 0) {
                                    // your pagination code
                                    page_number += 1;
                                    if (response_status) {
                                        perfirmPagination();
                                    }
                                }
                            }
                        });
                    } else {
                        ll_nested_scroll_quiz.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                            @Override
                            public void onScrollChanged() {
                                View view = (View) ll_nested_scroll_quiz.getChildAt(ll_nested_scroll_quiz.getChildCount() - 1);

                                int diff = (view.getBottom() - (ll_nested_scroll_quiz.getHeight() + ll_nested_scroll_quiz
                                        .getScrollY()));

                                if (diff == 0) {
                                    // your pagination cod
                                }
                            }
                        });
                    }

                    adapter.setOnItemClickListener(new QuizsAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            switch (view.getId()) {
                                case R.id.ll_quiz:
                                    if (quizList.get(position).getTotal_questions() > 0) {

                                        Bundle bundle2 = new Bundle();
                                        bundle2.putString("id", quizList.get(position).getId());
                                        bundle2.putString("right_count", String.valueOf(quizList.get(position).getRight_answers()));
                                        bundle2.putString("total_count", String.valueOf(quizList.get(position).getTotal_questions()));
                                        bundle2.putString("quiz_name", quizList.get(position).getTitle());
                                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_QUESTIONRIGHTWRONG_SCREEN, bundle2);

                                    } else if (quizList.get(position).getQuestions_count() == 0) {
                                        Toast.makeText(context, "No questions to Attempt ", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Bundle bundle2 = new Bundle();
                                        bundle2.putString("quizs_id", quizList.get(position).getId());
                                        bundle2.putString("questions_count", String.valueOf(quizList.get(position).getQuestions_count()));
                                        bundle2.putString("quiz_name", quizList.get(position).getTitle());
                                        bundle2.putString("quiz_time", quizList.get(position).getTime());

                                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_QUIZ_LIST_SCREEN, bundle2);
                                    }
                                    break;
                            }

                        }
                    });

                } else {

                    response_status = false;
                    if (page_number == 0) {
                        rv_quizs.setVisibility(View.GONE);
                    }

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }


}
