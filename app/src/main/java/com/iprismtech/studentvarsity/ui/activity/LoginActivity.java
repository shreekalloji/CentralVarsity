package com.iprismtech.studentvarsity.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.activity.LoginActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.LoginActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseAbstractActivity<LoginActivitylmpl> implements LoginActivityContract.IView, View.OnClickListener, APIResponseCallback {

    EditText edt_loginUser, edt_loginPasscode;
    AppCompatTextView login, tv_forgot;
    ImageView im_back;

    APIResponseCallback apiResponseCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        // setContentView(R.layout.activity_login);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_login, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new LoginActivitylmpl(this, this);
    }


    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        edt_loginUser = findViewById(R.id.edt_loginUser);
        edt_loginPasscode = findViewById(R.id.edt_loginPasscode);
        login = findViewById(R.id.loginbtn);
        im_back = findViewById(R.id.im_back);
        tv_forgot = findViewById(R.id.tv_forgot);
        tv_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FORGOT_SCREEN);
                finishAffinity();
            }
        });
        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
//                startActivity(intent);

                if (isUserCredentialsValid()) {
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("mobile", edt_loginUser.getText().toString().trim());
                    requestBody.put("password", edt_loginPasscode.getText().toString().trim());
                    presenter.userLogin(LoginActivity.this, apiResponseCallback, requestBody);

                }
            }
        });

    }

    private boolean isUserCredentialsValid() {
        if (edt_loginUser.getText().toString().length() == 0) {
            // edt_loginUser.setError("please enter mobile number");
            Toast.makeText(this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
            edt_loginUser.setFocusable(true);
            //todo: Add shake animation
            return false;
        } else if (edt_loginPasscode.getText().toString().length() == 0) {
            //edt_loginPasscode.setError("please enter password");
            Toast.makeText(context, "Please enter password", Toast.LENGTH_SHORT).show();
            edt_loginPasscode.setFocusable(true);
            return false;
        } else if (edt_loginPasscode.getText().length() < 6) {
            Util.getInstance().cusToast(context, "Password have atleast 6 characters");
            edt_loginPasscode.setFocusable(true);
            return false;
        }
        //todo: Set bg color for gender
        else {
            return true;
//            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_HOME_SCREEN);
            //  Toast.makeText(getApplicationContext(), "user not registered with us!", Toast.LENGTH_LONG).show();
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
            } else if (NetworkConstants.RequestCode.USER_LOGIN == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    JSONObject response = jsonObject.optJSONObject("response");
                    Util.getInstance().cusToast(context, "Login Successful");
                    String token = jsonObject.optString("token");
                    String id = response.optString("id");
                    String name = response.optString("name");
                    String university = response.optString("university");
                    String country_id = response.optString("country_id");
                    String city_id = response.optString("city_id");
                    String mobile = response.optString("mobile");
                    String bio = response.optString("bio");
                    String profile_pic = response.optString("profile_pic");
                    String education_id = response.optString("education_id");
                    String stream_id = response.optString("stream_id");
                    String years = response.optString("years");
                    String subjects = response.optString("subjects");
                    String subject_names = response.optString("subject_names");
                    String stream = response.optString("stream");

                    userSession.StoreUserDetails(id, name, mobile, "", "", profile_pic, "", university, token, country_id, city_id, bio, education_id, stream_id, years, subjects, subject_names, stream);

                    if (subjects.isEmpty() || subjects.equalsIgnoreCase("null")) {
                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_WELCOME_SCREEN);
                        finishAffinity();
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putString("comimg_through", "login");
                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN, bundle);
                        finishAffinity();
                    }

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
