package com.iprismtech.studentvarsity.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.PingGroupsListAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.base.BaseAbstractActivity;
import com.iprismtech.studentvarsity.mvp.contract.activity.PingFriendsGroupContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.PingFriendsGroupImpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.PingGroupsListPojo;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PingFriendsGroup extends BaseAbstractActivity<PingFriendsGroupImpl> implements PingFriendsGroupContract.IView, View.OnClickListener, APIResponseCallback {

    private RecyclerView rview_ping_grps;
    private LinearLayoutManager manager;
    private PingGroupsListAdapter pingGroupsListAdapter;
    private PingGroupsListPojo pingGroupsListPojo;
    private LinearLayout ll_create_grp;
    private String token, user_id;
    private UserSession userSession;
    private TextView tv_no_grps;
    private ImageView iv_back;


    @Override
    protected void initializeViews() {
        super.initializeViews();


        userSession = new UserSession(PingFriendsGroup.this);

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        rview_ping_grps = findViewById(R.id.rview_ping_grps);
        ll_create_grp = findViewById(R.id.ll_create_grp);
        tv_no_grps = findViewById(R.id.tv_no_grps);
        iv_back = findViewById(R.id.iv_back);

        rview_ping_grps.setLayoutManager(new GridLayoutManager(PingFriendsGroup.this, 3));


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        presenter.groupsList(PingFriendsGroup.this, this, requestBody);

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_ping, null);
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        ll_create_grp.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_create_grp:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_CREATE_GROUP_SCREEN);
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void setPresenter() {
        presenter = new PingFriendsGroupImpl(this, this);
    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                //   Util.getInstance().cusToast(getActivity(), jsonObject.optString("message"));
                  Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false,context);

            } else if (NetworkConstants.RequestCode.GET_PING_GROUPS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    tv_no_grps.setVisibility(View.GONE);
                    rview_ping_grps.setVisibility(View.VISIBLE);
                    pingGroupsListPojo = new Gson().fromJson(responseString, PingGroupsListPojo.class);
                    pingGroupsListAdapter = new PingGroupsListAdapter(PingFriendsGroup.this, pingGroupsListPojo);
                    rview_ping_grps.setAdapter(pingGroupsListAdapter);


                    pingGroupsListAdapter.setOnItemClickListener(new PingGroupsListAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            switch (view.getId()) {
                                case R.id.ll_item_grp:
                                    Bundle bundle = new Bundle();
                                    bundle.putString("key_grp_id", pingGroupsListPojo.getResponse().get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_GROUP_DETAILS_SCREEN, bundle);
                                    break;
                            }
                        }
                    });
                } else {
                    tv_no_grps.setVisibility(View.VISIBLE);
                    rview_ping_grps.setVisibility(View.GONE);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
