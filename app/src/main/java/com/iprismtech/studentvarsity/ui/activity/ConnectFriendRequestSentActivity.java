package com.iprismtech.studentvarsity.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.FriendsAdapter;
import com.iprismtech.studentvarsity.adapters.GetusersfromcontactsAdapter;
import com.iprismtech.studentvarsity.mvp.contract.activity.ConnectFriendRequestSentActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.ConnectFriendRequestSentActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.GetusersfromcontactsPOJO;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ConnectFriendRequestSentActivity extends BaseAbstractActivity<ConnectFriendRequestSentActivitylmpl> implements ConnectFriendRequestSentActivityContract.IView, View.OnClickListener, APIResponseCallback {
    APIResponseCallback apiResponseCallback;
    private UserSession userSession;
    private String token, user_id;
    RecyclerView rv_connectfriends;
    FriendsAdapter adapter;

    EditText search_bar;
    GetusersfromcontactsPOJO yearPOJO;
    ArrayList<String> friendscount = new ArrayList<>();
    int selectedpos;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        //setContentView(R.layout.activity_connect_university_frds);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_connect_friend_request_sent, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new ConnectFriendRequestSentActivitylmpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        userSession = new UserSession(ConnectFriendRequestSentActivity.this);

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");

        search_bar = findViewById(R.id.search_bar);

        rv_connectfriends = findViewById(R.id.rv_connectfriends);
        iv_back = findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
//
//                adapter.getFilter().filter(s);
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
//                adapter.getFilter().filter(s);
//                adapter.notifyDataSetChanged();
            }
        });

        if (token != null || !token.isEmpty()) {
//                {
//                    "token":
//                    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlmYjVhZWE4ODZjYWY3ZjNjNTRiMDcyNTljMWJlNmVhIn0.0SgRJrqCZfmAl9EVLGMChgK-NzyBgqjJFNL05TJc-vs",
//                            "mobile_numbers":["9999999999", "7777777777"]
//                }
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            requestBody.put("user_id", user_id);
            presenter.friend_requests_sent(ConnectFriendRequestSentActivity.this, apiResponseCallback, requestBody);
        }


    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {

            Log.d("registerResponse", responseString);
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equalsIgnoreCase("5000")) {
                //todo: if network is not availiable then show some dialog box
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false,context);
            } else if (NetworkConstants.RequestCode.FRIEND_REQUEST_SENT == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Util.getInstance().cusToast(context, message);

                    yearPOJO = new Gson().fromJson(responseString, GetusersfromcontactsPOJO.class);
                    if (yearPOJO.getResponse().size() > 0) {
                        for (int i = 0; i < yearPOJO.getResponse().size(); i++) {
                            friendscount.add(i, "0");
                        }
                    }

                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(ConnectFriendRequestSentActivity.this);
                    //setting horizontal list
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rv_connectfriends.setLayoutManager(mLayoutManager);
                    rv_connectfriends.setItemAnimator(new DefaultItemAnimator());

                    //Adding Adapter to recyclerView
                    adapter = new FriendsAdapter(yearPOJO.getResponse(), context);
                    rv_connectfriends.setAdapter(adapter);

                } else {
                    Util.getInstance().cusToast(context, message);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {
        Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, OverviewActivity.class);
        startActivity(intent);
        finish();
    }
}

