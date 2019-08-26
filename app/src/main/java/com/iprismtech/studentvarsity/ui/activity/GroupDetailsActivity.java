package com.iprismtech.studentvarsity.ui.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.GroupMembersAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.activity.GroupDetailsContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.GroupDetailsImpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.GroupMembersPojo;
import com.iprismtech.studentvarsity.utils.Util;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GroupDetailsActivity extends BaseAbstractActivity<GroupDetailsImpl> implements GroupDetailsContract.IView, View.OnClickListener, APIResponseCallback {

    private RecyclerView rview_grp_members;
    private LinearLayoutManager manager;
    private GroupMembersPojo groupMembersPojo;
    private String group_id, token, user_id, profile_img;
    private Bundle bundle;
    private GroupMembersAdapter groupMembersAdapter;
    private TextView tv_grp_name, tv_grp_members, tv_admin_name, tv_admin_uni;
    private LinearLayout ll_add_participants;
    private StringBuilder idsBilder;
    private AlertDialog alertDialog;
    private int selected_position;
    private ImageView iv_back, ic_admin_img;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_add_participants:
                Bundle bundle = new Bundle();
                bundle.putString("key_user_ids", idsBilder.toString());
                bundle.putString("key_grp_id", group_id);
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ADD_PARTCIPANTS_SCREEN, bundle);
                break;

            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_group, null);
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        ll_add_participants.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        profile_img = userSession.getUserDetails().get("profileImg");
        rview_grp_members = findViewById(R.id.rview_grp_members);
        tv_grp_members = findViewById(R.id.tv_grp_members);
        tv_grp_name = findViewById(R.id.tv_grp_name);
        tv_admin_name = findViewById(R.id.tv_admin_name);
        tv_admin_uni = findViewById(R.id.tv_admin_uni);
        ll_add_participants = findViewById(R.id.ll_add_participants);
        iv_back = findViewById(R.id.iv_back);
        ic_admin_img = findViewById(R.id.ic_admin_img);
        manager = new LinearLayoutManager(GroupDetailsActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_grp_members.setLayoutManager(manager);


        bundle = getIntent().getExtras();
        if (bundle != null) {
            group_id = bundle.getString("key_grp_id");
        }
//        {
//            "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
//                "group_id":"2"
//        }


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("group_id", group_id);
        presenter.getGrpDetails(GroupDetailsActivity.this, this, requestBody);


    }

    @Override
    public void setPresenter() {
        presenter = new GroupDetailsImpl(this, this);
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

            } else if (NetworkConstants.RequestCode.GET_GROUP_MEMBERS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    groupMembersPojo = new Gson().fromJson(responseString, GroupMembersPojo.class);
                    tv_grp_name.setText(groupMembersPojo.getResponse().get(0).getUniversity());
                    tv_grp_members.setText(groupMembersPojo.getResponse().get(0).getMembers().size() + " Members");

                    Picasso.with(context)
                            .load(NetworkConstants.URL.Imagepath_URL + groupMembersPojo.getResponse().get(0).getProfile_pic())
                            .error(R.drawable.no_image)
                            .into(ic_admin_img);


                    groupMembersAdapter = new GroupMembersAdapter(GroupDetailsActivity.this, groupMembersPojo);
                    rview_grp_members.setAdapter(groupMembersAdapter);
                    groupMembersAdapter.notifyDataSetChanged();

                    groupMembersAdapter.setOnItemClickListener(new GroupMembersAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            selected_position = position;
                            switch (view.getId()) {
                                case R.id.ll_item:
                                    openRemoveDialog();
                                    break;
                            }
                        }
                    });
                    tv_admin_uni.setText(groupMembersPojo.getResponse().get(0).getUniversity());
                    tv_admin_name.setText(groupMembersPojo.getResponse().get(0).getName());

                    idsBilder = new StringBuilder();
                    String selectedid;
                    for (int i = 0; i < groupMembersPojo.getResponse().get(0).getMembers().size(); i++) {
                        selectedid = groupMembersPojo.getResponse().get(0).getMembers().get(i).getUser_id() + ",";
                        idsBilder.append(selectedid);
                    }
                }
            } else if (NetworkConstants.RequestCode.REMOVE_PARTICIPANT == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    Toast.makeText(context, "Removed Successfully", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    groupMembersPojo.getResponse().get(0).getMembers().remove(selected_position);
                    groupMembersAdapter.notifyItemRemoved(selected_position);
                    groupMembersAdapter.notifyItemRangeChanged(selected_position, groupMembersPojo.getResponse().get(0).getMembers().size());

                    groupMembersAdapter.notifyDataSetChanged();
                }
            }

        } catch (
                Exception e)

        {
            e.printStackTrace();
        }

    }

    private void openRemoveDialog() {
        LayoutInflater inflater = LayoutInflater.from(context);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.item_remove_group_dialouge, null);

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setView(view1);
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        alertDialog.setCancelable(true);
        TextView yes, no;
        yes = view1.findViewById(R.id.remove_grup_yes);
        no = view1.findViewById(R.id.tv_no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRemovePersonWS();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void callRemovePersonWS() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("group_id", group_id);
        requestBody.put("user_id", groupMembersPojo.getResponse().get(0).getMembers().get(selected_position).getUser_id());
        presenter.remove_participant(GroupDetailsActivity.this, this, requestBody);


    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
