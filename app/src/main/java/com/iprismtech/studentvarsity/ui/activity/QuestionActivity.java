package com.iprismtech.studentvarsity.ui.activity;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.PaginationAdapterForQuizlist;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.activity.QuestionActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.QuestionActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.MemberQuizSubmit;
import com.iprismtech.studentvarsity.pojos.QuizQuestionModel;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.PaginationScrollListener;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class QuestionActivity extends BaseAbstractActivity<QuestionActivitylmpl> implements QuestionActivityContract.IView, View.OnClickListener, APIResponseCallback {

    private static final String TAG = "QuestionActivity";

    PaginationAdapterForQuizlist adapter;
    LinearLayoutManager linearLayoutManager;

    RecyclerView rv;
    ProgressBar progressBar;
    TextView tv_next, tv_prev, tv_exam_title;
    TextView tv_countdown;


    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 5;
    private int currentPage = PAGE_START;

    APIResponseCallback apiResponseCallback;
    Bundle bundle;
    String quizs_id;

    String abc = "1";

    LinearLayout ll_next;

    public static HashMap<Integer, String> useranswers = new HashMap<>();
    public static HashMap<Integer, String> correctanswers = new HashMap<>();
    public static HashMap<Integer, String> arrquiz_id = new HashMap<>();
    public static HashMap<Integer, String> arroption_id = new HashMap<>();
    public static HashMap<Integer, String> arranswer_status = new HashMap<>();

    private UserSession userSession;
    private String token, user_id, education_id, stream_id, subjects;

    //Declare timer
    CountDownTimer cTimer = null;
    private String questions_count, quiz_name, quiz_time;
    private long time_in_mills;
    public int questions_count_int;


    //start timer function
    void startTimer() {
        cTimer = new CountDownTimer(time_in_mills, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                //String hms = String.format("%02d:%02d:%02d",TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                tv_countdown.setText(hms);

                // tv_countdown.setText(""+millisUntilFinished / 1000);
            }

            public void onFinish() {

                if (token != null || !token.isEmpty()) {
                    JSONArray contactsArray = new JSONArray();
                    List<JSONObject> bjhgjg = new ArrayList<>();

                    // for (int i = 0; i < PaginationAdapterForQuizlist.movies.size(); i++) {
                    for (int i = 0; i < arrquiz_id.size(); i++) {

                        try {
                            JSONObject jsonObject = new JSONObject();
                            String quiz_idmap = arrquiz_id.get(i);
                            String option_idmap = arroption_id.get(i);
                            String answermap = useranswers.get(i);
                            String correct_answermap = correctanswers.get(i);
                            String answer_statusmap = arranswer_status.get(i);

                            jsonObject.put("quiz_id", quiz_idmap);
                            jsonObject.put("option_id", option_idmap);
                            jsonObject.put("answer", answermap);
                            jsonObject.put("correct_answer", correct_answermap);
                            jsonObject.put("answer_status", answer_statusmap);

                            contactsArray.put(jsonObject);
                            bjhgjg.add(jsonObject);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    JSONObject jsonObject_requestt = new JSONObject();
                    try {
                        jsonObject_requestt.put("token", token);
                        jsonObject_requestt.put("user_id", user_id);
                        jsonObject_requestt.put("quizs_id", quizs_id);
                        jsonObject_requestt.put("answers", contactsArray);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    MemberQuizSubmit member = new MemberQuizSubmit();
                    member.setToken(token);
                    member.setUser_id(user_id);
                    member.setQuizs_id(quizs_id);
                    member.setAnswers(bjhgjg);
                    Gson gson = new Gson();
                    String json = gson.toJson(member);

//                        Map<String, String> requestBody = new HashMap<>();
//                        requestBody.put("token", token);
//                        //  requestBody.put("mobile_numbers", selectUsersphone.toString());
//                        requestBody.put("mobile_numbers", json);

                    presenter.submit_quiz_answers(QuestionActivity.this, apiResponseCallback, jsonObject_requestt);
                }
            }

        };
        cTimer.start();
    }


    //cancel timer
    void cancelTimer() {
        if (cTimer != null)
            cTimer.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        // setContentView(R.layout.activity_question);
    }


    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_question, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new QuestionActivitylmpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();


        userSession = new UserSession(QuestionActivity.this);


        apiResponseCallback = this;

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        education_id = userSession.getUserDetails().get("education_id");
        stream_id = userSession.getUserDetails().get("stream_id");
        subjects = userSession.getUserDetails().get("subjects");

        bundle = getIntent().getExtras();
        if (bundle != null) {
            quizs_id = bundle.getString("quizs_id");
            questions_count = bundle.getString("questions_count");
            quiz_name = bundle.getString("quiz_name");
            quiz_time = bundle.getString("quiz_time");
        }

        int hours = Integer.parseInt(quiz_time.substring(0, 2));
        long hours_mills = hours * 60 * 60 * 1000;

        int min = Integer.parseInt(quiz_time.substring(3, 5));
        long min_mills = min * 60 * 1000;
        int sec = Integer.parseInt(quiz_time.substring(6, 8));
        long sec_mills = sec * 1000;

        time_in_mills = hours_mills + min_mills + sec_mills;

        ll_next = findViewById(R.id.ll_next);
        tv_next = findViewById(R.id.tv_next);
        tv_prev = findViewById(R.id.tv_prev);
        tv_countdown = findViewById(R.id.tv_countdown);
        tv_exam_title = findViewById(R.id.tv_exam_title);
        tv_exam_title.setText(quiz_name);


        questions_count_int = Integer.parseInt(questions_count);

        TOTAL_PAGES = questions_count_int / 40;
//        ll_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(QuestionActivity.this, QestioncontinuationActivity.class);
//                startActivity(intent);
//            }
//        });


        rv = (RecyclerView) findViewById(R.id.rv_quizquestion);
        progressBar = (ProgressBar) findViewById(R.id.main_progress);

        adapter = new PaginationAdapterForQuizlist(this, questions_count_int);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        linearLayoutManager = new LinearLayoutManager(this){
//            @Override
//            public boolean canScrollHorizontally() {
//                return false;
//            }
//
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }
//        };
        rv.setLayoutManager(linearLayoutManager);

        rv.setItemAnimator(new DefaultItemAnimator());

//        RecyclerView.OnItemTouchListener disabler = new RecyclerViewDisabler();
//
//        rv.addOnItemTouchListener(disabler);        // disables scolling
// do stuff while scrolling is disabled
        //   rv.removeOnItemTouchListener(disabler);

        rv.setAdapter(adapter);


        adapter.setOnItemClickListener(new PaginationAdapterForQuizlist.OnitemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

//                if (position == questions_count_int-1) {
//                   TextView textView=view.findViewById(R.id.tv_next);
//                    textView.setText("Submit");
//                }
                switch (view.getId()) {
                    case R.id.ll_opt1:
                        useranswers.put(position, "A");
                        correctanswers.put(position, PaginationAdapterForQuizlist.movies.get(position).getAnswer());
                        arrquiz_id.put(position, PaginationAdapterForQuizlist.movies.get(position).getArroptionsquiz_id().get(0));
                        arroption_id.put(position, PaginationAdapterForQuizlist.movies.get(position).getArroptionsid().get(0));

                        if (PaginationAdapterForQuizlist.movies.get(position).getAnswer().equalsIgnoreCase("A")) {
                            arranswer_status.put(position, "correct");
                        } else {
                            arranswer_status.put(position, "wrong");
                        }

                        for (int i = 0; i < PaginationAdapterForQuizlist.arroptionsselected.size(); i++) {
                            if (i == 0) {
                                PaginationAdapterForQuizlist.arroptionsselected.set(0, "1");
                            } else {
                                PaginationAdapterForQuizlist.arroptionsselected.set(i, "0");
                            }
                        }
                        adapter.notifyDataSetChanged();

                        break;
                    case R.id.ll_opt2:
                        useranswers.put(position, "B");
                        correctanswers.put(position, PaginationAdapterForQuizlist.movies.get(position).getAnswer());
                        arrquiz_id.put(position, PaginationAdapterForQuizlist.movies.get(position).getArroptionsquiz_id().get(1));
                        arroption_id.put(position, PaginationAdapterForQuizlist.movies.get(position).getArroptionsid().get(1));

                        if (PaginationAdapterForQuizlist.movies.get(position).getAnswer().equalsIgnoreCase("B")) {
                            arranswer_status.put(position, "correct");
                        } else {
                            arranswer_status.put(position, "wrong");
                        }

                        for (int i = 0; i < PaginationAdapterForQuizlist.arroptionsselected.size(); i++) {
                            if (i == 1) {
                                PaginationAdapterForQuizlist.arroptionsselected.set(1, "1");
                            } else {
                                PaginationAdapterForQuizlist.arroptionsselected.set(i, "0");
                            }
                        }
                        adapter.notifyDataSetChanged();

                        break;
                    case R.id.ll_opt3:

                        useranswers.put(position, "C");
                        correctanswers.put(position, PaginationAdapterForQuizlist.movies.get(position).getAnswer());
                        arrquiz_id.put(position, PaginationAdapterForQuizlist.movies.get(position).getArroptionsquiz_id().get(2));
                        arroption_id.put(position, PaginationAdapterForQuizlist.movies.get(position).getArroptionsid().get(2));

                        if (PaginationAdapterForQuizlist.movies.get(position).getAnswer().equalsIgnoreCase("C")) {
                            arranswer_status.put(position, "correct");
                        } else {
                            arranswer_status.put(position, "wrong");
                        }

                        for (int i = 0; i < PaginationAdapterForQuizlist.arroptionsselected.size(); i++) {
                            if (i == 2) {
                                PaginationAdapterForQuizlist.arroptionsselected.set(2, "1");
                            } else {
                                PaginationAdapterForQuizlist.arroptionsselected.set(i, "0");
                            }
                        }
                        adapter.notifyDataSetChanged();

                        break;

                    case R.id.ll_opt4:
                        useranswers.put(position, "D");
                        correctanswers.put(position, PaginationAdapterForQuizlist.movies.get(position).getAnswer());
                        arrquiz_id.put(position, PaginationAdapterForQuizlist.movies.get(position).getArroptionsquiz_id().get(3));
                        arroption_id.put(position, PaginationAdapterForQuizlist.movies.get(position).getArroptionsid().get(3));

                        if (PaginationAdapterForQuizlist.movies.get(position).getAnswer().equalsIgnoreCase("D")) {
                            arranswer_status.put(position, "correct");
                        } else {
                            arranswer_status.put(position, "wrong");
                        }

                        for (int i = 0; i < PaginationAdapterForQuizlist.arroptionsselected.size(); i++) {
                            if (i == 3) {
                                PaginationAdapterForQuizlist.arroptionsselected.set(3, "1");
                            } else {
                                PaginationAdapterForQuizlist.arroptionsselected.set(i, "0");
                            }
                        }
                        adapter.notifyDataSetChanged();

                        break;
                    case R.id.ll_opt5:
                        useranswers.put(position, "E");
                        correctanswers.put(position, PaginationAdapterForQuizlist.movies.get(position).getAnswer());
                        arrquiz_id.put(position, PaginationAdapterForQuizlist.movies.get(position).getArroptionsquiz_id().get(4));
                        arroption_id.put(position, PaginationAdapterForQuizlist.movies.get(position).getArroptionsid().get(4));

                        if (PaginationAdapterForQuizlist.movies.get(position).getAnswer().equalsIgnoreCase("E")) {
                            arranswer_status.put(position, "correct");
                        } else {
                            arranswer_status.put(position, "wrong");
                        }

                        for (int i = 0; i < PaginationAdapterForQuizlist.arroptionsselected.size(); i++) {
                            if (i == 4) {
                                PaginationAdapterForQuizlist.arroptionsselected.set(4, "1");
                            } else {
                                PaginationAdapterForQuizlist.arroptionsselected.set(i, "0");
                            }
                        }
                        adapter.notifyDataSetChanged();

                        break;


                    case R.id.ll_discuss:
                        Map<String, String> requestBody = new HashMap<>();
                        requestBody.put("token", token);
                        requestBody.put("user_id", user_id);
                        requestBody.put("education_id", education_id);
                        requestBody.put("stream_id", stream_id);
                        requestBody.put("subject_id", subjects);
                        requestBody.put("chapter_id", PaginationAdapterForQuizlist.movies.get(position).getChapter_id());
                        requestBody.put("image", "");
                        requestBody.put("youtube", "");
                        requestBody.put("description", PaginationAdapterForQuizlist.movies.get(position).getQuestion());
                        presenter.create_post(context, apiResponseCallback, requestBody);

                        break;
                    case R.id.ll_next:
                        if (position + 1 == questions_count_int) {
                            if (token != null || !token.isEmpty()) {
                                JSONArray contactsArray = new JSONArray();
                                List<JSONObject> bjhgjg = new ArrayList<>();
                                // for (int i = 0; i < PaginationAdapterForQuizlist.movies.size(); i++) {
                                for (int i = 0; i < arrquiz_id.size(); i++) {

                                    try {
                                        JSONObject jsonObject = new JSONObject();
                                        String quiz_idmap = arrquiz_id.get(i);
                                        String option_idmap = arroption_id.get(i);
                                        String answermap = useranswers.get(i);
                                        String correct_answermap = correctanswers.get(i);
                                        String answer_statusmap = arranswer_status.get(i);

                                        jsonObject.put("quiz_id", quiz_idmap);
                                        jsonObject.put("option_id", option_idmap);
                                        jsonObject.put("answer", answermap);
                                        jsonObject.put("correct_answer", correct_answermap);
                                        jsonObject.put("answer_status", answer_statusmap);

                                        contactsArray.put(jsonObject);
                                        bjhgjg.add(jsonObject);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                JSONObject jsonObject_requestt = new JSONObject();
                                try {
                                    jsonObject_requestt.put("token", token);
                                    jsonObject_requestt.put("user_id", user_id);
                                    jsonObject_requestt.put("quizs_id", quizs_id);
                                    jsonObject_requestt.put("answers", contactsArray);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                MemberQuizSubmit member = new MemberQuizSubmit();
                                member.setToken(token);
                                member.setUser_id(user_id);
                                member.setQuizs_id(quizs_id);
                                member.setAnswers(bjhgjg);
                                Gson gson = new Gson();
                                String json = gson.toJson(member);

                                presenter.submit_quiz_answers(QuestionActivity.this, apiResponseCallback, jsonObject_requestt);
                            }
                        } else if (linearLayoutManager.findLastCompletelyVisibleItemPosition() < (adapter.getItemCount() - 1)) {
                            linearLayoutManager.scrollToPosition(linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1);
                        } else {
                            if (token != null || !token.isEmpty()) {
                                JSONArray contactsArray = new JSONArray();
                                List<JSONObject> bjhgjg = new ArrayList<>();
                                // for (int i = 0; i < PaginationAdapterForQuizlist.movies.size(); i++) {
                                for (int i = 0; i < arrquiz_id.size(); i++) {

                                    try {
                                        JSONObject jsonObject = new JSONObject();
                                        String quiz_idmap = arrquiz_id.get(i);
                                        String option_idmap = arroption_id.get(i);
                                        String answermap = useranswers.get(i);
                                        String correct_answermap = correctanswers.get(i);
                                        String answer_statusmap = arranswer_status.get(i);

                                        jsonObject.put("quiz_id", quiz_idmap);
                                        jsonObject.put("option_id", option_idmap);
                                        jsonObject.put("answer", answermap);
                                        jsonObject.put("correct_answer", correct_answermap);
                                        jsonObject.put("answer_status", answer_statusmap);

                                        contactsArray.put(jsonObject);
                                        bjhgjg.add(jsonObject);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                JSONObject jsonObject_requestt = new JSONObject();
                                try {
                                    jsonObject_requestt.put("token", token);
                                    jsonObject_requestt.put("user_id", user_id);
                                    jsonObject_requestt.put("quizs_id", quizs_id);
                                    jsonObject_requestt.put("answers", contactsArray);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                MemberQuizSubmit member = new MemberQuizSubmit();
                                member.setToken(token);
                                member.setUser_id(user_id);
                                member.setQuizs_id(quizs_id);
                                member.setAnswers(bjhgjg);
                                Gson gson = new Gson();
                                String json = gson.toJson(member);

                                presenter.submit_quiz_answers(QuestionActivity.this, apiResponseCallback, jsonObject_requestt);
                            }
                        }

                        break;
                    case R.id.ll_prev:

                        if (linearLayoutManager.findLastCompletelyVisibleItemPosition() >= 0) {
                            linearLayoutManager.scrollToPosition(linearLayoutManager.findLastCompletelyVisibleItemPosition() - 1);
                        }
                        break;


                    default:
                        Log.d(TAG, "dffgfgfgfgfgfgfgfgfgfgfgfgfgfgfgfg: ");
                        break;
                }


            }
        });


//        adapter.setOnItemClickListener(new PaginationAdapterForQuizlist.OnitemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                switch (view.getId()) {
//                    case R.id.ll_opt1:
//
//                        for (int i = 0; i < PaginationAdapterForQuizlist.arroptionsselected.size(); i++) {
//                            if (i == 0) {
//                                PaginationAdapterForQuizlist.arroptionsselected.set(0, "1");
//                            } else {
//                                PaginationAdapterForQuizlist.arroptionsselected.set(i, "0");
//                            }
//                        }
//                        adapter.notifyDataSetChanged();
//
//                        break;
//                }
//            }
//        });


        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayoutManager.findLastCompletelyVisibleItemPosition() < (adapter.getItemCount() - 1)) {

                    linearLayoutManager.scrollToPosition(linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1);
                } else {
                    if (token != null || !token.isEmpty()) {
                        tv_next.setText("Submit");
                        JSONArray contactsArray = new JSONArray();
                        List<JSONObject> bjhgjg = new ArrayList<>();

                        // for (int i = 0; i < PaginationAdapterForQuizlist.movies.size(); i++) {
                        for (int i = 0; i < arrquiz_id.size(); i++) {

                            try {
                                JSONObject jsonObject = new JSONObject();
                                String quiz_idmap = arrquiz_id.get(i);
                                String option_idmap = arroption_id.get(i);
                                String answermap = useranswers.get(i);
                                String correct_answermap = correctanswers.get(i);
                                String answer_statusmap = arranswer_status.get(i);

                                jsonObject.put("quiz_id", quiz_idmap);
                                jsonObject.put("option_id", option_idmap);
                                jsonObject.put("answer", answermap);
                                jsonObject.put("correct_answer", correct_answermap);
                                jsonObject.put("answer_status", answer_statusmap);

                                contactsArray.put(jsonObject);
                                bjhgjg.add(jsonObject);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        JSONObject jsonObject_requestt = new JSONObject();
                        try {
                            jsonObject_requestt.put("token", token);
                            jsonObject_requestt.put("user_id", user_id);
                            jsonObject_requestt.put("quizs_id", quizs_id);
                            jsonObject_requestt.put("answers", contactsArray);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        MemberQuizSubmit member = new MemberQuizSubmit();
                        member.setToken(token);
                        member.setUser_id(user_id);
                        member.setQuizs_id(quizs_id);
                        member.setAnswers(bjhgjg);
                        Gson gson = new Gson();
                        String json = gson.toJson(member);

//                        Map<String, String> requestBody = new HashMap<>();
//                        requestBody.put("token", token);
//                        //  requestBody.put("mobile_numbers", selectUsersphone.toString());
//                        requestBody.put("mobile_numbers", json);

                        presenter.submit_quiz_answers(QuestionActivity.this, apiResponseCallback, jsonObject_requestt);
                    }
                }

            }
        });
        tv_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayoutManager.findLastCompletelyVisibleItemPosition() >= 0) {
                    linearLayoutManager.scrollToPosition(linearLayoutManager.findLastCompletelyVisibleItemPosition() - 1);
                }

            }
        });

        rv.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
        // mocking network delay for API call
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadFirstPage();
            }
        }, 1000);

    }

    private void loadFirstPage() {
        abc = "1";

        Log.d(TAG, "loadFirstPage: ");

        if (token != null && token.length() > 0) {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
//            requestBody.put("user_id", user_id);
//            requestBody.put("education_id", education_id);
//            requestBody.put("stream_id", stream_id);
//            requestBody.put("subject_ids", subjects);
            requestBody.put("user_id", user_id);
            requestBody.put("education_id", education_id);
            requestBody.put("stream_id", stream_id);
            requestBody.put("subject_ids", subjects);
            requestBody.put("chapter_id", "0");
            requestBody.put("quizs_id", quizs_id);
            requestBody.put("count", String.valueOf(currentPage));
            presenter.quiz_list(QuestionActivity.this, apiResponseCallback, requestBody);
        }

    }

    public class RecyclerViewDisabler implements RecyclerView.OnItemTouchListener {

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            return true;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    private void loadNextPage() {

        abc = "2";

        Log.d(TAG, "loadNextPage: " + currentPage);

        if (token != null && token.length() > 0) {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
//            requestBody.put("user_id", user_id);
//            requestBody.put("education_id", education_id);
//            requestBody.put("stream_id", stream_id);
//            requestBody.put("subject_ids", subjects);
            requestBody.put("user_id", user_id);
            requestBody.put("education_id", education_id);
            requestBody.put("stream_id", stream_id);
            requestBody.put("subject_ids", subjects);
            requestBody.put("chapter_id", "0");
            requestBody.put("quizs_id", quizs_id);
            requestBody.put("count", String.valueOf(currentPage));
            presenter.quiz_list(QuestionActivity.this, apiResponseCallback, requestBody);
        }
    }


    @Override
    public void onClick(View v) {

    }


    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {
            Log.d("registerResponse", responseString);
            final JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equalsIgnoreCase("5000")) {
                //todo: if network is not availiable then show some dialog box
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false,context);
            } else if (NetworkConstants.RequestCode.QUIZ_LIST == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    // Util.getInstance().cusToast(context, message);
                    // QuizQuestionPOJO quizQuestionPOJO = new Gson().fromJson(responseString, QuizQuestionPOJO.class);

                    // JSONObject jsonArrayresponse = jsonObject.optJSONObject("response");
                    JSONArray jsonArray = jsonObject.optJSONArray("response");
                    // JSONArray jsonArray = jsonArrayresponse.optJSONArray("Reviews");

                    List<QuizQuestionModel> movies = new ArrayList<>();

                    if (jsonArray != null && jsonArray.length() > 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject item = jsonArray.getJSONObject(i);
                            //add items to model

                            String idd = item.optString("id");
                            String quizs_id = item.optString("quizs_id");
                            String question = item.optString("question");
                            String answer = item.optString("answer");
                            int question_number = item.optInt("question_number");
                            String subject_id = item.optString("subject_id");
                            String chapter_id = item.optString("chapter_id");

                            JSONArray jsonArrayoptions = item.optJSONArray("options");
                            ArrayList<String> arroptions = new ArrayList<>();
                            ArrayList<String> arroptionsquiz_id = new ArrayList<>();
                            ArrayList<String> arroptionsid = new ArrayList<>();
                            ArrayList<String> arroptionsselected = new ArrayList<>();
                            if (jsonArrayoptions != null && jsonArrayoptions.length() > 0) {
                                for (int j = 0; j < jsonArrayoptions.length(); j++) {
                                    JSONObject itemoptions = jsonArrayoptions.getJSONObject(j);
                                    String options = itemoptions.optString("options");
                                    String id = itemoptions.optString("id");
                                    String quiz_id = itemoptions.optString("quiz_id");

                                    arroptions.add(options);
                                    arroptionsid.add(id);
                                    arroptionsquiz_id.add(quiz_id);
                                    arroptionsselected.add("0");
                                }
                            }

                            QuizQuestionModel movie = new QuizQuestionModel(quizs_id, question, answer, arroptions, arroptionsid, arroptionsquiz_id, arroptionsselected, question_number, subject_id, chapter_id);
                            movies.add(movie);
                        }
                        List<QuizQuestionModel> moviesmain = movies;

                        if (abc.equalsIgnoreCase("1")) {

                            startTimer();

                            progressBar.setVisibility(View.GONE);
                            adapter.addAll(moviesmain);
                            if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                            else isLastPage = true;
                        } else {
                            adapter.removeLoadingFooter();
                            isLoading = false;

                            adapter.addAll(moviesmain);

                            if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
                            else {
                                isLastPage = true;
                            }
                        }


                    } else {

                    }


                } else {
                    Util.getInstance().cusToast(context, message);
                    tv_next.setText("Submit");
                }
            } else if (NetworkConstants.RequestCode.CREATE_POST == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Toast.makeText(context, "Question posted in Discussion Section", Toast.LENGTH_SHORT).show();


                } else {
                    Util.getInstance().cusToast(context, message);
                }
            } else if (NetworkConstants.RequestCode.SUBMIT_QUIZ_ANSWERS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Util.getInstance().cusToast(context, message);

                    final Dialog congratuations = new Dialog(QuestionActivity.this);

                    congratuations.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    congratuations.setContentView(R.layout.item_congratulations);
                    TextView tv_marks, tv_marks_text;

                    Button bt_ok = congratuations.findViewById(R.id.btn_ok);
                    tv_marks = congratuations.findViewById(R.id.tv_marks);
                    tv_marks_text = congratuations.findViewById(R.id.tv_marks_text);
                    tv_marks.setText(jsonObject.getString("correct_answer_count"));
                    tv_marks_text.setText("You got " + jsonObject.getString("correct_answer_count") + " marks out of " + questions_count);
                    bt_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle2 = new Bundle();
                            bundle2.putString("id", quizs_id);
                            try {
                                bundle2.putString("right_count", jsonObject.getString("correct_answer_count"));
                                bundle2.putString("total_count", questions_count);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            };

                            bundle2.putString("quiz_name",quiz_name);

                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_QUESTIONRIGHTWRONG_SCREEN, bundle2);
                            congratuations.dismiss();
                            finish();
                        }
                    });

                    Window window = congratuations.getWindow();
                    congratuations.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    window.setGravity(Gravity.CENTER);
                    congratuations.show();

                } else {

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {
        Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show();
    }
}
