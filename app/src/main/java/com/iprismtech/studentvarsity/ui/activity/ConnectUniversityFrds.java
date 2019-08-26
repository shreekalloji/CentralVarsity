package com.iprismtech.studentvarsity.ui.activity;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.adapters.GetusersfromcontactsAdapter;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.activity.ConnectUniversityFrdsContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.ConnectUniversityFrdslmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.pojos.GetusersfromcontactsPOJO;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;
import com.iprismtech.studentvarsity.utils.Variables;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectUniversityFrds extends BaseAbstractActivity<ConnectUniversityFrdslmpl> implements ConnectUniversityFrdsContract.IView, View.OnClickListener, APIResponseCallback {
    TextView next;
    APIResponseCallback apiResponseCallback;
    private UserSession userSession;
    private String token, user_id, user_university;
    RecyclerView rv_connectfriends;
    GetusersfromcontactsAdapter adapter;

    EditText search_bar;

    GetusersfromcontactsPOJO yearPOJO;

    List<GetusersfromcontactsPOJO.ResponseBean> arrayList = new ArrayList<>();
    ArrayList<String> friendscount = new ArrayList<>();
    int selectedpos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        //setContentView(R.layout.activity_connect_university_frds);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_connect_university_frds, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new ConnectUniversityFrdslmpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        userSession = new UserSession(ConnectUniversityFrds.this);

        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");
        user_university = userSession.getUserDetails().get("university");

        search_bar = findViewById(R.id.search_bar);

        rv_connectfriends = findViewById(R.id.rv_connectfriends);

        next = findViewById(R.id.text_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("comimg_through", "registration");
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN, bundle);
            }
        });


        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub


                // filter your list from your input
                adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();
                //you can use runnable postDelayed like 500 ms to delay search text


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();

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
            if (user_university != null) {
                requestBody.put("university", user_university);
            } else {
                requestBody.put("university", Variables.university);
            }


            //requestBody.put("university", "Geetham College & University");

            presenter.get_users_using_university(ConnectUniversityFrds.this, apiResponseCallback, requestBody);
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
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, context);
            } else if (NetworkConstants.RequestCode.GET_USERS_USING_UNIVERSITY == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Util.getInstance().cusToast(context, message);

                    yearPOJO = new Gson().fromJson(responseString, GetusersfromcontactsPOJO.class);
                    arrayList = yearPOJO.getResponse();
                    if (arrayList.size() > 0) {
                        for (int i = 0; i < arrayList.size(); i++) {
                            friendscount.add(i, "0");
                        }
                    }
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(ConnectUniversityFrds.this);
                    //setting horizontal list
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rv_connectfriends.setLayoutManager(mLayoutManager);
                    rv_connectfriends.setItemAnimator(new DefaultItemAnimator());

                    //Adding Adapter to recyclerView
                    adapter = new GetusersfromcontactsAdapter(arrayList, ConnectUniversityFrds.this, friendscount);
                    rv_connectfriends.setAdapter(adapter);

                    adapter.setOnItemClickListener(new GetusersfromcontactsAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(View view, int position, List<GetusersfromcontactsPOJO.ResponseBean> arrayList1) {
                            selectedpos = position;
                            arrayList = arrayList1;
                            //   stream_id = yearPOJO.getStreams().get(position).getId();
                            if (token != null || !token.isEmpty()) {
                                Map<String, String> requestBody = new HashMap<>();
                                requestBody.put("token", token);
                                requestBody.put("user_id", user_id);
                                requestBody.put("friend_id", arrayList1.get(position).getUser_id());
                                presenter.send_friend_request(ConnectUniversityFrds.this, apiResponseCallback, requestBody);
                            }

                        }
                    });

                } else {
                    Util.getInstance().cusToast(context, message);
                }
            } else if (NetworkConstants.RequestCode.SEND_FRIEND_REQUEST == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Util.getInstance().cusToast(context, message);

                    arrayList.get(selectedpos).setChecked(true);
                    adapter.notifyDataSetChanged();

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
}
