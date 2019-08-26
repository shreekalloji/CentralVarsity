package com.iprismtech.studentvarsity.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.customviews.CustomTextViewNormal;
import com.iprismtech.studentvarsity.mvp.contract.activity.ResetPasswordActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.ResetPasswordActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResetPasswordActivity extends BaseAbstractActivity<ResetPasswordActivitylmpl> implements ResetPasswordActivityContract.IView, View.OnClickListener, APIResponseCallback {

    APIResponseCallback apiResponseCallback;

    private UserSession userSession;
    private String token;
    CustomTextViewNormal btn_save;
    EditText et_newpass, et_confpass;
    TextView tv_title;
    private Bundle bundle;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        // setContentView(R.layout.activity_forgot_password);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_reset_password, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new ResetPasswordActivitylmpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();


        userSession = new UserSession(ResetPasswordActivity.this);

        apiResponseCallback = this;

        tv_title = findViewById(R.id.tv_title);
        btn_save = findViewById(R.id.btn_save);
        et_newpass = findViewById(R.id.et_newpass);
        et_confpass = findViewById(R.id.et_confpass);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            user_id = bundle.getString("key_id");
        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String set_newpass = et_newpass.getText().toString().trim();
                String set_confpass = et_confpass.getText().toString().trim();
                if (set_newpass.length() == 0) {
                    // et_newpass.setError("please enter new password");
                    Toast.makeText(context, "Please enter new password", Toast.LENGTH_SHORT).show();

                } else if (set_newpass.length() < 6) {
                    Util.getInstance().cusToast(ResetPasswordActivity.this, "password should be minimum 6 characters");
                } else if (set_confpass.length() == 0) {
                    //et_confpass.setError("please enter confirm password");
                    Toast.makeText(context, "Please enter confirm password", Toast.LENGTH_SHORT).show();

                } else if (!set_newpass.equals(set_confpass)) {
                    // et_newpass.setError("Password not matched!");
                    Toast.makeText(context, "Password not matched!", Toast.LENGTH_SHORT).show();

                } else {
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("token", token);
                    requestBody.put("user_id", user_id);
                    requestBody.put("password", set_newpass);
                    presenter.update_password(ResetPasswordActivity.this, apiResponseCallback, requestBody);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString,
                                  @Nullable Object object) {

        try {

            Log.d("registerResponse", responseString);
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equalsIgnoreCase("5000")) {
                //todo: if network is not availiable then show some dialog box
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false,context);
            } else if (NetworkConstants.RequestCode.UPDATE_PASSWORD == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Util.getInstance().cusToast(context, message);

                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
                    finishAffinity();
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
