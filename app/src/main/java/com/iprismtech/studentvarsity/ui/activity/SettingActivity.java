package com.iprismtech.studentvarsity.ui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iprismtech.studentvarsity.R;
import com.iprismtech.studentvarsity.app.constants.AppConstants;
import com.iprismtech.studentvarsity.app.controller.ApplicationController;
import com.iprismtech.studentvarsity.mvp.contract.activity.SettingActivityContract;
import com.iprismtech.studentvarsity.mvp.presenter.activity.SettingActivitylmpl;
import com.iprismtech.studentvarsity.network.constants.NetworkConstants;
import com.iprismtech.studentvarsity.network.listener.APIResponseCallback;
import com.iprismtech.studentvarsity.sharepref.UserSession;
import com.iprismtech.studentvarsity.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SettingActivity extends BaseAbstractActivity<SettingActivitylmpl> implements SettingActivityContract.IView, View.OnClickListener, APIResponseCallback {
    TextView tv_feedback;
    LinearLayout ll_logout, ll_changesub;
    SwitchCompat switchButton, switchButton2;

    private String st_switchButton = "0", st_switchButton2 = "0", education_id, stream_id, years;

    APIResponseCallback apiResponseCallback;

    private UserSession userSession;
    private String token, user_id;
    private ImageView iv_back;
    private AlertDialog feedback_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.start();

        // setContentView(R.layout.activity_setting);

    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_setting, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new SettingActivitylmpl(this, this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();


        Bundle bundle = getIntent().getExtras();
        String notifi_status = bundle.getString("notification_status");
        String ping_notifi_status = bundle.getString("ping_notification_status");


        userSession = new UserSession(SettingActivity.this);
        token = userSession.getUserDetails().get("token");
        user_id = userSession.getUserDetails().get("id");


        education_id = userSession.getUserDetails().get("education_id");
        stream_id = userSession.getUserDetails().get("stream_id");
        years = userSession.getUserDetails().get("years");


        apiResponseCallback = this;

        switchButton = findViewById(R.id.switchButton);
        switchButton2 = findViewById(R.id.switchButton2);
        iv_back = findViewById(R.id.iv_back);


        if (notifi_status.equalsIgnoreCase("on")) {
            switchButton.setChecked(true);
        } else {
            switchButton.setChecked(false);
        }
        if (ping_notifi_status.equalsIgnoreCase("on")) {
            switchButton2.setChecked(true);
        } else {
            switchButton2.setChecked(false);
        }


//        if (st_switchButton.equals("0")) {
//            switchButton.setChecked(false);
//        } else {
//            switchButton.setChecked(true);
//        }
//
//        if (st_switchButton2.equals("0")) {
//            switchButton2.setChecked(false);
//        } else {
//            switchButton2.setChecked(true);
//        }

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    // The toggle is enabled
                    st_switchButton = "1";

                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("token", token);
                    requestBody.put("user_id", user_id);
                    requestBody.put("type", "normal");
                    requestBody.put("status", "on");
                    presenter.update_notification_status(SettingActivity.this, apiResponseCallback, requestBody);
                } else {
                    // The toggle is disabled
                    st_switchButton = "0";

                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("token", token);
                    requestBody.put("user_id", user_id);
                    requestBody.put("type", "normal");
                    requestBody.put("status", "off");
                    presenter.update_notification_status(SettingActivity.this, apiResponseCallback, requestBody);
                }
            }
        });

        switchButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    // The toggle is enabled
                    st_switchButton2 = "1";

                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("token", token);
                    requestBody.put("user_id", user_id);
                    requestBody.put("type", "ping");
                    requestBody.put("status", "on");
                    presenter.update_notification_status(SettingActivity.this, apiResponseCallback, requestBody);
                } else {
                    // The toggle is disabled
                    st_switchButton2 = "0";

                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("token", token);
                    requestBody.put("user_id", user_id);
                    requestBody.put("type", "ping");
                    requestBody.put("status", "off");
                    presenter.update_notification_status(SettingActivity.this, apiResponseCallback, requestBody);
                }
            }
        });

        tv_feedback = findViewById(R.id.tv_feedback);
        tv_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = LayoutInflater.from(context);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
                View view1 = inflater.inflate(R.layout.item_feedback_dialouge, null);

                feedback_dialog = new AlertDialog.Builder(context).create();

                feedback_dialog = new AlertDialog.Builder(context).create();
                feedback_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                feedback_dialog.setView(view1);
                feedback_dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                WindowManager.LayoutParams wlp = feedback_dialog.getWindow().getAttributes();
                wlp.gravity = Gravity.CENTER;
                feedback_dialog.setCancelable(true);


//                feedback_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                feedback_dialog.setContentView(R.layout.item_feedback_dialouge);
//                Window window = feedback_dialog.getWindow();
//                window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
//                feedback_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//                window.setGravity(Gravity.CENTER_VERTICAL);
                feedback_dialog.show();
                final EditText et_feedback = view1.findViewById(R.id.et_feedback);
                Button btn_feedback = view1.findViewById(R.id.btn_feedback);

                btn_feedback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (et_feedback.getText().toString().length() == 0) {
                            et_feedback.setError("please enter mobile number");
                        } else {
                            //if (token != null && token.length() > 0) {
                            Map<String, String> requestBody = new HashMap<>();
                            requestBody.put("token", token);
                            requestBody.put("user_id", user_id);
                            requestBody.put("message", et_feedback.getText().toString().trim());
                            presenter.submit_feedback(SettingActivity.this, apiResponseCallback, requestBody);

                            feedback_dialog.dismiss();
                            // }
                        }
                    }
                });


            }
        });

        ll_changesub = findViewById(R.id.ll_changesub);
        ll_changesub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id", education_id);
                bundle.putString("stream_id", stream_id);
                bundle.putString("years", years);
                bundle.putString("sending_through", "settings");

                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SUBJECT_SCREEN, bundle);
                finish();

            }
        });
        ll_logout = findViewById(R.id.ll_logout);
        ll_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SettingActivity.this);
                alertDialogBuilder.setMessage("Are you sure, You want to Logout?");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                userSession.logoutUser();
                                //Clear the application data to generate the new device token
                                // MyApplication.getInstance().clearApplicationData();
                                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
                                finishAffinity();

                            }
                        });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString,
                                  @Nullable Object object) {

        try {

            Log.d("registerResponse", responseString);
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equalsIgnoreCase("5000")) {
                //todo: if network is not availiable then show some dialog box
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, context);
            } else if (NetworkConstants.RequestCode.SUBMIT_FEEDBACK == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Util.getInstance().cusToast(context, message);
                } else {
                    Util.getInstance().cusToast(context, message);
                }
            } else if (NetworkConstants.RequestCode.UPDATE_NOTIFICATION_STATUS == requestId) {
                boolean status = jsonObject.getBoolean("status");
                String message = jsonObject.getString("message");

                if (status) {
                    Util.getInstance().cusToast(context, message);
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
        Intent intent = new Intent(SettingActivity.this, ViewProfile_Activity.class);
        startActivity(intent);
        finish();
    }
}
