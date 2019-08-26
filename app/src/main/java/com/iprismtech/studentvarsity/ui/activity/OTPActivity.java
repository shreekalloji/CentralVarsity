package com.iprismtech.studentvarsity.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.customviews.CustomTextViewNormal;
import com.iprismtech.studentvarsity.mvp.contract.activity.OTPActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.OTPActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.utils.EditTextOtp;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OTPActivity extends BaseAbstractActivity<OTPActivitylmpl> implements OTPActivityContract.IView, View.OnClickListener, APIResponseCallback {
    TextView txtVerify;
    APIResponseCallback apiResponseCallback;
    // PinEntryEditText editText;
    String mobile = "", password = "", response = "", otp_confirmed = "";

    EditText merchantPasscode1, merchantPasscode2, merchantPasscode3, merchantPasscode4, merchantPasscode5, merchantPasscode6;
    CustomTextViewNormal resend_otp, tvTimer;
    //Declare timer
    CountDownTimer cTimer = null;
    EditTextOtp editTextOtp;
    TextView tv_otp_temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
        // setContentView(R.layout.activity_otp);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_otp, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new OTPActivitylmpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mobile = bundle.getString("mobile");
            password = bundle.getString("password");
            response = bundle.getString("response");
        }

        apiResponseCallback = this;
        txtVerify = findViewById(R.id.txtVerify);
        tv_otp_temp = findViewById(R.id.tv_otp_temp);

        tvTimer = (CustomTextViewNormal) findViewById(R.id.tvTimer);
        resend_otp = (CustomTextViewNormal) findViewById(R.id.resend_otp);
//        merchantPasscode1 = (EditText) findViewById(R.id.merchantPasscode1);
//        merchantPasscode2 = (EditText) findViewById(R.id.merchantPasscode2);
//        merchantPasscode3 = (EditText) findViewById(R.id.merchantPasscode3);
//        merchantPasscode4 = (EditText) findViewById(R.id.merchantPasscode4);
//        merchantPasscode5 = (EditText) findViewById(R.id.merchantPasscode5);
//        merchantPasscode6 = (EditText) findViewById(R.id.merchantPasscode6);
//
//        this.merchantPasscode1.addTextChangedListener(new OtpTextWatcher(merchantPasscode1));
//        this.merchantPasscode2.addTextChangedListener(new OtpTextWatcher(merchantPasscode2));
//        this.merchantPasscode3.addTextChangedListener(new OtpTextWatcher(merchantPasscode3));
//        this.merchantPasscode4.addTextChangedListener(new OtpTextWatcher(merchantPasscode4));
//        this.merchantPasscode5.addTextChangedListener(new OtpTextWatcher(merchantPasscode5));
//        this.merchantPasscode6.addTextChangedListener(new OtpTextWatcher(merchantPasscode6));
        // editText = findViewById(R.id.otp);

        //editText.setText(response);
