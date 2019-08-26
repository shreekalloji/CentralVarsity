package com.iprismtech.studentvarsity.ui.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.MCQsAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.activity.McqActivityContract;
import com.iprismtech.studentvarsity.mvp.contract.activity.NotesActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.McqActivitylmpl;
import com.iprismtech.studentvarsity.mvp.presenter.activity.NotesActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.MCQsPojo;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;
import com.iprismtech.studentvarsity.utils.Variables;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class McqActivity extends BaseAbstractActivity<McqActivitylmpl> implements McqActivityContract.IView, APIResponseCallback {
    APIResponseCallback apiResponseCallback;
    RecyclerView recycle_mcqlist;
    ImageView im_back;
    private RecyclerView rview_mcqs;
    private MCQsPojo mcQsPojo;
    private List<MCQsPojo.ResponseBean> quizList;
    private LinearLayoutManager manager;
    private NestedScrollView ll_nested_scroll;
    private int count = 0;
    private MCQsAdapter mcQsAdapter;
    private TextView tv_title;

    private String chapter_id, subject_id, topic;
    int topic_count, read_count;

    private int selected_position;
    private String token, user_id, user_profile_pic, user_name, user_university, user_bio, stream, years, user_subjects, education_id;
    private UserSession userSession;
    private boolean response_status = false;
    private int page_number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        //  setContentView(R.layout.activity_mcq);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_mcq, null);
        return view;
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        apiResponseCallback = this;
        rview_mcqs = findViewById(R.id.rview_mcqs);
        im_back = findViewById(R.id.im_back);
        tv_title = findViewById(R.id.tv_title);
        if (getIntent().getExtras() != null) {
            chapter_id = getIntent().getStringExtra("chapter_id");
            subject_id = getIntent().getStringExtra("subject_id");
            topic = getIntent().getStringExtra("topic");
            read_count = getIntent().getIntExtra("read_count", 0);
            topic_count = getIntent().getIntExtra("topic_count", 0);
            //do here
        }

        tv_title.setText(topic + "(" + read_count + "/" + topic_count + ")");
        quizList = new ArrayList<>();
        userSession = new UserSession(this);
        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        user_name = userSession.getUserDetails().get("name");
        user_university = userSession.getUserDetails().get("university");
        education_id = userSession.getUserDetails().get("education_id");
        user_bio = userSession.getUserDetails().get("bio");
        user_profile_pic = userSession.getUserDetails().get("profileImg");
        stream = userSession.getUserDetails().get("stream_id");
        years = userSession.getUserDetails().get("years");
        user_subjects = userSession.getUserDetails().get("subject_names");
        //  subject_id = userSession.getUserDetails().get("subjects");
        callMCQsWs();

    }

    @Override
    public void setPresenter() {
        presenter = new McqActivitylmpl(this, this);

    }


    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                //   Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, context);

            } else if (NetworkConstants.RequestCode.GET_MCQS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    response_status = true;
                    rview_mcqs.setVisibility(View.VISIBLE);
                    mcQsPojo = new Gson().fromJson(responseString, MCQsPojo.class);
                    quizList.addAll(mcQsPojo.getResponse());
                    mcQsAdapter = new MCQsAdapter(this, quizList);
                    rview_mcqs.setAdapter(mcQsAdapter);
                    mcQsAdapter.notifyDataSetChanged();
//                    ll_nested_scroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//                        @Override
//                        public void onScrollChanged() {
//                            View view = (View) ll_nested_scroll.getChildAt(ll_nested_scroll.getChildCount() - 1);
//
//                            int diff = (view.getBottom() - (ll_nested_scroll.getHeight() + ll_nested_scroll
//                                    .getScrollY()));
//
//                            if (diff == 0) {
//                                // your pagination code
//                                page_number ++;
//                                //perfirmPagination();
//                            }
//                        }
//                    });


                    if (response_status) {
                        ll_nested_scroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                            @Override
                            public void onScrollChanged() {
                                View view = (View) ll_nested_scroll.getChildAt(ll_nested_scroll.getChildCount() - 1);

                                int diff = (view.getBottom() - (ll_nested_scroll.getHeight() + ll_nested_scroll
                                        .getScrollY()));

                                if (diff == 0) {
                                    // your pagination code
                                    page_number++;
                                    if (response_status) {
                                        perfirmPagination();
                                    }
                                }
                            }
                        });
                    } else {
                        ll_nested_scroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                            @Override
                            public void onScrollChanged() {
                                View view = (View) ll_nested_scroll.getChildAt(ll_nested_scroll.getChildCount() - 1);

                                int diff = (view.getBottom() - (ll_nested_scroll.getHeight() + ll_nested_scroll
                                        .getScrollY()));

                                if (diff == 0) {
                                    // your pagination cod
                                }
                            }
                        });
                    }

                    mcQsAdapter.setOnItemClickListener(new MCQsAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            selected_position = position;
                            switch (view.getId()) {
                                case R.id.ll_question:
                                    Bundle bundle = new Bundle();
                                    bundle.putString("topic_id", quizList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MCQS_DETAILING_SCREEN, bundle);
                                    break;
                                case R.id.ll_ping:
                                    Bundle bundle_ping = new Bundle();
                                    bundle_ping.putString("sections_id", quizList.get(position).getId());
                                    bundle_ping.putString("type", "mcqs");
                                    if (quizList.get(position).getQuestion().length() > 70) {
                                        bundle_ping.putString("content", quizList.get(position).getQuestion().substring(0, 70));
                                    } else {
                                        bundle_ping.putString("content", quizList.get(position).getQuestion());
                                    }
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PING_TO_FRIENDS_GROUPS_SCREEN, bundle_ping);
                                    break;
                                case R.id.ll_comment:

                                    Bundle bundle_comment = new Bundle();
                                    bundle_comment.putString("topic_id", quizList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MCQS_DETAILING_SCREEN, bundle_comment);
                                    break;
                                case R.id.tv_comments_count:

                                    Bundle bundle_comment_ = new Bundle();
                                    bundle_comment_.putString("topic_id", quizList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MCQS_DETAILING_SCREEN, bundle_comment_);
                                    break;
                                case R.id.ll_unread:
                                    callMCQsReadStatus();
                                    break;
                            }
                        }
                    });

                } else if (NetworkConstants.RequestCode.SUBMIT_AS_READ == requestId) {
                    boolean status12 = jsonObject.getBoolean("status");
                    if (status12 == true) {
                        quizList.get(selected_position).setReaded("yes");
                        mcQsAdapter.notifyItemChanged(selected_position);
                        mcQsAdapter.notifyDataSetChanged();
                        read_count = read_count + 1;
                        tv_title.setText(topic + "(" + read_count + "/" + topic_count + ")");

                        callMCQsWs();

                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void callMCQsReadStatus() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("subject_id", quizList.get(selected_position).getSubject_id());
        requestBody.put("chapter_id", quizList.get(selected_position).getChapter_id());
        requestBody.put("sections_id", quizList.get(selected_position).getId());
        requestBody.put("type", "mcqs");
        presenter.submit_as_read(this, this, requestBody);

    }


    private void perfirmPagination() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("education_id", education_id);
        requestBody.put("stream_id", stream);
        requestBody.put("subject_ids", subject_id);
        requestBody.put("chapter_id", " ");
        requestBody.put("count", String.valueOf(page_number));
        /*if (Variables.search_text != null) {
            requestBody.put("search", Variables.search_text);
        }*/
        presenter.userRegister(this, this, requestBody);

    }


    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }

    private void callMCQsWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("education_id", education_id);
        requestBody.put("stream_id", stream);
        requestBody.put("subject_ids", subject_id);
        requestBody.put("chapter_id", chapter_id);
        requestBody.put("count", String.valueOf(page_number));
      /*  if (Variables.search_text != null) {
            requestBody.put("search", Variables.search_text);
        }*/
        presenter.userRegister(this, this, requestBody);
    }

}
