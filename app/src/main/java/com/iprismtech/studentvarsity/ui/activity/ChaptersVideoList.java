package com.iprismtech.studentvarsity.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.ChapterVideoListAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.dao.AdapterCallBack;
import com.iprismtech.studentvarsity.dao.VideoCallBack;
import com.iprismtech.studentvarsity.mvp.contract.activity.ChaptersVideoListContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.ChapterVideoListlmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.VideosListPOJO;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChaptersVideoList extends BaseAbstractActivity<ChapterVideoListlmpl> implements ChaptersVideoListContract.IView, APIResponseCallback, VideoCallBack {
    APIResponseCallback apiResponseCallback;
    private UserSession userSession;
    private String token, user_id;
    Intent intent;
    String chapter_id = "", subject_id = "", topic = "";
    int read_count, topic_count;
    private VideosListPOJO departmentPOJO;
    private List<VideosListPOJO.ResponseBean> videosList;
    private TextView tv_title;
    ImageView im_back, iv_auto_play;
    private String KEY_USERID, KEY_TOKEN, KEY_EDUCATION_ID, KEY_STREAM_ID, KEY_SUBJECTS;

    RecyclerView recycle_videolist;
    ChapterVideoListAdapter chapterVideoListAdapter;
    private int selected_postion;
    private NestedScrollView ll_nested_scroll;

    VideoCallBack videoCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        // setContentView(R.layout.activity_chapters_video_list);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_chapters_video_list, null);
        return view;
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        apiResponseCallback = this;
        videoCallBack = this;
        videosList = new ArrayList<>();
        recycle_videolist = findViewById(R.id.recycle_videolist);
        im_back = findViewById(R.id.im_back);
        tv_title = findViewById(R.id.tv_title);
        //iv_auto_play = findViewById(R.id.iv_auto_play);
        userSession = new UserSession(this);
        KEY_USERID = userSession.getUserDetails().get("id");
        KEY_TOKEN = userSession.getUserDetails().get("token");
        KEY_EDUCATION_ID = userSession.getUserDetails().get("education_id");
        KEY_STREAM_ID = userSession.getUserDetails().get("stream_id");
        KEY_SUBJECTS = userSession.getUserDetails().get("subjects");
        ll_nested_scroll = findViewById(R.id.ll_nested_scroll_videos_list);
        if (getIntent().getExtras() != null) {
            chapter_id = getIntent().getStringExtra("chapter_id");
            subject_id = getIntent().getStringExtra("subject_id");
            //do here
            topic = getIntent().getStringExtra("topic");

            read_count = getIntent().getIntExtra("read_count", 0);
            topic_count = getIntent().getIntExtra("topic_count", 0);
        }
        tv_title.setText(topic + "(" + read_count + "/" + topic_count + ")");
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", KEY_TOKEN);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("education_id", KEY_EDUCATION_ID);
        requestBody.put("stream_id", KEY_STREAM_ID);
        requestBody.put("subject_ids", subject_id);
        requestBody.put("chapter_id", chapter_id);
        requestBody.put("count", "0");
        presenter.userLogin(this, apiResponseCallback, requestBody);

        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        videosList = new ArrayList<>();
        chapterVideoListAdapter.notifyDataSetChanged();
        chapterVideoListAdapter.video_position=0;
        finish();
    }

    @Override
    public void setPresenter() {
        presenter = new ChapterVideoListlmpl(this, this);
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
            } else if (NetworkConstants.RequestCode.VIDEOS == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    departmentPOJO = new Gson().fromJson(responseString, VideosListPOJO.class);
                    videosList.addAll(departmentPOJO.getResponse());

                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
                    //setting horizontal list
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycle_videolist.setLayoutManager(mLayoutManager);
                    recycle_videolist.setItemAnimator(new DefaultItemAnimator());
                    //Adding Adapter to recyclerView
                    chapterVideoListAdapter = new ChapterVideoListAdapter(videosList, this, videoCallBack);
                    recycle_videolist.setAdapter(chapterVideoListAdapter);

                    chapterVideoListAdapter.setOnItemClickListener(new ChapterVideoListAdapter.OnitemClickListener() {
                        @Override

                        public void onItemClick(View view, int position) {
                            selected_postion = position;
                            switch (view.getId()) {
                                case R.id.tv_heading:
                                    Bundle bundle2 = new Bundle();
                                    bundle2.putString("video_id", videosList.get(position).getId());


                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VIDEODETAIL_SCREEN, bundle2);
                                    break;

                                case R.id.ll_ping:
                                    Bundle bundle_ping = new Bundle();
                                    bundle_ping.putString("sections_id", videosList.get(position).getId());
                                    bundle_ping.putString("type", "videos");

                                    if (videosList.get(position).getDescription().length() > 70) {
                                        bundle_ping.putString("content", videosList.get(position).getDescription().substring(0, 70));
                                    } else {
                                        bundle_ping.putString("content", videosList.get(position).getDescription());
                                    }

                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PING_TO_FRIENDS_GROUPS_SCREEN, bundle_ping);
                                    break;
                                case R.id.ll_comment:
                                    Bundle bundle_commnet = new Bundle();
                                    bundle_commnet.putString("video_id", videosList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VIDEODETAIL_SCREEN, bundle_commnet);

                                    break;
                                case R.id.tv_comments_count:
                                    Bundle bundle_commnet_ = new Bundle();
                                    bundle_commnet_.putString("video_id", videosList.get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VIDEODETAIL_SCREEN, bundle_commnet_);
                                    break;
                                case R.id.ll_unread:
                                    callVideossReadStatus();
                                    break;
                            }
                        }
                    });
                }
            } else if (NetworkConstants.RequestCode.SUBMIT_AS_READ == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    videosList.get(selected_postion).setReaded("yes");
                    chapterVideoListAdapter.notifyItemChanged(selected_postion);
                    chapterVideoListAdapter.notifyDataSetChanged();
                    read_count = read_count + 1;
                    tv_title.setText(topic + "(" + read_count + "/" + topic_count + ")");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void callVideossReadStatus() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", KEY_TOKEN);
        requestBody.put("user_id", KEY_USERID);
        requestBody.put("subject_id", videosList.get(selected_postion).getSubject_id());
        requestBody.put("chapter_id", videosList.get(selected_postion).getChapter_id());
        requestBody.put("sections_id", videosList.get(selected_postion).getId());
        requestBody.put("type", "videos");
        presenter.submit_as_read(ChaptersVideoList.this, this, requestBody);

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }

    @Override
    public void clickevent(String id, int checked) {
        recycle_videolist.scrollToPosition(checked); //use to focus the item with index
        chapterVideoListAdapter.notifyItemChanged(checked);

    }
}
