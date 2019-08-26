package com.iprismtech.studentvarsity.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.FaqsAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.activity.FaqActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.FaqActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.FaqsListPOJO;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaqActivity extends BaseAbstractActivity<FaqActivitylmpl> implements FaqActivityContract.IView, APIResponseCallback {
    APIResponseCallback apiResponseCallback;
    RecyclerView recycle_faqslist;
    ImageView im_back;

    private NestedScrollView ll_nested_scroll;
    private boolean response_status = false;

    RecyclerView rv_faqs;
    FaqsAdapter adapter;
    String chapter_id = "", subject_id = "", topic = "";
    int read_count, topic_count;
    private TextView tv_title;

    private FaqsListPOJO departmentPOJO;
    private List<FaqsListPOJO.ResponseBean> faqsList;
    private int selected_postion;
    private int page_number = 0;
    private String token, user_id, user_profile_pic, user_name, user_university, user_bio, stream, years, user_subjects, education_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        // setContentView(R.layout.activity_faq);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_faq, null);
        return view;
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        apiResponseCallback = this;
        rv_faqs = findViewById(R.id.recycle_faqslist);
        ll_nested_scroll = findViewById(R.id.ll_nested_scroll_faqs);
        im_back = findViewById(R.id.im_back);
        tv_title = findViewById(R.id.tv_title);

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
        // subject_id = userSession.getUserDetails().get("subjects");

        faqsList = new ArrayList<>();
        if (getIntent().getExtras() != null) {
            chapter_id = getIntent().getStringExtra("chapter_id");
            subject_id = getIntent().getStringExtra("subject_id");
            topic = getIntent().getStringExtra("topic");

            read_count = getIntent().getIntExtra("read_count", 0);
            topic_count = getIntent().getIntExtra("topic_count", 0);
            //do here
        }

        tv_title.setText(topic + "(" + read_count + "/" + topic_count + ")");
        callFAQsWs();

        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void setPresenter() {
        presenter = new FaqActivitylmpl(this, this);

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
            } else if (NetworkConstants.RequestCode.FAQS == requestId) {
                if (jsonObject.optBoolean("status") == true) {

                    response_status = true;
                    rv_faqs.setVisibility(View.VISIBLE);
                    Util.getInstance().cusToast(this, jsonObject.optString("message"));
                    departmentPOJO = new Gson().fromJson(responseString, FaqsListPOJO.class);
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
                    //setting horizontal list
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rv_faqs.setLayoutManager(mLayoutManager);
                    rv_faqs.setItemAnimator(new DefaultItemAnimator());

                    faqsList.addAll(departmentPOJO.getResponse());

                    //Adding Adapter to recyclerView
                    adapter = new FaqsAdapter(faqsList, this);
                    rv_faqs.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


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

                    adapter.setOnItemClickListener(new FaqsAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            selected_postion = position;
                            switch (view.getId()) {
                                case R.id.tv_question:
                                    Bundle bundle2 = new Bundle();
                                    bundle2.putString("faq_id", faqsList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FAQDETAIL_SCREEN, bundle2);
                                    break;
                                case R.id.ll_ping:
                                    Bundle bundle_ping = new Bundle();
                                    bundle_ping.putString("sections_id", faqsList.get(position).getId());
                                    bundle_ping.putString("type", "faqs");

                                    if (faqsList.get(position).getQuestion().length() > 70) {
                                        bundle_ping.putString("content", faqsList.get(position).getQuestion().substring(0, 70));
                                    } else {
                                        bundle_ping.putString("content", faqsList.get(position).getQuestion());
                                    }

                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PING_TO_FRIENDS_GROUPS_SCREEN, bundle_ping);
                                    break;
                                case R.id.ll_comment:
                                    Bundle bundle_comment = new Bundle();
                                    bundle_comment.putString("faq_id", faqsList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FAQDETAIL_SCREEN, bundle_comment);
                                    break;
                                case R.id.tv_cmntcount:
                                    Bundle bundle_comment_ = new Bundle();
                                    bundle_comment_.putString("faq_id", faqsList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FAQDETAIL_SCREEN, bundle_comment_);
                                    break;
                                case R.id.ll_unread:
                                    callFAQsReadStatus();
                                    break;
                            }


                        }
                    });


                }
            } else if (NetworkConstants.RequestCode.SUBMIT_AS_READ == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    faqsList.get(selected_postion).setReaded("yes");
                    adapter.notifyItemChanged(selected_postion);
                    adapter.notifyDataSetChanged();
                    read_count = read_count + 1;
                    tv_title.setText(topic + "(" + read_count + "/" + topic_count + ")");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }

    private void callFAQsReadStatus() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("subject_id", faqsList.get(selected_postion).getSubject_id());
        requestBody.put("chapter_id", faqsList.get(selected_postion).getChapter_id());
        requestBody.put("sections_id", faqsList.get(selected_postion).getId());
        requestBody.put("type", "faqs");
        presenter.submit_as_read(this, this, requestBody);

    }


    private void perfirmPagination() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("education_id", education_id);
        requestBody.put("stream_id", stream);
        requestBody.put("subject_ids", subject_id);
        requestBody.put("chapter_id", chapter_id);
        requestBody.put("count", String.valueOf(page_number));
        /*if (Variables.search_text != null) {
            requestBody.put("search", Variables.search_text);
        }*/
        presenter.userRegister(this, apiResponseCallback, requestBody);

    }


    private void callFAQsWs() {


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("education_id", education_id);
        requestBody.put("stream_id", stream);
        requestBody.put("subject_ids", subject_id);
        requestBody.put("chapter_id", chapter_id);
        requestBody.put("count", String.valueOf(page_number));

//            requestBody.put("token", KEY_TOKEN);
//            requestBody.put("user_id", KEY_USERID);
//            requestBody.put("education_id", KEY_EDUCATION_ID);
//            requestBody.put("stream_id", KEY_STREAM_ID);
//            requestBody.put("subject_ids", KEY_SUBJECTS);
//            requestBody.put("chapter_id", "1");
//            requestBody.put("count", "0");
        /*if (Variables.search_text != null) {
            requestBody.put("search", Variables.search_text);
        }*/
        presenter.userRegister(this, apiResponseCallback, requestBody);
    }

}
