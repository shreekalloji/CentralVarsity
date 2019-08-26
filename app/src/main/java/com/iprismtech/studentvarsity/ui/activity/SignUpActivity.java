package com.iprismtech.studentvarsity.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.activity.SignUpActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.SignUpActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends BaseAbstractActivity<SignUpActivitylmpl> implements SignUpActivityContract.IView, View.OnClickListener, APIResponseCallback {
    TextView txtSignup, tv_login;
    ImageView im_back;
    EditText edt_loginUser, edt_loginpassword, edt_logincnfpassword;

    APIResponseCallback apiResponseCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        //  setContentView(R.layout.activity_sign_up);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_sign_up, null);
        return view;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void setPresenter() {
        presenter = new SignUpActivitylmpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        txtSignup = findViewById(R.id.txtSignup);
        im_back = findViewById(R.id.im_back);
        edt_loginUser = findViewById(R.id.edt_loginUser);
        edt_loginpassword = findViewById(R.id.edt_loginpassword);
        edt_logincnfpassword = findViewById(R.id.edt_logincnfpassword);

        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(SignUpActivity.this, OTPActivity.class));
                if (isUserCredentialsValid()) {
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("mobile", edt_loginUser.getText().toString().trim());
                    requestBody.put("password", edt_loginpassword.getText().toString().trim());
                    requestBody.put("otp_confirmed", "No");
                    presenter.userRegister(SignUpActivity.this, apiResponseCallback, requestBody);

                }
            }
        });

        tv_login = findViewById(R.id.tv_login);

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
                finish();
            }
        });
    }

    private boolean isUserCredentialsValid() {
        if (edt_loginUser.getText().toString().length() == 0) {
            //edt_loginUser.setError("please enter mobile number");
            Toast.makeText(context, "please enter mobile number", Toast.LENGTH_SHORT).show();
            //todo: Add shake animation
            return false;
        } else if (edt_loginUser.getText().toString().length() < 10) {
            // edt_loginUser.setError("please enter 10 digits");
            Toast.makeText(context, "please enter 10 digits", Toast.LENGTH_SHORT).show();
            edt_loginUser.requestFocus();
            edt_loginUser.setFocusable(true);

            return false;
        } else if (edt_loginpassword.getText().toString().length() == 0) {
            // edt_loginpassword.setError("please enter password");
            Toast.makeText(context, "please enter password", Toast.LENGTH_SHORT).show();
            edt_loginpassword.requestFocus();
            return false;
        } else if (edt_loginpassword.getText().length() < 6) {
            // Util.getInstance().cusToast(context, "password should be minimum 5 characters");
            Toast.makeText(context, "password should be minimum 6 characters", Toast.LENGTH_SHORT).show();
            edt_loginpassword.requestFocus();
            return false;
        } else if (edt_logincnfpassword.getText().toString().length() == 0) {
            // edt_logincnfpassword.setError("please enter password");
            Toast.makeText(context, "please enter password", Toast.LENGTH_SHORT).show();
            edt_logincnfpassword.requestFocus();
            return false;
        } else if (edt_logincnfpassword.getText().length() < 6) {
            // Util.getInstance().cusToast(context, "password should be minimum 5 characters");
            Toast.makeText(context, "password should be minimum 6 characters", Toast.LENGTH_SHORT).show();
            edt_logincnfpassword.requestFocus();
            return false;
        } else if (edt_loginpassword.getText().toString().equalsIgnoreCase(edt_logincnfpassword.getText().toString())) {
            edt_loginpassword.requestFocus();
            return true;

        }
        //todo: Set bg color for gender
        else {

            // edt_loginpassword.setError("password not matched");
            Toast.makeText(context, "password not matched", Toast.LENGTH_SHORT).show();

            return false;
//            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_HOME_SCREEN);
            //  Toast.makeText(getApplicationContext(), "user not registered with us!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

        try {

            Log.d("registerResponse", responseString);
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equalsIgnoreCase("5000")) {
                //todo: if network is not availiable then show some dialog box
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false,context);
            } else if (NetworkConstants.RequestCode.USER_REGISTER == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    String response = jsonObject.getString("response");

                    Util.getInstance().cusToast(context, message);
                    Util.getInstance().cusToast(context, response);

                    //Sending bundle to verification screen
                    Bundle bundle = new Bundle();
                    bundle.putString("mobile", edt_loginUser.getText().toString().trim());
                    bundle.putString("password", edt_loginpassword.getText().toString().trim());
                    bundle.putString("response", response);

                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VERIFICATION_SCREEN, bundle);


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
    public void onClick(View view) {

    }


    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }
}
