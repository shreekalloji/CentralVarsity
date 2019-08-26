package com.iprismtech.studentvarsity.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.CreateGroupAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.base.BaseAbstractActivity;
import com.iprismtech.studentvarsity.mvp.contract.activity.AddPartcipantsContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.AddPartcipantsImpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.FriendsPojo;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddPartcipantsActivity extends BaseAbstractActivity<AddPartcipantsImpl> implements AddPartcipantsContract.IView, View.OnClickListener, APIResponseCallback {
    private RecyclerView rview_list;
    private LinearLayoutManager manager;
    private FriendsPojo friendsPojo;
    private CreateGroupAdapter groupAdapter;
    ArrayList<FriendsPojo.ResponseBean> selectedList = new ArrayList<>();
    private StringBuilder selectedidsBilder;
    private EditText et_grp_name, et_search;
    private List<FriendsPojo.ResponseBean> friendsList;
    private Bundle bundle;
    private String user_ids, grp_id;
    private TextView tv_add_to_group;
    private String selected_ids_str;
    private String token, user_id, user_profile_pic, user_name, user_university, user_bio, stream, years, user_subjects;
    UserSession userSession;
    private TextView tv_no_members;
    private LinearLayout ll_cancel;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_to_group:
                selectedList = groupAdapter.getselectedList();
                selectedidsBilder = new StringBuilder();
                String selectedid;
                if (selectedList != null && selectedList.size() > 0) {
                    for (int i = 0; i < selectedList.size(); i++) {
                        selectedid = selectedList.get(i).getUser_id() + ",";
                        selectedidsBilder.append(selectedid);
                    }

                    selected_ids_str = selectedidsBilder.toString();
                    selected_ids_str = selected_ids_str.substring(0, selected_ids_str.length() - 1);
                    //Log.d("selected_", selectedidsBilder.toString() + "  " + selected_ids_str);
                    callAddtoGrpWs();
                } else {
                    Toast.makeText(context, "Select Group Member", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ll_cancel:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void callAddtoGrpWs() {
        /*{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
    "group_id":"2",
    "member_ids":"5"
}
*/
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("group_id", grp_id);
        requestBody.put("member_ids", selected_ids_str);
        presenter.addParticipants(AddPartcipantsActivity.this, this, requestBody);


    }


    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        tv_add_to_group.setOnClickListener(this);
        ll_cancel.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        rview_list = findViewById(R.id.rview_list);
        et_search = findViewById(R.id.et_search);
        tv_no_members = findViewById(R.id.tv_no_members);
        tv_add_to_group = findViewById(R.id.tv_add_to_group);
        ll_cancel = findViewById(R.id.ll_cancel);
        manager = new LinearLayoutManager(AddPartcipantsActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_list.setLayoutManager(manager);

        userSession = new UserSession(AddPartcipantsActivity.this);

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        user_name = userSession.getUserDetails().get("name");
        user_university = userSession.getUserDetails().get("university");
        user_bio = userSession.getUserDetails().get("bio");
        user_profile_pic = userSession.getUserDetails().get("profileImg");
        stream = userSession.getUserDetails().get("stream_id");
        years = userSession.getUserDetails().get("years");
        user_subjects = userSession.getUserDetails().get("subject_names");


        bundle = getIntent().getExtras();
        if (bundle != null) {
            user_ids = bundle.getString("key_user_ids");
            grp_id = bundle.getString("key_grp_id");
        }


        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_search.getText().toString().length() > 1) {
                    groupAdapter.getFilter().filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        requestBody.put("excluding_ids", user_ids);
        presenter.getnewParList(AddPartcipantsActivity.this, this, requestBody);
    }

    @Override
    public void setPresenter() {
        presenter = new AddPartcipantsImpl(this, this);
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

            } else if (NetworkConstants.RequestCode.ADD_PARTICIPANTS_MEMBERS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {

                    tv_no_members.setVisibility(View.GONE);
                    rview_list.setVisibility(View.VISIBLE);
                    tv_add_to_group.setVisibility(View.VISIBLE);
                    et_search.setVisibility(View.VISIBLE);

                    friendsPojo = new Gson().fromJson(responseString, FriendsPojo.class);
                    friendsList = friendsPojo.getResponse();
                    groupAdapter = new CreateGroupAdapter(AddPartcipantsActivity.this, friendsList);
                    rview_list.setAdapter(groupAdapter);
//
                } else {
                    tv_no_members.setVisibility(View.VISIBLE);
                    rview_list.setVisibility(View.GONE);
                    tv_add_to_group.setVisibility(View.GONE);
                    et_search.setVisibility(View.GONE);
                }
            } else if (NetworkConstants.RequestCode.ADD_PARTICIPANTS_TO_GROUP == requestId) {
                Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PING_GROUPS_LIST_SCREEN);
                finish();
            }


        } catch (
                Exception e)

        {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }

    @Override
    protected View getView() {

        View view = null;
        view = getLayoutInflater().inflate(R.layout.add_partivipant_to_group, null);
        return view;
    }
}