//        merchantPasscode1.setText(response.charAt(0) + "");
//        merchantPasscode2.setText(response.charAt(1) + "");
//        merchantPasscode3.setText(response.charAt(2) + "");
//        merchantPasscode4.setText(response.charAt(3) + "");
//        merchantPasscode5.setText(response.charAt(4) + "");
//        merchantPasscode6.setText(response.charAt(5) + "");

        editTextOtp = (EditTextOtp) findViewById(R.id.etOtp);

        cTimer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                tvTimer.setText(" in " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                tvTimer.setText("");
            }

        }.start();

        resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mobile != null) {
                    otp_confirmed = "No";
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("mobile", mobile);
                    requestBody.put("password", password);
                    requestBody.put("otp_confirmed", "No");
                    presenter.userRegister(OTPActivity.this, apiResponseCallback, requestBody);
                } else {
                    Toast.makeText(OTPActivity.this, getString(R.string.Please_enter_otp), Toast.LENGTH_LONG).show();

                }

            }
        });

        txtVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  String checkotp = merchantPasscode1.getText().toString() + merchantPasscode2.getText().toString() + merchantPasscode3.getText().toString() + merchantPasscode4.getText().toString() + merchantPasscode5.getText().toString() + merchantPasscode6.getText().toString();
                String checkotp = editTextOtp.getOtp();
                // startActivity(new Intent(OTPActivity.this, WelcomeActivity.class));
                if (checkotp.equals(response)) {
                    tv_otp_temp.setText("");
                    otp_confirmed = "Yes";
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("mobile", mobile);
                    requestBody.put("password", password);
                    requestBody.put("otp_confirmed", "Yes");
                    presenter.userRegister(OTPActivity.this, apiResponseCallback, requestBody);
                } else {
                    Toast.makeText(OTPActivity.this, getString(R.string.Please_enter_otp), Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    @Override
    public void onClick(View view) {

    }

    //cancel timer
    void cancelTimer() {
        if (cTimer != null)
            cTimer.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }

//    private class OtpTextWatcher implements TextWatcher {
//        private View view;
//
//        OtpTextWatcher(View view) {
//            this.view = view;
//        }
//
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            String text = charSequence.toString();
//            switch (view.getId()) {
////                case R.id.merchantPasscode1:
////                    if (text.length() == 1) {
////                        merchantPasscode2.requestFocus();
////                        merchantPasscode2.setSelection(merchantPasscode2.getText().length());
////                    } else {
////                        merchantPasscode1.requestFocus();
////
////                    }
////                    break;
////                case R.id.merchantPasscode2:
////                    if (text.length() == 0) {
////                        merchantPasscode1.requestFocus();
////                        merchantPasscode1.setSelection(merchantPasscode1.getText().length());
////                    }
////                    break;
////                case R.id.merchantPasscode3:
////                    if (text.length() == 0) {
////                        merchantPasscode2.requestFocus();
////                        merchantPasscode2.setSelection(merchantPasscode2.getText().length());
////                    }
////                    break;
////                case R.id.merchantPasscode4:
////                    if (text.length() == 0) {
////                        merchantPasscode3.requestFocus();
////                        merchantPasscode3.setSelection(merchantPasscode3.getText().length());
////                    }
////                case R.id.merchantPasscode5:
////                    if (text.length() == 0) {
////                        merchantPasscode4.requestFocus();
////                        merchantPasscode4.setSelection(merchantPasscode4.getText().length());
////                    }
////                case R.id.merchantPasscode6:
////                    if (text.length() == 0) {
////                        merchantPasscode5.requestFocus();
////                        merchantPasscode5.setSelection(merchantPasscode5.getText().length());
////                    }
////                    break;
//                default:
//                    break;
//            }
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable) {
//            String text = editable.toString();
//            switch (view.getId()) {
//                case R.id.merchantPasscode1:
//                    if (text.length() == 1)
//                        merchantPasscode2.requestFocus();
//                    else
//                        merchantPasscode1.requestFocus();
//                    merchantPasscode1.setSelection(merchantPasscode1.getText().length());
//                    break;
//                case R.id.merchantPasscode2:
//                    if (text.length() == 1)
//                        merchantPasscode3.requestFocus();
//                    else if (text.length() == 0) {
//                        merchantPasscode1.requestFocus();
//                        merchantPasscode1.setSelection(merchantPasscode1.getText().length());
//                    }
//                    break;
//                case R.id.merchantPasscode3:
//                    if (text.length() == 1)
//                        merchantPasscode4.requestFocus();
//                    else if (text.length() == 0) {
//                        merchantPasscode2.requestFocus();
//                        merchantPasscode2.setSelection(merchantPasscode2.getText().length());
//                    }
//                    break;
//                case R.id.merchantPasscode4:
//                    if (text.length() == 1)
//                        merchantPasscode5.requestFocus();
//                    else if (text.length() == 0) {
//                        merchantPasscode3.requestFocus();
//                        merchantPasscode3.setSelection(merchantPasscode3.getText().length());
//                    }
//                    break;
//                case R.id.merchantPasscode5:
//                    if (text.length() == 1)
//                        merchantPasscode6.requestFocus();
//                    else if (text.length() == 0) {
//                        merchantPasscode4.requestFocus();
//                        merchantPasscode4.setSelection(merchantPasscode4.getText().length());
//                    }
//                    break;
//                case R.id.merchantPasscode6:
//                    if (text.length() == 1)
//                        merchantPasscode6.requestFocus();
//                    else if (text.length() == 0) {
//                        merchantPasscode5.requestFocus();
//                        merchantPasscode5.setSelection(merchantPasscode5.getText().length());
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
//
//    }


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
            } else if (NetworkConstants.RequestCode.USER_REGISTER == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    String responsee = jsonObject.getString("response");
                    //    tv_otp_temp.setText(responsee);
                    Util.getInstance().cusToast(context, message);

                    Toast.makeText(context, "message", Toast.LENGTH_LONG).show();

                    // String id = response.optString("id");
                    //String name=response.optString("name");
                    // String university=response.optString("university");
                    //  String profile_pic=response.optString("profile_pic");
                    // String mobile = response.optString("mobile");

                    // userSession.StoreUserDetails(id, "", mobile, "", "", "", "", "", "","","","","","","","");


                    if (otp_confirmed.equalsIgnoreCase("No")) {
                        response = responsee;

                        editTextOtp.setOtp("");

//                        merchantPasscode1.setText(response.charAt(0) + "");
//                        merchantPasscode2.setText(response.charAt(1) + "");
//                        merchantPasscode3.setText(response.charAt(2) + "");
//                        merchantPasscode4.setText(response.charAt(3) + "");
//                        merchantPasscode5.setText(response.charAt(4) + "");
//                        merchantPasscode6.setText(response.charAt(5) + "");

                    } else {
                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
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
