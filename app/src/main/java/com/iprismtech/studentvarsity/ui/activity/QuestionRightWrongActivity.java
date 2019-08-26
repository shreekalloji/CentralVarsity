package com.iprismtech.studentvarsity.ui.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.RightAttemptAdapter;
import com.iprismtech.studentvarsity.adapters.WrongAttemptAdapter;
import com.iprismtech.studentvarsity.mvp.contract.activity.QuestionRightWrongActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.QuestionRightWrongActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.RightWrongPOJO;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class QuestionRightWrongActivity extends BaseAbstractActivity<QuestionRightWrongActivitylmpl> implements QuestionRightWrongActivityContract.IView, View.OnClickListener, APIResponseCallback {
    // AppCompatTextView tv_discuss;
    private LinearLayout ll_discuss, ll_right, ll_wrong;
    private TextView tv_right, tv_wrong, tv_marks;
    private ScrollView scroll1, scroll;
    private TextView tv_next, tv_prev, tv_quiz_name;

    private APIResponseCallback apiResponseCallback;
    private UserSession userSession;
    private String token, user_id, quiz_name, right_count, total_count;

    private RecyclerView rv_rightattempt, rv_wrongattempt;
    private RightAttemptAdapter adapter;
    private WrongAttemptAdapter adapterwrong;
    private LinearLayoutManager mLayoutManager, mLayoutManager2;
    private Bundle bundle;
    private String id, abc = "1";
    private ImageView iv_back;

    private String KEY_EDUCATION_ID, KEY_YEARS, KEY_STREAM_ID, KEY_SUBJECTS, KEY_USERID, KEY_UserName, KEY_MOBILENO, KEY_PROFILE, KEY_UNIVERSITY, KEY_COUNTRY_ID, KEY_CITY_ID, KEY_BIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();

        // setContentView(R.layout.activity_question_right_wrong);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_question_right_wrong, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new QuestionRightWrongActivitylmpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        userSession = new UserSession(QuestionRightWrongActivity.this);

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");

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

        apiResponseCallback = this;

        bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
            quiz_name = bundle.getString("quiz_name");
            right_count = bundle.getString("right_count");
            total_count = bundle.getString("total_count");
        }

        ll_right = findViewById(R.id.ll_right);
        ll_wrong = findViewById(R.id.ll_wrong);
        tv_right = findViewById(R.id.tv_right);
        tv_wrong = findViewById(R.id.tv_wrong);
        rv_rightattempt = findViewById(R.id.rv_rightattempt);
        rv_wrongattempt = findViewById(R.id.rv_wrongattempt);
        tv_next = findViewById(R.id.tv_next);
        tv_prev = findViewById(R.id.tv_prev);
        iv_back = findViewById(R.id.iv_back);
        tv_quiz_name = findViewById(R.id.tv_quiz_name);
        tv_marks = findViewById(R.id.tv_marks);

        tv_marks.setText("Marks : " + right_count + "/" + total_count);
        // scroll1=findViewById(R.id.scroll1);
        //scroll=findViewById(R.id.scroll);

        ll_discuss = findViewById(R.id.ll_discuss);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_quiz_name.setText(quiz_name);
        ll_discuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog discussion_dialogue = new Dialog(QuestionRightWrongActivity.this, R.style.Theme_Dialog);

                discussion_dialogue.requestWindowFeature(Window.FEATURE_NO_TITLE);
                discussion_dialogue.setContentView(R.layout.item_discussion);
                Window window = discussion_dialogue.getWindow();
                discussion_dialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.BOTTOM);
                discussion_dialogue.show();


            }
        });


        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abc = "1";

                ll_right.setVisibility(View.VISIBLE);
                ll_wrong.setVisibility(View.GONE);
                //tv_right.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_discussion_bg));
                tv_right.setBackgroundDrawable(getResources().getDrawable(R.drawable.horizantal_bg));
                //  tv_right.setBackgroundColor(Color.WHITE);
                tv_wrong.setBackgroundDrawable(getResources().getDrawable(R.drawable.dark_red));
                tv_right.setTextColor(Color.BLACK);
                tv_wrong.setTextColor(Color.WHITE);

            }
        });

        tv_wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abc = "2";

                ll_wrong.setVisibility(View.VISIBLE);
                ll_right.setVisibility(View.GONE);
                // tv_discussCategory.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_categeory_bg));
                tv_wrong.setBackgroundDrawable(getResources().getDrawable(R.drawable.horizantal_bg));
                tv_right.setBackgroundDrawable(getResources().getDrawable(R.drawable.dark_red));
                tv_wrong.setTextColor(Color.BLACK);
                tv_right.setTextColor(Color.WHITE);


            }
        });

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (abc.equalsIgnoreCase("1")) {
                    if (mLayoutManager.findLastCompletelyVisibleItemPosition() < (adapter.getItemCount() - 1)) {
                        mLayoutManager.scrollToPosition(mLayoutManager.findLastCompletelyVisibleItemPosition() + 1);
                    }
                } else {
                    if (mLayoutManager2.findLastCompletelyVisibleItemPosition() < (adapterwrong.getItemCount() - 1)) {
                        mLayoutManager2.scrollToPosition(mLayoutManager2.findLastCompletelyVisibleItemPosition() + 1);
                    }
                }

            }
        });
        tv_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (abc.equalsIgnoreCase("1")) {
                    if (mLayoutManager.findLastCompletelyVisibleItemPosition() >= 0) {
                        mLayoutManager.scrollToPosition(mLayoutManager.findLastCompletelyVisibleItemPosition() - 1);
                    }
                } else {
                    if (mLayoutManager2.findLastCompletelyVisibleItemPosition() >= 0) {
                        mLayoutManager2.scrollToPosition(mLayoutManager2.findLastCompletelyVisibleItemPosition() - 1);
                    }
                }

            }
        });
        if (token != null && token.length() > 0) {
            Map<String, String> requestBody = new HashMap<>();
//            {
//                "token":
//                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
//                        "user_id":"1",
//                    "quizs_id":"1"
//            }
            requestBody.put("token", token);
            requestBody.put("user_id", user_id);
            requestBody.put("quizs_id", id);
            presenter.right_wrong_attempts(QuestionRightWrongActivity.this, apiResponseCallback, requestBody);
        }
    }

    @Override
    public void onClick(View v) {

    }
    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString,
                                  @Nullable Object object) {

        try {

            Log.d("registerResponse", responseString);
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equalsIgnoreCase("5000")) {
                //todo: if network is not availiable then show some dialog box
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false,context);


            } else if (NetworkConstants.RequestCode.RIGHT_WRONG_ATTEMPTS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Util.getInstance().cusToast(context, message);
                    final RightWrongPOJO yearPOJO = new Gson().fromJson(responseString, RightWrongPOJO.class);


                    mLayoutManager = new LinearLayoutManager(QuestionRightWrongActivity.this);
                    //setting horizontal list
                    mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    rv_rightattempt.setLayoutManager(mLayoutManager);
                    rv_rightattempt.setItemAnimator(new DefaultItemAnimator());

                    //Adding Adapter to recyclerView
                    adapter = new RightAttemptAdapter(yearPOJO, QuestionRightWrongActivity.this);
                    rv_rightattempt.setAdapter(adapter);

                    adapter.setOnItemClickListener(new RightAttemptAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            switch (view.getId()) {
                                case R.id.ll_discuss:
                                    Map<String, String> requestBody = new HashMap<>();
                                    requestBody.put("token", token);
                                    requestBody.put("user_id", user_id);
                                    requestBody.put("education_id", KEY_EDUCATION_ID);
                                    requestBody.put("stream_id", KEY_STREAM_ID);
                                    requestBody.put("subject_id", KEY_SUBJECTS);
                                    requestBody.put("chapter_id", yearPOJO.getQuiz_details().getChapter_id());
                                    requestBody.put("image", "");
                                    requestBody.put("youtube", "");
                                    requestBody.put("description", yearPOJO.getRight_answers().get(position).getQuestion());
                                    presenter.create_post(context, apiResponseCallback, requestBody);

                                    break;
                                case R.id.ll_next:
                                    if (abc.equalsIgnoreCase("1")) {
                                        if (mLayoutManager.findLastCompletelyVisibleItemPosition() < (adapter.getItemCount() - 1)) {
                                            mLayoutManager.scrollToPosition(mLayoutManager.findLastCompletelyVisibleItemPosition() + 1);
                                        }
                                    } else {
                                        if (mLayoutManager2.findLastCompletelyVisibleItemPosition() < (adapterwrong.getItemCount() - 1)) {
                                            mLayoutManager2.scrollToPosition(mLayoutManager2.findLastCompletelyVisibleItemPosition() + 1);
                                        }
                                    }
                                    break;
                                case R.id.ll_prev:

                                    if (abc.equalsIgnoreCase("1")) {
                                        if (mLayoutManager.findLastCompletelyVisibleItemPosition() >= 0) {
                                            mLayoutManager.scrollToPosition(mLayoutManager.findLastCompletelyVisibleItemPosition() - 1);
                                        }
                                    } else {
                                        if (mLayoutManager2.findLastCompletelyVisibleItemPosition() >= 0) {
                                            mLayoutManager2.scrollToPosition(mLayoutManager2.findLastCompletelyVisibleItemPosition() - 1);
                                        }
                                    }
                                    break;

                            }
                        }
                    });


                    mLayoutManager2 = new LinearLayoutManager(QuestionRightWrongActivity.this);
                    //setting horizontal list
                    mLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                    rv_wrongattempt.setLayoutManager(mLayoutManager2);
                    rv_wrongattempt.setItemAnimator(new DefaultItemAnimator());

                    //Adding Adapter to recyclerView
                    adapterwrong = new WrongAttemptAdapter(yearPOJO, QuestionRightWrongActivity.this);
                    rv_wrongattempt.setAdapter(adapterwrong);


                    adapterwrong.setOnItemClickListener(new WrongAttemptAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            switch (view.getId()) {
                                case R.id.ll_discuss:

                                    Map<String, String> requestBody = new HashMap<>();
                                    requestBody.put("token", token);
                                    requestBody.put("user_id", user_id);
                                    requestBody.put("education_id", KEY_EDUCATION_ID);
                                    requestBody.put("stream_id", KEY_STREAM_ID);
                                    requestBody.put("subject_id", yearPOJO.getQuiz_details().getSubject_id());
                                    requestBody.put("chapter_id", yearPOJO.getQuiz_details().getChapter_id());
                                    requestBody.put("image", "");
                                    requestBody.put("youtube", "");
                                    requestBody.put("description", yearPOJO.getWrong_answers().get(position).getQuestion());
                                    presenter.create_post(context, apiResponseCallback, requestBody);
                                    break;
                                case R.id.ll_next:
                                    if (abc.equalsIgnoreCase("1")) {
                                        if (mLayoutManager.findLastCompletelyVisibleItemPosition() < (adapter.getItemCount() - 1)) {
                                            mLayoutManager.scrollToPosition(mLayoutManager.findLastCompletelyVisibleItemPosition() + 1);
                                        }
                                    } else {
                                        if (mLayoutManager2.findLastCompletelyVisibleItemPosition() < (adapterwrong.getItemCount() - 1)) {
                                            mLayoutManager2.scrollToPosition(mLayoutManager2.findLastCompletelyVisibleItemPosition() + 1);
                                        }
                                    }
                                    break;
                                case R.id.ll_prev:

                                    if (abc.equalsIgnoreCase("1")) {
                                        if (mLayoutManager.findLastCompletelyVisibleItemPosition() >= 0) {
                                            mLayoutManager.scrollToPosition(mLayoutManager.findLastCompletelyVisibleItemPosition() - 1);
                                        }
                                    } else {
                                        if (mLayoutManager2.findLastCompletelyVisibleItemPosition() >= 0) {
                                            mLayoutManager2.scrollToPosition(mLayoutManager2.findLastCompletelyVisibleItemPosition() - 1);
                                        }
                                    }
                                    break;

                            }
                        }
                    });


                } else {
                    Util.getInstance().cusToast(context, message);
                }
            } else if (NetworkConstants.RequestCode.CREATE_POST == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Toast.makeText(context, "Question posted in Discussion Section", Toast.LENGTH_SHORT).show();


                } else {
                    Util.getInstance().cusToast(context, message);
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
