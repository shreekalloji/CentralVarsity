package com.iprismtech.studentvarsity.ui.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.customviews.CustomTextViewNormal;
import com.iprismtech.studentvarsity.mvp.contract.activity.ForgotPasswordActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.ForgotPasswordActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgotPasswordActivity extends BaseAbstractActivity<ForgotPasswordActivitylmpl> implements ForgotPasswordActivityContract.IView, View.OnClickListener, APIResponseCallback {
    APIResponseCallback apiResponseCallback;

    private UserSession userSession;
    private String token, user_id;

    CustomTextViewNormal btn_next;
    EditText et_mobile;
    LinearLayout ll_forgot;
    TextView tv_title;
    private ImageView myaccountback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        // setContentView(R.layout.activity_forgot_password);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_forgot_password, null);
        return view;
    }


    @Override
    public void setPresenter() {
        presenter = new ForgotPasswordActivitylmpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();


        userSession = new UserSession(ForgotPasswordActivity.this);
        token = userSession.getUserDetails().get("token");
        // user_id = userSession.getUserDetails().get("id");

        apiResponseCallback = this;

        tv_title = findViewById(R.id.tv_title);
        btn_next = findViewById(R.id.btn_next);
        et_mobile = findViewById(R.id.et_mobile);
        ll_forgot = findViewById(R.id.ll_forgot);
        myaccountback = findViewById(R.id.myaccountback);

        btn_next.setPaintFlags(btn_next.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        myaccountback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile = et_mobile.getText().toString().trim();
                if (mobile.length() == 0) {
                    et_mobile.setError("please enter mobile number");
                } else {
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("token", token);
                    requestBody.put("mobile", mobile);
                    presenter.forgot_password(ForgotPasswordActivity.this, apiResponseCallback, requestBody);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        finish();
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
            } else if (NetworkConstants.RequestCode.FORGOT_PASSWORD == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");
                String id = jsonObject.getJSONObject("response").getString("id");

                if (status) {
                    Util.getInstance().cusToast(context, message);
                    Bundle bundle = new Bundle();
                    bundle.putString("key_id", id);
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_RESETPASSWORD_SCREEN, bundle);
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
