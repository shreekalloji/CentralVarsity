package com.iprismtech.studentvarsity.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.NotesAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.activity.NotesActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.NotesActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.NotesPojo;
import com.iprismtech.studentvarsity.pojos.VideosListPOJO;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotesActivty extends BaseAbstractActivity<NotesActivitylmpl> implements NotesActivityContract.IView, APIResponseCallback {
    APIResponseCallback apiResponseCallback;

    UserSession userSession;

    RecyclerView recycle_noteslist;
    private boolean response_status = false;

    ImageView im_back;
    private String token, user_id;
    Intent intent;
    String chapter_id = "", subject_id = "", topic = "";
    int read_count, topic_count;
    private VideosListPOJO departmentPOJO;
    private List<VideosListPOJO.ResponseBean> videosList;

    private LinearLayoutManager manager;
    private NotesAdapter notesAdapter;
    private NotesPojo notesPojo;

    private List<NotesPojo.ResponseBean> notesBeans;

    //  ImageView im_back;
    private String KEY_USERID, KEY_TOKEN, KEY_EDUCATION_ID, KEY_STREAM_ID, KEY_SUBJECTS;

    NestedScrollView ll_nested_scroll;
    private int page_number = 0;

    private int selected_postion;
    private TextView tv_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        // setContentView(R.layout.activity_notes_activty);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_notes_activty, null);
        return view;
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        apiResponseCallback = this;
        userSession = new UserSession(context);
        im_back = findViewById(R.id.im_back);
        recycle_noteslist = findViewById(R.id.recycle_noteslist);
        ll_nested_scroll = findViewById(R.id.ll_nested_scroll_notes);
        tv_title = findViewById(R.id.tv_title);
        notesBeans = new ArrayList<>();
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle_noteslist.setLayoutManager(manager);
        KEY_USERID = userSession.getUserDetails().get("id");
        KEY_TOKEN = userSession.getUserDetails().get("token");
        KEY_EDUCATION_ID = userSession.getUserDetails().get("education_id");
        KEY_STREAM_ID = userSession.getUserDetails().get("stream_id");
        KEY_SUBJECTS = userSession.getUserDetails().get("subjects");
        if (getIntent().getExtras() != null) {
            chapter_id = getIntent().getStringExtra("chapter_id");
            subject_id = getIntent().getStringExtra("subject_id");

            topic = getIntent().getStringExtra("topic");
            read_count = getIntent().getIntExtra("read_count", 0);
            topic_count = getIntent().getIntExtra("topic_count", 0);
            //do here
        }

        tv_title.setText(topic + "(" + read_count + "/" + topic_count + ")");
        callNotesWs();

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

    private void callNotesWs() {


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", KEY_TOKEN);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("education_id", KEY_EDUCATION_ID);
        requestBody.put("stream_id", KEY_STREAM_ID);
        requestBody.put("subject_ids", subject_id);
        requestBody.put("chapter_id", chapter_id);
        presenter.userRegister(this, this, requestBody);
    }

    private void callNotesReadStatus() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", KEY_TOKEN);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("subject_id", notesBeans.get(selected_postion).getSubject_id());
        requestBody.put("chapter_id", notesBeans.get(selected_postion).getChapter_id());
        requestBody.put("sections_id", notesBeans.get(selected_postion).getId());
        requestBody.put("type", "notes");
        presenter.submit_as_read(this, this, requestBody);

    }


    @Override
    public void setPresenter() {
        presenter = new NotesActivitylmpl(this, this);
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

            } else if (NetworkConstants.RequestCode.GET_NOTES_DATA == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {

                    response_status = true;

                    notesPojo = new Gson().fromJson(responseString, NotesPojo.class);
                    notesBeans.addAll(notesPojo.getResponse());

                    notesAdapter = new NotesAdapter(this, notesBeans);
                    recycle_noteslist.setAdapter(notesAdapter);
                    notesAdapter.notifyDataSetChanged();


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

                    notesAdapter.setOnItemClickListener(new NotesAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            selected_postion = position;
                            switch (view.getId()) {

                                case R.id.ll_topic:
                                    Bundle bundle = new Bundle();
                                    bundle.putString("title", notesBeans.get(position).getHeading());
                                    bundle.putString("subtitle", notesBeans.get(position).getHeading());
                                    bundle.putString("description", notesBeans.get(position).getDescription());
                                    bundle.putString("ping_count", notesBeans.get(position).getPings());
                                    bundle.putString("comments_count", notesBeans.get(position).getComments());
                                    bundle.putString("views_count", notesBeans.get(position).getViews());
                                    bundle.putString("sending_through", "Notes");
                                    bundle.putString("topic_id", notesBeans.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_NOTES_DETAILING_SCREEN, bundle);

                                    break;
                                case R.id.ll_ping:
                                    Bundle bundle_ping = new Bundle();
                                    bundle_ping.putString("sections_id", notesBeans.get(position).getId());
                                    bundle_ping.putString("type", "notes");

                                    if (notesBeans.get(position).getDescription().length() > 70) {
                                        bundle_ping.putString("content", notesBeans.get(position).getDescription().substring(0, 70));
                                    } else {
                                        bundle_ping.putString("content", notesBeans.get(position).getDescription());
                                    }
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PING_TO_FRIENDS_GROUPS_SCREEN, bundle_ping);
                                    break;
                                case R.id.ll_comment:
                                    Bundle bundle_comment = new Bundle();
                                    bundle_comment.putString("title", notesBeans.get(position).getHeading());
                                    bundle_comment.putString("subtitle", notesBeans.get(position).getHeading());
                                    bundle_comment.putString("description", notesBeans.get(position).getDescription());
                                    bundle_comment.putString("ping_count", notesBeans.get(position).getPings());
                                    bundle_comment.putString("comments_count", notesBeans.get(position).getComments());
                                    bundle_comment.putString("views_count", notesBeans.get(position).getViews());
                                    bundle_comment.putString("sending_through", "Notes");
                                    bundle_comment.putString("topic_id", notesBeans.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_NOTES_DETAILING_SCREEN, bundle_comment);

                                    break;
                                case R.id.ll_unread:

                                    callNotesReadStatus();
                                    break;
                                case R.id.tv_comments_count:
                                    Bundle bundle_comment_ = new Bundle();
                                    bundle_comment_.putString("title", notesBeans.get(position).getHeading());
                                    bundle_comment_.putString("subtitle", notesBeans.get(position).getHeading());
                                    bundle_comment_.putString("description", notesBeans.get(position).getDescription());
                                    bundle_comment_.putString("ping_count", notesBeans.get(position).getPings());
                                    bundle_comment_.putString("comments_count", notesBeans.get(position).getComments());
                                    bundle_comment_.putString("views_count", notesBeans.get(position).getViews());
                                    bundle_comment_.putString("sending_through", "Notes");
                                    bundle_comment_.putString("topic_id", notesBeans.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_NOTES_DETAILING_SCREEN, bundle_comment_);

                                    break;
                            }
                        }
                    });

                }
            } else if (NetworkConstants.RequestCode.SUBMIT_AS_READ == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    notesBeans.get(selected_postion).setReaded("yes");
                    notesAdapter.notifyItemChanged(selected_postion);
                    read_count = read_count + 1;
                    tv_title.setText(topic + "(" + read_count + "/" + topic_count + ")");
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void perfirmPagination() {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", KEY_TOKEN);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("education_id", KEY_EDUCATION_ID);
        requestBody.put("stream_id", KEY_STREAM_ID);
        requestBody.put("subject_ids", subject_id);
        requestBody.put("chapter_id", chapter_id);
        requestBody.put("count", String.valueOf(page_number));
       /* if (Variables.search_text != null) {
            requestBody.put("search", Variables.search_text);
        }*/
        presenter.userRegister(this, this, requestBody);

    }


    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
