package com.iprismtech.studentvarsity.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.CreateGroupAdapter;
import com.iprismtech.studentvarsity.adapters.PingToGroupAdapter;
import com.iprismtech.studentvarsity.mvp.contract.activity.PingToFGriendsGroupContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.PingToFGriendsGroupImpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.FriendsPojo;
import com.iprismtech.studentvarsity.pojos.PingGroupsListPojo;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PingToFriendsGroupActivity extends BaseAbstractActivity<PingToFGriendsGroupImpl> implements PingToFGriendsGroupContract.IView, View.OnClickListener, APIResponseCallback {

    private TextView tv_frnds, tv_ping_grups, tv_ping;
    private RecyclerView rview_ping_to_frds, rview_ping_grp;
    private LinearLayoutManager manager;
    private FriendsPojo friendsPojo;
    private PingGroupsListPojo pingGroupsListPojo;
    private List<FriendsPojo.ResponseBean> friendsList;
    private CreateGroupAdapter createGroupAdapter;
    ArrayList<FriendsPojo.ResponseBean> selectedList = new ArrayList<>();
    private StringBuilder selectedidsBilder;
    private String selected_ids_str;
    private LinearLayout linearlayout_frds;

    private PingToGroupAdapter pingToGroupAdapter;
    private List<PingGroupsListPojo.ResponseBean> groupssList;
    ArrayList<PingGroupsListPojo.ResponseBean> selectedGrpList = new ArrayList<>();
    private StringBuilder selected_grp_idsBilder;
    private String selected_grp_ids_str;
    private String token, user_id, user_profile_pic, user_name, user_university, user_bio, stream, years, user_subjects;

    private String tab_selected_str = "friends";
    private Bundle bundle;
    private RadioButton rb_ping_all;
    private String sections_id, type, content;
    private ImageView iv_back;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_frnds:
                tab_selected_str = "friends";
                linearlayout_frds.setVisibility(View.VISIBLE);
                tv_frnds.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_txt_bg));
                tv_ping_grups.setBackgroundColor(getResources().getColor(R.color.dark_red));
                tv_frnds.setTextColor(Color.BLACK);
                tv_ping_grups.setTextColor(Color.WHITE);
                tv_ping_grups.setBackgroundResource(R.drawable.dark_red);

                rview_ping_grp.setVisibility(View.GONE);
                rview_ping_to_frds.setVisibility(View.VISIBLE);
                manager = new LinearLayoutManager(PingToFriendsGroupActivity.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rview_ping_to_frds.setLayoutManager(manager);


                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("token", token);
                requestBody.put("user_id", user_id);
                presenter.friendsList(PingToFriendsGroupActivity.this, this, requestBody);


                break;
            case R.id.iv_back:
                onBackPressed();
                finish();
                break;
            case R.id.tv_ping_grups:

                rview_ping_grp.setVisibility(View.VISIBLE);
                rview_ping_to_frds.setVisibility(View.GONE);

                linearlayout_frds.setVisibility(View.GONE);
                tab_selected_str = "groups";
                tv_ping_grups.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_txt_bg));
                tv_frnds.setBackgroundColor(getResources().getColor(R.color.dark_red));
                tv_frnds.setTextColor(Color.WHITE);
                tv_ping_grups.setTextColor(Color.BLACK);
                tv_frnds.setBackgroundResource(R.drawable.dark_red);
                manager = new LinearLayoutManager(PingToFriendsGroupActivity.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rview_ping_grp.setLayoutManager(manager);


                Map<String, String> requestBody_group = new HashMap<>();
                requestBody_group.put("token", token);
                requestBody_group.put("user_id", user_id);
                presenter.groupsList(PingToFriendsGroupActivity.this, this, requestBody_group);
                break;

            case R.id.tv_ping:

                if (tab_selected_str.equalsIgnoreCase("friends")) {
                    String selectedid = "";
                    selectedList = createGroupAdapter.getselectedList();
                    selectedidsBilder = new StringBuilder();
                    if (selectedList != null && selectedList.size() > 0) {
                        for (int i = 0; i < selectedList.size(); i++) {
                            selectedid = selectedList.get(i).getUser_id() + ",";
                            selectedidsBilder.append(selectedid);
                        }

                        selected_ids_str = selectedidsBilder.toString();
                        selected_ids_str = selected_ids_str.substring(0, selected_ids_str.length() - 1);
                        // Log.d("selected_", selectedidsBilder.toString() + "  " + selected_ids_str);
                        callPingtoFrdWs();
                    } else {
                        if (rb_ping_all.isChecked()) {
                            for (int i = 0; i < friendsPojo.getResponse().size(); i++) {
                                selectedid = friendsPojo.getResponse().get(i).getUser_id() + ",";
                                selectedidsBilder.append(selectedid);
                            }
                            selected_ids_str = selectedidsBilder.toString();
                            selected_ids_str = selected_ids_str.substring(0, selected_ids_str.length() - 1);
                            callPingtoFrdWs();

                        } else {
                            Toast.makeText(context, "Please select people", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    String selectedid = "";
                    selectedGrpList = pingToGroupAdapter.getselectedList();
                    selected_grp_idsBilder = new StringBuilder();
                    if (selectedGrpList != null && selectedGrpList.size() > 0) {
                        for (int i = 0; i < selectedGrpList.size(); i++) {
                            selectedid = selectedGrpList.get(i).getUser_id() + ",";
                            selected_grp_idsBilder.append(selectedid);
                        }

                        selected_grp_ids_str = selected_grp_idsBilder.toString();
                        selected_grp_ids_str = selected_grp_ids_str.substring(0, selected_grp_ids_str.length() - 1);
                        // Log.d("selected_", selectedidsBilder.toString() + "  " + selected_ids_str);
                        callPingtoFGrpWs();
                    }
                    break;
                }
        }
    }

    private void callPingtoFGrpWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("sections_id", sections_id);
        requestBody.put("type", type);
        // requestBody.put("ping_to_id", selected_ids_str);
        requestBody.put("ping_to_id", selected_grp_ids_str);
        requestBody.put("ping_type", "group");
        requestBody.put("name", user_name);
        requestBody.put("discussion", content);
        presenter.submit_ping(PingToFriendsGroupActivity.this, this, requestBody);

    }

    private void callPingtoFrdWs() {
/*{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
    "user_id":"1",
    "sections_id":"1",
    "ping_to_id":"2",
    "type":"notes" (or) “videos” (or) “faqs” (or) “mcqs” (or) “quiz” (or) “discuss” (or) “activity”,
    "ping_type":"group",
    "name":"John Doe",
    "discussion":"Lorem ipsum dolor sit amet, consectetur adipiscing elit"
}
*/

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("sections_id", sections_id);
        requestBody.put("type", type);
        //requestBody.put("ping_to_id", selected_grp_ids_str);
        requestBody.put("ping_to_id", selected_ids_str);
        requestBody.put("ping_type", "friends");
        requestBody.put("name", user_name);
        requestBody.put("discussion", content);
        presenter.submit_ping(PingToFriendsGroupActivity.this, this, requestBody);
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        tv_frnds.setOnClickListener(this);
        tv_ping_grups.setOnClickListener(this);
        tv_ping.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        tv_frnds = findViewById(R.id.tv_frnds);
        tv_ping_grups = findViewById(R.id.tv_ping_grups);
        rview_ping_grp = findViewById(R.id.rview_ping_grp);
        rview_ping_to_frds = findViewById(R.id.rview_ping_to_frds);
        tv_ping = findViewById(R.id.tv_ping);
        linearlayout_frds = findViewById(R.id.linearlayout_frds);
        rb_ping_all = findViewById(R.id.rb_ping_all);
        iv_back = findViewById(R.id.iv_back);

        userSession = new UserSession(PingToFriendsGroupActivity.this);


        user_id = userSession.getUserDetails().get("id");
        token = userSession.getUserDetails().get("token");
        user_name = userSession.getUserDetails().get("name");
        user_university = userSession.getUserDetails().get("university");
        user_bio = userSession.getUserDetails().get("bio");
        user_profile_pic = userSession.getUserDetails().get("profileImg");
        stream = userSession.getUserDetails().get("stream");
        years = userSession.getUserDetails().get("years");
        user_subjects = userSession.getUserDetails().get("subject_names");


        linearlayout_frds.setVisibility(View.VISIBLE);

        bundle = getIntent().getExtras();
        if (bundle != null) {

            sections_id = bundle.getString("sections_id");
            type = bundle.getString("type");
            content = bundle.getString("content");
        }
        tab_selected_str = "friends";
        linearlayout_frds.setVisibility(View.VISIBLE);
        tv_frnds.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_txt_bg));
        tv_ping_grups.setBackgroundColor(getResources().getColor(R.color.dark_red));
        tv_frnds.setTextColor(Color.BLACK);
        tv_ping_grups.setTextColor(Color.WHITE);


        rview_ping_grp.setVisibility(View.GONE);
        rview_ping_to_frds.setVisibility(View.VISIBLE);
        manager = new LinearLayoutManager(PingToFriendsGroupActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_ping_to_frds.setLayoutManager(manager);


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        presenter.friendsList(PingToFriendsGroupActivity.this, this, requestBody);

        rb_ping_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

    }

    @Override
    public void setPresenter() {
        presenter = new PingToFGriendsGroupImpl(this, this);
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
                  Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false,context);

            } else if (NetworkConstants.RequestCode.MY_FRIENDS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    friendsPojo = new Gson().fromJson(responseString, FriendsPojo.class);
                    friendsList = friendsPojo.getResponse();
                    createGroupAdapter = new CreateGroupAdapter(PingToFriendsGroupActivity.this, friendsList);
                    rview_ping_to_frds.setAdapter(createGroupAdapter);
                }
            } else if (NetworkConstants.RequestCode.GET_PING_GROUPS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    pingGroupsListPojo = new Gson().fromJson(responseString, PingGroupsListPojo.class);
                    groupssList = pingGroupsListPojo.getResponse();
                    pingToGroupAdapter = new PingToGroupAdapter(PingToFriendsGroupActivity.this, groupssList);
                    rview_ping_grp.setAdapter(pingToGroupAdapter);
                }
            } else if (NetworkConstants.RequestCode.SUBMIT_PING == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_ping_friends_group, null);
        return view;
    }
}
