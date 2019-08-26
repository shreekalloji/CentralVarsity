package com.iprismtech.studentvarsity.ui.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.CreateGroupAdapter;
import com.iprismtech.studentvarsity.adapters.SeletctedPeopleAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.base.BaseAbstractActivity;
import com.iprismtech.studentvarsity.mvp.contract.activity.CreateNewGroupContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.CreateNewGroupImpl;
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

public class NewGroupActivity extends BaseAbstractActivity<CreateNewGroupImpl> implements CreateNewGroupContract.IView, View.OnClickListener, APIResponseCallback {


    private RecyclerView rview_list;
    private ImageView ic_radio;
    private LinearLayoutManager manager;
    private FriendsPojo friendsPojo;
    private CreateGroupAdapter createGroupAdapter;
    private TextView tv_create_group, tv_cancel,tv_no_members;
    ArrayList<FriendsPojo.ResponseBean> selectedList = new ArrayList<>();
    private StringBuilder selectedidsBilder;
    private EditText et_grp_name, et_search;
    private List<FriendsPojo.ResponseBean> friendsList;
    private RecyclerView rview_selected_people;
    private SeletctedPeopleAdapter seletctedPeopleAdapter;
    private String selected_ids_str, token, user_id;
    private UserSession userSession;


    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_new_group, null);
        return view;
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        rview_list = findViewById(R.id.rview_list);
        rview_selected_people = findViewById(R.id.rview_selected_people);
        ic_radio = findViewById(R.id.ic_radio);
        tv_create_group = findViewById(R.id.tv_create_group);
        et_grp_name = findViewById(R.id.et_grp_name);
        tv_cancel = findViewById(R.id.tv_cancel);
        et_search = findViewById(R.id.et_search);
        tv_no_members = findViewById(R.id.tv_no_members);

        userSession = new UserSession(NewGroupActivity.this);

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");

        manager = new LinearLayoutManager(NewGroupActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_list.setLayoutManager(manager);

        rview_selected_people.setLayoutManager(new GridLayoutManager(NewGroupActivity.this, 3));
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        presenter.getList(NewGroupActivity.this, this, requestBody);


        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_search.getText().toString().length() > 1) {
                    createGroupAdapter.getFilter().filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        tv_create_group.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_create_group:
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
                    //Log.d("selected_", selectedidsBilder.toString() + "  " + selected_ids_str);
                    callCreateGrpWs();
                }
                break;
            case R.id.tv_cancel:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PING_GROUPS_LIST_SCREEN);
                finish();
                break;
        }
    }

    private void callCreateGrpWs() {
//        {
//            "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
//                "title":"Centralvarsity",
//                "group_admin_id":"1",
//                "member_ids":"2,3,4"
//        }
        if (et_grp_name.getText().toString().length() < 1 || et_grp_name.getText().toString().equalsIgnoreCase(" ")) {
            Toast.makeText(this, "Please enter Group name", Toast.LENGTH_SHORT).show();
        } else {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            requestBody.put("title", et_grp_name.getText().toString());
            requestBody.put("group_admin_id", user_id);
            requestBody.put("member_ids", selected_ids_str);
            presenter.createGroup(NewGroupActivity.this, this, requestBody);

        }
    }

    @Override
    public void setPresenter() {
        presenter = new CreateNewGroupImpl(this, this);

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

                    createGroupAdapter = new CreateGroupAdapter(NewGroupActivity.this, friendsList);
                    rview_list.setAdapter(createGroupAdapter);
                    if (friendsList.size() > 0) {
                        et_search.setVisibility(View.VISIBLE);
                        tv_create_group.setVisibility(View.VISIBLE);
                        tv_no_members.setVisibility(View.GONE);
                    } else {
                        et_search.setVisibility(View.GONE);
                        tv_create_group.setVisibility(View.GONE);
                        tv_no_members.setVisibility(View.VISIBLE);
                    }
//                    createGroupAdapter.setOnItemClickListener(new CreateGroupAdapter.OnitemClickListener() {
//                        @Override
//                        public void onItemClick(View view, int position) {
//                            switch (view.getId()) {
//                                case R.id.ic_radio:
//                                    try {
//                                        if (createGroupAdapter.getselectedList().size() > 0) {
//                                            selectedList = createGroupAdapter.getselectedList();
//                                        }
//                                        if (selectedList.size() > 0) {
//                                            seletctedPeopleAdapter = new SeletctedPeopleAdapter(NewGroupActivity.this, selectedList);
//                                            rview_selected_people.setAdapter(seletctedPeopleAdapter);
//                                            seletctedPeopleAdapter.notifyDataSetChanged();
//                                        }
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//
//                                    break;
//                            }
//                        }
//                    });
                }
            } else if (NetworkConstants.RequestCode.CREATE_GROUP == requestId) {
                boolean status = jsonObject.getBoolean("status");
                if (status == true) {
                    Toast.makeText(context, "Group Created Successfully", Toast.LENGTH_SHORT).show();
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PING_GROUPS_LIST_SCREEN);
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
}
