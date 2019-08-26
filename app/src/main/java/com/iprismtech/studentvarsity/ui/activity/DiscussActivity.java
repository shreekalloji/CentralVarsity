package com.iprismtech.studentvarsity.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.DiscussionsAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.activity.DsicussActvityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.DiscussActvityImpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.DiscussionsPojo;
import com.iprismtech.studentvarsity.pojos.NotesPojo;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;
import com.iprismtech.studentvarsity.utils.Variables;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscussActivity extends BaseAbstractActivity<DiscussActvityImpl> implements DsicussActvityContract.IView, APIResponseCallback {

    private RecyclerView rview_discussions;
    private LinearLayoutManager manager;
    private DiscussionsPojo discussionsPojo;
    private DiscussionsAdapter discussionsAdapter;
    private int selected_position;
    String chapter_id = "", subject_id = "", topic = "";
    int read_count, topic_count;
    private List<DiscussionsPojo.ResponseBean> notesBeans;
    private NestedScrollView ll_nested_scroll;

    private TextView tv_title;
    private int page_number = 0;
    private String token, KEY_EDUCATION_ID, KEY_YEARS, KEY_STREAM_ID, KEY_SUBJECTS, KEY_USERID, KEY_UserName, KEY_MOBILENO, KEY_PROFILE, KEY_UNIVERSITY, KEY_COUNTRY_ID, KEY_CITY_ID, KEY_BIO;

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_discuss, null);
        return view;
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        rview_discussions = findViewById(R.id.recycle_discuss_list);
        tv_title = findViewById(R.id.tv_title);
        userSession = new UserSession(context);

        ll_nested_scroll = findViewById(R.id.ll_nested_scroll_discuss);
        notesBeans = new ArrayList<>();
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_discussions.setLayoutManager(manager);

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


        if (getIntent().getExtras() != null) {
            chapter_id = getIntent().getStringExtra("chapter_id");
            subject_id = getIntent().getStringExtra("subject_id");

            topic = getIntent().getStringExtra("topic");
            read_count = getIntent().getIntExtra("read_count", 0);
            topic_count = getIntent().getIntExtra("topic_count", 0);
            //do here
        }

        tv_title.setText(topic + "(" + read_count + "/" + topic_count + ")");

        callDiscussionData();


    }

    private void callDiscussionData() {
        page_number = 0;
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("education_id", KEY_EDUCATION_ID);
        requestBody.put("stream_id", KEY_STREAM_ID);
        requestBody.put("subject_ids", KEY_SUBJECTS);
        requestBody.put("chapter_id", "0");
        requestBody.put("count", String.valueOf(page_number));
        if (Variables.search_text != null) {
            requestBody.put("search", Variables.search_text);
        }
        presenter.getDiscussions(context, this, requestBody);

    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {


        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                //   Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, context);

            } else if (NetworkConstants.RequestCode.DISCUSSIONS_DATA == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {

                    rview_discussions.setVisibility(View.VISIBLE);
                    discussionsPojo = new Gson().fromJson(responseString, DiscussionsPojo.class);
                    notesBeans.addAll(discussionsPojo.getResponse());


                    if (page_number == 0) {
                        discussionsAdapter = new DiscussionsAdapter(notesBeans, context);
                        rview_discussions.setAdapter(discussionsAdapter);
                        discussionsAdapter.notifyDataSetChanged();
                    } else {
                        discussionsAdapter.notifyDataSetChanged();
                    }


                    ll_nested_scroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                        @Override
                        public void onScrollChanged() {
                            View view = (View) ll_nested_scroll.getChildAt(ll_nested_scroll.getChildCount() - 1);

                            int diff = (view.getBottom() - (ll_nested_scroll.getHeight() + ll_nested_scroll
                                    .getScrollY()));

                            if (diff == 0) {
                                // your pagination code


                                page_number++;
                                perfirmPagination();

                            }
                        }
                    });


                    discussionsAdapter.setOnItemClickListener(new DiscussionsAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            selected_position = position;
                            switch (view.getId()) {
                                case R.id.ll_discuss_comment:
                                    Bundle bundle = new Bundle();
                                    bundle.putString("title", notesBeans.get(position).getChapter());
                                    bundle.putString("subtitle", notesBeans.get(position).getChapter());
                                    bundle.putString("description", notesBeans.get(position).getDescription());
                                    bundle.putString("ping_count", notesBeans.get(position).getPings());
                                    bundle.putString("comments_count", notesBeans.get(position).getComments());
                                    bundle.putString("views_count", notesBeans.get(position).getViews());
                                    bundle.putString("topic_id", notesBeans.get(position).getId());
                                    bundle.putString("sending_through", "discuss");
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_NOTES_DETAILING_SCREEN, bundle);

                                    break;
                                case R.id.tv_discuss_comments_count:
                                    Bundle bundle_comment = new Bundle();
                                    bundle_comment.putString("title", notesBeans.get(position).getChapter());
                                    bundle_comment.putString("subtitle", notesBeans.get(position).getChapter());
                                    bundle_comment.putString("description", notesBeans.get(position).getDescription());
                                    bundle_comment.putString("ping_count", notesBeans.get(position).getPings());
                                    bundle_comment.putString("comments_count", notesBeans.get(position).getComments());
                                    bundle_comment.putString("views_count", notesBeans.get(position).getViews());
                                    bundle_comment.putString("topic_id", notesBeans.get(position).getId());
                                    bundle_comment.putString("sending_through", "discuss");
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_NOTES_DETAILING_SCREEN, bundle_comment);

                                    break;
                                case R.id.tv_discuss_user_post:
                                    Bundle bundle_comment_ = new Bundle();
                                    bundle_comment_.putString("title", notesBeans.get(position).getChapter());
                                    bundle_comment_.putString("subtitle", notesBeans.get(position).getChapter());
                                    bundle_comment_.putString("description", notesBeans.get(position).getDescription());
                                    bundle_comment_.putString("ping_count", notesBeans.get(position).getPings());
                                    bundle_comment_.putString("comments_count", notesBeans.get(position).getComments());
                                    bundle_comment_.putString("views_count", notesBeans.get(position).getViews());
                                    bundle_comment_.putString("topic_id", notesBeans.get(position).getId());
                                    bundle_comment_.putString("sending_through", "discuss");
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_NOTES_DETAILING_SCREEN, bundle_comment_);

                                    break;
                                case R.id.ll_discuss_unread:
                                    callNotesReadStatus();
                                    break;

                                case R.id.ll_discuss_ping:
                                    Bundle bundle_ping = new Bundle();
                                    bundle_ping.putString("sections_id", notesBeans.get(position).getId());
                                    bundle_ping.putString("type", "discuss");
                                    if (notesBeans.get(position).getDescription().length() > 70) {
                                        bundle_ping.putString("content", notesBeans.get(position).getDescription().substring(0, 70));
                                    } else {
                                        bundle_ping.putString("content", notesBeans.get(position).getDescription());
                                    }

                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PING_TO_FRIENDS_GROUPS_SCREEN, bundle_ping);

                            }

                        }
                    });
                } else {
                    //Toast.makeText(getActivity(), "No Data for selected Category", Toast.LENGTH_SHORT).show();
                    rview_discussions.setVisibility(View.GONE);
                    discussionsAdapter.notifyDataSetChanged();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callNotesReadStatus() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("subject_id", notesBeans.get(selected_position).getSubject_id());
        requestBody.put("chapter_id", notesBeans.get(selected_position).getChapter());
        requestBody.put("sections_id", notesBeans.get(selected_position).getId());
        requestBody.put("type", "discuss");
        presenter.submit_as_read(context, this, requestBody);

    }

    private void perfirmPagination() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("education_id", KEY_EDUCATION_ID);
        requestBody.put("stream_id", KEY_STREAM_ID);
        requestBody.put("subject_ids", subject_id);
        requestBody.put("chapter_id", chapter_id);
        requestBody.put("count", String.valueOf(page_number));

        presenter.getChapters(context, this, requestBody);
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
